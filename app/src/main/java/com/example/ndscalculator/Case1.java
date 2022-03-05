package com.example.ndscalculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;

public class Case1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case1);

        Log.i("log","oncreate success");

    }

    public void setSpinner2(View v)
    {
        Log.i("log", "\nEntered setSpinner2");
        Spinner woodSelect = (Spinner)findViewById(R.id.woodSelect);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);

        try {
            Log.i("log", "retrieved spinners, pulling selection from 1");
            String selection1 = woodSelect.getSelectedItem().toString(); //this is the problem line
            Log.i("log", "selection 1 retrieved, using getTypes");
            String[] spinner2Options = getTypesFromWood(selection1);
            Log.i("log", "\nsuccess retrieving wood array");

            //pulling code from stack overflow
            Log.i("log", "\nentering array adapter");
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item,
                            spinner2Options); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                    .simple_spinner_dropdown_item);
            spinner2.setAdapter(spinnerArrayAdapter);
            Log.i("success", "\nSuccess!!\n");
        }
        catch(Exception e)
        {
            Log.i("oof","youch");
        }
    }

    public String[] getTypesFromWood(String woodType)
    {
        //The goal of this monstrosity is to, from the file they selected in the first spinner,
        //to create a second spinner of the options from within that wood type.

        //The longest subtype is 14, taking the spinner option and concatenating .csv
        Log.i("log","making array and adding .csv");
        String[] returnArray = new String[14];
        String location = "/CSVs/";
        Log.i("log","about to concat");
        location = location.concat(woodType);
        location = location.concat(".csv");
        Log.i("log","our file/location should be: " + location);

        //try catch because I don't have faith in myself

            Log.i("log","starting file pull attempt");
            //pull in the file, make a scanner, make an iterable
            File input = new File(location);
            //Scanner scan = new Scanner(input);
            int i = 0;
            Log.i("log", "Pulled file B)");

            //go through the csv and pull the initial value, the essentially title of that line,
            //and yoink it into an array we can return. Those yoinked values will be the options for the second spinner
            //so we know which thing they want and can pull exact values in our equations
            /*while (scan.hasNextLine())
            {
                String line = scan.nextLine();
                String[] resultLine = line.split(",");
                returnArray[i] = resultLine[0];
                i++;
            }*/
            Log.i("returnArray","Return = " + returnArray);
            return returnArray;



        //this should be impossible because this will be dictated by a spinner
        //Log.i("shit", "we broke it");
        //String[] zoinks= new String[1];
        //zoinks[0]="zoinks scoob";
        //return zoinks;


    }

}