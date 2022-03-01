package com.example.ndscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void nav(View v) {
        Button calc = findViewById(R.id.calcBTN);
        Button equations = findViewById(R.id.seeEquationsBTN);
        Button tables = findViewById(R.id.seeTablesBTN);

        Intent toCalc = new Intent(this, CalculatorLanding.class);
        Intent toEquations = new Intent(this, SeeEquations.class);
        Intent toTables = new Intent(this, SeeTables.class);
        switch (v.getId()) {
            case (R.id.calcBTN):
                startActivity(toCalc);
            case (R.id.seeEquationsBTN):
                startActivity(toEquations);
            case (R.id.seeTablesBTN):
                startActivity(toTables);
        }
    }

    //this method is for the calculator landing page.
    //the landing page has this same code but the xml can't read it from there.
    public void enterCases(View v) {
        Log.i("log", "Entered switch case for cases");
        switch (v.getId()) {
            case (R.id.case1BTN):
                Intent case1 = new Intent(this, Case1.class);
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
            /*default:
                Intent custom1 = new Intent(this, CaseCustom.class);
                startActivity(custom1);
                break;
*/
        }
    }


    public void returnToCalc(View v)
    {
        Intent toCalc = new Intent(this, CalculatorLanding.class);
        startActivity(toCalc);
    }

    public void returnToHome(View v)
    {

    }
}