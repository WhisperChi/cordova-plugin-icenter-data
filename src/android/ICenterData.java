package icenterdata;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class echoes a string called from JavaScript.
 */
public class ICenterData extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            Method func = ICenterData.class.getDeclaredMethod(action, JSONArray.class, CallbackContext.class);
            func.invoke(this, args, callbackContext);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void info(
            JSONArray args,
            CallbackContext callbackContext) {
        callbackContext.success("Hello from whisperchi!");
    }

    private void startServer(
            JSONArray args,
            CallbackContext callbackContext) {
        Context ct = this.cordova.getActivity().getApplicationContext();
        cordova.getThreadPool().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        DataServer dServer = DataServer.getInstance();
                        try {
                            dServer.setContext(ct);
                            dServer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("whisperchi", "Server start failed.");
                        }
                    }
                }
        );
    }

    private void stopServer(
            JSONArray args,
            CallbackContext callbackContext) {
        cordova.getThreadPool().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        DataServer.getInstance().stop();
                    }
                }
        );
    }

    private void setScale(
            JSONArray args,
            CallbackContext callbackContext) {
        try {
            int scale = args.getInt(0);
            DataServer.getInstance().setScale(scale);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setDataDir(
            JSONArray args,
            CallbackContext callbackContext) {
        String dir = null;
        try {
            dir = args.getString(0);
            DataServer.getInstance().setDataDir(dir);
            callbackContext.success("setDataDir succeed");
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("setDataDir failed ");
        }
    }
}
