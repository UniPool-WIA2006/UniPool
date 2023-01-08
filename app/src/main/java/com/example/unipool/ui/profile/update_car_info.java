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

public class update_car_info extends AppCompatActivity {

    private ImageButton btn_backUpdateCI;
    private AppCompatButton Btn_Update_Car;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car_info);

        dbHandler = new DatabaseHandler(update_car_info.this);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");

        EditText ET_Car_Model = findViewById(R.id.ET_CarModel);
        EditText ET_Vehicle_Color = findViewById(R.id.ET_VehicleColour);
        EditText ET_Plate_No = findViewById(R.id.ET_PlateNumber);
        EditText ET_License_Exp = findViewById(R.id.ET_LicenseExpiry);

        //back to Profile Page 2 (after update)
        ImageButton btn_backUpdateCI = (ImageButton) findViewById(R.id.btn_backUpdateCI);
        btn_backUpdateCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(update_car_info.this, profile_page_2.class);
                finish();
            }
        });

        //Update
        AppCompatButton Btn_Update_Car = (AppCompatButton) findViewById(R.id.Btn_Update_Car);
        Btn_Update_Car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String car_model = ET_Car_Model.getText().toString();
                String vec_color = ET_Vehicle_Color.getText().toString();
                String plate_no = ET_Plate_No.getText().toString();
                String license_exp = ET_License_Exp.getText().toString();
                dbHandler.UpdateCarInfo(username,car_model,vec_color,plate_no,license_exp);
                Toast.makeText(update_car_info.this, "Car info is successfully updated!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(update_car_info.this, profile_page_2.class);
                intent.putExtra("username", username);
                startActivity(intent);
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