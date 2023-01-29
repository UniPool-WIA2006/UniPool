package com.example.unipool.ui.manage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.databinding.FragmentManageCarpoolRequestBinding;
import com.example.unipool.databinding.FragmentManageDetailsBinding;

public class manageDetails extends Fragment {

    private FragmentManageDetailsBinding binding;
    private DatabaseHandler DB;

    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentManageDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = binding.manageName;
        TextView number = binding.manageNumber;
        TextView location = binding.manageLocation;
        TextView destination = binding.manageDestination;
        TextView fees = binding.manageFees;
        TextView trustPoint = binding.manageTrustPoint;
        TextView extraNotes = binding.manageExtraNote;

//      set text with data from database
//      here

        Button acceptBtn = binding.acceptBtn;
        Button rateBtn = binding.rateBtn;

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