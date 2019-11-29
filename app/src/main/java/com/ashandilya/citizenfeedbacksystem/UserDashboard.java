package com.ashandilya.citizenfeedbacksystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class UserDashboard extends AppCompatActivity {

    ViewPager viewPager;
    Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        viewPager = findViewById(R.id.viewPager);

        schemeViewPager schemeViewPager = new schemeViewPager(this);

        viewPager.setAdapter(schemeViewPager);
    }

    public void feedbacklist(View view) {
        Intent intent = new Intent(UserDashboard.this, All_feedback.class);
        startActivity(intent);
    }

    public void selectdept(View view) {
        Intent intent = new Intent(UserDashboard.this, MainActivity.class);
        startActivity(intent);
    }

    public void setting(View view) {
        Intent intent = new Intent(UserDashboard.this, setting.class);
        startActivity(intent);
    }

    public void shareApp(View view) {
    }

    public void explore(View view) {
        Intent intent = new Intent(UserDashboard.this, explore.class);
        startActivity(intent);
    }
}
