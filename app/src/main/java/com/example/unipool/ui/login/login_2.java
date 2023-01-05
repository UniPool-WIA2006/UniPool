package com.example.unipool.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.unipool.R;

public class login_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        TextView recovery_email = findViewById(R.id.recovery_email);
        EditText fillemail1 = findViewById(R.id.fillemail1);
        Button continue_ = findViewById(R.id.continueBtn);
        Button login_back = findViewById(R.id.login_back);
        Button register_back = findViewById(R.id.register_back);

        continue_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fillemail1.getText().length() > 0) {
                    String toastMessage = "Email: " + fillemail1.getText().toString();
                    //Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    Toast.makeText(login_2.this, "Email Sent Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent_four = new Intent(login_2.this, login_3.class);
                    startActivity(intent_four);
                } else {
                    //Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    Toast.makeText(login_2.this, "Email Failed", Toast.LENGTH_SHORT).show();
                }

                login_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_five = new Intent(login_2.this, login_4.class);
                        startActivity(intent_five);
                    }

                });

                register_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_six = new Intent(login_2.this, register_profile.class);
                        startActivity(intent_six);
                    }

                });


            }
        });
    }
}