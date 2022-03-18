package icenterdata;


import android.util.Log;

import java.io.IOException;

public class DataServer extends NanoHTTPD {
    private static DataServer instance = null;
    private static NanoHTTPD dataServer;
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

    public void start() {
        try {
            dataServer = NanoHTTPD.class.newInstance();
            dataServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "服务启动失败");
        } catch (InstantiationException e) {
            e.printStackTrace();
            Log.e(TAG, "服务创建失败");
        }
    }

    public void stop() {
        Log.d(TAG, "---finish---");
        if (dataServer != null) {
            dataServer.stop();
            Log.i(TAG, "服务已经关闭");
            dataServer = null;
        }
    }

}


