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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class inventory_details extends AppCompatActivity implements inventoryDetailsDialog.inventoryDetailsDialogListener{

    public static final String EXTRA_INV = "com.example.manageyourfood.EXTRA_INV";
    public static final String EXTRA_INT = "com.example.manageyourfood.EXTRA_INT";

    private RecyclerView invDetailsRecyclerView;
    private inventoryDetailsAdapter invDetailsAdapter;
    private RecyclerView.LayoutManager invDetailsLayoutManager;

    private Button invNavButton;
    private Button grocListNavButton;
    private Button mealSuggestNavButton;
    private Button homeNavButton;
    private Button addNewFoodItemButton;

    private TextView invDetailsTitle;
    private inventoryItem currentInventory;
    private ArrayList<foodItem> currentInventoryArray;

    private String pos;

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
        pos = Integer.toString(receivingIntent.getIntExtra(EXTRA_INT, 0));
       // currentInventoryArray = currentInventory.getInvFoodList();
        invDetailsTitle = findViewById(R.id.invDetailsTitle);
        invDetailsTitle.setText(currentInventory.getInvName());
    }

    public void buildInvDetailsRecyclerView(){
        invDetailsRecyclerView = findViewById(R.id.inventoryDetailsRecyclerView);
        invDetailsRecyclerView.setHasFixedSize(true);
        invDetailsAdapter = new inventoryDetailsAdapter(currentInventory.getInvFoodList());
        invDetailsLayoutManager = new LinearLayoutManager(this);
        invDetailsRecyclerView.setAdapter(invDetailsAdapter);
        invDetailsRecyclerView.setLayoutManager(invDetailsLayoutManager);

        invDetailsAdapter.setOnInventoryDetailsClickListener(new inventoryDetailsAdapter.onInventoryDetailsClickListener() {
            @Override
            public void onInvDetailsItemClick(int position) {

            }

            @Override
            public void onDeleteClick(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(inventory_details.this);
                builder.setMessage("Do you want to delete this item?").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentInventory.removeFoodItem(position);
                        //currentInventoryArray.remove(position);
                        invDetailsAdapter.notifyDataSetChanged();
                        saveCurrentInventoryData();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public void applyNewFood(String name, String PDate, String ExDate) {
         currentInventory.addFoodtoInvList(new foodItem(name, PDate, ExDate));
         //currentInventoryArray.add(new foodItem(name, PDate, ExDate));
         invDetailsAdapter.notifyDataSetChanged();
         saveCurrentInventoryData();
    }
    private void saveCurrentInventoryData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory details" + pos, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(currentInventory.getInvFoodList());
        editor.putString("Current Inventory List", json);
        editor.apply();
    }
    private void loadCurrentInventoryData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory details" + pos, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Current Inventory List", null);
        Type type = new TypeToken<ArrayList<foodItem>>(){}.getType();
        currentInventoryArray = gson.fromJson(json, type);
        if (currentInventoryArray != null) {
            currentInventory.setInvFoodList(currentInventoryArray);
        }
      //if (currentInventoryArray == null){
      //    currentInventoryArray = new ArrayList<>();
      //}d
    }
}
