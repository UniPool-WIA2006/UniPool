package com.example.unipool.ui.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;

public class profile_page_2 extends Fragment {

    private Button Btn_EditProfile;
    private Button Btn_UpdateCarInfo;
    private TextView TV_username, TV_bio, TV_contact, TV_email, TV_emer_cont, TV_trust_point;
    private ProgressBar progressBar;
    private DatabaseHandler dbHandler;
    CircleImageView profile_image;
    FloatingActionButton changeProfile2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_profile_page_2, container, false);
        dbHandler = new DatabaseHandler(getActivity());
        Bundle bundle = getActivity().getIntent().getExtras();
        String username = bundle.getString("username");
        ArrayList<String> arr = dbHandler.searchUserInfo(username);

        TV_username = getView().findViewById(R.id.TV_Username);
        TV_username.setText(arr.get(0));

        if (arr.get(2).equals("Male")||arr.get(2).equals("")) {
            TV_username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gender_male, 0, 0, 0);
        }
        else {
            TV_username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gender_female, 0, 0, 0);
        }

        TV_bio = getView().findViewById(R.id.TV_bio);
        TV_bio.setText(arr.get(1));

        TV_contact = getView().findViewById(R.id.TV_ContactNumber);
        TV_contact.setText(arr.get(3).toString());

        TV_email = getView().findViewById(R.id.TV_Email);
        TV_email.setText(arr.get(4).toString());

        TV_emer_cont = getView().findViewById(R.id.TV_EmerCont);
        TV_emer_cont.setText(arr.get(6).toString()+" ("+arr.get(5).toString()+" )");

        progressBar = getView().findViewById(R.id.progressbar);
        TV_trust_point = getView().findViewById(R.id.TV_trustpoint);
        TV_trust_point.setText("Trust Point: "+arr.get(13));
        progressBar.setProgress(Integer.valueOf(arr.get(13)));


        Btn_EditProfile = getView().findViewById(R.id.Btn_EditProfile);
        Btn_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment.setArguments(bundle);
//                Navigation.findNavController(view).navigate(R.id.action_profilePage2_to_edit_profile);
            }
        });

        //Go to Update Car Info Page
        Button Btn_UpdateCarInfo = getView().findViewById(R.id.Btn_UpdateCarInfo);
        Btn_UpdateCarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment.setArguments(bundle);
//                Navigation.findNavController(view).navigate(R.id.action_profile_page_1_to_update_car_info);
            }
        });

        //Change Profile Picture
        profile_image = getView().findViewById(R.id.profile_image);
        changeProfile2 = getView().findViewById(R.id.changeProfile2);
        changeProfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(getActivity())
                        .galleryOnly()
                        .crop()
                        .compress(1024) //Final image size will be less than 1MB (Optional)
                        .maxResultSize(1080, 1080)
                        .start(20);
            }
        });
        return v;
    }

    public void onStart() {
        super.onStart();
        dbHandler = new DatabaseHandler(getActivity());
        Bundle bundle = getActivity().getIntent().getExtras();
        String username = bundle.getString("username");
        ArrayList<String> arr = dbHandler.searchUserInfo(username);
        TV_username = getView().findViewById(R.id.TV_Username);
        TV_username.setText(arr.get(0));
        if (arr.get(2).equals("Male")||arr.get(2).equals("")) {
            TV_username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gender_male, 0, 0, 0);
        }
        else {
            TV_username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gender_female, 0, 0, 0);
        }

        TV_bio = getView().findViewById(R.id.TV_bio);
        TV_bio.setText(arr.get(1));

        TV_contact = getView().findViewById(R.id.TV_ContactNumber);
        TV_contact.setText(arr.get(3));

        TV_email = getView().findViewById(R.id.TV_Email);
        TV_email.setText(arr.get(4));

        TV_emer_cont = getView().findViewById(R.id.TV_EmerCont);
        TV_emer_cont.setText(arr.get(6) + " ("+arr.get(5) + " )");

        progressBar = getView().findViewById(R.id.progressbar);
        TV_trust_point = getView().findViewById(R.id.TV_trustpoint);
        TV_trust_point.setText("Trust Point: "+arr.get(13));
        progressBar.setProgress(Integer.valueOf(arr.get(13)));

        Btn_EditProfile = getView().findViewById(R.id.Btn_EditProfile);
        Btn_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment.setArguments(bundle);
//                Navigation.findNavController(view).navigate(R.id.action_profilePage2_to_edit_profile);
            }
        });

        //Go to Update Car Info Page
        Button Btn_UpdateCarInfo = getView().findViewById(R.id.Btn_UpdateCarInfo);
        Btn_UpdateCarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment.setArguments(bundle);
//                Navigation.findNavController(view).navigate(R.id.action_profile_page_1_to_update_car_info);
            }
        });

        //Change Profile Picture
        profile_image = getView().findViewById(R.id.profile_image);
        changeProfile2 = getView().findViewById(R.id.changeProfile2);
        changeProfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(getActivity())
                        .galleryOnly()
                        .crop()
                        .compress(1024) //Final image size will be less than 1MB (Optional)
                        .maxResultSize(1080, 1080)
                        .start(20);
            }
        });
    }

    //Change Profile Picture
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 20){
            Uri uri = data.getData();
            profile_image.setImageURI(uri);
        }

    }
}