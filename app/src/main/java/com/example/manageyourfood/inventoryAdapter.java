package com.example.manageyourfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class inventoryAdapter extends RecyclerView.Adapter<inventoryAdapter.invViewHolder> {

    private ArrayList<inventoryItem> aInventoryList;
    private OnInvItemClickListener invListener;

    public interface OnInvItemClickListener{
        void onItemClick(int position);
    }
    public void setOnInvItemClickListener (OnInvItemClickListener listener){
        invListener = listener;
    }

    public static class invViewHolder extends RecyclerView.ViewHolder{

        public ImageView invImageView;
        public TextView invTextView;

        public invViewHolder(View itemView, final OnInvItemClickListener listener){
            super(itemView);
            invImageView = itemView.findViewById(R.id.invItemPic);
            invTextView = itemView.findViewById(R.id.invItemName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public inventoryAdapter(ArrayList<inventoryItem> inventoryList) {
        aInventoryList = inventoryList;
    }
    @NonNull
    @Override
    public invViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_list_item, parent, false);
        invViewHolder ivh = new invViewHolder(v, invListener);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull invViewHolder holder, int position) {
        inventoryItem currentItem = aInventoryList.get(position);

        holder.invImageView.setImageResource(currentItem.getInvPic());
        holder.invTextView.setText(currentItem.getInvName());
    }

    @Override
    public int getItemCount() {
        return aInventoryList.size();
    }
}
