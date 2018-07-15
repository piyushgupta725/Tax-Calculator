package com.example.piyush.tax_gst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        int time_out =4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, time_out);
        Log.v("hello", "mc");
    }
}