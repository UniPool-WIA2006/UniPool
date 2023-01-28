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

public class myRequest extends Fragment implements HomeAdapter.buttonClickListener{

    RecyclerView recyclerView;
    HomeAdapter adapter;
    ArrayList<HomeData> rvHomeList;

    private Button createRequestBtn;
    private Button deleteRequestBtn;
    private CardView requestCV;

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
        adapter = new HomeAdapter(getContext(), rvHomeList, this);
        recyclerView.setAdapter(adapter);

//      insert data from database into arraylist
        rvHomeList.add(new HomeData("a", "aa", "aaa"));
        rvHomeList.add(new HomeData("b", "bb", "bbb"));
        adapter.notifyDataSetChanged();

        createRequestBtn = binding.homeCreateBtn;
        createRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_myRequest_to_createRequest);
            }
        });

        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onButtonClick(int position) {
//      kena tambah remove dari database, mockup data ni akan ada balik kalau refresh
        rvHomeList.remove(position);
        adapter.notifyItemRemoved(position);
    }
}