package com.example.unipool.ui.chat;

import static java.util.Collections.singletonList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unipool.databinding.FragmentChatBinding;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private final ChatClient client = ChatClient.instance();
    private final User user = new User();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChatViewModel homeViewModel =
                new ViewModelProvider(this).get(ChatViewModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupUser();
        setupChannels();

        return root;
    }

    private void setupUser() {
        // Authenticate and connect the user
        // TO_DO connect to login
        user.setId("UniPool");
        user.setName("UniPool");
        user.setImage("https://bit.ly/2TIt8NR");

        String token = client.devToken(user.getId());

        client.connectUser(user, token).enqueue(result -> {
            if(result.isSuccess()) {
                Log.d( "ChatFragment", "Success Connecting");
            } else {
                Log.d( "ChatFragment", result.error().getMessage());
            }
        });
    }

    private void setupChannels() {
        FilterObject filters = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.in("members", singletonList(user.getId()))
        );

        ViewModelProvider.Factory factory = new ChannelListViewModelFactory.Builder()
                .filter(filters)
                .sort(ChannelListViewModel.DEFAULT_SORT)
                .build();

        ChannelListViewModel channelsViewModel =
                new ViewModelProvider(this, factory).get(ChannelListViewModel.class);

        ChannelListViewModelBinding.bind(channelsViewModel, binding.channelListView, this);
        binding.channelListView.setChannelItemClickListener(channel -> {
            // TODO - start channel activity
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

