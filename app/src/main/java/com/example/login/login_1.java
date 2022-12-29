package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;

public class login_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        TextView register_ = (TextView) findViewById(R.id.register_);
        Button login = (Button) findViewById(R.id.login);
        Button fpass = (Button) findViewById(R.id.fpass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_two = new Intent(login_1.this, login_4.class);
                startActivity(intent_two);

            }

        });

        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_three = new Intent(login_1.this, login_2.class);
                startActivity(intent_three);
            }

        });

    }
}