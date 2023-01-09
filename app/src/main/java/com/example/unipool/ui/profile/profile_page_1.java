package com.example.unipool.ui.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.unipool.DatabaseHandler;
import com.example.unipool.MainActivity;
import com.example.unipool.R;
import com.example.unipool.databinding.FragmentChatBinding;
import com.example.unipool.databinding.FragmentProfilePage1Binding;
import com.github.dhaval2404.imagepicker.ImagePicker;
/*
import com.github.drjacky.imagepicker.ImagePicker;
*/
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import de.hdodenhof.circleimageview.CircleImageView;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;


public class profile_page_1 extends Fragment {
    private Button Btn_EditProfile;
    private Button Btn_RegAsDriver;
    private TextView TV_username, TV_bio, TV_contact, TV_email, TV_emer_cont, TV_trust_point;
    private ProgressBar progressBar;
    private DatabaseHandler dbHandler;
    CircleImageView profile_image;
    FloatingActionButton changeProfile;

    private FragmentProfilePage1Binding binding;
    private String username;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        username = activity.getUsername();

        Toast.makeText(getActivity(), "Profile Page: " + username, Toast.LENGTH_SHORT).show();

        binding = FragmentProfilePage1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbHandler = new DatabaseHandler(getActivity());

        ArrayList<String> arr = dbHandler.searchUserInfo(username);

        TV_username = binding.TVUsername;
        TV_username.setText(arr.get(0));

        String gender = arr.get(2);

        if(gender.equals("Male") || gender.equals("")) {
            TV_username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gender_male, 0, 0, 0);
        } else {
            TV_username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gender_female, 0, 0, 0);
        }

        TV_bio = binding.TVBio;
        TV_bio.setText(arr.get(1));

        TV_contact = binding.TVContactNumber;
        TV_contact.setText(arr.get(3));

        TV_email = binding.TVEmail;
        TV_email.setText(arr.get(4));

        TV_emer_cont = binding.TVEmerCont;
        TV_emer_cont.setText(arr.get(6) + " (" + arr.get(5) + " )");

        progressBar = binding.progressbar;
        TV_trust_point = binding.TVTrustpoint;
        TV_trust_point.setText("Trust Point: " + arr.get(13));
        progressBar.setProgress(Integer.valueOf(arr.get(13)));

        Btn_EditProfile = binding.BtnEditProfile;
        Btn_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), edit_profile.class);
                intent.putExtra("username", username);
                startActivity(intent);

//                Fragment fragment = new Fragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("username", username);
//                fragment.setArguments(bundle);
//                Navigation.findNavController(view).navigate(R.id.action_profilePage1_to_edit_profile);
            }
        });

        //Go to Register As Driver Page
        Btn_RegAsDriver = binding.BtnRegAsDriver;
        Btn_RegAsDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), register_as_a_driver.class);
                intent.putExtra("username", username);
                startActivity(intent);

//                Fragment fragment = new Fragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("username", username);
//                fragment.setArguments(bundle);
//                Navigation.findNavController(view).navigate(R.id.action_profile_page_1_to_register_as_a_driver);
            }
        });



        //Change Profile Picture
        profile_image = binding.profileImage;
        ArrayList<String> uri_arr = dbHandler.searchUserProfilePicture(username);
        String s = uri_arr.get(0);
        Uri uri = Uri.parse(s);
        profile_image.setImageURI(uri);
        changeProfile = binding.changeProfile;
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(profile_page_1.this)
                        .galleryOnly()
                        .crop()
                        .compress(1024) //Final image size will be less than 1MB (Optional)
                        .maxResultSize(1080, 1080)
                        .start(20);
            }
        });

        return root;
    }

    //Change Profile Picture
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        MainActivity activity = (MainActivity) getActivity();
        username = activity.getUsername();

        if(requestCode == 20) {
            Uri uri = data.getData();
            profile_image.setImageURI(uri);
            String image = uri.toString();
            dbHandler.UpdateProfileImage(username, image);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

