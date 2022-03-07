package com.example.ndscalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;

public class Case1 extends AppCompatActivity {

    String[] dropDownWoods = new String[]{"Douglas Fir-Larch","Douglas Fir-South",
            "Eastern Softwoods", "Eastern White Pine", "Hem-Fir", "Redwood",
            "Spruce-Pine-Fir", "Spruce-Pine-Fir (South)", "Western Woods"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case1);

        Log.i("log","setting dropDown view");
        Spinner woodSelect = (Spinner)findViewById(R.id.woodSelect);

        //make our spinner/array adapter
        Log.i("log", "\nentering array adapter");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dropDownWoods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        woodSelect.setAdapter(adapter);


    }

    public void setSpinner2(View v)
    {

        Log.i("log", "\nEntered setSpinner2");
        Spinner woodSelect = (Spinner)findViewById(R.id.woodSelect);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);

        try {
            Log.i("log", "retrieved spinners, pulling selection from 1");
            String selection1 = woodSelect.getSelectedItem().toString(); //this is the problem line

            //bug fixing, looking for reason of failure
            if(selection1 == null)
            {
                Log.i("log","selected item returned null");
            }
            else if(selection1 == "")
            {
                Log.i("log","selected item returned empty string");
            }
            else
            {
                Log.i("log", "item is: " + selection1.getClass());
            }

            Log.i("log", "selection 1 retrieved, should be: " + selection1);
            String[] spinner2Options = getTypesFromWood(selection1);
            Log.i("log", "\nsuccess retrieving wood array");


            Log.i("log", "\nSuccess!!\n");
        }
        catch(Exception e)
        {
            Log.i("log","Failure");
        }


    }

    public String[] getTypesFromWood(String woodType) throws FileNotFoundException {
        //The goal of this monstrosity is to, from the file they selected in the first spinner,
        //to create a second spinner of the options from within that wood type.

        //The longest subtype is 14, taking the spinner option and concatenating .csv
        Log.i("log","making array and adding .csv");
        String[] retrieved = new String[14];

        Log.i("log","about to concat");
        String location = (woodType);
        location = location.concat(".txt");
        Log.i("log","our file/location should be: " + location);
        Log.i("log","starting file pull attempt");
        File input = new File(location);

        //this is going to be a stupid piece of code but java doesn't have easy resize for arrays
        //count actual length of array
        int actualLength = 0;
        for (int j = 0; j<retrieved.length;j++)
        {
            if(retrieved[j]!=null && retrieved[j]!="")
            {
                actualLength++;
            }
        }
        //resizing/copying the array
        String[] returnArray = new String[actualLength];
        for(int k = 0;k<=actualLength;k++)
        {
            returnArray[k] = retrieved[k];
        }

        return returnArray;

    }

}

/*        Log.i("log","entering while loop");
                while (scan.hasNextLine())
                {
                String line = scan.nextLine();
                String[] resultLine = line.split(",");
                returnArray[i] = resultLine[0];
                i++;
                }
                scan.close();
                Log.i("returnArray","Return = " + returnArray);
                return returnArray;*/
