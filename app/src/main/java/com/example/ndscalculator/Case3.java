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

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Case3 extends AppCompatActivity {
    String[] dropDownWoods = new String[]{"Douglas Fir-Larch", "Douglas Fir-South",
            "Eastern Softwoods", "Eastern White Pine", "Hem-Fir", "Redwood",
            "Spruce-Pine-Fir", "Spruce-Pine-Fir (South)", "Western Woods"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);

        Log.i("log", "setting dropDown view");
        Spinner woodSelect = (Spinner) findViewById(R.id.woodSelect);

        //make our spinner/array adapter
        Log.i("log", "\nentering array adapter");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dropDownWoods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        woodSelect.setAdapter(adapter);
    }

    public void returnToCalc(View v) {
        Intent toCalc = new Intent(this, CalculatorLanding.class);
        startActivity(toCalc);
    }

    public void setSpinner2(View v) {

        Log.i("log", "\nEntered setSpinner2");
        Spinner woodSelect = (Spinner) findViewById(R.id.woodSelect);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

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

    public String[] getGradeResult(String woodType, String grade) {
        //most of this is copied from the getWoodType method, slight variation
        String[] returnArray = new String[9];
        String location = woodType.concat(".txt");
        Log.i("log", "file is: " + location + ", starting input stream");

        try {
            DataInputStream inputStream = null;
            //stack overflow
            try {
                inputStream = new DataInputStream(getAssets().open(String.format(location)));
                Log.i("log", "success making input stream");
            } catch (IOException e) {
                Log.i("log", "input stream failed");
                e.printStackTrace();
            }
            Scanner scan = new Scanner(inputStream);
            Log.i("log", "parsing file, looking for: " + grade);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineArr = line.split(",");
                String gradeAndSize = lineArr[0].concat(", ".concat(lineArr[1]));
                Log.i("log", "reviewed grade: " + gradeAndSize);
                //find the line they picked in the file that they selected
                if (grade.equals(gradeAndSize)) {
                    Log.i("log", "found entered value");
                    Log.i("log", "returning array");
                    return lineArr;
                }
            }
        } catch (Exception e) {
            Log.i("log", "failed to read file");
        }
        Log.i("log", "returning empty array, this is bad");
        return returnArray; //should be impossible
    }

    public void calculateCase3(View v) {
        Log.i("log", "Entered calculations");
        //make values for result and pull in all attributes from the xml
        float rVMax, mMax, deltaMaxCenter, vx, mx, deltax, e; //this is what we're calculating
        float w, l, x; //what we're using
        EditText wET = (EditText) findViewById(R.id.pET);
        EditText lET = (EditText) findViewById(R.id.lET);
        EditText xET = (EditText) findViewById(R.id.xET);
        TextView resultTV1 = (TextView) findViewById(R.id.result1TV);
        TextView resultTV2 = (TextView) findViewById(R.id.result2TV);
        Spinner woodSelect = (Spinner) findViewById(R.id.woodSelect);
        Spinner gradeSelect = (Spinner) findViewById(R.id.spinner2);
        Log.i("log", "pulled all views from activity");

        //clear views
        resultTV1.setText("");
        resultTV2.setText("");

        //get the values for calculations
        Log.i("log", "retrieving spinner result data");
        String woodSelection = woodSelect.getSelectedItem().toString();
        String gradeSelection = gradeSelect.getSelectedItem().toString();

        Log.i("log", "getting grade result");
        String[] selectedLine = getGradeResult(woodSelection, gradeSelection);

        Log.i("log", "getting e value");
        Log.i("log", "should get: " + selectedLine[7]);
        e = Float.parseFloat(selectedLine[7]);
        Log.i("log", "e value recieved was: " + e);

        //make sure they entered values
        try {
            Log.i("log", "getting w,l,x");
            w = Float.parseFloat(wET.getText().toString());
            l = Float.parseFloat(lET.getText().toString());

            Log.i("log", "starting initial calculations");
            rVMax = (w * l) / 2;
            mMax = ((float) Math.pow(l, 2) * w) / 8; //ask if wl^2 = (w*l)^2 or w*(l^2)
            Log.i("log", "rvmax, mmax complete");
            deltaMaxCenter = (5 * w * ((float) Math.pow(l, 4))) / (384 * e * l);

            //set outputs
            String output1 = "R=V(max): " + rVMax +
                    "\n\nM(max): " + mMax +
                    "\n\nDelta(max)\nat center:\n" + deltaMaxCenter;
            resultTV1.setText(output1);
        } catch (Exception except) {
            Toast.makeText(getApplicationContext(), "L and W are required", Toast.LENGTH_SHORT).show();
        }

        try {
            x = Float.parseFloat(xET.getText().toString());
            w = Float.parseFloat(wET.getText().toString());
            l = Float.parseFloat(lET.getText().toString());

            vx = w * ((l / 2) - x);
            mx = ((w * x) / 2) * (l - x);

            //splitting this nonsense up
            Log.i("log", "starting deltax");
            float deltaxpt1, deltaxpt2;
            deltaxpt1 = ((w * x) / (24 * e * l));
            deltaxpt2 = ((float) Math.pow(l, 3)) * (2 * l * (float) Math.pow(x, 2)) * ((float) Math.pow(x, 3));
            deltax = deltaxpt1 * deltaxpt2;
            Log.i("log", "deltax complete, got:" + deltax);
            String output2 = "V(x): " + vx +
                    "\n\nM(x): " + mx +
                    "\n\nDelta(x): " + deltax;
            resultTV2.setText(output2);

            if (x > l) {
                Toast.makeText(getApplicationContext(), "X should not be larger than L", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception except) {

        }
    }
}