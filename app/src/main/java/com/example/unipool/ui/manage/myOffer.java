package com.example.unipool.ui.manage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.MainActivity;
import com.example.unipool.R;
import com.example.unipool.databinding.FragmentMyOfferBinding;
import com.example.unipool.ui.home.HomeViewModel;
import com.example.unipool.ui.notification.NotificationList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class myOffer extends Fragment implements HomeAdapter.buttonClickListener, HomeInterface{

    RecyclerView recyclerView;
    HomeAdapter adapter;
    ArrayList<HomeData> rvHomeList;

    private Button createOfferBtn;
    private Button deleteOfferBtn;
    private CardView offerCV;

    private FragmentMyOfferBinding binding;
    private String username;
    DatabaseHandler DB;
    SharedViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DatabaseHandler(getActivity());

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        //for debugging
        viewModel.getData().observe(this, data -> {
            System.out.println("View " + data);
        });


        MainActivity activity = (MainActivity) getActivity();
        username = activity.getUsername();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyOfferBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createOfferBtn = binding.createNewOfferBtn;
        createOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_myOffer_to_createOffer);
            }
        });

        recyclerView = binding.homeRV;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        rvHomeList = new ArrayList<>();
        adapter = new HomeAdapter(getContext(), rvHomeList, this, this);
        recyclerView.setAdapter(adapter);

        Cursor cursor = DB.searchCarpoolingByType(username, "offer");
        try {
            if (cursor.getCount() == 0) {
                Toast.makeText(getActivity(), "No Your Carpool Offer Available", Toast.LENGTH_SHORT).show();
                return;
            } else {
                while (cursor.moveToNext()) {
                    String rvHomeName = cursor.getString(0);
                    String rvHomeNumber = cursor.getString(1);
                    String rvHomeLocation = cursor.getString(2);
                    String rvHomeDestination = cursor.getString(3);
                    String rvHomeFees = cursor.getString(4);
                    int id = cursor.getInt(10);

                    HomeData homeDataObj = new HomeData(rvHomeName, rvHomeNumber, rvHomeLocation,
                            rvHomeDestination, rvHomeFees, id);

                    rvHomeList.add(homeDataObj);
                    Toast.makeText(getContext(), "arrayList size: " + rvHomeList.size(), Toast.LENGTH_SHORT).show();
                }
            }
        } catch(Exception e) {
            Toast.makeText(getActivity(), "Error in Retrieving Data", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onButtonClick(int position) {
//      remove data dari database
        DB.DeleteCarpool(rvHomeList.get(position).getId());
        Toast.makeText(getActivity(), "Delete Successful", Toast.LENGTH_SHORT).show();
        rvHomeList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onClickItem(int position, View view) {
        setRvHomeOfferListID(position);
        Intent intent = new Intent(getActivity(), myOfferDetailsActivity.class);
        intent.putExtra("POSITION", getRvHomeOfferListID());
        startActivity(intent);
    }

    private int rvHomeOfferListID;
    public void setRvHomeOfferListID(int position) {
        this.rvHomeOfferListID = rvHomeList.get(position).getId();
    }

    public int getRvHomeOfferListID() {
        return this.rvHomeOfferListID;
    }
}