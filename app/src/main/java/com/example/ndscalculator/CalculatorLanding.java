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

    /*
    hear me out

    we are going to need to use intents like there is no tomorrow for this page

    switch case of buttons? I feel like a switch case for all of the things would be really helpful and efficient
    it seems like the code on this page will be a complete nightmare but I think we've got it
     */

    public void enterCases(View v)
    {
        switch(v.getId())
        {
            case (R.id.case1BTN):
                Intent case1 = new Intent(Case1.class);
                startActivity(case1);
            case (R.id.case2BTN):
                Intent case2 = new Intent(Case2.class);
                startActivity(case2);
            case (R.id.case3BTN):
                Intent case3 = new Intent(Case3.class);
                startActivity(case3);
            case (R.id.case4BTN):
                Intent case4 = new Intent(Case4.class);
                startActivity(case4);
            case (R.id.case5BTN):
                Intent case5 = new Intent(Case5.class);
                startActivity(case5);
                /*
            case (R.id.case6BTN):
                Intent case6 = new Intent(Case6.class);
                startActivity(case6);
            case (R.id.case7BTN):
                Intent case7 = new Intent(Case7.class);
                startActivity(case7);
            case (R.id.case8BTN):
                Intent case8 = new Intent(Case8.class);
                startActivity(case8);
            case (R.id.case9BTN):
                Intent case9 = new Intent(Case9.class);
                startActivity(case9);
            case (R.id.case10BTN):
                Intent case10 = new Intent(Case10.class);
                startActivity(case10);
                 */
        }
    }
}