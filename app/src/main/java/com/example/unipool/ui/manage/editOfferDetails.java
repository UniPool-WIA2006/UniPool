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
import com.example.unipool.databinding.FragmentEditOfferDetailsBinding;
import com.example.unipool.ui.home.HomeViewModel;


public class editOfferDetails extends Fragment {

    private SharedViewModel sharedViewModel;

    private FragmentEditOfferDetailsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentEditOfferDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button saveEditOfferBtn = binding.saveEditOfferBtn;

        saveEditOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel.setOfferLocation(binding.editPickupLocation.getText().toString());
                sharedViewModel.setOfferDestination(binding.editDestination.getText().toString());
                sharedViewModel.setOfferFees(binding.editFees.getText().toString());
                sharedViewModel.setOfferExtraNotes(binding.editExtraNotes.getText().toString());
                Navigation.findNavController(view).navigate(R.id.action_editOfferDetails_to_navigation_home);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}