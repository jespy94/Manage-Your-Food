package com.example.manageyourfood;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class inventoryItem implements Parcelable {
    private String invName;
    private int invPic;
    private ArrayList<foodItem> invFoodList;

    public inventoryItem(String name, int image){
        invName = name;
        invPic = image;
        invFoodList = new ArrayList<>();
    }

    protected inventoryItem(Parcel in) {
        invName = in.readString();
        invPic = in.readInt();
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

    public void setInvName(String newInvName){
        invName = newInvName;
    }

    public String getInvName(){
        return invName;
    }

    public void setInvPic(int invPic) {
        this.invPic = invPic;
    }

    public int getInvPic(){
        return invPic;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(invName);
        dest.writeInt(invPic);
        dest.writeTypedList(invFoodList);
    }
}
