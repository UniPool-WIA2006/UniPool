package com.example.unipool.ui.manage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.MainActivity;
import com.example.unipool.R;
import com.example.unipool.databinding.FragmentMyRequestBinding;
import com.example.unipool.ui.home.HomeViewModel;

import java.util.ArrayList;

public class myRequest extends Fragment implements HomeAdapter.buttonClickListener, HomeInterface{

    RecyclerView recyclerView;
    HomeAdapter adapter;
    ArrayList<HomeData> rvHomeList;

    private Button createRequestBtn;
    private Button deleteRequestBtn;
    private CardView requestCV;

    private FragmentMyRequestBinding binding;

    private String username;
    DatabaseHandler DB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DatabaseHandler(getActivity());

        MainActivity activity = (MainActivity) getActivity();
        username = activity.getUsername();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyRequestBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createRequestBtn = binding.homeCreateBtn;
        createRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_myRequest_to_createRequest);
            }
        });

        recyclerView = binding.homeRV;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        rvHomeList = new ArrayList<>();
        adapter = new HomeAdapter(getContext(), rvHomeList, this, this);
        recyclerView.setAdapter(adapter);

//      insert data from database into arraylist
        Cursor cursor = DB.searchCarpoolingByType(username, "offer");
        try {
            if (cursor.getCount() == 0) {
                Toast.makeText(getActivity(), "No Your Carpool Request Available", Toast.LENGTH_SHORT).show();
                return;
            } else {
                while (cursor.moveToNext()) {
                    rvHomeList.add(new HomeData(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getInt(10)));
                }
            }
        } catch(Exception e) {
            Toast.makeText(getActivity(), "Error in Retrieving Data", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

//        rvHomeList.add(new HomeData("a", "aa", "aaa"));
//        rvHomeList.add(new HomeData("b", "bb", "bbb"));
        adapter.notifyDataSetChanged();


        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onButtonClick(int position) {
//      kena remove data dari database, mockup data ni akan ada balik kalau refresh
        DB.DeleteCarpool(rvHomeList.get(position).getId());
        Toast.makeText(getActivity(), "Delete Successful", Toast.LENGTH_SHORT).show();
        rvHomeList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onClickItem(int position, View view) {
        setRvHomeOfferListID(position);

//        System.out.println(position);
//        Navigation.findNavController(view).navigate(R.id.action_myOffer_to_myOfferDetails);
        Intent intent = new Intent(getActivity(), myRequestDetailsActivity.class);
        intent.putExtra("POSITION", getRvHomeOfferListID());
        startActivity(intent);

//        viewModel.setData(getRvHomeOfferListID());
//        Navigation.findNavController(view).navigate(R.id.action_myRequest_to_myRequestDetails);
    }

    public ArrayList<HomeData> getRvHomeRequestList() {
        return rvHomeList;
    }

    private int rvHomeOfferListID;
    public void setRvHomeOfferListID(int position) {
        this.rvHomeOfferListID = rvHomeList.get(position).getId();
    }

    public int getRvHomeOfferListID() {
        return this.rvHomeOfferListID;
    }

}