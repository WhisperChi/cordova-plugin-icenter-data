package icenterdata;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;


public class DataServer extends NanoHTTPD {
    private static DataServer instance = null;
    private static NanoHTTPD dataServer;
    private static final int PORT = 9001;
    private static String TAG = "whisperchi: ";
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

//    public DataServer() {
//        super(9001);
//    }



    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello server</h1>\n";
        Map<String, String> parms = session.getParms();
        if (parms.get("username") == null) {
            msg += "<form action='?' method='get'>\n";
            msg += "<p>Your name: <input type='text' name='username'></p>\n";
            msg += "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("username") + "!</p>";
        }
        return newFixedLengthResponse(msg + "</body></html>\n");
    }

}


