package com.example.unipool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final ManageInterface manageInterface;
    Context context;
    ArrayList<ModelClass> rvList;

    public Adapter(Context context, ArrayList<ModelClass> rvList, ManageInterface manageInterface) {
        this.context = context;
        this.rvList = rvList;
        this.manageInterface = manageInterface;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_recyclerview, parent, false);
        return new ViewHolder(view, manageInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        ModelClass modelClass =rvList.get(position);
        holder.vhPicture.setImageResource(modelClass.getRvPicture());
        holder.vhGenderIcon.setImageResource(modelClass.getRvGenderIcon());
        holder.vhName.setText(modelClass.getRvName());
        holder.vhNumber.setText(modelClass.getRvNumber());
        holder.vhFees.setText(modelClass.getRvFees());
        holder.vhLocation.setText(modelClass.getRvLocation());
        holder.vhDestination.setText(modelClass.getRvDestination());

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


        public ViewHolder(@NonNull View itemView, ManageInterface manageDetails) {
            super(itemView);

            vhPicture = itemView.findViewById(R.id.rcPicture);
            vhGenderIcon = itemView.findViewById(R.id.rcGenderIcon);
            vhName = itemView.findViewById(R.id.rcName);
            vhNumber = itemView.findViewById(R.id.rcNumber);
            vhFees = itemView.findViewById(R.id.rcFees);
            vhLocation = itemView.findViewById(R.id.rcLocation);
            vhDestination = itemView.findViewById(R.id.rcDestination);

            itemView.findViewById(R.id.manageCardView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (manageDetails != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            manageDetails.onClickItem(position, itemView);
                        }
                    }
                }
            });
        }

    }
}



