package com.jabb.plugin.uber;

import java.io.UnsupportedEncodingException;
import java.lang.StringBuilder;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONException;

import android.net.Uri;

/**
 * Utility class for assisting various JSON/URL related things.
 *
 * Created by ben on 5/13/15.
 */
public class UberUtils {

    // Payload keys
    private static final String PICKUP_LATITUDE = "fromLatitude";
    private static final String PICKUP_LONGITUDE = "fromLongitude";
    private static final String PICKUP_ADDRESS = "fromAddress";
    private static final String PICKUP_NICKNAME = "fromNickname";
    private static final String DROPOFF_LATITUDE = "toLatitude";
    private static final String DROPOFF_LONGITUDE = "toLongitude";
    private static final String DROPOFF_ADDRESS = "toAddress";
    private static final String DROPOFF_NICKNAME = "toNickname";
    private static final String CLIENT_ID = "clientId";
    private static final String PRODUCT_ID = "productId";

    /**
     * Constructs a deep link URI from a JSONObject containing
     * the payload data.
     *
     * @param payload   the payload
     * @return deep link URI
     * @throws JSONException
     */
    public static Uri uriFromPayload(JSONObject payload) throws JSONException {
        StringBuilder sb = new StringBuilder("uber://?action=setPickup");
        HashMap<String,String> qParams = mapQueryParams();

        for(String paramKey : qParams.keySet()) {
            if(hasParam(payload,paramKey)) {
                if(paramKey.equals(PICKUP_ADDRESS) ||
                        paramKey.equals(DROPOFF_ADDRESS) ||
                        paramKey.equals(PICKUP_NICKNAME) ||
                        paramKey.equals(DROPOFF_NICKNAME)) {
                    try {
                        sb.append(qParams.get(paramKey))
                                .append(uberUrlEncode(payload.get(paramKey).toString()));
                    } catch(UnsupportedEncodingException uee) {
                        // Bad encoding, report the exception back to the caller
                        throw new JSONException(payload.get(paramKey).toString());
                    }

                } else {
                    sb.append(qParams.get(paramKey))
                            .append(payload.get(paramKey).toString());
                }
            }
        }

        return Uri.parse(sb.toString());
    }

    /**
     * Helper method to replace all instances of '+' with '%20' for spaces
     * in a url query string to adhere to the guidelines listed at:
     * https://developer.uber.com/v1/deep-linking/
     *
     * @param rawUrl    Raw URL
     * @return  An encoded URL
     * @throws UnsupportedEncodingException
     */
    private static String uberUrlEncode(String rawUrl) throws UnsupportedEncodingException {
        return URLEncoder.encode(rawUrl,"UTF-8").replace("+", "%20");
    }

    /**
     * Helper method for checking if a specific key is
     * contained within a JSON object and that it has a value.
     *
     * @param obj   JSONObject
     * @param key   Key in question
     * @return True if a key and value is found, else false.
     */
    private static boolean hasParam(JSONObject obj, String key) {
        return obj.has(key) && !obj.isNull(key);
    }

    /**
     * Creates a HashMap containing valid query params and
     * their URI components
     *
     * @return the query params HashMap
     */
    private static HashMap<String,String> mapQueryParams() {
        HashMap<String,String> queryParams = new HashMap<String,String>();
        queryParams.put(PICKUP_LATITUDE, "&pickup[latitude]=");
        queryParams.put(PICKUP_LONGITUDE,"&pickup[longitude]=");
        queryParams.put(PICKUP_ADDRESS, "&pickup[formatted_address]=");
        queryParams.put(PICKUP_NICKNAME,"&pickup[nickname]=");
        queryParams.put(DROPOFF_LATITUDE, "&dropoff[latitude]=");
        queryParams.put(DROPOFF_LONGITUDE, "&dropoff[longitude]=");
        queryParams.put(DROPOFF_ADDRESS, "&dropoff[formatted_address]=");
        queryParams.put(DROPOFF_NICKNAME, "&dropoff[nickname]=");
        queryParams.put(CLIENT_ID, "&client_id=");
        queryParams.put(PRODUCT_ID, "&product_id=");
        return queryParams;
    }
}
