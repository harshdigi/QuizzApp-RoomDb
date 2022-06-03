/*
 * *
 *  * Created by harsh on 03/06/22, 8:26 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 03/06/22, 8:26 PM
 *
 */
package com.digitaldealsolution.quizly.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.digitaldealsolution.quizly.R;
import com.digitaldealsolution.quizly.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        play = binding.playBtn;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDialog();
            }
        });

    }


    // Dialog For Timer/NoTimer Selection
    private void callDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setDimAmount(0.2f);
        Button timer, noTimer,cancle;
        timer = dialog.findViewById(R.id.timer_btn);
        noTimer = dialog.findViewById(R.id.no_timer_btn);
        cancle = dialog.findViewById(R.id.cancel_btn);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dialog.getContext(), QuizActivity.class);
                intent.putExtra("timer", true);
                startActivity(intent);
            }
        });
        noTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dialog.getContext(), QuizActivity.class);
                intent.putExtra("timer", false);
                startActivity(intent);
            }
        });
        dialog.show();


    }



}