package com.example.unipool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> rvList;

    public Adapter(List<ModelClass> rvList) {
        this.rvList = rvList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        int picture = rvList.get(position).getRvPicture();
        int genderIcon = rvList.get(position).getRvGenderIcon();
        String name = rvList.get(position).getRvName();
        String number = rvList.get(position).getRvNumber();
        String fees = rvList.get(position).getRvFees();
        String location = rvList.get(position).getRvLocation();
        String destination = rvList.get(position).getRvDestination();

        holder.setData(picture, genderIcon, name, number, fees, location, destination);
    }

    @Override
    public int getItemCount() {
        return rvList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView vhPicture;
        private ImageView vhGenderIcon;
        private TextView vhName;
        private TextView vhNumber;
        private TextView vhFees;
        private TextView vhLocation;
        private TextView vhDestination;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vhPicture = itemView.findViewById(R.id.rcPicture);
            vhGenderIcon = itemView.findViewById(R.id.rcGenderIcon);
            vhName = itemView.findViewById(R.id.rcName);
            vhNumber = itemView.findViewById(R.id.rcNumber);
            vhFees = itemView.findViewById(R.id.rcFees);
            vhLocation = itemView.findViewById(R.id.rcLocation);
            vhDestination = itemView.findViewById(R.id.rcDestination);

        }

        public void setData(int picture, int genderIcon, String name, String number, String fees, String location, String destination) {
            vhPicture.setImageResource(picture);
            vhGenderIcon.setImageResource(genderIcon);
            vhName.setText(name);
            vhNumber.setText(number);
            vhFees.setText(fees);
            vhLocation.setText(location);
            vhDestination.setText(destination);
        }
    }
}



