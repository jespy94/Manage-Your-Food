package com.example.manageyourfood;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;

public class inventoryItem implements Parcelable {
    private String invName;
    private ArrayList<foodItem> invFoodList;

    public inventoryItem(String name){
        invName = name;
        invFoodList = new ArrayList<>();
    }

    public void setInvName(String newInvName){
        invName = newInvName;
    }

    public String getInvName(){
        return invName;
    }

    public ArrayList<foodItem> getInvFoodList(){
        return invFoodList;
    }

    public void addFoodtoInvList(foodItem item){
        invFoodList.add(item);
    }

    public void setInvFoodList(ArrayList<foodItem> list) {invFoodList = list;}

    public void removeFoodItem(int position){
        invFoodList.remove(position);
    }

    //Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(invName);
        dest.writeTypedList(invFoodList);
    }

    protected inventoryItem(Parcel in) {
        invName = in.readString();
        invFoodList = in.createTypedArrayList(foodItem.CREATOR);
    }

    public static final Creator<inventoryItem> CREATOR = new Creator<inventoryItem>() {
        @Override
        public inventoryItem createFromParcel(Parcel in) {
            return new inventoryItem(in);
        }

        @Override
        public inventoryItem[] newArray(int size) {
            return new inventoryItem[size];
        }
    };
}
