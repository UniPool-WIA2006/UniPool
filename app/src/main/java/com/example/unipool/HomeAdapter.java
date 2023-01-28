package com.example.unipool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    Context context;
    ArrayList<HomeData> rvList;

    public HomeAdapter(Context context, ArrayList<HomeData> rvList) {
        this.context = context;
        this.rvList = rvList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview, parent, false);
        return new HomeAdapter.MyViewHolder(view);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView vhLocation;
        private TextView vhDestination;
        private TextView vhFees;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vhLocation = itemView.findViewById(R.id.homeLocation);
            vhDestination = itemView.findViewById(R.id.homeDestination);
            vhFees = itemView.findViewById(R.id.homeFees);
        }
    }



}
