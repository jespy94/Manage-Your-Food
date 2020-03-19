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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class grocListMainDialog extends AppCompatDialogFragment {

    private EditText grocListName;
    private grocListMainDialogListener gcListener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.groc_list_main_dialog, null);

        builder.setView(view).setTitle("Create a List").setNegativeButton(
                "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        ).setPositiveButton(
                "Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String gcName = grocListName.getText().toString();
                        gcListener.applyGrocListsInfo(gcName);
                    }
                }
        );

        grocListName = view.findViewById(R.id.grocListName);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            gcListener = (grocListMainDialogListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement grocListMainDialogListener");
        }
    }

    public interface grocListMainDialogListener{
        void applyGrocListsInfo(String name);
    }
}
