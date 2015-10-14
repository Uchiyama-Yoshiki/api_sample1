package com.jawbone.helloup;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jawbone.upplatformsdk.api.ApiManager;
import com.jawbone.upplatformsdk.utils.UpPlatformSdkConstants;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;


/**
 * Created by yoshiki on 2015/09/28.
 */
public class UserActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

//        Intent intent=getIntent();
//        String input = intent.getStringExtra("INPUT");
        TextView textview =(TextView) findViewById(R.id.textView);
        textview.setText(GetInformation.AllUrl);
     }

}
