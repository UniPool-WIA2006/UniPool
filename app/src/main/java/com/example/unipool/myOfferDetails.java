package com.example.unipool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unipool.databinding.FragmentHomeBinding;
import com.example.unipool.databinding.FragmentMyOfferDetailsBinding;
import com.example.unipool.ui.home.HomeViewModel;


public class myOfferDetails extends Fragment {

    private SharedViewModel sharedViewModel;

    private FragmentMyOfferDetailsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Observer<String> locationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerDetailsLocation.setText(str);
            }
        };
        sharedViewModel.getOfferLocation().observe(this, locationObserver);

        Observer<String> destinationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerDetailsDestination.setText(str);
            }
        };
        sharedViewModel.getOfferDestination().observe(this, destinationObserver);

        Observer<String> feesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerDetailsFees.setText(str);
            }
        };
        sharedViewModel.getOfferFees().observe(this, feesObserver);

        Observer<String> extraNotesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerDetailsExtraNotes.setText(str);
            }
        };
        sharedViewModel.getOfferExtraNotes().observe(this, extraNotesObserver);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentMyOfferDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button editOfferBtn = binding.editOfferBtn;

        editOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myOfferDetails_to_editOfferDetails);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}