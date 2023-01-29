package com.example.unipool.ui.manage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unipool.R;

public class manageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_details);

        TextView name = findViewById(R.id.manageNameActivity);
        TextView number = findViewById(R.id.manageNumberActivity);
        TextView location = findViewById(R.id.manageLocationActivity);
        TextView destination = findViewById(R.id.manageDestinationActivity);
        TextView fees = findViewById(R.id.manageFeesPriceActivity);
        TextView trustPoint = findViewById(R.id.manageTrustPointNumberActivity);
        TextView extraNotes = findViewById(R.id.manageExtraNoteActivity);

//      set text with data from database
//      here

        Button acceptBtn = findViewById(R.id.acceptBtnActivity);
        Button rateBtn = findViewById(R.id.rateBtnActivity);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              accept notification - marvin
            }
        });

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              go to rating activity
            }
        });
    }
}