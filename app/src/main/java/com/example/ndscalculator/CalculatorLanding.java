package com.example.ndscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CalculatorLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_landing_page);
    }

    public void enterCases(View v)
    {
        switch(v.getId())
        {
            case (R.id.case1BTN):
                Intent case1 = new Intent(this, Case1.class);
                startActivity(case1);
            case (R.id.case2BTN):
                Intent case2 = new Intent(this, Case2.class);
                startActivity(case2);
            case (R.id.case3BTN):
                Intent case3 = new Intent(this, Case3.class);
                startActivity(case3);
            case (R.id.case4BTN):
                Intent case4 = new Intent(this, Case4.class);
                startActivity(case4);
            case (R.id.case5BTN):
                Intent case5 = new Intent(this, Case5.class);
                startActivity(case5);
            case (R.id.case6BTN):
                Intent case6 = new Intent(this, Case6.class);
                startActivity(case6);
            case (R.id.case7BTN):
                Intent case7 = new Intent(this, Case7.class);
                startActivity(case7);
            case (R.id.case8BTN):
                Intent case8 = new Intent(this, Case8.class);
                startActivity(case8);
            case (R.id.case9BTN):
                Intent case9 = new Intent(this, Case9.class);
                startActivity(case9);
            case (R.id.case10BTN):
                Intent case10 = new Intent(this, Case10.class);
                startActivity(case10);
            case (R.id.case11BTN):
                Intent case11 = new Intent(this, Case10.class);
                startActivity(case11);
            case (R.id.case12BTN):
                Intent case12 = new Intent(this, Case10.class);
                startActivity(case12);
            case (R.id.case13BTN):
                Intent case13 = new Intent(this, Case10.class);
                startActivity(case13);
            case (R.id.case14BTN):
                Intent case14 = new Intent(this, Case10.class);
                startActivity(case14);
            case (R.id.case15BTN):
                Intent case15 = new Intent(this, Case10.class);
                startActivity(case15);
            case (R.id.customBTN):
                Intent custom = new Intent(this, Case10.class);
                startActivity(custom);

        }
    }
}