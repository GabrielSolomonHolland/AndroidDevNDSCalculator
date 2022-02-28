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
            case (R.id.case1):
                Intent case1 = new Intent(Case1.class);
        }
    }
}