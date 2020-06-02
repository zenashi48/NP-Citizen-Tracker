package com.example.npcitizentracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
private String verificationID;
private FirebaseAuth mAuth;
private EditText editText1,editText2,editText3,editText4,editText5,editText6;
private String number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAuth=FirebaseAuth.getInstance();
        editText1=findViewById(R.id.otp1);
        editText2=findViewById(R.id.otp2);
        editText3=findViewById(R.id.otp3);
        editText4=findViewById(R.id.otp4);
        editText5=findViewById(R.id.otp5);
        editText6=findViewById(R.id.otp6);
        Intent intent=getIntent();
        number=intent.getStringExtra(screen_2.code);
        sendVerificationCode(number);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=editText1.getText().toString().trim()+editText2.getText().toString().trim()+editText3.getText().toString().trim()+editText4.getText().toString().trim()+editText5.getText().toString().trim()+editText6.getText().toString().trim();
                if(code.isEmpty()||code.length()<6) {
                    Toast.makeText(Login.this, "Invalid OTP", Toast.LENGTH_SHORT);
                    return;
                }
                verifyCode(code);
            }
        });
    }

    private void verifyCode(String code){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationID,code);
        signInWithCredential(credential);
    }



    private void display(String code){
        String codeTemp=code;
        editText1.setText(codeTemp.charAt(0));
        editText2.setText(codeTemp.charAt(1));
        editText3.setText(codeTemp.charAt(2));
        editText4.setText(codeTemp.charAt(3));
        editText5.setText(codeTemp.charAt(4));
        editText6.setText(codeTemp.charAt(5));


        Log.i("code recieved", code);
        Log.i("editText1", String.valueOf(editText1.getText()));
        Log.i("editText2", String.valueOf(editText2.getText()));
        Log.i("editText3", String.valueOf(editText3.getText()));
        Log.i("editText4", String.valueOf(editText4.getText()));
        Log.i("editText5", String.valueOf(editText5.getText()));
        Log.i("editText6", String.valueOf(editText6.getText()));




    }

    private void signInWithCredential(PhoneAuthCredential credential) {
    mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Intent location_screen=new Intent(Login.this,location.class);
                location_screen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(location_screen);


            }else{
                Toast.makeText(Login.this, (CharSequence) task.getException(),Toast.LENGTH_LONG).show();
            }

        }
    });
    }

    private void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationID = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
//                display(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    public void resendOTP(View view) {
        Toast.makeText(this,"code has been resent",Toast.LENGTH_SHORT);
        sendVerificationCode(number);
    }
}
