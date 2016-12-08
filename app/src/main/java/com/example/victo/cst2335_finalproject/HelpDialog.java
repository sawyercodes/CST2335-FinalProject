package com.example.victo.cst2335_finalproject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by victo on 2016-12-07.
 */

public class HelpDialog extends Dialog {
    public HelpDialog(Context context) {
        super(context);
        this.context = context;
    }


    public static Dialog onCreateDialog(int version, int title, int message, Context context) {
        final String degree = Character.toString((char) 0x00B0);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Get the layout inflater
        // Pass null as the parent view because its going in the dialog layout
        String messageStr = String.format(context.getResources().getString(version),
                context.getResources().getString(message));
        builder.setMessage(messageStr).setTitle(context.getResources().getString(title));
                //.setView(view)
                // Add action buttons
                /*.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });*/
        builder.show();
        return builder.create();
    }

    private static Context context;
}
