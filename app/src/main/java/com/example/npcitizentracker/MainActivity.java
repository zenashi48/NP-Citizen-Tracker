package com.example.npcitizentracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void switchActivity(View view){
        Log.i("image clicked","yes clicked");
        Intent screen2=new Intent(this,screen_2.class);
        startActivity(screen2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
