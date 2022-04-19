package com.example.ndscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SeeTables extends AppCompatActivity {

    String[] dropDownWoods = new String[]{"Douglas Fir-Larch", "Douglas Fir-South",
            "Eastern Softwoods", "Eastern White Pine", "Hem-Fir", "Redwood",
            "Spruce-Pine-Fir", "Spruce-Pine-Fir (South)", "Western Woods"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_tables);

        Log.i("log", "About to initialize ads");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView madView = findViewById(R.id.seeTablesBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        madView.loadAd(adRequest);
        Log.i("log", "Ad initialized");

        Log.i("log", "setting dropDown view");
        Spinner woodSelect = (Spinner) findViewById(R.id.seeTablesSpinner);

        //make our spinner/array adapter
        Log.i("log", "\nentering array adapter for wood select");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dropDownWoods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        woodSelect.setAdapter(adapter);

    }

    public void returnToMain(View v) {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }

    public void setImage(View v) {
        Spinner caseSelect = (Spinner) findViewById(R.id.seeTablesSpinner);
        ImageView tables = findViewById(R.id.seeTableIImageView);
        try {
            switch (caseSelect.getSelectedItem().toString()) {
                case ("Douglas Fir-Larch"):
                    tables.setImageResource(R.drawable.douglas_fir_larch);
                    break;
                case ("Douglas Fir-South"):
                    tables.setImageResource(R.drawable.douglas_fir_south);
                    break;
                case ("Eastern Softwoods"):
                    tables.setImageResource(R.drawable.eastern_softwoods);
                    break;
                case ("Eastern White Pine"):
                    tables.setImageResource(R.drawable.eastern_white_pine);
                    break;
                case ("Hem-Fir"):
                    tables.setImageResource(R.drawable.hem_fir);
                    break;
                case ("Redwood"):
                    tables.setImageResource(R.drawable.redwood);
                    break;
                case ("Spruce-Pine-Fir"):
                    tables.setImageResource(R.drawable.spruce_pine_fir);
                    break;
                case ("Spruce-Pine-Fir (South)"):
                    tables.setImageResource(R.drawable.spruce_pine_fir__south_);
                    break;
                case ("Western Woods"):
                    tables.setImageResource(R.drawable.western_woods);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("log", "Could not load image");
        }
    }
}

