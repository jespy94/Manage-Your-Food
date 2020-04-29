package com.example.manageyourfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MealSuggest extends AppCompatActivity implements ManualMealSuggestDialog.ManualMealSuggestListener, FromExistingMealSuggestDialog.fromExistingMealSuggestListener {
    public RecyclerView MealQueryRecyclerView;
    public MealQueryAdapter MealQueryAdapter;
    public RecyclerView.LayoutManager MealQueryLayoutManager;

    private Button invNavButton;
    private Button grocListNavButton;
    private Button mealSuggestNavButton;
    private Button homeNavButton;
    private Button mealSearchButton;
    private Button manualAddButton;
    private Button fromExistingAddButton;

    public ArrayList<foodItem> mealSuggestQueryList;
    public String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_suggest);
        mealSuggestQueryList = new ArrayList<>();
        buildMealQueryRecyclerView();

        invNavButton = findViewById(R.id.InvNav);
        grocListNavButton = findViewById(R.id.GroceryListNav);
        mealSuggestNavButton = findViewById(R.id.mealSuggestNav);
        homeNavButton = findViewById(R.id.homeNav);
        manualAddButton = findViewById(R.id.manualButton);
        fromExistingAddButton = findViewById(R.id.FromExistingButton);
        mealSearchButton = findViewById(R.id.MealSearchButton);

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
        manualAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManualDialog();
            }
        });
        fromExistingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFromExistingDialog();
            }
        });
        mealSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealSearch();
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

    //Builds recyclerview for the search query
    public void buildMealQueryRecyclerView(){
        MealQueryRecyclerView = findViewById(R.id.MealQueryRecyclerView);
        MealQueryRecyclerView.setHasFixedSize(true);
        MealQueryAdapter = new MealQueryAdapter(mealSuggestQueryList);
        MealQueryLayoutManager = new LinearLayoutManager(this);
        MealQueryRecyclerView.setAdapter(MealQueryAdapter);
        MealQueryRecyclerView.setLayoutManager(MealQueryLayoutManager);

        MealQueryAdapter.setOnItemClickListener(new MealQueryAdapter.OnItemClickListener() {
            @Override
            public void deleteItem(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MealSuggest.this);
                builder.setMessage("Do you want to delete this item?").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mealSuggestQueryList.remove(position);
                        MealQueryAdapter.notifyDataSetChanged();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void openManualDialog(){
        ManualMealSuggestDialog MMSDialog = new ManualMealSuggestDialog();
        MMSDialog.show(getSupportFragmentManager(), "manual add dialog");
    }
    public void openFromExistingDialog(){
        FromExistingMealSuggestDialog FEMSDialog = new FromExistingMealSuggestDialog();
        FEMSDialog.show(getSupportFragmentManager(), "from existing add dialog");
    }

    //Applies a new food items to the query recyclerview
    @Override
    public void applyTextToQueue(String name) {
        foodItem listItem = new foodItem(name, 0);
        mealSuggestQueryList.add(listItem);
        MealQueryAdapter.notifyDataSetChanged();
    }

    //Does the web search for the recipes
    public void mealSearch(){
        Intent mealSearchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        getRecyclerViewQuery();
        mealSearchIntent.putExtra(SearchManager.QUERY,  query + " recipe");
        startActivity(mealSearchIntent);
    }

    //Adds each item's name to search query
    public String getRecyclerViewQuery(){
        int i = 0;
        query = "";
        while ( i < mealSuggestQueryList.size()){
            query = query + " " + mealSuggestQueryList.get(i).getFoodName();
            i++;
        }
        return query;
    }

    //Applies checked items to query recyclerview
    @Override
    public void applyFromExistingRW(ArrayList<foodItem> list) {
        int i = 0;
        while ( i < list.size()){
            mealSuggestQueryList.add(list.get(i));
            i++;
        }
        MealQueryAdapter.notifyDataSetChanged();
    }
}
