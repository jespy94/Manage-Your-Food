package com.example.manageyourfood;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class grocListAdapter extends RecyclerView.Adapter<grocListAdapter.grocListViewHolder> {
    private ArrayList<grocListItem> gList;
    private onInvItemClickListener invListener;

    public interface onInvItemClickListener{
        void onInvItemClick(int position);
    }

    public void setOnInvItemClickListener(onInvItemClickListener listener){
        invListener = listener;
    }


    public static class grocListViewHolder extends RecyclerView.ViewHolder{
        public TextView gListName;
        public grocListViewHolder(View itemView, final onInvItemClickListener listener){
            super(itemView);
            gListName = itemView.findViewById(R.id.grocListName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onInvItemClick(position);
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
