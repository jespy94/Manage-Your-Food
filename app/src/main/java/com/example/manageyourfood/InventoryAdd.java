package com.example.manageyourfood;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class InventoryAdd extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.manageyourfood.EXTRA_TEXT";
    public static final String EXTRA_PIC = "com.example.manageyourfood.EXTRA_PIC";

    private Button invNavButton;
    private Button grocListNavButton;
    private Button mealSuggestNavButton;
    private Button homeNavButton;
    private Button addNewInvButton;
    private EditText newInvName;
    inventoryItem invItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_add);

        Intent intent = getIntent();


        invNavButton = findViewById(R.id.InvNav);
        grocListNavButton = findViewById(R.id.GroceryListNav);
        mealSuggestNavButton = findViewById(R.id.mealSuggestNav);
        homeNavButton = findViewById(R.id.homeNav);
        addNewInvButton = findViewById(R.id.addNewInvButton);
        newInvName = findViewById(R.id.newInvText);

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
        addNewInvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInventory();
            }
        });
    }
    public void navHome(){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
    }

    public void navInv (){
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
    public void addInventory(){
        invItem = new inventoryItem(newInvName.toString(), R.drawable.cupboard);
        Intent backInvMainIntent = new Intent(this, Inventory.class);
        backInvMainIntent.putExtra(EXTRA_TEXT, newInvName.toString());
        backInvMainIntent.putExtra(EXTRA_PIC, R.drawable.cupboard);
        startActivity(backInvMainIntent);
        finish();
    }
   // @Override
    //public void finish(){
     //   Intent returnIntent = new Intent();
    //    returnIntent.putExtra(EXTRA_TEXT, invItem);
    //    setResult(RESULT_OK, returnIntent);
    //    super.finish();
    //}
}
