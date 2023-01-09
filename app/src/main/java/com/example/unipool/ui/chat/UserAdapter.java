package com.example.unipool.ui.chat;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.unipool.R;
import com.example.unipool.databinding.UserRowLayoutBinding;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.channel.ChannelClient;
import io.getstream.chat.android.client.models.Channel;
import io.getstream.chat.android.client.models.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    UserRowLayoutBinding binding;
    private Context context;
    private List<User> userList;
    private ChatClient client = ChatClient.instance();
    // i3 & create constructor also

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = UserRowLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        MyViewHolder holder = new MyViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User currentUser = userList.get(position);

        binding.avatarImageView.setUserData(currentUser);
        binding.usernameTextView.setText(currentUser.getId());
        binding.lastActiveTextView.setText(convertDate(currentUser.getLastActive().getTime()));
        binding.rootLayout.setOnClickListener(view -> {
            createNewChannel(currentUser.getId(), holder);
        });
    }

    public void createNewChannel(String selectedUser, MyViewHolder holder) {
        ChannelClient channelClient = client.channel("messaging", "");

        Map<String, Object> extraData = new HashMap<>();
        List<String> memberIds = new LinkedList<>();
        memberIds.add(client.getCurrentUser().getId());
        memberIds.add(selectedUser);

        channelClient.create(memberIds, extraData)
                .enqueue(result -> {
                    if (result.isSuccess()) {
                        navigateToChatFragment(holder, result.data().getCid());
                    } else {
                        Log.d( "ChatFragment", result.error().getMessage());
                    }
                });
    }

    private void navigateToChatFragment(MyViewHolder holder, String cid) {
        Bundle bundle = new Bundle();
        bundle.putString("cid", cid);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_userFragment_to_navigation_chat, bundle);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        UserRowLayoutBinding binding;
        public MyViewHolder(@NonNull UserRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private String convertDate(Long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String dateString = formatter.format(new Date(milliseconds));
        return dateString;
    }
}