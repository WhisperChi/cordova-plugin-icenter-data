package icenterdata;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;


public class DataServer extends NanoHTTPD {
    private static DataServer instance = null;
    private static final int PORT = 9001;
    private static String TAG = "whisperchi: ";

    private DataServer() {
        super(PORT);
    }

    public static DataServer getInstance() {
        if (instance == null) {
            instance = new DataServer();
        }

        return instance;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello server</h1>\n";
        Map<String, String> parms = session.getParms();

        // Handle different url
        String arr[] = session.getUri().split("/");

        String path ;
        path = parms.get("path");
        if (path == null ) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "Params error : (path is null)");
        }

        Response resp = null;

        // test part
        boolean test = true;
        if (test) {
            FileInputStream fis = null;
            File file = new File(Environment.getExternalStorageDirectory()
                    + "/data/test/" + "test.png"); //path exists and its correct
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                resp = newFixedLengthResponse(Response.Status.OK,"image/png",fis,fis.available());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // end test

        return resp;
    }

}


