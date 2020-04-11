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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    public static final String EXTRA_TEXT = "com.example.manageyourfood.EXTRA_TEXT";
    public static final String EXTRA_GROCLIST = "com.example.manageyourfood.EXTRA_GROCLIST";
    public static final String EXTRA_INT = "com.example.manageyourfood.EXTRA_INT";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groc_list);

        loadGrocListData();
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

        grocListAdapter.setOnGrocItemClickListener(new grocListAdapter.onGrocItemClickListener() {
            @Override
            public void onGrocItemClick(int position) {
                Intent gcLIntent = new Intent(GrocList.this, GrocListDetails.class);
                gcLIntent.putExtra(EXTRA_GROCLIST, grocListList.get(position));
                gcLIntent.putExtra(EXTRA_INT, position);
                startActivity(gcLIntent);
            }

            @Override
            public void onDeleteClick(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GrocList.this);
                builder.setMessage("Do you want to delete this item?").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        grocListList.remove(position);
                        grocListAdapter.notifyDataSetChanged();
                        saveGrocListData();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

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
        saveGrocListData();
    }
    private void saveGrocListData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(grocListList);
        editor.putString("Main Grocery List", json);
        editor.apply();
    }
    private void loadGrocListData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Main Grocery List", null);
        Type type = new TypeToken<ArrayList<grocListItem>>(){}.getType();
        grocListList = gson.fromJson(json, type);

        if (grocListList == null){
            grocListList = new ArrayList<>();
        }
    }
}
