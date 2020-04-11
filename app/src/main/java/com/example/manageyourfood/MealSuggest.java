package com.example.manageyourfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
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

    public void buildMealQueryRecyclerView(){
        MealQueryRecyclerView = findViewById(R.id.MealQueryRecyclerView);
        MealQueryRecyclerView.setHasFixedSize(true);
        MealQueryAdapter = new MealQueryAdapter(mealSuggestQueryList);
        MealQueryLayoutManager = new LinearLayoutManager(this);
        MealQueryRecyclerView.setAdapter(MealQueryAdapter);
        MealQueryRecyclerView.setLayoutManager(MealQueryLayoutManager);
    }

    public void openManualDialog(){
        ManualMealSuggestDialog MMSDialog = new ManualMealSuggestDialog();
        MMSDialog.show(getSupportFragmentManager(), "manual add dialog");
    }
    public void openFromExistingDialog(){
        FromExistingMealSuggestDialog FEMSDialog = new FromExistingMealSuggestDialog();
        FEMSDialog.show(getSupportFragmentManager(), "from existing add dialog");
    }

    @Override
    public void applyTextToQueue(String name) {
        foodItem listItem = new foodItem(name, 0);
        mealSuggestQueryList.add(listItem);
        MealQueryAdapter.notifyDataSetChanged();
    }


    public void mealSearch(){
        Intent mealSearchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        getRecyclerViewQuery();
        mealSearchIntent.putExtra(SearchManager.QUERY,  query + " recipe");
        startActivity(mealSearchIntent);
    }
    public String getRecyclerViewQuery(){
        int i = 0;
        query = "";
        while ( i < mealSuggestQueryList.size()){
            query = query + " " + mealSuggestQueryList.get(i).getFoodName();
            i++;
        }
        return query;
    }

    @Override
    public void applyFromExistingRW(ArrayList<foodItem> list) {
        int i = 0;
        while ( i < list.size()){
            mealSuggestQueryList.add(list.get(i));
            i++;
        }
        MealQueryAdapter.notifyDataSetChanged();
    }

    //@Override
    //public void applyFromExistingRW() {
      //    mealSuggestQueryList.add();
       // MealQueryAdapter.notifyDataSetChanged();
    //}
}
