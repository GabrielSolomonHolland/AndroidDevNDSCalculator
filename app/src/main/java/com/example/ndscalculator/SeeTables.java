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

    String[] dropDownWoods = new String[]{"Douglas Fir-Larch","Douglas Fir-South",
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

/*
        //setting the deltaMax spinner adapter/content
        Log.i("log", "entering array adapter for deltaMax");

        //pull in spinner and make data for entries
        Spinner deltaMaxSpinner = (Spinner) findViewById(R.id.deltaMaxSpinner);
        String[] deltaMaxOptions = new String[]{"240", "180", "360"};

        //make adapter
        ArrayAdapter<String> DeltaMaxAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, deltaMaxOptions);
        DeltaMaxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deltaMaxSpinner.setAdapter(DeltaMaxAdapter);
        Log.i("log", "Finished adapter for deltaMax, options should be set");
*/
    }

    public void returnToMain(View v) {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }

    public void showTable(View v){
        Button showTable = findViewById(R.id.seeTablesShowTableBTN);



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

    public void setTableSpinner2(View v) {

        Log.i("log", "\nEntered setSpinner2");
        Spinner woodSelect = (Spinner) findViewById(R.id.woodSelect);
        Spinner spinner2 = (Spinner) findViewById(R.id.seeTablesSpinner);

        try {
            //Log.i("log", "retrieved spinners, pulling selection from 1");
            String selection1 = woodSelect.getSelectedItem().toString();
            Log.i("log", "selection 1 retrieved, should be: " + selection1);
            String[] spinner2Options = getTypesFromWood(selection1);
            Log.i("log", "success retrieving wood array");
            //Log.i("log","first and last value: " + spinner2Options[0] + spinner2Options[spinner2Options.length]);

            //setspinner here
            Log.i("log", "entering array adapter #2");
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, spinner2Options);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);

            Log.i("log", "success?!");
        } catch (Exception e) {
            Log.i("log", "Failure");
        }
    }

    public String[] getTypesFromWood(String woodType) throws FileNotFoundException {
        //The goal of this monstrosity is to, from the file they selected in the first spinner,
        //pull the headings from the first position of each line and return that to set as the
        // 2nd spinner options
        Log.i("log", "we have entered getTypes method");

        //The longest subtype is 14, taking the spinner option and concatenating .txt
        String[] retrieved = new String[14];
        String location = woodType.concat(".txt");
        Log.i("log", "file is: " + location + ", starting input stream");

        try {
            //in case catch fails
            DataInputStream inputStream = null;
            //stack overflow?
            try {
                inputStream = new DataInputStream(getAssets().open(String.format(location)));
                Log.i("log", "success making input stream");
            } catch (IOException e) {
                Log.i("log", "input stream failed");
                e.printStackTrace();
            }
            Scanner scan = new Scanner(inputStream);
            int counter = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineArr = line.split(",");

                String gradeAndSize = lineArr[0].concat(", ".concat(lineArr[1]));
                retrieved[counter] = gradeAndSize;
                counter++;
            }

        } catch (Exception e) {
            Log.i("log", "failed to read file");
        }

        //this is going to be a stupid piece of code but java doesn't have easy resize for arrays
        //count actual length of array
        int actualLength = 0;
        for (int j = 0; j < retrieved.length; j++) {
            if (retrieved[j] != null && retrieved[j] != "") {
                actualLength++;
            }
        }
        Log.i("log", "Counted length = " + actualLength + ", about to resize array");
        //resizing/copying the array
        String[] returnArray = new String[actualLength - 1];
        for (int k = 1; k < actualLength; k++) {
            //copy all values except the first, the first is the same as the wood type
            returnArray[k - 1] = retrieved[k];
        }
        Log.i("log", "resized");

        Log.i("log", "Returning the array");
        return returnArray;
    }

}

