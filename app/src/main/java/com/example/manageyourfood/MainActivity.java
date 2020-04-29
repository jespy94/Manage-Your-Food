package com.example.manageyourfood;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.manageyourfood.AppNotif.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notifMan;

    private Button invNavButton;
    private Button grocListNavButton;
    private Button mealSuggestNavButton;
    private Button homeNavButton;
    private Button notifButton;
    private ArrayList<inventoryItem> mainInvList;
    private ArrayList<foodItem> currentDetailList;
    private ArrayList<foodItem> allFoodList;
    private String notifExpList;
    private long tillExpiry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        invNavButton = findViewById(R.id.InvNav);
        grocListNavButton = findViewById(R.id.GroceryListNav);
        mealSuggestNavButton = findViewById(R.id.mealSuggestNav);
        homeNavButton = findViewById(R.id.homeNav);
        notifButton = findViewById(R.id.testNotificationButton);

        invNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navInv();
            }
        });
        grocListNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navGrocList();
            }
        });
        mealSuggestNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navMealSuggest();
            }
        });
        homeNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navHome();
            }
        });
        notifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chan1Notif(v);
            }
        });


        notifMan = NotificationManagerCompat.from(this);

    }
        public void navHome(){
            Intent homeIntent = new Intent(this, MainActivity.class);
            startActivity(homeIntent);
        }

        public void navInv () {
            Intent InvIntent = new Intent(this, Inventory.class);
            startActivity(InvIntent);
        }

        public void navGrocList () {
            Intent GrocListIntent = new Intent(this, GrocList.class);
            startActivity(GrocListIntent);
        }

        public void navMealSuggest () {
            Intent MealSuggestIntent = new Intent(this, MealSuggest.class);
            startActivity(MealSuggestIntent);
        }

        //Creates notification with items that are expiring within 7 days
        public void chan1Notif(View v) {
            loadFromExistingInv();
            String current = Calendar.getInstance().getTime().toString();
            String currentToSDF = toSDF(current);
            notifExpList = "";
            tillExpiry = 0;
            //This checks if the current date is within 7 days of the expiration date and adds it to a string
            for(int i = 0 ; i < allFoodList.size(); i++){
                String expiry = allFoodList.get(i).getExpDate();
                if(Integer.parseInt(expiry.substring(6,10)) - Integer.parseInt(currentToSDF.substring(6,10)) <= 1){
                    if((Integer.parseInt(expiry.substring(0,2)) * 30 + Integer.parseInt(expiry.substring(3,5))) - (Integer.parseInt(currentToSDF.substring(0,2)) * 30 + Integer.parseInt(currentToSDF.substring(3,5))) <= 7){
                        notifExpList += allFoodList.get(i).getFoodName() + ", ";
                    }
                }
            }
            if(notifExpList != ""){
                Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.myflogo)
                        .setContentTitle("Expiring Items")
                        .setContentText("The following items will expire within 7 days: " + notifExpList)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setStyle(new NotificationCompat.BigTextStyle())
                        .build();
                notifMan.notify(1, notification);
            }
            else{
                //Creates this notification if nothing is expiring
                Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.myflogo)
                        .setContentTitle("Expiring Items")
                        .setContentText("No items are expiring within 7 days")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build();
                notifMan.notify(1, notification);
            }
        }

        public void loadFromExistingInv(){
            SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("Main Inventory List", null);
            Type type = new TypeToken<ArrayList<inventoryItem>>(){}.getType();
            mainInvList = gson.fromJson(json, type);
            allFoodList = new ArrayList<>();
            if (mainInvList == null){
                mainInvList = new ArrayList<>();
            }
            for(int i = 0; i < mainInvList.size(); i++){
                loadInvDetails(i);
                for(int j = 0; j < currentDetailList.size(); j++){
                    allFoodList.add(currentDetailList.get(j));
                }
            }
        }

        public void loadInvDetails(int i){
            SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory details" + Integer.toString(i), MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("Current Inventory List", null);
            Type type = new TypeToken<ArrayList<foodItem>>(){}.getType();
            currentDetailList = gson.fromJson(json, type);
        }

        //Turns the string to MM/dd/YYYY format
        public String toSDF(String s){
            String x = null;
            if(s.substring(4, 7).equals("Jan")){
                x =  "01/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Feb")){
                x =  "02/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Mar")){
                x =  "03/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Apr")){
                x =  "04/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("May")){
                x =  "05/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Jun")){
                x =  "06/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Jul")){
                x =  "07/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Aug")){
                x =  "08/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Sep")){
                x =  "09/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Oct")){
                x =  "10/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Nov")){
                x =  "11/" + s.substring(8,10) + "/" + s.substring(24);
            }
            if(s.substring(4, 7).equals("Dec")){
                x =  "12/" + s.substring(8,10) + "/" + s.substring(24);
            }
            return x;
        }
}
