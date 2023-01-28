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

import com.example.unipool.databinding.FragmentMyRequestBinding;
import com.example.unipool.ui.home.HomeViewModel;

import java.util.ArrayList;

public class myRequest extends Fragment {

    RecyclerView recyclerView;
    HomeAdapter adapter;
    ArrayList<HomeData> rvHomeList;
    private FragmentMyRequestBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyRequestBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = binding.homeRV;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        rvHomeList = new ArrayList<>();
        adapter = new HomeAdapter(getContext(), rvHomeList);
        recyclerView.setAdapter(adapter);

//      insert data from database into arraylist
        rvHomeList.add(new HomeData("Request Location Baru", "Request Destination Baru", "Request RM3.79"));
        rvHomeList.add(new HomeData("Request Location Baru", "Request Destination Baru", "Request RM3.79"));
        rvHomeList.add(new HomeData("Request Location Baru", "Request Destination Baru", "Request RM3.79"));

        adapter.notifyDataSetChanged();

//        CardView cardViewRequest1 = binding.cardViewRequest1;
//        Button createNewRequest = binding.createNewRequestBtn;
//        Button deleteRequestBtn = binding.deleteRequestBtn;
//        Button deleteRequestBtn1 = binding.deleteRequestBtn1;

//        cardViewRequest1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.action_myRequest_to_myRequestDetails);
//            }
//        });
//
//        deleteRequestBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sharedViewModel.setRequestDelete("delete");
//                binding.cardViewRequest1.setVisibility(View.GONE);
//            }
//        });
//
//        deleteRequestBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sharedViewModel.setRequestDelete1("delete");
//                binding.cardViewRequest2.setVisibility(View.GONE);
//            }
//        });
//
//        createNewRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.action_myRequest_to_createRequest);
//                binding.cardViewRequest2.setVisibility(View.VISIBLE);
//            }
//        });
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}