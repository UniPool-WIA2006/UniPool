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
import com.example.unipool.databinding.FragmentEditRequestDetailsBinding;
import com.example.unipool.databinding.FragmentHomeBinding;
import com.example.unipool.ui.home.HomeViewModel;

import java.util.ArrayList;

public class editRequestDetails extends Fragment {
    private FragmentEditRequestDetailsBinding binding;
    private DatabaseHandler DB;
    private EditText editLocationSave, editDestinationSave, editFeesSave, editExtraNotesSave;
    private String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DatabaseHandler(getActivity());

        MainActivity activity = (MainActivity) getActivity();
        username = activity.getUsername();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditRequestDetailsBinding.inflate(getLayoutInflater());

        editLocationSave = binding.editLocationSave;
        editDestinationSave = binding.editDestinationSave;
        editFeesSave = binding.editFeesSave;
        editExtraNotesSave = binding.editExtraNotesSave;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> arr = DB.displayMyCarpool(username, "request");

        Button saveEditBtn = binding.requestSaveEditBtn;
        saveEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              change the data from database (edit)
                String location = editLocationSave.getText().toString();
                String destination = editDestinationSave.getText().toString();
                String fee = editFeesSave.getText().toString();
                String Notes = editExtraNotesSave.getText().toString();
                Integer id = Integer.parseInt(arr.get(10));

                DB.UpdateCarpool(location,destination,fee,Notes,id);

                Toast.makeText(getActivity(), "Offer successfully updated", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(view).navigate(R.id.action_editRequestDetails_to_navigation_home);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}