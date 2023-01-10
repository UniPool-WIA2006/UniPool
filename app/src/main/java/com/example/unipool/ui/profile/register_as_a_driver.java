package com.example.unipool.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.R;

public class register_as_a_driver extends AppCompatActivity {

    private ImageButton btn_backRegAsDriver;
    private AppCompatButton Btn_Register_Driver;
    private DatabaseHandler dbHandler;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as_adriver);
        dbHandler = new DatabaseHandler(register_as_a_driver.this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

//        Toast.makeText(register_as_a_driver.this, "Register Driver " + username, Toast.LENGTH_SHORT).show();

//        MainActivity activity = new MainActivity();
//        username = activity.getUsername();

        EditText ET_Car_Model = findViewById(R.id.ET_CarModel);
        EditText ET_Vehicle_Color = findViewById(R.id.ET_VehicleColour);
        EditText ET_Plate_No = findViewById(R.id.ET_PlateNumber);
        EditText ET_License_Exp = findViewById(R.id.ET_LicenseExpiry);

        //back to Profile Page 1
        ImageButton btn_backRegAsDriver = (ImageButton) findViewById(R.id.btn_backRegAsDriver);
        btn_backRegAsDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register_as_a_driver.this, profile_page_1.class);
                finish();
            }
        });

        //Go to Profile Page 2 (after register)
        AppCompatButton Btn_Register_Driver = (AppCompatButton) findViewById(R.id.Btn_Update_Car);
        Btn_Register_Driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String car_model = ET_Car_Model.getText().toString();
                String vec_color = ET_Vehicle_Color.getText().toString();
                String plate_no = ET_Plate_No.getText().toString();
                String license_exp = ET_License_Exp.getText().toString();
                dbHandler.UpdateStatus(username, "Driver");
                dbHandler.UpdateCarInfo(username,car_model,vec_color,plate_no,license_exp);
                Toast.makeText(register_as_a_driver.this, "Successfully registered as a driver!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(register_as_a_driver.this, profilePage2.class);
//                intent.putExtra("username", username);
//                startActivity(intent);
                finish();
            }
        });

    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(true);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}