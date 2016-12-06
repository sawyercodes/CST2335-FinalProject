package com.example.victo.cst2335_finalproject;

import android.app.Fragment;
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
            setTitle(R.string.title_garage_fragment);
            getSupportFragmentManager().beginTransaction().hide(garageFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(houseTempFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(weatherFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.house_settings_fragments, garageFragment).commit();
        }
        if (fragmentName.equals("House Temperature")) {
            houseTempFragment = new HouseTempFragment();
            setTitle(R.string.title_house_temp_fragment);
            getSupportFragmentManager().beginTransaction().hide(houseTempFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(garageFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(weatherFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.house_settings_fragments, houseTempFragment).commit();
        }
        if (fragmentName.equals("Weather")) {
            weatherFragment = new WeatherFragment();
            setTitle(R.string.title_weather_fragment);
            getSupportFragmentManager().beginTransaction().hide(houseTempFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(garageFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(weatherFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.house_settings_fragments, houseTempFragment).commit();
        }

    }

    private String fragmentName;
    private GarageFragment garageFragment;
    private HouseTempFragment houseTempFragment;
    private WeatherFragment weatherFragment;

}
