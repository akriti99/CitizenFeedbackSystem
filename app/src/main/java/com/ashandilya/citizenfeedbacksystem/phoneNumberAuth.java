package com.ashandilya.citizenfeedbacksystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class phoneNumberAuth extends AppCompatActivity {

    Spinner spinner;
    EditText editTextPhone;
    Button PhoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_auth);

        //DarkMode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        spinner = findViewById(R.id.CountryCode);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryCode.countryNames));

        editTextPhone = findViewById(R.id.phoneNoInput);
        PhoneBtn = findViewById(R.id.phoneBtn);

        PhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = CountryCode.countryCodes[spinner.getSelectedItemPosition()];

                String number = editTextPhone.getText().toString().trim();

                if(number.isEmpty()){
                    editTextPhone.setError("Valid Number is required");
                    editTextPhone.requestFocus();
                    return;
                }

                String phoneNo = "+" + code + number;

                Intent intent = new Intent(phoneNumberAuth.this, OTPVerify.class);
                intent.putExtra("phoneNumber", phoneNo);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(phoneNumberAuth.this, UserDashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(intent);
        }
    }
}
