package com.jawbone.helloup;

import android.util.Log;
import com.google.gson.Gson;
import com.jawbone.upplatformsdk.api.ApiManager;
import com.jawbone.upplatformsdk.utils.UpPlatformSdkConstants;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
/**
 * Created by yoshiki on 2015/10/06.
 */
public class GetInformation {
    private static final String TAG = UpApiListActivity.class.getSimpleName();

    private static Gson gson = new Gson();
    private static JSONObject json;
    public static String AllUrl = "";

    public static void getNextUrl1(){
        ApiManager.getRestApiInterface().getMoveEventsList(
                UpPlatformSdkConstants.API_VERSION_STRING,
                getMoveEventsListRequestParams(),
                new Callback<Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        getNextURL(o);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    }
                }
        );
    }

    public static void getNextURL(Object o) {
        String nextUrl = "";
        try {
            json = new JSONObject(gson.toJson(o));
            nextUrl = json.getJSONObject("data").getJSONObject("links").getString("next");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(nextUrl != "") {
            ApiManager.getRestApiInterface().getMoveEventsList(
                    UpPlatformSdkConstants.API_VERSION_STRING,
                    getQueryMap(nextUrl),
                    new Callback<Object>() {
                        @Override
                        public void success(Object o, Response response) {
                            getNextURL(o);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                        }
                    }
            );
        }

        AllUrl = AllUrl + nextUrl +"\n";
        Log.d(TAG,nextUrl);
    }

    public static HashMap<String, Integer> getQueryMap(String query)
    {
        String[] params = query.split("&");
        HashMap<String, Integer> map = new HashMap<String,Integer>();

        String[] splitted = params[0].split("=");
        int splitted_token = Integer.parseInt(splitted[1]);
        map.put("page_token", splitted_token);

        return map;
    }
    private static HashMap<String, Integer> getMoveEventsListRequestParams() {
        HashMap<String, Integer> queryHashMap = new HashMap<String, Integer>();

        //uncomment to add as needed parameters
//        queryHashMap.put("date", "<insert-date>");
//        queryHashMap.put("page_token", );
//        queryHashMap.put("start_time", "<insert-time>");
//        queryHashMap.put("end_time", "<insert-time>");
//        queryHashMap.put("updated_after", "<insert-time>");

        return queryHashMap;
    }



}
