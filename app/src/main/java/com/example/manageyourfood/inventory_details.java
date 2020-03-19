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

public class inventory_details extends AppCompatActivity implements inventoryDetailsDialog.inventoryDetailsDialogListener{

    public static final String EXTRA_INV = "com.example.manageyourfood.EXTRA_INV";
    private RecyclerView invDetailsRecyclerView;
    private inventoryDetailsAdapter invDetailsAdapter;
    private RecyclerView.LayoutManager invDetailsLayoutManager;

    private Button invNavButton;
    private Button grocListNavButton;
    private Button mealSuggestNavButton;
    private Button homeNavButton;
    private Button addNewFoodItemButton;

    private TextView invDetailsTitle;
    public inventoryItem currentInventory;
    private ArrayList<foodItem> currentInventoryArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);

        getInventory();
        loadCurrentInventoryData();
        buildInvDetailsRecyclerView();

        invNavButton = findViewById(R.id.InvNav);
        grocListNavButton = findViewById(R.id.GroceryListNav);
        mealSuggestNavButton = findViewById(R.id.mealSuggestNav);
        homeNavButton = findViewById(R.id.homeNav);
        addNewFoodItemButton = findViewById(R.id.addFoodToInvButton);

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
        addNewFoodItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodDialog();
            }
        });

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

    public void addFoodDialog (){
        inventoryDetailsDialog addDialog = new inventoryDetailsDialog();
        addDialog.show(getSupportFragmentManager(), "Add Food Dialog");
    }

    public void getInventory(){
        Intent receivingIntent = getIntent();
        currentInventory = receivingIntent.getParcelableExtra(EXTRA_INV);
        currentInventoryArray = currentInventory.getInvFoodList();
        invDetailsTitle = findViewById(R.id.invDetailsTitle);
        invDetailsTitle.setText(currentInventory.getInvName());
    }

    public void buildInvDetailsRecyclerView(){
        invDetailsRecyclerView = findViewById(R.id.inventoryDetailsRecyclerView);
        invDetailsRecyclerView.setHasFixedSize(true);
        invDetailsAdapter = new inventoryDetailsAdapter(currentInventoryArray);
        invDetailsLayoutManager = new LinearLayoutManager(this);
        invDetailsRecyclerView.setAdapter(invDetailsAdapter);
        invDetailsRecyclerView.setLayoutManager(invDetailsLayoutManager);
    }

    @Override
    public void applyNewFood(String name, String PDate, String ExDate) {
         //currentInventory.addFoodtoInvList(new foodItem(name, PDate, ExDate));
        currentInventoryArray.add(new foodItem(name, PDate, ExDate));
         invDetailsAdapter.notifyDataSetChanged();
         saveCurrentInventoryData();
    }
    private void saveCurrentInventoryData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(currentInventoryArray);
        editor.putString("Current Inventory List", json);
        editor.apply();
    }
    private void loadCurrentInventoryData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Current Inventory List", null);
        Type type = new TypeToken<ArrayList<foodItem>>(){}.getType();
        currentInventoryArray = gson.fromJson(json, type);

       // if (currentInventoryArray == null){
          //  currentInventoryArray = new ArrayList<>();
       // }
    }
}
