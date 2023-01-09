package com.example.unipool;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.unipool.ui.chat.ChatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

import com.example.unipool.databinding.ActivityMainBinding;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.offline.plugin.configuration.Config;
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType;
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String username;

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

}