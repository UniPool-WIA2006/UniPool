package com.example.unipool.ui.chat;

import static androidx.navigation.Navigation.findNavController;
import static java.util.Collections.singletonList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.unipool.MainActivity;
import com.example.unipool.MyApplication;
import com.example.unipool.R;
import com.example.unipool.databinding.FragmentChatBinding;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel;
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private final ChatClient client = ChatClient.instance();
    private final User user = new User();
    private String username;

    public ChatFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Very Dirty code :(
        MainActivity activity = (MainActivity) getActivity();
        username = activity.getUsername();

        Toast.makeText(getActivity(), "Connecting to " + username + " Chat", Toast.LENGTH_SHORT).show();

        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupUser();
        setupChannels();

        binding.channelListHeaderView.setOnActionButtonClickListener(() -> {
            Navigation.findNavController(root).navigate(R.id.action_navigation_chat_to_userFragment);
        });

        return root;
    }

    private void setupUser() {
        // Authenticate and connect the user
        user.setId(username);
        user.setName(username);
//        user.setImage("https://bit.ly/2TIt8NR");

        String token = client.devToken(user.getId());

        client.connectUser(user, token).enqueue(result -> {
            if(!result.isSuccess()) {
                Toast.makeText(getActivity(), "Failed to Connect", Toast.LENGTH_SHORT).show();
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

        ChannelListHeaderViewModel viewModel = new ViewModelProvider(this).get(ChannelListHeaderViewModel.class);
        ChannelListHeaderViewModelBinding.bind(viewModel, binding.channelListHeaderView, getViewLifecycleOwner());

        ChannelListViewModelBinding.bind(channelsViewModel, binding.channelListView, this);
        binding.channelListView.setChannelItemClickListener(
                channel -> startActivity(ChannelActivity.newIntent(getActivity(), channel))
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

