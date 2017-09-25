package com.example.atos.myapplication.controller;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.atos.myapplication.R;
import android.content.Intent;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* if (this.getWindow().getWindowManager().getDefaultDisplay()
                .getOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_splash_portrait)
        } else {
            setContentView(R.layout.activity_splash_land)
        }*/

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_splash_screen_landscape);
        }
        else {
            setContentView(R.layout.activity_splash_screen_portrait);
        }


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
