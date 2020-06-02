package com.example.npcitizentracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class screen_2 extends AppCompatActivity {
    public static final String code="com.we.will send.codeto next intent";
    public void getOTPfunc(View view) {
        TextView gmail=findViewById(R.id.phoneNumberText);
        String gmail_string=gmail.getText().toString();
//        number_string=number_string.replace(" ","");
        Intent screen3=new Intent(this,Login.class);
//        screen3.putExtra(code,number_string);
        startActivity(screen3);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_2);


    }
}
