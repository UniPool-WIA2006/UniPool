package com.example.unipool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.unipool.databinding.FragmentManageBinding;
import com.example.unipool.databinding.FragmentManageCarpoolRequestBinding;
import com.example.unipool.ui.manage.ManageViewModel;

import java.util.ArrayList;
import java.util.List;

public class manageCarpoolRequest extends Fragment implements ManageInterface{
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<ModelClass> rvList;

    private FragmentManageCarpoolRequestBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ManageViewModel homeViewModel =
                new ViewModelProvider(this).get(ManageViewModel.class);

        binding = FragmentManageCarpoolRequestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = binding.manageRV;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        rvList = new ArrayList<>();
        adapter = new Adapter(getContext(), rvList, this);
        recyclerView.setAdapter(adapter);

//      insert data from database into arrayList
        rvList.add(new ModelClass(R.drawable.man_2, R.drawable.ic_baseline_male_24, "Marvin", "011-31829578", "RM7.00", "UM", "Terminal Bersepadu Selatan"));
        rvList.add(new ModelClass(R.drawable.woman_2, R.drawable.ic_baseline_female_24, "Linda", "014-73817293", "RM2.00", "KK1, UM", "UM Arena"));

        adapter.notifyDataSetChanged();

        TextView tvCarpoolRequest = binding.tvCarpoolRequest;
        tvCarpoolRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_manageCarpoolRequest_to_navigation_manage);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClickItem(int position, View view) {
        Navigation.findNavController(view).navigate(R.id.action_manageCarpoolRequest_to_manageDetails);
    }
}