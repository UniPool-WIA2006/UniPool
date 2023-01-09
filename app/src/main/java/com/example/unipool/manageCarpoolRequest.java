package com.example.unipool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.unipool.databinding.FragmentManageBinding;
import com.example.unipool.databinding.FragmentManageCarpoolRequestBinding;
import com.example.unipool.ui.manage.ManageViewModel;

public class manageCarpoolRequest extends Fragment {

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

        TextView tvCarpoolRequest = binding.tvCarpoolRequest;
        CardView cvRequest1 = binding.cvCarpoolRequest1;
        CardView cvRequest2 = binding.cvCarpoolRequest2;
        tvCarpoolRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_manageCarpoolRequest_to_navigation_manage);
            }
        });
        cvRequest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_manageCarpoolRequest_to_carpoolRequest1);
            }
        });
        cvRequest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_manageCarpoolRequest_to_carpoolRequest2);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}