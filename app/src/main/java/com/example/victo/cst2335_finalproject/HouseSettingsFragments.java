package com.example.victo.cst2335_finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class HouseSettingsFragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_settings_fragments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentName = getIntent().getStringExtra("fragment name");

        if (fragmentName.equals("Garage")) {
            garageFragment = new GarageFragment();
            getSupportFragmentManager().beginTransaction().show(garageFragment).commit();
        }

    }

    private String fragmentName;
    private GarageFragment garageFragment;

}
