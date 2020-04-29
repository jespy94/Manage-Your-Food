package com.example.manageyourfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class grocListAdapter extends RecyclerView.Adapter<grocListAdapter.grocListViewHolder> {
    private ArrayList<grocListItem> gList;
    private onGrocItemClickListener invListener;


    public interface onGrocItemClickListener{
        void onGrocItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnGrocItemClickListener(onGrocItemClickListener listener){
        invListener = listener;
    }


    public static class grocListViewHolder extends RecyclerView.ViewHolder{
        public TextView gListName;
        public ImageView deleteIcon;
        public grocListViewHolder(View itemView, final onGrocItemClickListener listener){
            super(itemView);
            gListName = itemView.findViewById(R.id.grocListName);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onGrocItemClick(position);
                        }
                    }
                }
            });
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

    public grocListAdapter(ArrayList<grocListItem> list){
        gList = list;
    }

    @NonNull
    @Override
    public grocListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_list_item, parent, false);
        grocListViewHolder glvh = new grocListViewHolder(v, invListener);
        return glvh;
    }

    @Override
    public void onBindViewHolder(@NonNull grocListViewHolder holder, int position) {
        grocListItem currentItem = gList.get(position);

        holder.gListName.setText(currentItem.getGrocListName());
    }

    @Override
    public int getItemCount() {
        return gList.size();
    }
}
