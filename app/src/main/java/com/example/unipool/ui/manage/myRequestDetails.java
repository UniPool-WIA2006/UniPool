package com.example.unipool.ui.manage;

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

import com.example.unipool.R;
import com.example.unipool.databinding.FragmentMyRequestDetailsBinding;
import com.example.unipool.ui.home.HomeViewModel;


public class myRequestDetails extends Fragment {

    private SharedViewModel sharedViewModel;
    private FragmentMyRequestDetailsBinding binding;
    myRequest myRequestObj = new myRequest();

    @Override
    public void onResume() {
        super.onResume();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Observer<String> locationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDetailsLocation.setText(str);
            }
        };
        sharedViewModel.getRequestLocation().observe(this, locationObserver);

        Observer<String> destinationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDetailsDestination.setText(str);
            }
        };
        sharedViewModel.getRequestDestination().observe(this, destinationObserver);

        Observer<String> feesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDetailsFees.setText(str);
            }
        };
        sharedViewModel.getRequestFees().observe(this, feesObserver);

        Observer<String> extraNotesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDetailsExtraNotes.setText(str);
            }
        };
        sharedViewModel.getRequestExtraNotes().observe(this, extraNotesObserver);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Observer<String> locationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDetailsLocation.setText(str);
            }
        };
        sharedViewModel.getRequestLocation().observe(this, locationObserver);

        Observer<String> destinationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDetailsDestination.setText(str);
            }
        };
        sharedViewModel.getRequestDestination().observe(this, destinationObserver);

        Observer<String> feesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDetailsFees.setText(str);
            }
        };
        sharedViewModel.getRequestFees().observe(this, feesObserver);

        Observer<String> extraNotesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDetailsExtraNotes.setText(str);
            }
        };
        sharedViewModel.getRequestExtraNotes().observe(this, extraNotesObserver);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentMyRequestDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button editDetailsBtn = binding.editRequestBtn;

        editDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myRequestDetails_to_editRequestDetails);
            }
        });



    }
}