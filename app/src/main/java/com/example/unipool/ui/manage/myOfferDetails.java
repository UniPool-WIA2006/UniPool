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
import android.widget.TextView;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.MainActivity;
import com.example.unipool.R;
import com.example.unipool.databinding.FragmentHomeBinding;
import com.example.unipool.databinding.FragmentMyOfferDetailsBinding;
import com.example.unipool.ui.home.HomeViewModel;

import java.util.ArrayList;


public class myOfferDetails extends Fragment {
    private FragmentMyOfferDetailsBinding binding;
    private TextView offerDetailsName, offerDetailsNumber, offerDetailsLocation, offerDetailsDestination, offerDetailsFees
            , offerDetailsTrustPoint, offerDetailsExtraNotes;
    private Integer id;
    DatabaseHandler DB;
    SharedViewModel viewModel;

    public myOfferDetails() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DatabaseHandler(getActivity());

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel.getData().observe(this, data -> {
            id = data;
        });

        System.out.println(id);

        MainActivity activity = (MainActivity) getActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyOfferDetailsBinding.inflate(getLayoutInflater());

        viewModel.getData().observe(getViewLifecycleOwner(), data -> {
            id = data;
        });

        System.out.println(id);

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