package com.example.manageyourfood;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class inventoryDetailsDialog extends AppCompatDialogFragment{

    private EditText newFoodName;
    private EditText newFoodPurchDate;
    private EditText newFoodExpDate;


    private inventoryDetailsDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.inventory_details_dialog, null);

        //When new food item is added, checks inputs to make sure they are valid
        builder.setView(view)
                .setTitle("Add new item")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            //Checks to make sure name and dates are valid and sends info to add item to details
            public void onClick(DialogInterface dialog, int which) {
                String foodExDate = newFoodExpDate.getText().toString();
                String foodPDate = newFoodPurchDate.getText().toString();
                String foodName = newFoodName.getText().toString();
                if (newFoodName.getText().toString() == null || newFoodName.getText().toString() == "") {
                    Toast.makeText(getContext(), "Enter a valid string", Toast.LENGTH_LONG).show();
                }
                if (foodPDate.length() != 10 || foodExDate.length() != 10) {
                    Toast.makeText(getContext(), "A date is not in MM/DD/YYYY format", Toast.LENGTH_LONG).show();
                }
                else if (foodPDate.charAt(2) != '/' || foodExDate.charAt(5) != '/') {
                    Toast.makeText(getContext(), "A date is not in MM/DD/YYYY format", Toast.LENGTH_LONG).show();
                }
                else if (Integer.parseInt(foodPDate.substring(0, 2), 10) > 12 || Integer.parseInt(foodPDate.substring(3, 5), 10) > 31 || Integer.parseInt(foodPDate.substring(6, 10), 10) < 2000) {
                    Toast.makeText(getContext(), "Purchase date is not valid", Toast.LENGTH_LONG).show();
                }
                else if (Integer.parseInt(foodExDate.substring(0, 2), 10) > 12 || Integer.parseInt(foodExDate.substring(3, 5), 10) > 31 || Integer.parseInt(foodExDate.substring(6, 10), 10) < 2000) {
                    Toast.makeText(getContext(), "Expiration date is not valid", Toast.LENGTH_LONG).show();
                }
                else if (Integer.parseInt(foodPDate.substring(6, 10), 10) < Integer.parseInt(foodExDate.substring(6, 10), 10)) {
                    listener.applyNewFood(foodName, foodPDate, foodExDate);
                }
                else if(Integer.parseInt(foodPDate.substring(6, 10), 10) == Integer.parseInt(foodExDate.substring(6, 10), 10)){
                    if (Integer.parseInt(foodPDate.substring(0, 2), 10) < Integer.parseInt(foodExDate.substring(0, 2), 10)){
                        listener.applyNewFood(foodName, foodPDate, foodExDate);
                    }
                    else if (Integer.parseInt(foodPDate.substring(0, 2), 10) == Integer.parseInt(foodExDate.substring(0, 2), 10)){
                        if (Integer.parseInt(foodPDate.substring(3, 5), 10) <= Integer.parseInt(foodExDate.substring(3, 5), 10)){
                            listener.applyNewFood(foodName, foodPDate, foodExDate);
                        }
                        else{
                            Toast.makeText(getContext(), "Purchase date is after Expiration date", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(getContext(), "Purchase date is after Expiration date", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Purchase date is after Expiration date", Toast.LENGTH_LONG).show();
                }
            }
        });
        newFoodName = view.findViewById(R.id.newInvFoodItemName);
        newFoodPurchDate = view.findViewById(R.id.newInvFoodItemPurchDate);
        newFoodExpDate = view.findViewById(R.id.newInvFoodItemExpDate);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (inventoryDetailsDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement inventoryDetailsDialogListener");
        }
    }

    public interface inventoryDetailsDialogListener{
        void applyNewFood(String name, String PDate, String ExDate);
    }
}
