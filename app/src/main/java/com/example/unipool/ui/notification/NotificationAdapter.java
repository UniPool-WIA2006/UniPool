package com.example.unipool.ui.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipool.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name_id, type_id, date_id;
//
//    if(cursor.getString(7).equals("request")) {
//        description.add("has accepted your request!");
//    } else {
//        description.add("has accepted your offer!");
//    }

    public NotificationAdapter(Context context, ArrayList name_id, ArrayList type_id, ArrayList date_id) {
        this.context = context;
        this.name_id = name_id;
        this.type_id = type_id;
        this.date_id = date_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_notification,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.type_id.setText(String.valueOf(type_id.get(position)));
        holder.date_id.setText(String.valueOf(date_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_id, type_id, date_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            type_id = itemView.findViewById(R.id.textStatus);
            date_id = itemView.findViewById(R.id.textDate);
        }
    }
}