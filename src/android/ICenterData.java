package icenterdata;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

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

        cordova.getThreadPool().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        callbackContext.success("Hello from whisperchi!");
                    }
                }
        );
    }
}
