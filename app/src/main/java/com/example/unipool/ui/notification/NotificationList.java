package com.example.unipool.ui.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.MainActivity;
import com.example.unipool.R;
import com.example.unipool.databinding.ActivityNotificationListBinding;
import com.example.unipool.databinding.FragmentUserBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class NotificationList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name, type, date;
    DatabaseHandler DB;
    NotificationAdapter adapter;
    private String value; //current user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        DB = new DatabaseHandler(this);
        name = new ArrayList<>();
        type = new ArrayList<>();
        date = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new NotificationAdapter(this, name, type, date);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("user");
            //The key argument here must match that used in the other activity
        }
        System.out.println(value); // for debugging

        displaydata();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void displaydata()
    {
        Cursor cursor = DB.searchCarpoolingByStatus(value, "true");
        if(cursor.getCount()==0)
        {
            Toast.makeText(NotificationList.this, "No Notification Available", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                name.add(cursor.getString(9));
                type.add(cursor.getString(7));
                System.out.println(type.size());
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c); //for debugging

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm", Locale.getDefault());
                String formattedDate = df.format(c);
                date.add(formattedDate);
            }
        }
    }
}

//    ArrayList<String> arr = DB.searchCarpoolingByStatus(username, true);
//        if(arr.size()==0)
//                {
//                Toast.makeText(NotificationList.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//                return;
//                } else {
//                name.add(cursor.getString(9));
//
//                Date c = Calendar.getInstance().getTime();
//                System.out.println("Current time => " + c); //for debugging
//
//                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm", Locale.getDefault());
//                String formattedDate = df.format(c);
//                date.add(formattedDate);
//                }