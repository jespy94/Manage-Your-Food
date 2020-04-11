package com.example.manageyourfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class inventoryDetailsAdapter extends RecyclerView.Adapter<inventoryDetailsAdapter.inventoryDetailsViewHolder> {
    private ArrayList<foodItem> invFoodList;
    private onInventoryDetailsClickListener iDListener;

    public interface onInventoryDetailsClickListener{
        void onInvDetailsItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnInventoryDetailsClickListener(onInventoryDetailsClickListener listener){
        iDListener = listener;
    }

    public static class inventoryDetailsViewHolder extends RecyclerView.ViewHolder {
        public TextView invFoodName;
        public TextView invFoodPurchDate;
        public TextView invFoodExpDate;
        public ImageView deleteIcon;

        public inventoryDetailsViewHolder(@NonNull View itemView, final onInventoryDetailsClickListener listener) {
            super(itemView);
            invFoodName = itemView.findViewById(R.id.invFoodItemName);
            invFoodPurchDate = itemView.findViewById(R.id.foodItemPurchDate);
            invFoodExpDate = itemView.findViewById(R.id.foodItemExpDate);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public inventoryDetailsAdapter(ArrayList<foodItem> list){
        invFoodList = list;
    }

    @NonNull
    @Override
    public inventoryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inv_food_item, parent, false);
        inventoryDetailsViewHolder iDVH = new inventoryDetailsViewHolder(v, iDListener);
        return iDVH;
    }

    @Override
    public void onBindViewHolder(@NonNull inventoryDetailsViewHolder holder, int position) {
        foodItem currentItem = invFoodList.get(position);

        holder.invFoodName.setText(currentItem.getFoodName());
        holder.invFoodPurchDate.setText(String.valueOf(currentItem.getPurchDate()));
        holder.invFoodExpDate.setText(String.valueOf(currentItem.getExpDate()));
    }

    @Override
    public int getItemCount() {
        return invFoodList.size();
    }
}
