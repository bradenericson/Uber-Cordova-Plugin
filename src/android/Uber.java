package com.jabb.plugin.uber;

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
public class Uber extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("requestWithUber")) {
            JSONObject payload = (JSONObject)args.get(0);
            this.uber(payload,callbackContext);
            return true;
        }
        return false;
    }

    private void uber(JSONObject payload, CallbackContext callbackContext) throws JSONException {
        Context context = this.cordova.getActivity().getApplicationContext();
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
            Intent uberIntent = pm.getLaunchIntentForPackage("com.ubercab");

            uberIntent.setData(Uri.parse("uber://?action=setPickup&pickup[latitude]="+payload.get("fromLatitude").toString()+"&pickup[longitude]="+payload.get("fromLongitude").toString()+"&pickup[nickname]=UberHQ&pickup[formatted_address]=1455%20Market%20St%2C%20San%20Francisco%2C%20CA%2094103&dropoff[latitude]="+payload.get("toLatitude").toString()+"&dropoff[longitude]="+payload.get("toLongitude").toString()+"&dropoff[nickname]=Coit%20Tower&dropoff[formatted_address]=1%20Telegraph%20Hill%20Blvd%2C%20San%20Francisco%2C%20CA%2094133&product_id="+payload.get("productId").toString()));
            context.startActivity(uberIntent);
        } catch (PackageManager.NameNotFoundException e) {
            callbackContext.error("The uber app is NOT installed");
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