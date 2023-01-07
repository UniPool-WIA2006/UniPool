package com.example.unipool;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unipool.ui.login.LoginActivity;

public class MyApplication extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DatabaseHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_application);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DatabaseHandler(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MyApplication.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(MyApplication.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MyApplication.this,MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MyApplication.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MyApplication.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MyApplication.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}