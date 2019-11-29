package com.ashandilya.citizenfeedbacksystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
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

public class OTPVerify extends AppCompatActivity {

    private String verfrificationID;
    private FirebaseAuth mAuth;
    private EditText editTextOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);

        //DarkMode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

       editTextOTP = findViewById(R.id.OTPInput);
        mAuth = FirebaseAuth.getInstance();

        String phonenumber = getIntent().getStringExtra("phoneNumber");
        sendVerificationCode(phonenumber);

        findViewById(R.id.OTPBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = editTextOTP.getText().toString().trim();

                if(code.isEmpty() || code.length() < 6){
                    editTextOTP.setError("Enter Code. . .");
                    editTextOTP.requestFocus();
                    return;
                }
                verifyCode(code);

            }
        });

    }

    private void verifyCode(String code)
    {
        PhoneAuthCredential credential  = PhoneAuthProvider.getCredential(verfrificationID,code);
        signInWithCredential(credential);

    }

    private void signInWithCredential(PhoneAuthCredential credential) {
            mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(OTPVerify.this, UserDashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity(intent);

                    }else{
                        Toast.makeText(OTPVerify.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
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
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verfrificationID = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                editTextOTP.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OTPVerify.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };
}
