package com.example.unipool.ui.manage;

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

import com.example.unipool.R;
import com.example.unipool.databinding.FragmentMyOfferBinding;
import com.example.unipool.ui.home.HomeViewModel;


public class myOffer extends Fragment {

    private SharedViewModel sharedViewModel;
    private FragmentMyOfferBinding binding;

    @Override
    public void onResume() {
        super.onResume();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

///      delete purpose
        Observer<String> offerDeleteObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.cardViewOffer1.setVisibility(View.GONE);
            }
        };
        sharedViewModel.getOfferDelete().observe(this, offerDeleteObserver);

        Observer<String> offerDeleteObserver1 = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.cardViewOffer2.setVisibility(View.GONE);
            }
        };
        sharedViewModel.getOfferDelete1().observe(this, offerDeleteObserver1);

//      create purpose
        Observer<String> offerCreateObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.cardViewOffer2.setVisibility(View.VISIBLE);
            }
        };
        sharedViewModel.getOfferCreate().observe(this, offerCreateObserver);
//disini
        Observer<String> offerLocationObserver1 = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerLocation2.setText(str);
            }
        };
        sharedViewModel.getOfferLocation1().observe(this, offerLocationObserver1);

        Observer<String> offerDestinationObserver1 = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerDestination2.setText(str);
            }
        };
        sharedViewModel.getOfferDestination1().observe(this, offerDestinationObserver1);

        Observer<String> offerFeesObserver1 = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerFees2.setText(str);
            }
        };
        sharedViewModel.getOfferFees1().observe(this, offerFeesObserver1);


//      edit purpose
        Observer<String> offerLocationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerLocation1.setText(str);
            }
        };
        sharedViewModel.getOfferLocation().observe(this, offerLocationObserver);

        Observer<String> offerDestinationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerDestination1.setText(str);
            }
        };
        sharedViewModel.getOfferDestination().observe(this, offerDestinationObserver);

        Observer<String> offerFeesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerFees1.setText(str);
            }
        };
        sharedViewModel.getOfferFees().observe(this, offerFeesObserver);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

///      delete purpose
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
                binding.offerLocation1.setText(str);
            }
        };
        sharedViewModel.getOfferLocation().observe(this, offerLocationObserver);

        Observer<String> offerDestinationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerDestination1.setText(str);
            }
        };
        sharedViewModel.getOfferDestination().observe(this, offerDestinationObserver);

        Observer<String> offerFeesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.offerFees1.setText(str);
            }
        };
        sharedViewModel.getOfferFees().observe(this, offerFeesObserver);
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
        Button deleteOfferBtn1 = binding.deleteOfferBtn1;

        cardViewOffer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myOffer_to_myOfferDetails);
            }
        });

        deleteOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel.setOfferDelete("delete");
                binding.cardViewOffer1.setVisibility(View.GONE);
            }
        });

        deleteOfferBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel.setOfferDelete("delete");
                binding.cardViewOffer2.setVisibility(View.GONE);
            }
        });

        createOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myOffer_to_createOffer);
                binding.cardViewOffer2.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}