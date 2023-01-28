package com.example.unipool.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.MainActivity;
import com.example.unipool.R;
import com.example.unipool.ui.profile.change_password;
import com.example.unipool.ui.profile.edit_profile;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email, contact_number;
    Button register;
    DatabaseHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toast.makeText(this, "Username cannot be changed once registered", Toast.LENGTH_LONG).show();
        final Pattern PASSWORD_PATTERN =
                Pattern.compile(
                        "(?=.*[0-9])" +         //at least 1 digit
                                "(?=.*[a-z])" +         //at least 1 upper case letter
                                "(?=.*[A-Z])" +         //at least 1 upper case letter
                                "(?=.*[\"[^\\w\\s]\"])" +    //at least 1 special character
                                ".{6,}"                 //at least 6 characters
                );

        username = (EditText) findViewById(R.id.fillname);
        password = (EditText) findViewById(R.id.fillpassword);
        email = (EditText) findViewById(R.id.fillemail);
        contact_number = (EditText) findViewById(R.id.fillcontact);
        register = (Button) findViewById(R.id.btnregister);
        DB = new DatabaseHandler(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String mail = email.getText().toString();
                String contact = contact_number.getText().toString();

                if(user.equals("")||pass.equals("")||mail.equals("")||contact.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = DB.checkusername(user);
                    if (TextUtils.isEmpty(pass)) {
                        Toast.makeText(RegisterActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                    else if (!PASSWORD_PATTERN.matcher(pass).matches()) {
                        Toast.makeText(RegisterActivity.this, "The password should be at least 6 character, including 1 upper case, 1 lower case and 1 special character!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                    if(checkuser==false){
                        Boolean insert = DB.insertData(user, pass, mail, contact);
                        if(insert==true){
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                    }
                }
            }
        });
    }
}

