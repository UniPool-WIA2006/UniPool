package com.example.unipool.ui.manage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.MainActivity;
import com.example.unipool.R;
import com.example.unipool.databinding.FragmentCreateOfferBinding;
import com.example.unipool.ui.home.HomeViewModel;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unipool.databinding.FragmentCreateOfferBinding;
import com.example.unipool.databinding.FragmentHomeBinding;
import com.example.unipool.ui.home.HomeViewModel;

import java.util.ArrayList;


public class createOffer extends Fragment {
    private FragmentCreateOfferBinding binding;
    private DatabaseHandler DB;
    private EditText createOfferLocationSave, createOfferDestinationSave, createOfferFeesSave, createOfferExtraNotesSave;
    private String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DatabaseHandler(getActivity());

        MainActivity activity = (MainActivity) getActivity();
        username = activity.getUsername();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateOfferBinding.inflate(getLayoutInflater());

        createOfferLocationSave = binding.createOfferLocationSave;
        createOfferDestinationSave = binding.createOfferDestinationSave;
        createOfferFeesSave = binding.createOfferFeesSave;
        createOfferExtraNotesSave = binding.createOfferExtraNotesSave;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> arr = DB.searchUserInfo(username);

        Button submitOfferBtn = binding.submitOfferBtn;
        submitOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              save new offer location, destination, fees, and extra notes to database
//              database will use the data to create new items for recyclerview
                String location = createOfferLocationSave.getText().toString();
                String destination = createOfferDestinationSave.getText().toString();
                String fee = createOfferFeesSave.getText().toString();
                String Notes = createOfferExtraNotesSave.getText().toString();
                String contact = arr.get(3);
                Integer point = Integer.parseInt(arr.get(13));

                DB.AddCarpool(username, contact,location, destination, fee, Notes, point,"offer");
                Toast.makeText(getActivity(), "Offer successfully created", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(view).navigate(R.id.action_createOffer_to_navigation_home);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}