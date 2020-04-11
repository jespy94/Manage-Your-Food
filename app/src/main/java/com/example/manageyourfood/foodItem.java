package com.example.manageyourfood;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class foodItem extends Object implements Parcelable {
    private String foodName;
    private String purchDate;
    private String expDate;
    private int quantity;
    private Boolean checked;
    //For Inventory Function
    public foodItem (String name, String pDate, String eDate){
        foodName = name;
        purchDate = pDate;
        expDate = eDate;
        quantity = 0;
        checked = false;
    }
    //For Grocery List Function
    public foodItem (String name, int num){
        foodName = name;
        quantity = num;
        checked = false;
    }

    protected foodItem(Parcel in) {
        foodName = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<foodItem> CREATOR = new Creator<foodItem>() {
        @Override
        public foodItem createFromParcel(Parcel in) {
            return new foodItem(in);
        }

        @Override
        public foodItem[] newArray(int size) {
            return new foodItem[size];
        }
    };

    public void setFoodName (String newName){
        foodName = newName;
    }

    public void setPurchDate (String newPDate){
        purchDate = newPDate;
    }

    public void setExpDate (String newEDate){
        expDate = newEDate;
    }

    public String getFoodName (){
        return foodName;
    }

    public String getPurchDate () {
        return purchDate;
    }

    public String getExpDate (){
        return expDate;
    }

    public int getQuantity(){return quantity;}

    public void setQuantity(int num){quantity = num;}

    public Boolean getChecked(){return checked;}

    public void setChecked(Boolean value){checked = value;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foodName);
        dest.writeInt(quantity);
    }
}
