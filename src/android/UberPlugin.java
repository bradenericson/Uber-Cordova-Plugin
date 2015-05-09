package com.plugin.echo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.pm.PackageManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
/**
 * This class echoes a string called from JavaScript.
 */
public class UberPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("uber")) {
            String message = args.getString(0);
            this.uberTest(callbackContext);
            return true;
        }
        return false;
    }

    private void uberTest(CallbackContext callbackContext) {
        Context context = this.cordova.getActivity().getApplicationContext();
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
            Intent uberIntent = pm.getLaunchIntentForPackage("com.ubercab");
            uberIntent.setData(Uri.parse("uber://?client_id=JdT8QZEfpbHEIneif1lMST1Gpb9Lb4dF&action=setPickup&pickup[latitude]=37.775818&pickup[longitude]=-122.418028&pickup[nickname]=UberHQ&pickup[formatted_address]=1455%20Market%20St%2C%20San%20Francisco%2C%20CA%2094103&dropoff[latitude]=37.802374&dropoff[longitude]=-122.405818&dropoff[nickname]=Coit%20Tower&dropoff[formatted_address]=1%20Telegraph%20Hill%20Blvd%2C%20San%20Francisco%2C%20CA%2094133&product_id=a1111c8c-c720-46c3-8534-2fcdd730040d"));
            context.startActivity(uberIntent);
        } catch (PackageManager.NameNotFoundException e) {
            callbackContext.error("The uber app is NOT installed");
            //
        }
    }

    private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}