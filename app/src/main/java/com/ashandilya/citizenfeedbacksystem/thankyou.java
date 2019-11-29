package com.ashandilya.citizenfeedbacksystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class thankyou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
    }

    public void backtoDashboard(View view) {
        Intent intent = new Intent(thankyou.this, UserDashboard.class);
        startActivity(intent);
        finish();
    }
}
