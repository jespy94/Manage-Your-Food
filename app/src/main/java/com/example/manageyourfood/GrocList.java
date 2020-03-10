package com.example.manageyourfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class GrocList extends AppCompatActivity implements grocListMainDialog.grocListMainDialogListener{
    public RecyclerView grocListRecyclerView;
    public grocListAdapter grocListAdapter;
    public RecyclerView.LayoutManager grocListLayoutManager;

    private Button invNavButton;
    private Button grocListNavButton;
    private Button mealSuggestNavButton;
    private Button homeNavButton;
    private Button addGrocListButton;
    public ArrayList<grocListItem> grocListList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groc_list);

        grocListList = new ArrayList<>();
        grocListList.add(new grocListItem("TestName"));
        buildGrocListRecyclerView();

        invNavButton = findViewById(R.id.InvNav);
        grocListNavButton = findViewById(R.id.GroceryListNav);
        mealSuggestNavButton = findViewById(R.id.mealSuggestNav);
        homeNavButton = findViewById(R.id.homeNav);
        addGrocListButton = findViewById(R.id.addGrocListButton);

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
        addGrocListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGrocListDialog();
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
    public void buildGrocListRecyclerView(){
        grocListRecyclerView = findViewById(R.id.grocListRecyclerView);
        grocListRecyclerView.setHasFixedSize(true);
        grocListLayoutManager = new LinearLayoutManager(this);
        grocListAdapter = new grocListAdapter(grocListList);
        grocListRecyclerView.setLayoutManager(grocListLayoutManager);
        grocListRecyclerView.setAdapter(grocListAdapter);

        grocListAdapter.setOnInvItemClickListener(new grocListAdapter.onInvItemClickListener() {
            @Override
            public void onInvItemClick(int position) {
                Intent intent = new Intent(this, GrocListDetails);
                startActivity(intent);
            }
        });
    }
    public void addGrocListDialog(){
        grocListMainDialog gLDialog = new grocListMainDialog();
        gLDialog.show(getSupportFragmentManager(), "Grocery List Dialog");
    }
    @Override
    public void applyGrocListsInfo(String name) {
        grocListList.add(new grocListItem(name));
        grocListAdapter.notifyDataSetChanged();
    }
}
