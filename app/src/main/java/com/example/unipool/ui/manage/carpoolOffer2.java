package com.example.unipool.ui.manage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unipool.R;
import com.example.unipool.databinding.FragmentCarpoolOffer2Binding;
import com.example.unipool.ui.profile.feedback_driver;


public class carpoolOffer2 extends Fragment {

    private FragmentCarpoolOffer2Binding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCarpoolOffer2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button rate = binding.btnRate;
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), feedback_driver.class);
                startActivity(intent);
            }
        });

        return root;
    }
}