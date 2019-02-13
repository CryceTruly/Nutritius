package com.happy.nutritius;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Boolean loggedIn=false;
    private static final String TAG = "MainActivity";
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initApp();

    }

    private void initApp() {
        Log.d(TAG, "initApp: initialting");
        mContext=this;
//        if(!loggedIn)
//            startActivity(new Intent(mContext,WelcomeActivity.class));
//        finish();
    }

}
