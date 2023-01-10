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
import com.example.unipool.databinding.FragmentEditRequestDetailsBinding;
import com.example.unipool.ui.home.HomeViewModel;

public class editRequestDetails extends Fragment {

    private SharedViewModel sharedViewModel;
    private FragmentEditRequestDetailsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentEditRequestDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button saveEditBtn = binding.requestSaveEditBtn;

        saveEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel.setRequestLocation(binding.editLocationSave.getText().toString());
                sharedViewModel.setRequestDestination(binding.editDestinationSave.getText().toString());
                sharedViewModel.setRequestFees(binding.editFeesSave.getText().toString());
                sharedViewModel.setRequestExtraNotes(binding.editExtraNotesSave.getText().toString());
                Navigation.findNavController(view).navigate(R.id.action_editRequestDetails_to_navigation_home);
            }
        });
    }
}