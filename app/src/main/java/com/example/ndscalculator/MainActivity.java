package com.example.ndscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void nav(View v)
    {
        Button calc = findViewById(R.id.calcBTN);
        Button equations = findViewById(R.id.seeEquationsBTN);
        Button tables = findViewById(R.id.seeTablesBTN);

        Intent toCalc = new Intent(this, CalculatorLanding.class);
        Intent toEquations = new Intent(this, SeeEquations.class);
        Intent toTables = new Intent(this, SeeTables.class);
        switch (v.getId())
        {
            case (R.id.calcBTN):
                startActivity(toCalc);
            case (R.id.seeEquationsBTN):
                startActivity(toEquations);
            case (R.id.seeTablesBTN):
                startActivity(toTables);
        }
    }
}