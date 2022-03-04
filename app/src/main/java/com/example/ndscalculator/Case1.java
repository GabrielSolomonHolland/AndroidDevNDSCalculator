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
        Log.i("log", "Entered setSpinner2");
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);

        Log.i("log","retrieved spinners, pulling selection from 1");
        String selection1 = spinner1.getSelectedItem().toString();
        String[] spinner2Options = getTypesFromWood(selection1);

        //pulling code from stack overflow
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        spinner2Options); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner2.setAdapter(spinnerArrayAdapter);
    }

    public String[] getTypesFromWood(String woodType)
    {
        //The goal of this monstrosity is to, from the file they selected in the first spinner,
        //to create a second spinner of the options from within that wood type.

        //The longest subtype is 14, taking the spinner option and concatenating .csv
        String[] returnArray = new String[14];
        String temp = "/CSVs/";
        temp = temp.concat(woodType);
        temp = temp.concat(".csv");
        Log.i("log","our file/location should be: " + temp);

        //try catch because I don't have faith in myself
        try
        {
            Log.i("log","starting file pull attempt");
            //pull in the file, make a scanner, make an iterable
            File input = new File(temp);
            Scanner scan = new Scanner(input);
            int i = 0;
            Log.i("log", "Pulled file B)");

            //go through the csv and pull the initial value, the essentially title of that line,
            //and yoink it into an array we can return. Those yoinked values will be the options for the second spinner
            //so we know which thing they want and can pull exact values in our equations
            while (scan.hasNextLine())
            {
                String line = scan.nextLine();
                String[] resultLine = line.split(",");
                returnArray[i] = resultLine[0];
                i++;
            }
            Log.i("returnArray","Return = " + returnArray);
            return returnArray;
        }
        catch (Exception e)
        {
            //this should be impossible because this will be dictated by a spinner
            Log.i("shit", "we broke it");
            String[] zoinks= new String[1];
            zoinks[0]="zoinks scoob";
            return zoinks;
        }

    }


   /* public void enterCases(View v) {
        Log.i("log", "Entered switch case for cases");
        switch (v.getId()) {
            case (R.id.case1BTN):
                Log.i("log","clicked case1");
                Intent case1 = new Intent(this, Case1.class);
                Log.i("log","entering intent");
                startActivity(case1);
                break;
            case (R.id.case2BTN):
                Intent case2 = new Intent(this, Case2.class);
                startActivity(case2);
                break;
            case (R.id.case3BTN):
                Intent case3 = new Intent(this, Case3.class);
                startActivity(case3);
                break;
            case (R.id.case4BTN):
                Intent case4 = new Intent(this, Case4.class);
                startActivity(case4);
                break;
            case (R.id.case5BTN):
                Intent case5 = new Intent(this, Case5.class);
                startActivity(case5);
                break;
            case (R.id.case6BTN):
                Intent case6 = new Intent(this, Case6.class);
                startActivity(case6);
                break;
            case (R.id.case7BTN):
                Intent case7 = new Intent(this, Case7.class);
                startActivity(case7);
                break;
            case (R.id.case8BTN):
                Intent case8 = new Intent(this, Case8.class);
                startActivity(case8);
                break;
            case (R.id.case9BTN):
                Intent case9 = new Intent(this, Case9.class);
                startActivity(case9);
                break;
            case (R.id.case10BTN):
                Intent case10 = new Intent(this, Case10.class);
                startActivity(case10);
                break;
            case (R.id.case11BTN):
                Intent case11 = new Intent(this, Case11.class);
                startActivity(case11);
                break;
            case (R.id.case12BTN):
                Intent case12 = new Intent(this, Case12.class);
                startActivity(case12);
                break;
            case (R.id.case13BTN):
                Intent case13 = new Intent(this, Case13.class);
                startActivity(case13);
                break;
            case (R.id.case14BTN):
                Intent case14 = new Intent(this, Case14.class);
                startActivity(case14);
                break;
            case (R.id.case15BTN):
                Intent case15 = new Intent(this, Case15.class);
                startActivity(case15);
                break;
            case (R.id.customBTN):
                Intent custom = new Intent(this, CaseCustom.class);
                startActivity(custom);
                break;
        }
    }*/

}