package com.example.unipool.ui.profile;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.R;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class edit_profile extends AppCompatActivity {

    private ImageButton btn_backEditProfile;
    private AppCompatButton Btn_Save;
    private ImageButton btn_proceed_to_change_password;
    private DatabaseHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Validate Email
        Btn_Save = findViewById(R.id.Btn_Save);
        dbHandler = new DatabaseHandler(edit_profile.this);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");

        EditText ET_user_bio = findViewById(R.id.ET_Bio);
        EditText ET_user_contact = findViewById(R.id.ET_ContactNumber);
        EditText ET_user_email = findViewById(R.id.ET_Email);
        EditText ET_user_emer_cont_name = findViewById(R.id.ET_EmerCont_Name);
        EditText ET_user_emer_cont_num = findViewById(R.id.ET_EmerCont_Num);

        //back to Profile Page 1
        ImageButton btn_backEditProfile = (ImageButton) findViewById(R.id.btn_backEditProfile);
        btn_backEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edit_profile.this, profile_page_1.class);
                finish();
            }
        });

        ArrayList<String> arr = dbHandler.searchUserInfo(username);

        ET_user_bio.setText(arr.get(1));
        ET_user_contact.setText(arr.get(3));
        ET_user_email.setText(arr.get(4));
        ET_user_emer_cont_name.setText(arr.get(5));
        ET_user_emer_cont_num.setText(arr.get(6));
        RadioGroup radioGroup = findViewById(R.id.radioGrp);
        RadioButton RadioM = findViewById(R.id.radioM);
        RadioButton RadioF = findViewById(R.id.radioF);

        //radiobutton for gender, and this code will remember the user's last choice.
        String current_gender = arr.get(2);
        if (current_gender.equals("Male") || current_gender.equals("")) {
            RadioM.setChecked(true);
        } else if (current_gender.equals("Female")) {
            RadioF.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                String gender = radioButton.getText().toString();
                dbHandler.UpdateGender(username,gender);
            }
        });

        //Save Button
        AppCompatButton Btn_Save = (AppCompatButton) findViewById(R.id.Btn_Save);

        Btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_bio = ET_user_bio.getText().toString();
                ArrayList<String> arr = dbHandler.searchUserInfo(username);
                String gender = arr.get(2);
                String user_contact = ET_user_contact.getText().toString();
                String email = ET_user_email.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(edit_profile.this, "Please enter your registered email", Toast.LENGTH_SHORT).show();
                    ET_user_email.setError("Email is required");
                    ET_user_email.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(edit_profile.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    ET_user_email.setError("Valid email is required");
                    ET_user_email.requestFocus();
                } else {
                    ET_user_email.setError(null);
                }
                String user_emer_cont_name = ET_user_emer_cont_name.getText().toString();
                String user_emer_cont_num = ET_user_emer_cont_num.getText().toString();

                //save the updated information into the database created.
                dbHandler.UpdateUserProfile(username, user_bio, gender, user_contact, email, user_emer_cont_name, user_emer_cont_num);
                Toast.makeText(edit_profile.this, "Profile has been updated.", Toast.LENGTH_SHORT).show();
                ET_user_bio.setText("");
                ET_user_contact.setText("");
                ET_user_email.setText("");
                ET_user_emer_cont_name.setText("");
                ET_user_emer_cont_num.setText("");
                Intent intent = new Intent(edit_profile.this, profile_page_1.class);
                finish();
            }
        });

        //Change Password >
        ImageButton btn_proceed_to_change_password = (ImageButton) findViewById(R.id.btn_proceed_to_change_password);
        btn_proceed_to_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edit_profile.this, change_password.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}