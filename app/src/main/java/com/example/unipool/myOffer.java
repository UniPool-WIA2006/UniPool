package com.example.unipool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unipool.databinding.FragmentHomeBinding;
import com.example.unipool.databinding.FragmentMyOfferBinding;
import com.example.unipool.ui.home.HomeViewModel;


public class myOffer extends Fragment {

    private SharedViewModel sharedViewModel;
    private FragmentMyOfferBinding binding;

    @Override
    public void onResume() {
        super.onResume();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

//      delete purpose
        Observer<String> offerDeleteObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.cardViewOffer1.setVisibility(View.GONE);
            }
        };
        sharedViewModel.getOfferDelete().observe(this, offerDeleteObserver);

//      edit purpose
        Observer<String> offerLocationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerLocation.setText(str);
            }
        };
        sharedViewModel.getOfferLocation().observe(this, offerLocationObserver);

        Observer<String> offerDestinationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerDestination.setText(str);
            }
        };
        sharedViewModel.getOfferDestination().observe(this, offerDestinationObserver);

        Observer<String> offerFeesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerFees.setText(str);
            }
        };
        sharedViewModel.getOfferFees().observe(this, offerFeesObserver);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        //      delete purpose
        Observer<String> offerDeleteObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.cardViewOffer1.setVisibility(View.GONE);
            }
        };
        sharedViewModel.getOfferDelete().observe(this, offerDeleteObserver);


        Observer<String> offerLocationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerLocation.setText(str);
            }
        };
        sharedViewModel.getOfferLocation().observe(this, offerLocationObserver);

        Observer<String> offerDestinationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerDestination.setText(str);
            }
        };
        sharedViewModel.getOfferDestination().observe(this, offerDestinationObserver);

        Observer<String> offerFeesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerFees.setText(str);
            }
        };
        sharedViewModel.getOfferDestination().observe(this, offerFeesObserver);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentMyOfferBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView cardViewOffer1 = binding.cardViewOffer1;
        Button createOfferBtn = binding.createNewOfferBtn;
        Button deleteOfferBtn = binding.deleteOfferBtn;

        cardViewOffer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myOffer_to_myOfferDetails);
            }
        });

        createOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myOffer_to_createOffer);
            }
        });

        deleteOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel.setOfferDelete("delete");
                binding.cardViewOffer1.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}