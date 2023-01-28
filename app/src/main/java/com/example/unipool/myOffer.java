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


public class myOffer extends Fragment implements HomeAdapter.buttonClickListener{

    RecyclerView recyclerView;
    HomeAdapter adapter;
    ArrayList<HomeData> rvHomeList;

    private Button createOfferBtn;
    private Button deleteOfferBtn;
    private CardView offerCV;

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
        adapter = new HomeAdapter(getContext(), rvHomeList, this);
        recyclerView.setAdapter(adapter);

        //      insert data from database into arraylist
        rvHomeList.add(new HomeData("d", "dd", "ddd"));
        rvHomeList.add(new HomeData("e", "ee", "eee"));
        adapter.notifyDataSetChanged();

        createOfferBtn = binding.createNewOfferBtn;
        createOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_myOffer_to_createOffer);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    @Override
    public void onButtonClick(int position) {
//      remove dari database, mockup data ni akan ada balik kalau refresh
        rvHomeList.remove(position);
        adapter.notifyItemRemoved(position);
    }
}