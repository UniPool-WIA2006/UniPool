package com.example.unipool.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.example.unipool.R;

public class login_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        TextView email_sent = findViewById(R.id.email_sent);
        Button continue_login = findViewById(R.id.continue_login);

        continue_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_seven = new Intent(login_3.this, login_4.class);
                startActivity(intent_seven);
            }
        });
    }
}