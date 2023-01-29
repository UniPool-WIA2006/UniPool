package com.example.unipool.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.R;

import java.util.ArrayList;

public class feedback_passenger extends AppCompatActivity {

    private DatabaseHandler dbHandler;
    private Integer id, current_trust_point;
    private String username = "Ming";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_passenger);
        dbHandler = new DatabaseHandler(feedback_passenger.this);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Button btn_rate = findViewById(R.id.btn_rate);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        ArrayList<String> arr = dbHandler.searchCarpool(id);
        username = arr.get(0);
        current_trust_point = Integer.parseInt(arr.get(6));

        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = getIntent().getExtras();
//                String username = bundle.getString("username");
                float rating = ratingBar.getRating();
                if(rating==5.0){
                    if((current_trust_point+3>100)==false){
                        current_trust_point+=3;
                        dbHandler.UpdateTrustPoint(username, current_trust_point);
                    }
                    else{
                        current_trust_point=100;
                        dbHandler.UpdateTrustPoint(username, current_trust_point);
                    }
                }
                else if(rating==4.0){
                    if((current_trust_point+1>100)==false){
                        current_trust_point+=1;
                        dbHandler.UpdateTrustPoint(username, current_trust_point);
                    }
                    else{
                        current_trust_point=100;
                        dbHandler.UpdateTrustPoint(username, current_trust_point);
                    }
                }
                else if(rating==3.0){
                    current_trust_point=current_trust_point;
                }
                else if(rating==2.0){
                    if((current_trust_point-1<0)==false){
                        current_trust_point-=1;
                        dbHandler.UpdateTrustPoint(username, current_trust_point);
                    }
                    else{
                        current_trust_point=0;
                        dbHandler.UpdateTrustPoint(username, current_trust_point);
                    }
                }
                else{
                    if((current_trust_point-3<0)==false){
                        current_trust_point-=3;
                        dbHandler.UpdateTrustPoint(username, current_trust_point);
                    }
                    else{
                        current_trust_point=0;
                        dbHandler.UpdateTrustPoint(username, current_trust_point);
                    }
                }
                dbHandler.UpdateTrustPoint(username,current_trust_point);
                Toast.makeText(feedback_passenger.this, "review submitted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}