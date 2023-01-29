package com.example.unipool.ui.manage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unipool.R;

public class myOfferDetailsActivity extends AppCompatActivity {

    TextView positionID;
    int positionIDInt = 69;
    String positionIDString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offer_details);

        Button editOfferBtn = findViewById(R.id.editOfferActivityBtn);
        editOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myOfferDetails_to_editOfferDetails);
            }
        });
        positionID = findViewById(R.id.offerDetailsActivityExtraNotes);
        positionIDInt = getIntent().getIntExtra("POSITION", 69);
        positionIDString = String.valueOf(positionIDInt);
        positionID.setText(positionIDString);
    }


}