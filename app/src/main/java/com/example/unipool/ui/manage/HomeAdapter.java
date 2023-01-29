package com.example.unipool.ui.manage;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipool.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private final HomeInterface homeInterface;

    Context context;
    ArrayList<HomeData> rvList;
    buttonClickListener buttonClickListener;

    public HomeAdapter(Context context, ArrayList<HomeData> rvList, buttonClickListener buttonClickListener, HomeInterface homeInterface) {
        this.context = context;
        this.rvList = rvList;
        this.buttonClickListener = buttonClickListener;
        this.homeInterface = homeInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview, parent, false);
        return new HomeAdapter.MyViewHolder(view, buttonClickListener, homeInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeData homeData =rvList.get(position);
        holder.vhLocation.setText(homeData.getRvHomeLocation());
        holder.vhDestination.setText(homeData.getRvHomeDestination());
        holder.vhFees.setText(homeData.getRvHomeFees());

    }

    @Override
    public int getItemCount() {
        return rvList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView vhLocation;
        private TextView vhDestination;
        private TextView vhFees;
        private Button deleteBtn;
        buttonClickListener buttonClickListener;

        public MyViewHolder(@NonNull View itemView, buttonClickListener buttonClickListener, HomeInterface homeInterface) {
            super(itemView);

            vhLocation = itemView.findViewById(R.id.homeLocation);
            vhDestination = itemView.findViewById(R.id.homeDestination);
            vhFees = itemView.findViewById(R.id.homeFees);
            deleteBtn = itemView.findViewById(R.id.homeDeleteBtn);

            this.buttonClickListener = buttonClickListener;
            deleteBtn.setOnClickListener(this);

            itemView.findViewById(R.id.homeCardView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            itemView.findViewById(R.id.homeCardView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (homeInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            homeInterface.onClickItem(position, itemView);
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            buttonClickListener.onButtonClick(getAdapterPosition());
        }

    }

    public interface buttonClickListener{
        void onButtonClick (int position);
    }

}
