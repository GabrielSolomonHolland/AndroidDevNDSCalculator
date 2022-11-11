package com.NDSCalculator.ndscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Case9 extends AppCompatActivity {

    String[] dropDownWoods = new String[]{"Douglas Fir-Larch","Douglas Fir-South",
            "Eastern Softwoods", "Eastern White Pine", "Hem-Fir", "Redwood",
            "Spruce-Pine-Fir", "Spruce-Pine-Fir (South)", "Western Woods"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case9);

        Log.i("log","setting dropDown view");
        Spinner woodSelect = (Spinner)findViewById(R.id.c9woodSelect);

        //make our spinner/array adapter
        Log.i("log", "\nentering array adapter for wood select");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dropDownWoods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        woodSelect.setAdapter(adapter);


        //setting the deltaMax spinner adapter/content
        Log.i("log", "entering array adapter for deltaMax");

        //pull in spinner and make data for entries
        Spinner deltaMaxSpinner = (Spinner)findViewById(R.id.c9deltaSpinner);
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
        Spinner woodSelect = (Spinner)findViewById(R.id.c9woodSelect);
        Spinner spinner2 = (Spinner)findViewById(R.id.c9spinner);

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
        float r1, r2, mmax, mx, mx1, i_req1, i_req2, i_req3, i_req4; //this is what we're calculating
        float p,l,x, x1, deltaMax, e, a; //what we're using
        EditText pET =(EditText)findViewById(R.id.c9PValue);
        EditText lET =(EditText)findViewById(R.id.c9LValue);
        EditText xET =(EditText)findViewById(R.id.c9xValue);
        EditText aET =(EditText)findViewById(R.id.c9AValue);
        EditText x1ET=(EditText)findViewById(R.id.c9x1Value);
        //EditText deltaMaxET = (EditText)findViewById((R.id.deltaMaxET));
        TextView resultTV1 = (TextView)findViewById(R.id.c9results1);
        TextView resultTV2 = (TextView)findViewById(R.id.c9results2);
        Spinner woodSelect = (Spinner)findViewById(R.id.c9woodSelect);
        Spinner gradeSelect = (Spinner)findViewById(R.id.c9spinner);
        Spinner deltaMaxSpinner = (Spinner)findViewById(R.id.c9deltaSpinner);
        Switch bsSwitch = (Switch)findViewById(R.id.c9bsSwitch);
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
                p = Float.parseFloat(pET.getText().toString());
                l = Float.parseFloat(lET.getText().toString());
                a = Float.parseFloat(aET.getText().toString());

                Log.i("log","starting initial calculations");
                r1 = (p*a)/l;
                r2 = p/l*(l+a);
                mmax = p*a;

                //set outputs
                String output1 = "  R1=V1: " + r1 +
                        "\n  R2=V1+V2: " + r2 +
                        "\n  V2: " + p +
                        "\n  Mmax at R2: " + mmax;
                resultTV1.setText(output1);

                if(bsSwitch.isChecked())
                {
                    Toast.makeText(getApplicationContext(),"Yeah this was BS to program too, I feel you",Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception except)
            {
                Toast.makeText(getApplicationContext(), "Please fill out all values", Toast.LENGTH_SHORT).show();
            }

            try{
                //float r1, r2, mmax, mx, mx1, i_req1, i_req2, i_req3, i_req4; //this is what we're calculating
                x = Float.parseFloat(xET.getText().toString());
                x1 = Float.parseFloat(x1ET.getText().toString());
                p = Float.parseFloat(pET.getText().toString());
                l = Float.parseFloat(lET.getText().toString());
                a = Float.parseFloat(aET.getText().toString());

                //get deltaMax from spinner
                Log.i("log","getting deltaMax from spinner");
                deltaMax = l/(Float.parseFloat(deltaMaxSpinner.getSelectedItem().toString()));

                mx = (p*a*x)/l;
                mx1 = p*(a-x1);
                i_req1 = Float.parseFloat("0.06415") * ((p*a*l*l)/(e*deltaMax)); //dawg why
                i_req2 = ((p*a*a)/(e*3*deltaMax))*(l+a);
                i_req3 = ((p*a*x)/(6*e*deltaMax*l))*((l*l)-(x*x)); // this is actually deltax
                i_req4 = ((p*x1)/(6*e*deltaMax)) *
                        (
                        (2*a*l)
                        + (3*a*x1)
                        - (x1*x1)
                        ); //this is actually deltax1

                String output2 = "  Mx (Between supporst): " + mx +
                        "\n  Mx1 (For overhang): " + mx1 +
                        "\n  i Required (Between supports (x=1/sqrt3)): " + i_req1 +
                        "\n  i Required (For overhang (x1 = a): " + i_req2 +
                        "\n  deltax (between supports): " + i_req3 +
                        "\n  deltax1 (for overhang)" + i_req4;

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