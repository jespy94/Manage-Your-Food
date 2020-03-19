package com.example.manageyourfood;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class inventoryDetailsDialog extends AppCompatDialogFragment {

    private EditText newFoodName;
    private EditText newFoodPurchDate;
    private EditText newFooodExpDate;

    private inventoryDetailsDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.inventory_details_dialog, null);

        builder.setView(view).setTitle("Add new item").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String foodName = newFoodName.getText().toString();
                String foodPDate = newFoodPurchDate.getText().toString();
                String foodExDate = newFooodExpDate.getText().toString();
                listener.applyNewFood(foodName, foodPDate, foodExDate);
            }
        });

        newFoodName = view.findViewById(R.id.newInvFoodItemName);
        newFoodPurchDate = view.findViewById(R.id.newInvFoodItemPurchDate);
        newFooodExpDate = view.findViewById(R.id.newInvFoodItemExpDate);

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
