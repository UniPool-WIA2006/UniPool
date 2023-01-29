package com.example.unipool.ui.manage;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipool.ui.manage.Adapter;
import com.example.unipool.ui.manage.ManageInterface;
import com.example.unipool.ui.manage.ModelClass;
import com.example.unipool.R;
import com.example.unipool.databinding.FragmentManageBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ManageFragment extends Fragment implements ManageInterface {
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<ModelClass> rvList;

    private FragmentManageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentManageBinding.inflate(inflater, container, false);
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
        rvList.add(new ModelClass(R.drawable.man_1, R.drawable.ic_baseline_male_24, "Jared", "012-3456789", "RM3.00", "KK8, UM", "DTC, UM"));
        rvList.add(new ModelClass(R.drawable.woman_1, R.drawable.ic_baseline_female_24, "Lili", "014-73817293", "Free", "FSKTM, UM", "KK12, UM"));

        adapter.notifyDataSetChanged();

        TextView tvCarpoolOffer = binding.tvCarpoolOffer;
        tvCarpoolOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_manage_to_manageCarpoolRequest);
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
        Navigation.findNavController(view).navigate(R.id.action_navigation_manage_to_manageDetails);
    }
}