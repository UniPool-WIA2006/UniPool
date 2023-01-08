package com.example.unipool.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unipool.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class change_password extends AppCompatActivity {

    private ImageButton btn_back_Change_Password;
    private AppCompatButton Btn_Confirm;
    private DatabaseHandler dbHandler;

    private EditText ET_NewPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_change_password);

        final Pattern PASSWORD_PATTERN =
                Pattern.compile(
                        "(?=.*[0-9])" +         //at least 1 digit
                                "(?=.*[a-z])" +         //at least 1 upper case letter
                                "(?=.*[A-Z])" +         //at least 1 upper case letter
                                "(?=.*[\"[^\\w\\s]\"])" +    //at least 1 special character
                                ".{6,}"                 //at least 6 characters
                );
        //Validate Password
        ET_NewPass = findViewById(R.id.ET_NewPass);
        Btn_Confirm = findViewById(R.id.Btn_Confirm);

        //back to Edit Profile Page
        btn_back_Change_Password = findViewById(R.id.btn_back_Change_Password);
        btn_back_Change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(change_password.this, edit_profile.class);
                finish();
            }
        });
        dbHandler = new DatabaseHandler(change_password.this);

        EditText ET_CurrentPass = findViewById(R.id.ET_CurrentPass);
        EditText ET_NewPass = findViewById(R.id.ET_NewPass);
        EditText ET_ReenterPass = findViewById(R.id.ET_ReenterPass);
        ImageView Show_CurrentPass = findViewById(R.id.current_pwd_show);
        ImageView Show_NewPass = findViewById(R.id.new_pwd_show);
        ImageView Show_ReenterPass = findViewById(R.id.reenter_pwd_show);

        Show_CurrentPass.setOnClickListener(new View.OnClickListener(){
            int count = 0;
            @Override
            public void onClick(View view){
                if(count==0) {
                    ET_CurrentPass.setTransformationMethod(null);
                    Show_CurrentPass.setImageResource(R.drawable.show_pwd);
                    count = 1;
                }
                else {
                    ET_CurrentPass.setTransformationMethod(new PasswordTransformationMethod());
                    Show_CurrentPass.setImageResource(R.drawable.hide_pwd);
                    count = 0;
                }
            }
        });

        Show_NewPass.setOnClickListener(new View.OnClickListener(){
            int count = 0;
            @Override
            public void onClick(View view){
                if(count==0) {
                    ET_NewPass.setTransformationMethod(null);
                    Show_NewPass.setImageResource(R.drawable.show_pwd);
                    count = 1;
                }
                else {
                    ET_NewPass.setTransformationMethod(new PasswordTransformationMethod());
                    Show_NewPass.setImageResource(R.drawable.hide_pwd);
                    count = 0;
                }
            }
        });

        Show_ReenterPass.setOnClickListener(new View.OnClickListener(){
            int count = 0;
            @Override
            public void onClick(View view){
                if(count==0) {
                    ET_ReenterPass.setTransformationMethod(null);
                    Show_ReenterPass.setImageResource(R.drawable.show_pwd);
                    count = 1;
                }
                else {
                    ET_ReenterPass.setTransformationMethod(new PasswordTransformationMethod());
                    Show_ReenterPass.setImageResource(R.drawable.hide_pwd);
                    count = 0;
                }
            }
        });

        //Go to Edit Profile Page (after confirm password change)
        Btn_Confirm = findViewById(R.id.Btn_Confirm);
        Btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                String username = bundle.getString("username");
                ArrayList<String> arr = dbHandler.searchUserInfo(username);
                String password = arr.get(7); //get current password stored in database.
                String current_password = ET_CurrentPass.getText().toString();
                String new_password = ET_NewPass.getText().toString();
                String reenter_password = ET_ReenterPass.getText().toString();

                //Detect whether user enter correct password or incorrect password.
                if(!new_password.equals(reenter_password)||!current_password.equals(password)){
                    if(!new_password.equals(reenter_password)) {
                        Toast.makeText(change_password.this, "Password reentered does not matched.", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(change_password.this, change_password.class);
                        //startActivity(intent);
                    }
                    else{
                        Toast.makeText(change_password.this, "Incorrect Current Password.", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(change_password.this, change_password.class);
                        //startActivity(intent);
                    }
                }
                else {
                    if (TextUtils.isEmpty(new_password)) {
                        Toast.makeText(change_password.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                        ET_NewPass.setError("Field cannot be empty");
                        ET_NewPass.requestFocus();
                    }
                    else if (!PASSWORD_PATTERN.matcher(new_password).matches()) {
                        Toast.makeText(change_password.this, "The password should be at least 6 character, including 1 upper case, 1 lower case and 1 special character!", Toast.LENGTH_SHORT).show();
                        ET_NewPass.setError("Valid password is required");
                        ET_NewPass.requestFocus();
                    }
                    else {
                        ET_NewPass.setError(null);
                        dbHandler.UpdatePassword(username,new_password);
                        Toast.makeText(change_password.this, "Password successfully updated.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(change_password.this, edit_profile.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}