/*
 * *
 *  * Created by harsh on 03/06/22, 8:26 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 03/06/22, 8:26 PM
 *
 */
package com.digitaldealsolution.quizly.Activities;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.window.SplashScreen;

import com.digitaldealsolution.quizly.R;
import com.digitaldealsolution.quizly.databinding.ActivitySplashScreenBinding;


public class SplashScreenActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }

}