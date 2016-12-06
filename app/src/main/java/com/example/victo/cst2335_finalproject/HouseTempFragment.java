package com.example.victo.cst2335_finalproject;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HouseTempFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_house_temp_fragment, container, false);

        houseTempList = (ListView) view.findViewById(R.id.house_temp_listview);
        String[] tempSchedule = {""};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.house_temp_textview, tempSchedule);
        houseTempList.setAdapter(adapter);

        return view;
    }

    private ListView houseTempList;

}
