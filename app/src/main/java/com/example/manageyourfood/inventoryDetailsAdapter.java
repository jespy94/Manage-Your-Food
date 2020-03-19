package com.example.manageyourfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class inventoryDetailsAdapter extends RecyclerView.Adapter<inventoryDetailsAdapter.inventoryDetailsViewHolder> {
    private ArrayList<foodItem> invFoodList;

    public static class inventoryDetailsViewHolder extends RecyclerView.ViewHolder {
        public TextView invFoodName;
        public TextView invFoodPurchDate;
        public TextView invFoodExpDate;
        public inventoryDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            invFoodName = itemView.findViewById(R.id.invFoodItemName);
            invFoodPurchDate = itemView.findViewById(R.id.foodItemPurchDate);
            invFoodExpDate = itemView.findViewById(R.id.foodItemExpDate);
        }
    }

    public inventoryDetailsAdapter(ArrayList<foodItem> list){
        invFoodList = list;
    }

    @NonNull
    @Override
    public inventoryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inv_food_item, parent, false);
        inventoryDetailsViewHolder iDVH = new inventoryDetailsViewHolder(v);
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
