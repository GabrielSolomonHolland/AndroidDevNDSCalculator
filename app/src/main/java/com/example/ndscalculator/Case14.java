package com.example.ndscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Case14 extends AppCompatActivity {

    String[] dropDownWoods = new String[]{"Douglas Fir-Larch","Douglas Fir-South",
            "Eastern Softwoods", "Eastern White Pine", "Hem-Fir", "Redwood",
            "Spruce-Pine-Fir", "Spruce-Pine-Fir (South)", "Western Woods"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case14);

        Log.i("log","setting dropDown view");
        Spinner woodSelect = (Spinner)findViewById(R.id.c14woodSelect);

        //make our spinner/array adapter
        Log.i("log", "\nentering array adapter for wood select");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dropDownWoods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        woodSelect.setAdapter(adapter);


        //setting the deltaMax spinner adapter/content
        Log.i("log", "entering array adapter for deltaMax");

        //pull in spinner and make data for entries
        Spinner deltaMaxSpinner = (Spinner)findViewById(R.id.c14deltaSpinner);
        String[] deltaMaxOptions = new String[]{"240","180","360"};

        //make adapter
        ArrayAdapter<String> DeltaMaxAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, deltaMaxOptions);
        DeltaMaxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deltaMaxSpinner.setAdapter(DeltaMaxAdapter);
        Log.i("log","Finished adapter for deltaMax, options should be set");

    }
    public void returnToCalc(View v)
    {
        Intent toCalc = new Intent(this, CalculatorLanding.class);
        startActivity(toCalc);
    }

    public void setSpinner2(View v)
    {

        Log.i("log", "\nEntered setSpinner2");
        Spinner woodSelect = (Spinner)findViewById(R.id.c14woodSelect);
        Spinner spinner2 = (Spinner)findViewById(R.id.c14spinner);

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
        }
        catch(Exception e)
        {
            Log.i("log","Failure");
        }
    }

    public String[] getTypesFromWood(String woodType) throws FileNotFoundException {
        //The goal of this monstrosity is to, from the file they selected in the first spinner,
        //pull the headings from the first position of each line and return that to set as the
        // 2nd spinner options
        Log.i("log","we have entered getTypes method");

        //The longest subtype is 14, taking the spinner option and concatenating .txt
        String[] retrieved = new String[14];
        String location = woodType.concat(".txt");
        Log.i("log","file is: " + location + ", starting input stream");

        try {
            //in case catch fails
            DataInputStream inputStream = null;
            //stack overflow?
            try {
                inputStream = new DataInputStream(getAssets().open(String.format(location)));
                Log.i("log","success making input stream");
            } catch (IOException e) {
                Log.i("log","input stream failed");
                e.printStackTrace();
            }
            Scanner scan = new Scanner(inputStream);
            int counter = 0;
            while (scan.hasNextLine())
            {
                String line=scan.nextLine();
                String[] lineArr = line.split(",");

                String gradeAndSize = lineArr[0].concat(", ".concat(lineArr[1]));
                retrieved[counter]=gradeAndSize;
                counter++;
            }

        } catch (Exception e) {
            Log.i("log","failed to read file");
        }

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
        Log.i("log","Counted length = " + actualLength + ", about to resize array");
        //resizing/copying the array
        String[] returnArray = new String[actualLength-1];
        for(int k=1;k<actualLength;k++)
        {
            //copy all values except the first, the first is the same as the wood type
            returnArray[k-1] = retrieved[k];
        }
        Log.i("log","resized");

        Log.i("log","Returning the array");
        return returnArray;
    }

    public String[] getGradeResult(String woodType, String grade)
    {
        //most of this is copied from the getWoodType method, slight variation
        String[] returnArray = new String[9];
        String location = woodType.concat(".txt");
        Log.i("log","file is: " + location + ", starting input stream");

        try {
            DataInputStream inputStream = null;
            //stack overflow
            try {
                inputStream = new DataInputStream(getAssets().open(String.format(location)));
                Log.i("log","success making input stream");
            } catch (IOException e) {
                Log.i("log","input stream failed");
                e.printStackTrace();
            }
            Scanner scan = new Scanner(inputStream);
            Log.i("log","parsing file, looking for: " + grade);
            while (scan.hasNextLine())
            {
                String line=scan.nextLine();
                String[] lineArr = line.split(",");
                String gradeAndSize = lineArr[0].concat(", ".concat(lineArr[1]));
                Log.i("log","reviewed grade: " + gradeAndSize);
                //find the line they picked in the file that they selected
                if(grade.equals(gradeAndSize))
                {
                    Log.i("log","found entered value");
                    Log.i("log","returning array");
                    return lineArr;
                }
            }
        } catch (Exception e) {
            Log.i("log","failed to read file");
        }
        Log.i("log","returning empty array, this is bad");
        return returnArray; //should be impossible
    }

    public void calculateCase1(View v)
    {
        Log.i("log","Entered calculations");
        //make values for result and pull in all attributes from the xml
        float rVMax,mMax,vx,mx,deltax,i_required; //this is what we're calculating
        float w,l,x, deltaMax, e; //what we're using
        EditText wET =(EditText)findViewById(R.id.c14wValue);
        EditText lET =(EditText)findViewById(R.id.c14LValue);
        EditText xET =(EditText)findViewById(R.id.c14xValue);
        //EditText deltaMaxET = (EditText)findViewById((R.id.deltaMaxET));
        TextView resultTV1 = (TextView)findViewById(R.id.c14results1);
        TextView resultTV2 = (TextView)findViewById(R.id.c14results2);
        Spinner woodSelect = (Spinner)findViewById(R.id.c14woodSelect);
        Spinner gradeSelect = (Spinner)findViewById(R.id.c14spinner);
        Spinner deltaMaxSpinner = (Spinner)findViewById(R.id.c14deltaSpinner);
        Log.i("log","pulled all views from activity");

        //clear views
        resultTV1.setText("");
        resultTV2.setText("");

        try
        {
            //get the values for calculations
            Log.i("log","retrieving spinner result data");
            String woodSelection = woodSelect.getSelectedItem().toString();
            String gradeSelection = gradeSelect.getSelectedItem().toString();

            Log.i("log","getting grade result");
            String[] selectedLine = getGradeResult(woodSelection,gradeSelection);

            Log.i("log","getting e value");
            Log.i("log","should get: " + selectedLine[7]);
            e=Float.parseFloat(selectedLine[7]);
            Log.i("log","e value recieved was: " + e);

            //make sure they entered values
            try{
                //pull info from edit texts
                Log.i("log","getting w,l");
                w = Float.parseFloat(wET.getText().toString());
                l = Float.parseFloat(lET.getText().toString());

                //get deltaMax from spinner
                Log.i("log","getting deltaMax from spinner");
                deltaMax = l/(Float.parseFloat(deltaMaxSpinner.getSelectedItem().toString()));


                Log.i("log","starting initial calculations");
                rVMax = (w*l)/2;
                mMax = (l*l*w)/8; //l^2 * w
                Log.i("log","rvmax, mmax complete");
                i_required = (5*w*l*l*l*l)/(384*e*deltaMax);

                //set outputs
                String output1 = "R=V(max): " + rVMax +
                        "\nM(max): " + mMax +
                        "\ni required:\n" + i_required;
                resultTV1.setText(output1);
            }
            catch (Exception except)
            {
                Toast.makeText(getApplicationContext(), "L, W, and deltaMax are required", Toast.LENGTH_SHORT).show();
            }

            try{
                x = Float.parseFloat(xET.getText().toString());
                w = Float.parseFloat(wET.getText().toString());
                l = Float.parseFloat(lET.getText().toString());

                vx = w*((l/2)-x);
                mx = ((w*x)/2)*(l-x);

                Log.i("log","starting deltax");
                deltax = ((w*x)/(24*e*l))*(((float)Math.pow(l,3)) - (2*l*x*x) + ((float)Math.pow(x,3)));

                Log.i("log","deltax complete, got:" + deltax);
                String output2 = "V(x): " + vx +
                        "\nM(x): " + mx +
                        "\nDelta(x): " + deltax;
                resultTV2.setText(output2);

                if(x>l)
                {
                    Toast.makeText(getApplicationContext(), "X should not be larger than L", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception except)
            {

            }

        }
        catch(Exception E)
        {
            Toast.makeText(getApplicationContext(), "Grade must be selected", Toast.LENGTH_SHORT).show();
        }
    }
}