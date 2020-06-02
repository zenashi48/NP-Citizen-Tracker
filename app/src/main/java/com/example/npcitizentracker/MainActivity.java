package com.example.npcitizentracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent screen2;
    View view;
    public void switchActivity(View view){
        Log.i("image clicked","yes clicked");
        screen2=new Intent(this,screen_2.class);
        startActivity(screen2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(3000,1000) {
            public void onTick(long millisecondsUntilFinished){
                Log.i("timer", String.valueOf((int) millisecondsUntilFinished/1000));
            }
            public void onFinish(){
                switchActivity(view);
            }
        }.start();

    }
}
