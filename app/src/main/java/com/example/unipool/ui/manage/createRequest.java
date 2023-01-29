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
import com.example.unipool.databinding.FragmentCreateRequestBinding;
import com.example.unipool.databinding.FragmentHomeBinding;
import com.example.unipool.ui.home.HomeViewModel;

import java.util.ArrayList;


public class createRequest extends Fragment {
    private FragmentCreateRequestBinding binding;
    private DatabaseHandler DB;
    private EditText createLocationSave, createDestinationSave, createFeesSave, createExtraNotesSave;
    private String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB = new DatabaseHandler(getActivity());

        MainActivity activity = (MainActivity) getActivity();
        username = activity.getUsername();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateRequestBinding.inflate(getLayoutInflater());

        createLocationSave = binding.createLocationSave;
        createDestinationSave = binding.createDestinationSave;
        createFeesSave = binding.createFeesSave;
        createExtraNotesSave = binding.createExtraNotesSave;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> arr = DB.searchUserInfo(username);

        Button submitRequestBtn = binding.submitRequestBtn;
        submitRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              save new request location, destination, fees, and extra notes to database
//              database will use the data to create new items for recyclerview
                String location = createLocationSave.getText().toString();
                String destination = createDestinationSave.getText().toString();
                String fee = createFeesSave.getText().toString();
                String Notes = createExtraNotesSave.getText().toString();
                String contact = arr.get(3);
                Integer point = Integer.parseInt(arr.get(13));

                DB.AddCarpool(username, contact,location, destination, fee, Notes, point,"request");
                Toast.makeText(getActivity(), "Offer successfully created", Toast.LENGTH_SHORT).show();


                Navigation.findNavController(view).navigate(R.id.action_createRequest_to_navigation_home);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}