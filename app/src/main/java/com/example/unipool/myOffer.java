package com.example.unipool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unipool.databinding.FragmentMyOfferBinding;
import com.example.unipool.ui.home.HomeViewModel;

import java.util.ArrayList;


public class myOffer extends Fragment {

    RecyclerView recyclerView;
    HomeAdapter adapter;
    ArrayList<HomeData> rvHomeList;
    private FragmentMyOfferBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyOfferBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = binding.homeRV;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        rvHomeList = new ArrayList<>();
        adapter = new HomeAdapter(getContext(), rvHomeList);
        recyclerView.setAdapter(adapter);

        //      insert data from database into arraylist
        rvHomeList.add(new HomeData("Offer Location Baru", "Offer Destination Baru", " Offer RM3.79"));
        rvHomeList.add(new HomeData("Offer Location Baru", "Offer Destination Baru", " Offer RM3.79"));
        rvHomeList.add(new HomeData("Offer Location Baru", "Offer Destination Baru", " Offer RM3.79"));
        adapter.notifyDataSetChanged();

//        CardView cardViewOffer1 = binding.cardViewOffer1;
//        Button createOfferBtn = binding.createNewOfferBtn;
//        Button deleteOfferBtn = binding.deleteOfferBtn;
//        Button deleteOfferBtn1 = binding.deleteOfferBtn1;

//        cardViewOffer1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.action_myOffer_to_myOfferDetails);
//            }
//        });
//
//        deleteOfferBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sharedViewModel.setOfferDelete("delete");
//                binding.cardViewOffer1.setVisibility(View.GONE);
//            }
//        });
//
//        deleteOfferBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sharedViewModel.setOfferDelete("delete");
//                binding.cardViewOffer2.setVisibility(View.GONE);
//            }
//        });
//
//        createOfferBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.action_myOffer_to_createOffer);
//                binding.cardViewOffer2.setVisibility(View.VISIBLE);
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}