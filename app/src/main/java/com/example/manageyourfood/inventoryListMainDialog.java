package com.example.manageyourfood;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class inventoryListMainDialog extends AppCompatDialogFragment {
    private EditText addInvName;
    private inventoryListMainListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.inventory_list_main_dialog, null);

        builder.setView(view).setTitle("Create New Inventory").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String invName = addInvName.getText().toString();
                int invPic = R.drawable.cupboard;
                listener.applyInventoryDetails(invName, invPic);
            }
        });
        addInvName = view.findViewById(R.id.invName);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (inventoryListMainListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement inventoryListMainListener");
        }
    }

    public interface inventoryListMainListener{
        void applyInventoryDetails(String name, int pic);
    }
}
