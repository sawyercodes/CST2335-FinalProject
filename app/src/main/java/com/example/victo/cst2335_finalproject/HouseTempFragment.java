package com.example.victo.cst2335_finalproject;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class HouseTempFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.house_temp_fragment, container, false);

        houseTempList = (ListView) view.findViewById(R.id.house_temp_listview);
        numberPicker = (NumberPicker) view.findViewById(R.id.house_temp_number_picker);
        tempScheduleButton = (Button) view.findViewById(R.id.edit_temp_schdule);
        deleteScheduleButton = (Button) view.findViewById(R.id.delete_temp_schdule);

        tempSchedule = new ArrayList<>();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.house_temp_textview, tempSchedule);
        adapter = new ArrayAdapter<String>(view.getContext(), R.layout.house_temp_textview, tempSchedule);
        houseTempList.setAdapter(adapter);

        TempScheduleDB tempScheduleDB = new TempScheduleDB(view.getContext());
        tempDB = tempScheduleDB.getWritableDatabase();
        //final ContentValues cValues = new ContentValues();
        Cursor cursor = tempDB.query(tempScheduleDB.TABLE_NAME, new String[]{tempScheduleDB.KEY_ID, tempScheduleDB.KEY_SCHEDULE}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String schedule = cursor.getString(cursor.getColumnIndex(tempScheduleDB.KEY_SCHEDULE));
                tempSchedule.add(schedule);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        numberPicker.setMaxValue(10);
        numberPicker.setMaxValue(30);
//        numberPicker.setWrapSelectorWheel(false);
//        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        tempScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateEditDialog();
            }
        });

        deleteScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateDeleteDialog();
            }
        });


        return view;
    }

    public Dialog onCreateEditDialog() {
        final String degree = Character.toString((char) 0x00B0);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_layout, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setTitle(R.string.edit_schedule_title)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        name = (EditText) view.findViewById(R.id.dialog_name_edittext);
                        temp = (EditText) view.findViewById(R.id.dialog_temp_edittext);
                        time = (EditText) view.findViewById(R.id.dialog_time_edittext);
                        String str = name.getText().toString() + ": " + temp.getText().toString() + degree + " @ " + time.getText().toString();
                        tempSchedule.add(str);
                        adapter.notifyDataSetChanged();
                        cValues.put("schedule",str);
                        tempDB.insert(TempScheduleDB.TABLE_NAME, null, cValues);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
        return builder.create();
    }

    public Dialog onCreateDeleteDialog() {
        final String degree = Character.toString((char) 0x00B0);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_delete_layout, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setTitle(R.string.delete_schedule_title)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        deleteName = (EditText) view.findViewById(R.id.dialog_name_delete_edittext);
                        String str = deleteName.getText().toString();
                        Iterator iter = tempSchedule.iterator();
                        Object scheduleItem;
                        while (iter.hasNext()) {
                            scheduleItem = iter.next();
                            if (scheduleItem.toString().contains(str)) {
                                tempSchedule.remove(scheduleItem);
                                adapter.notifyDataSetChanged();
                                cValues.remove(scheduleItem.toString());
                                tempDB.insert(TempScheduleDB.TABLE_NAME, null, cValues);
                            }
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
        return builder.create();
    }

    public void onDestroy() {
        super.onDestroy();
        tempDB.close();
    }

    private View view;
    private NumberPicker numberPicker;
    private Button tempScheduleButton;
    private Button deleteScheduleButton;
    private EditText name;
    private EditText deleteName;
    private EditText temp;
    private EditText time;
    private ListView houseTempList;
    ArrayAdapter<String> adapter;
    private ArrayList<String> tempSchedule;
    private static SQLiteDatabase tempDB;
    private final ContentValues cValues = new ContentValues();
}
