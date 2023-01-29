package com.example.unipool.ui.manage;
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

import com.example.unipool.R;
import com.example.unipool.databinding.FragmentCreateRequestBinding;
import com.example.unipool.databinding.FragmentHomeBinding;
import com.example.unipool.ui.home.HomeViewModel;


public class createRequest extends Fragment {
    private FragmentCreateRequestBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateRequestBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button submitRequestBtn = binding.submitRequestBtn;
        submitRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              save new request location, destination, fees, and extra notes to database
//              database will use the data to create new items for recyclerview

                Navigation.findNavController(view).navigate(R.id.action_createRequest_to_navigation_home);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}