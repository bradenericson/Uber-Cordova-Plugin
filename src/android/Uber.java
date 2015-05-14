package com.jabb.plugin.uber;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.pm.PackageManager;
import android.content.Context;
import android.content.Intent;

/**
 * Cordova plugin for the Uber app.
 *
 * Created by Ben Hansen on 5/11/15.
 * Last updated 5/13/15
 */
public class Uber extends CordovaPlugin {
    private static final String UBER_ACTION = "requestWithUber";
    private static final String UBER_PACKAGE = "com.ubercab";

    private Context context = null;
    private PackageManager packageManager = null;

    /**
     *
     * @param action          The action to execute.
     * @param args            The exec() arguments.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return service confirmation
     * @throws JSONException
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.context = this.cordova.getActivity().getApplicationContext();
        this.packageManager = this.context.getPackageManager();

        if (action.equals(UBER_ACTION)) {
            if(!this.isUberInstalled()) {
                callbackContext.error("The Uber app is not installed!");
            } else {
                JSONObject payload = (JSONObject)args.get(0);
                this.launchUber(payload,callbackContext);
            }
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the Uber app is installed on the users device.
     *
     * @return true if installed, else false.
     */
    private boolean isUberInstalled() {
        try {
            this.packageManager.getPackageInfo(UBER_PACKAGE, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Launches the Uber app.
     *
     * @param payload the payload
     * @param callbackContext callback to the javascript layer
     */
    private void launchUber(JSONObject payload, CallbackContext callbackContext) {
        try {
            Intent uberIntent = this.packageManager.getLaunchIntentForPackage(UBER_PACKAGE);
            uberIntent.setData(UberUtils.uriFromPayload(payload));
            this.context.startActivity(uberIntent);
        } catch (JSONException jse) {
            callbackContext.error(jse.getMessage());
        }
    }
}
