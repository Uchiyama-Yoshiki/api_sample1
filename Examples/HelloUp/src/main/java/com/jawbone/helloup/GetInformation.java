package com.jawbone.helloup;

import android.util.Log;
import com.google.gson.Gson;
import com.jawbone.upplatformsdk.api.ApiManager;
import com.jawbone.upplatformsdk.utils.UpPlatformSdkConstants;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    private static JSONObject json= new JSONObject();
    public static String AllUrl;

    public static JSONObject jsonConvert(Object o){
        try {
            json = new JSONObject(gson.toJson(o));
         } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    public static ArrayList<String> getMoves(Object o){
        ArrayList<String> a =new ArrayList<String>();
        jsonConvert(o);

        return a;
        }
/*
get a nexturl from URl.
 */
    public static void getNextURL(Object o) {
        String nextUrl = "";
        try {
            nextUrl = jsonConvert(0).getJSONObject("data").getJSONObject("links").getString("next");
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
}
