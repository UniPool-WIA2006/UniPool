package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class register_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView register = (TextView) findViewById(R.id.register);
        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView contactnumber = (TextView) findViewById(R.id.contactnumber);
        TextView password = (TextView) findViewById(R.id.password);
        TextView enter_credentials = (TextView) findViewById(R.id.enter_credentials);

        EditText fillname = (EditText) findViewById(R.id.fillname);
        EditText fillemail = (EditText) findViewById(R.id.fillemail);
        EditText fillcontact = (EditText) findViewById(R.id.fillcontact);
        EditText fillpassword = (EditText) findViewById(R.id.fillpassword);

        Button b2 = (Button) findViewById(R.id.b2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fillname.getText().length() > 0 && fillpassword.getText().length() > 0 && fillcontact.getText().length() > 0 && fillemail.getText().length() > 0) {
                    String toastMessage = "Username: " + fillname.getText().toString() + ", Password: " + fillpassword.getText().toString();
                    //Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    Toast.makeText(register_profile.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent_one = new Intent(register_profile.this, login_1.class);
                    startActivity(intent_one);
                } else {
                    //Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    Toast.makeText(register_profile.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
            }

        });


    }
}





