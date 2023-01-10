package com.example.unipool.ui.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.unipool.R;
import com.example.unipool.databinding.FragmentUserBinding;

import java.util.List;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.api.models.QueryUsersRequest;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private ChatClient client = ChatClient.instance();
    private UserAdapter adapter = new UserAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FragmentActivity fragmentActivity = this.getActivity();
        ((AppCompatActivity) fragmentActivity).setSupportActionBar(binding.toolbar2);

        setupToolbar();
        setupRecyclerView();
        queryAllUsers();

        return root;
    }

    private void setupToolbar() {
        binding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.usersRecyclerView.setLayoutManager(layoutManager);
        binding.usersRecyclerView.setAdapter(adapter);
    }

    private void queryAllUsers() {
        FilterObject filter = Filters.autocomplete("id", client.getCurrentUser().getId());
        int offset = 0;
        int limit = 10;
        QueryUsersRequest request = new QueryUsersRequest(filter, offset, limit);

        client.queryUsers(request).enqueue(result -> {
            if (result.isSuccess()) {
                List<User> users = result.data();
//                adapter.setData(users);
                Toast.makeText(getActivity(), "no user to add as friend", Toast.LENGTH_LONG).show();
            } else {
                Log.e("UsersFragment", result.error().getMessage().toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}