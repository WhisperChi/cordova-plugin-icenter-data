package icenterdata;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;


public class DataServer extends NanoHTTPD {
    private static DataServer instance = null;
    private static final int PORT = 9001;
    private static final String TAG = "whisperchi: ";

    private int scale = 250; // 对应iCenter切片导出的数据库的粒度
    private String dataDir = "/data/test";
    private Context context;

    private DataServer() {
        super(PORT);
    }

    public static DataServer getInstance() {
        if (instance == null) {
            instance = new DataServer();
        }

        return instance;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    public void setContext(Context ct) {
        this.context = ct;
    }

    @Override
    public Response serve(IHTTPSession session) {
        Map<String, String> parms = session.getParms();

        String path;
        path = parms.get("path");
        if (path == null) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "Params error : (path is null)");
        }

        int z, x, y;

        z = Integer.parseInt(parms.get("z"));
        x = Integer.parseInt(parms.get("x"));
        y = Integer.parseInt(parms.get("y"));

        String format = parms.get("accept");

        return handleZXYData(path, z, x, y, format);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Response handleZXYData(String path, int z, int x, int y, String format) {
        // test path
        String file = Environment.getExternalStorageDirectory() + dataDir + "/" + path;
        file = file + "/" + z + "/";

        // tiles_0_0.sqlite
        String sqliteName = "tiles_" + x / scale + "_" + y / scale + ".sqlite";
        file += sqliteName;

        DataReader dReader = new DataReader(context, file);
        byte[] data = dReader.getTileData(x, y);

        String mimeType = format.split(",")[0];
        Response resp = null;
        resp = newFixedLengthResponse(Response.Status.OK, mimeType, new ByteArrayInputStream(data), data.length);

        return resp;
    }

    // test part
    public Response sendTestPngFile(IHTTPSession seesion) {
        Response resp = null;
        boolean test = true;
        if (test) {
            FileInputStream fis = null;
            File file = new File(Environment.getExternalStorageDirectory()
                    + "/data/test/" + "test.png");
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                resp = newFixedLengthResponse(Response.Status.OK, "image/png", fis, fis.available());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resp;
    }

}


