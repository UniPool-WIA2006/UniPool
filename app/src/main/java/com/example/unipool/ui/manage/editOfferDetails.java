package com.example.unipool.ui.manage;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.MainActivity;
import com.example.unipool.R;
import com.example.unipool.databinding.FragmentEditOfferDetailsBinding;
import com.example.unipool.databinding.FragmentHomeBinding;
import com.example.unipool.ui.home.HomeViewModel;

import java.util.ArrayList;


public class editOfferDetails extends Fragment {
    private FragmentEditOfferDetailsBinding binding;
    private DatabaseHandler DB;
    private EditText editPickupLocation, editDestination, editFees, editExtraNotes;
    private String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DatabaseHandler(getActivity());

        MainActivity activity = (MainActivity) getActivity();
        username = activity.getUsername();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditOfferDetailsBinding.inflate(getLayoutInflater());

        editPickupLocation = binding.editPickupLocation;
        editDestination = binding.editDestination;
        editFees = binding.editFees;
        editExtraNotes = binding.editExtraNotes;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> arr = DB.displayMyCarpool(username, "offer");

        Button saveEditOfferBtn = binding.saveEditOfferBtn;
        saveEditOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              change the data from database (edit)
                String location = editPickupLocation.getText().toString();
                String destination = editDestination.getText().toString();
                String fee = editFees.getText().toString();
                String Notes = editExtraNotes.getText().toString();
                Integer id = Integer.parseInt(arr.get(10));

                DB.UpdateCarpool(location,destination,fee,Notes,id);

                Toast.makeText(getActivity(), "Offer successfully updated", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_editOfferDetails_to_navigation_home);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}