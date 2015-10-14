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

/*
URL��莟�̏��10���ɃA�N�Z�X
 */
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
