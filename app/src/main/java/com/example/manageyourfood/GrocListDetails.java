package com.example.manageyourfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    public void getGrocList(){
        Intent intent = getIntent();
        grocListName = findViewById(R.id.grocListDetailsTitle);
        currentGList = intent.getParcelableExtra(EXTRA_GROCLIST);
        gDLArray = currentGList.getFoodList();
        grocListName.setText(currentGList.getGrocListName());
    }

    public void openAddFoodItemDialog(){
        grocListDetailsDialog newItemDialog = new grocListDetailsDialog();
        newItemDialog.show(getSupportFragmentManager(), "Add a New Item.");
    }

    public void buildGrocListDetailsRecyclerView(){
        grocListDetailsRecyclerView = findViewById(R.id.grocListMainRecyclerView);
        grocListDetailsRecyclerView.setHasFixedSize(true);
        gLDLayoutManager = new LinearLayoutManager(this);
        gLDAdapter = new grocListDetailsAdapter(gDLArray);

        grocListDetailsRecyclerView.setLayoutManager(gLDLayoutManager);
        grocListDetailsRecyclerView.setAdapter(gLDAdapter);
    }

    @Override
    public void applyNewFoodDetails (String name, int num) {
        //currentGList.addFoodToList(new foodItem(name, num));
        gDLArray.add(new foodItem(name, num));
        gLDAdapter.notifyDataSetChanged();
        saveCurrentGrocListData();
    }
    private void saveCurrentGrocListData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(gDLArray);
        editor.putString("Current Grocery List", json);
        editor.apply();
    }
    private void loadCurrentGrocListData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Current Grocery List", null);
        Type type = new TypeToken<ArrayList<foodItem>>(){}.getType();
        gDLArray = gson.fromJson(json, type);

       // if (gDLArray == null){
         //    gDLArray = new ArrayList<>();
       // }
    }
}
