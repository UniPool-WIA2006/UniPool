package com.example.unipool.ui.chat;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.unipool.R;
import com.example.unipool.databinding.UserRowLayoutBinding;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.channel.ChannelClient;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.ui.avatar.AvatarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private View v;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(context).inflate(R.layout.user_row_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User currentUser = userList.get(position);

        holder.ImageView.setUserData(currentUser);
        holder.username.setText(currentUser.getId());
//        holder.binding.lastActiveTextView.setText(convertDate(currentUser.getLastActive().getTime()));
        holder.rootLayout.setOnClickListener(view -> {
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
        Navigation.findNavController(v).navigate(R.id.action_userFragment_to_navigation_chat, bundle);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setData(List<User> newList) {
        if(newList != null) {
            this.userList = newList;
        } else {
            this.userList.add(client.getCurrentUser());
        }
//        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, lastOnline;
        ConstraintLayout rootLayout;
        AvatarView ImageView;
        public MyViewHolder(@NonNull View binding) {
            super(binding);
            ImageView = binding.findViewById(R.id.avatar_imageView);
            username = binding.findViewById(R.id.username_textView);
            lastOnline = binding.findViewById(R.id.lastActive_textView);
        }
    }

    private String convertDate(Long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String dateString = formatter.format(new Date(milliseconds));
        return dateString;
    }
}