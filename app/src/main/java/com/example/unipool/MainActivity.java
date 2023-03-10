package com.example.unipool;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unipool.databinding.ActivityMainBinding;
import com.example.unipool.ui.notification.NotificationList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.offline.plugin.configuration.Config;
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType;
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String username;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Action Bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        // Display application icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Bottom Navigation
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_manage, R.id.navigation_chat, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Setting up Chat with offline function
        Config config = new Config(true, true, true, UploadAttachmentsNetworkType.NOT_ROAMING);
        StreamOfflinePluginFactory offlinePluginFactory = new StreamOfflinePluginFactory(config, this);
        ChatClient client = new ChatClient.Builder(getString(R.string.api_key), this).logLevel(ChatLogLevel.ALL).withPlugin(offlinePluginFactory).build();

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        Toast.makeText(MainActivity.this, "Welcome " + username, Toast.LENGTH_SHORT).show();

        dbHandler = new DatabaseHandler(this);
        ArrayList<String> arr = dbHandler.searchUserInfo(username);

        ImageView imageView = binding.imageView2;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = username;
                Intent intent = new Intent(MainActivity.this, NotificationList.class);
                intent.putExtra("user", value);
                startActivity(intent);
            }
        });

        FloatingActionButton emergency = binding.emergency;
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = arr.get(6);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                if(phone.equals(null) || phone.equals("") || phone == null)
                    intent.setData(Uri.parse("tel:999"));
                else
                    intent.setData(Uri.parse("tel:"+phone));

                startActivity(intent);
            }
        });

        // Authenticate and connect the user
//        User user = new User();
//        user.setId(username);
//        user.setName(username);
//        user.setImage("https://bit.ly/2TIt8NR");
//        String token = client.devToken(user.getId());
//
//        String token = client.devToken(user.getId());
//
//        client.connectUser(user, token).enqueue(result -> {
//            if(result.isSuccess()) {
//                Log.d( "ChatFragment", "Success Connecting");
//            } else {
//                Log.d( "ChatFragment", result.error().getMessage().toString());
//            }
//        });
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}