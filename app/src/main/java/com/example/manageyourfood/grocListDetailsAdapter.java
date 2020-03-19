package com.example.manageyourfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class grocListDetailsAdapter extends RecyclerView.Adapter<grocListDetailsAdapter.grocListDetailsViewHolder> {
    private ArrayList<foodItem> gDList;

    public static class grocListDetailsViewHolder extends RecyclerView.ViewHolder{
        public TextView grocListFoodItemName;
        public TextView foodItemQuantity;
        public grocListDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            grocListFoodItemName = itemView.findViewById(R.id.grocListFoodItemName);
            foodItemQuantity = itemView.findViewById(R.id.foodItemQuantity);
        }
    }
    public grocListDetailsAdapter(ArrayList<foodItem> list){
        gDList = list;
    }

    @NonNull
    @Override
    public grocListDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.groc_fooditem, parent, false);
        grocListDetailsViewHolder gLDVH = new grocListDetailsViewHolder(v);
        return gLDVH;
    }

    @Override
    public void onBindViewHolder(@NonNull grocListDetailsViewHolder holder, int position) {
        foodItem currentItem = gDList.get(position);

        holder.grocListFoodItemName.setText(currentItem.getFoodName());
        holder.foodItemQuantity.setText(String.valueOf(currentItem.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return gDList.size();
    }
}
