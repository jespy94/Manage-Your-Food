package com.example.manageyourfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GrocListDetails extends AppCompatActivity implements grocListDetailsDialog.grocListDetailsListener {

    public static final String EXTRA_TEXT = "com.example.manageyourfood.EXTRA_TEXT";
    public static final String EXTRA_GROCLIST = "com.example.manageyourfood.EXTRA_GROCLIST";
    public static final String EXTRA_INT = "com.example.manageyourfood.EXTRA_INT";

    public RecyclerView grocListDetailsRecyclerView;
    public grocListDetailsAdapter gLDAdapter;
    public RecyclerView.LayoutManager gLDLayoutManager;

    private Button invNavButton;
    private Button grocListNavButton;
    private Button mealSuggestNavButton;
    private Button homeNavButton;

    private Button addFoodItemButton;
    private TextView grocListName;
    private grocListItem currentGList;
    public ArrayList<foodItem> gDLArray;
    private String pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groc_list_details);

        getGrocList();
        loadCurrentGrocListData();
        buildGrocListDetailsRecyclerView();

        invNavButton = findViewById(R.id.InvNav);
        grocListNavButton = findViewById(R.id.GroceryListNav);
        mealSuggestNavButton = findViewById(R.id.mealSuggestNav);
        homeNavButton = findViewById(R.id.homeNav);
        addFoodItemButton = findViewById(R.id.addFoodItem);

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
        addFoodItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFoodItemDialog();
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

    //Gets position from previous page to load current inventory
    public void getGrocList(){
        Intent intent = getIntent();
        grocListName = findViewById(R.id.grocListDetailsTitle);
        currentGList = intent.getParcelableExtra(EXTRA_GROCLIST);
        pos = Integer.toString(intent.getIntExtra(EXTRA_INT, 0));
        gDLArray = currentGList.getFoodList();
        grocListName.setText(currentGList.getGrocListName());
    }

    //Opens dialog for adding food items
    public void openAddFoodItemDialog(){
        grocListDetailsDialog newItemDialog = new grocListDetailsDialog();
        newItemDialog.show(getSupportFragmentManager(), "Add a New Item.");
    }

    //Builds recyclerview and listeners
    public void buildGrocListDetailsRecyclerView(){
        grocListDetailsRecyclerView = findViewById(R.id.grocListMainRecyclerView);
        grocListDetailsRecyclerView.setHasFixedSize(true);
        gLDLayoutManager = new LinearLayoutManager(this);
        gLDAdapter = new grocListDetailsAdapter(currentGList.getFoodList());

        grocListDetailsRecyclerView.setLayoutManager(gLDLayoutManager);
        grocListDetailsRecyclerView.setAdapter(gLDAdapter);
        gLDAdapter.setOnGrocListDetailsClick(new grocListDetailsAdapter.grocListDetailsListener() {
            @Override
            public void onDeleteClick(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GrocListDetails.this);
                builder.setMessage("Do you want to delete this item?").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentGList.removeItem(position);
                        gLDAdapter.notifyDataSetChanged();
                        saveCurrentGrocListData();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    //Adds food item to grocery list details
    @Override
    public void applyNewFoodDetails (String name, int num) {
        currentGList.addFoodToList(new foodItem(name, num));
        gLDAdapter.notifyDataSetChanged();
        saveCurrentGrocListData();
    }

    //Saves any changes to the grocery list details
    private void saveCurrentGrocListData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences grocery list details" + pos, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(currentGList.getFoodList());
        editor.putString("Current Grocery List", json);
        editor.apply();
    }

    //Loads the grocery list details based on position
    private void loadCurrentGrocListData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences grocery list details" + pos, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Current Grocery List", null);
        Type type = new TypeToken<ArrayList<foodItem>>(){}.getType();
        gDLArray = gson.fromJson(json, type);

        if (gDLArray != null){
            currentGList.setFoodList(gDLArray);
        }
    }
}
