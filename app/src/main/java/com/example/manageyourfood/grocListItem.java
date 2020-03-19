package com.example.manageyourfood;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class grocListItem implements Parcelable {
    private String grocListName;
    private ArrayList<foodItem> foodList;

    public grocListItem(String name){
        grocListName = name;
        foodList = new ArrayList<>();
    }

    protected grocListItem(Parcel in) {
        grocListName = in.readString();
        foodList = in.createTypedArrayList(foodItem.CREATOR);
    }

    public static final Creator<grocListItem> CREATOR = new Creator<grocListItem>() {
        @Override
        public grocListItem createFromParcel(Parcel in) {
            return new grocListItem(in);
        }

        @Override
        public grocListItem[] newArray(int size) {
            return new grocListItem[size];
        }
    };

    public String getGrocListName(){
        return grocListName;
    }
    public void setGrocListName(String name){
        grocListName = name;
    }
    public ArrayList<foodItem> getFoodList(){
        return foodList;
    }
    public foodItem getFoodFromList(int pos){
        return foodList.get(pos);
    }
    public void addFoodToList(foodItem item){
        foodList.add(item);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(grocListName);
        dest.writeTypedList(foodList);
    }
}
