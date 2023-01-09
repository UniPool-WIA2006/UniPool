package com.example.unipool;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.unipool.databinding.FragmentMyRequestBinding;
import com.example.unipool.databinding.FragmentMyRequestDetailsBinding;
import com.example.unipool.ui.home.HomeViewModel;


public class myRequest extends Fragment {

    private SharedViewModel sharedViewModel;
    private FragmentMyRequestBinding binding;

    @Override
    public void onResume() {
        super.onResume();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

//      delete purpose
        Observer<String> requestDeleteObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.cardViewRequest1.setVisibility(View.GONE);
            }
        };
        sharedViewModel.getRequestDelete().observe(this, requestDeleteObserver);

//      create purpose
//        Observer<String> requestCreateObserver = new Observer<String>() {
//            @Override
//            public void onChanged(String str) {
//                binding.cardViewRequest2.setVisibility(View.VISIBLE);
//            }
//        };
//        sharedViewModel.getRequestCreate().observe(this, requestCreateObserver);

//        Observer<String> requestLocationObserver1 = new Observer<String>() {
//            @Override
//            public void onChanged(String str) {
//                binding.requestLocation2.setText(str);
//            }
//        };
//        sharedViewModel.getRequestLocation1().observe(this, requestLocationObserver1);
//
//        Observer<String> requestDestinationObserver1 = new Observer<String>() {
//            @Override
//            public void onChanged(String str) {
//                binding.requestDestination2.setText(str);
//            }
//        };
//        sharedViewModel.getRequestDestination1().observe(this, requestDestinationObserver1);
//
//        Observer<String> requestFeesObserver1 = new Observer<String>() {
//            @Override
//            public void onChanged(String str) {
//                binding.requestFees2.setText(str);
//            }
//        };
//        sharedViewModel.getRequestFees1().observe(this, requestFeesObserver1);

//      edit purpose
        Observer<String> requestLocationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestLocation.setText(str);
            }
        };
        sharedViewModel.getRequestLocation().observe(this, requestLocationObserver);

        Observer<String> requestDestinationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDestination.setText(str);
            }
        };
        sharedViewModel.getRequestDestination().observe(this, requestDestinationObserver);

        Observer<String> requestFeesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestFees.setText(str);
            }
        };
        sharedViewModel.getRequestFees().observe(this, requestFeesObserver);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

//      delete purpose
        Observer<String> requestDeleteObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.cardViewRequest1.setVisibility(View.GONE);
            }
        };
        sharedViewModel.getRequestDelete().observe(this, requestDeleteObserver);

//      create purpose
//        Observer<String> requestCreateObserver = new Observer<String>() {
//            @Override
//            public void onChanged(String str) {
//                binding.cardViewRequest2.setVisibility(View.VISIBLE);
//            }
//        };
//        sharedViewModel.getRequestCreate().observe(this, requestCreateObserver);

//        Observer<String> requestLocationObserver1 = new Observer<String>() {
//            @Override
//            public void onChanged(String str) {
//                binding.requestLocation2.setText(str);
//            }
//        };
//        sharedViewModel.getRequestLocation1().observe(this, requestLocationObserver1);
//
//        Observer<String> requestDestinationObserver1 = new Observer<String>() {
//            @Override
//            public void onChanged(String str) {
//                binding.requestDestination2.setText(str);
//            }
//        };
//        sharedViewModel.getRequestDestination1().observe(this, requestDestinationObserver1);
//
//        Observer<String> requestFeesObserver1 = new Observer<String>() {
//            @Override
//            public void onChanged(String str) {
//                binding.requestFees2.setText(str);
//            }
//        };
//        sharedViewModel.getRequestFees1().observe(this, requestFeesObserver1);

//      edit purpose
        Observer<String> requestLocationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestLocation.setText(str);
            }
        };
        sharedViewModel.getRequestLocation().observe(this, requestLocationObserver);

        Observer<String> requestDestinationObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestDestination.setText(str);
            }
        };
        sharedViewModel.getRequestDestination().observe(this, requestDestinationObserver);

        Observer<String> requestFeesObserver = new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.requestFees.setText(str);
            }
        };
        sharedViewModel.getRequestFees().observe(this, requestFeesObserver);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentMyRequestBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        CardView cardViewRequest1 = binding.cardViewRequest1;
        Button createNewRequest = binding.createNewRequestBtn;
        Button deleteRequestBtn = binding.deleteRequestBtn;

        cardViewRequest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myRequest_to_myRequestDetails);
            }
        });

        createNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myRequest_to_createRequest);
            }
        });
        super.onViewCreated(view, savedInstanceState);

        deleteRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel.setRequestDelete("delete");
                binding.cardViewRequest1.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}