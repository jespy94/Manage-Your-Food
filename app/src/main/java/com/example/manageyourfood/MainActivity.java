package com.example.manageyourfood;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.manageyourfood.AppNotif.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notifMan;

    private Button invNavButton;
    private Button grocListNavButton;
    private Button mealSuggestNavButton;
    private Button homeNavButton;
    private Button testNotifButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        invNavButton = findViewById(R.id.InvNav);
        grocListNavButton = findViewById(R.id.GroceryListNav);
        mealSuggestNavButton = findViewById(R.id.mealSuggestNav);
        homeNavButton = findViewById(R.id.homeNav);
        testNotifButton = findViewById(R.id.testNotificationButton);

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
        testNotifButton.setOnClickListener(new View.OnClickListener() {
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

        public void chan1Notif(View v){

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.cupboard)
                    .setContentTitle("Test Title")
                    .setContentText("This item will expire in XX days")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build();
            notifMan.notify(1, notification);
        }
}
