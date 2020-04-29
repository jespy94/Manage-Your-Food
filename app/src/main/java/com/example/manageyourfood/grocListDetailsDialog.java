package com.example.manageyourfood;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class grocListDetailsDialog extends AppCompatDialogFragment {

    private EditText newFoodItemName;
    private EditText newFoodItemQuant;
    private grocListDetailsListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (grocListDetailsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement grocListDetailsListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.groc_list_details_dialog, null);
        //Checks if inputs are valid and sends info to details
        builder.setView(view).setTitle("Add new item.").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String foodName = newFoodItemName.getText().toString();
                int foodQuant = Integer.parseInt(newFoodItemQuant.getText().toString());
                if(foodName == null || foodName == ""){
                    Toast.makeText(getContext(), "Put in valid item name", Toast.LENGTH_LONG).show();
                }
                else if(foodQuant == 0){
                    Toast.makeText(getContext(), "Put in valid quantity. At least 1", Toast.LENGTH_LONG).show();
                }
                else {
                    listener.applyNewFoodDetails(foodName, foodQuant);
                }
            }
        });

        newFoodItemName = view.findViewById(R.id.newFoodItemName);
        newFoodItemQuant = view.findViewById(R.id.newFoodItemQuant);

        return builder.create();
    }
    public interface grocListDetailsListener{
        void applyNewFoodDetails(String name, int num);
    }
}
