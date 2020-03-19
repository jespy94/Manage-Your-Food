package com.example.manageyourfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Inventory extends AppCompatActivity implements inventoryListMainDialog.inventoryListMainListener{

    public static final String EXTRA_TEXT = "com.example.manageyourfood.EXTRA_TEXT";
    public static final String EXTRA_PIC = "com.example.manageyourfood.EXTRA_PIC";
    public static final String EXTRA_INV = "com.example.manageyourfood.EXTRA_INV";
    static final int REQUEST_CODE = 1;

    public RecyclerView invRecyclerView;
    public inventoryAdapter invAdapter;
    public RecyclerView.LayoutManager invLayoutManager;

    private Button invNavButton;
    private Button grocListNavButton;
    private Button mealSuggestNavButton;
    private Button homeNavButton;
    private Button addInvButton;
    private Button deleteInvButton;
    public ArrayList<inventoryItem> invArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        loadInvListData();

        buildInvRecyclerView();

        invNavButton = findViewById(R.id.InvNav);
        grocListNavButton = findViewById(R.id.GroceryListNav);
        mealSuggestNavButton = findViewById(R.id.mealSuggestNav);
        homeNavButton = findViewById(R.id.homeNav);
        addInvButton = findViewById(R.id.addInvButton);
        deleteInvButton = findViewById(R.id.deleteInvButton);


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
        addInvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewInvDialog();
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

    //public void navNewInv (){
       // Intent newInvIntent = new Intent(this, InventoryAdd.class);
       // startActivityForResult(newInvIntent, REQUEST_CODE);
   // }
    public void buildInvRecyclerView(){
        invRecyclerView = findViewById(R.id.invRecyclerView);
        invRecyclerView.setHasFixedSize(true);
        invLayoutManager = new LinearLayoutManager(this);
        invAdapter = new inventoryAdapter(invArray);
        invRecyclerView.setLayoutManager(invLayoutManager);
        invRecyclerView.setAdapter(invAdapter);

        invAdapter.setOnInvItemClickListener(new inventoryAdapter.OnInvItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent navIntent = new Intent(Inventory.this, inventory_details.class);
                navIntent.putExtra(EXTRA_INV, invArray.get(position));
                startActivity(navIntent);
            }
        });
    }
    public void openNewInvDialog(){
        inventoryListMainDialog invListDialog = new inventoryListMainDialog();
        invListDialog.show(getSupportFragmentManager(), "New Inventory Dialog");
    }

    @Override
    public void applyInventoryDetails(String name, int pic) {
        inventoryItem item = new inventoryItem(name, R.drawable.cupboard);
        invArray.add(item);
        invAdapter.notifyDataSetChanged();
        saveInvListData();
    }
    private void saveInvListData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(invArray);
        editor.putString("Main Inventory List", json);
        editor.apply();
    }
    private void loadInvListData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences inventory", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Main Inventory List", null);
        Type type = new TypeToken<ArrayList<inventoryItem>>(){}.getType();
        invArray = gson.fromJson(json, type);

        if (invArray == null){
            invArray = new ArrayList<>();
        }
    }
    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, Intent data){
    //    super.onActivityResult(requestCode, resultCode, data);
    //    if(requestCode == REQUEST_CODE){
     //       if(resultCode == RESULT_OK){
     //           inventoryItem item = new inventoryItem(data.getStringExtra(EXTRA_TEXT), data.getIntExtra(EXTRA_PIC, R.drawable.fridge));
      //          invArray.add(item);
      //          invAdapter.notifyDataSetChanged();
      //          Toast.makeText(getApplicationContext(), "item added", Toast.LENGTH_LONG).show();
       //     }
      //  }
      //  else {
       //     Toast.makeText(getApplicationContext(),"did not work", Toast.LENGTH_LONG).show();
       // }

    //}

}
