package com.example.manageyourfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Inventory extends AppCompatActivity implements inventoryListMainDialog.inventoryListMainListener{

    public static final String EXTRA_TEXT = "com.example.manageyourfood.EXTRA_TEXT";
    public static final String EXTRA_PIC = "com.example.manageyourfood.EXTRA_PIC";
    static final int REQUEST_CODE = 1;

    public RecyclerView invRecyclerView;
    public RecyclerView.Adapter invAdapter;
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

        invArray = new ArrayList<>();
        invArray.add(new inventoryItem("Test Inv", R.drawable.cupboard));
        invArray.add(new inventoryItem("TestYoasdfa", R.drawable.fridge));
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
