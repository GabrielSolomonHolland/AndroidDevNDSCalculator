package com.example.ndscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class SeeEquations extends AppCompatActivity {
    String[] dropDownCase = new String[]{
            "Simple Beam: uniformly distributed load",
            "Simple Beam: concentrated load at center",
            "Simple Beam: concentrated load at any point",
            "Simple Beam: two equal concentrated load symmetrically placed",
            "Cantilever Beam: uniformly distributed load",
            "Cantilever beam: concentrated load at free end",
            "Cantilever Beam: concentrated load at any point",
            "Beam Overhanging One Support: uniformly distributed load",
            "Beam Overhanging One Support: concentrated load at end of overhang",
            "Continuous Beam with Equal Overhangs: uniformly distributed load",
            "Continuous Beam with Equal Overhangs: equal concentrated loads at ends of overhang",
            "Continuous Beam of Two Equal Spans: equal concentrated loads at the center of each span",
            "Continuous Beam of Two Equal Spans: uniformly distributed load",
            "Continuous Beam of Three Equal Spans: uniformly distributed load",
            "Continuous Beam of Four Equal Spans: uniformly distributed load"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_equations);

        Spinner caseSelect = (Spinner)findViewById(R.id.EquationsSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dropDownCase);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        caseSelect.setAdapter(adapter);
    }

    public void returnHome(View v)
    {
        Intent toHome = new Intent(this, MainActivity.class);
        startActivity(toHome);
    }

    public void setImage(View v){
        Spinner caseSelect = (Spinner)findViewById(R.id.EquationsSpinner);
        ImageView equations = findViewById(R.id.equationsImage);
        try {
            switch (caseSelect.getSelectedItem().toString()) {
                case ("Simple Beam: uniformly distributed load"):
                    equations.setImageResource(R.drawable.case1);
                    break;
                case ("Simple Beam: concentrated load at center"):
                    equations.setImageResource(R.drawable.case2);
                    break;
                case ("Simple Beam: concentrated load at any point"):
                    equations.setImageResource(R.drawable.case3);
                    break;
                case ("Simple Beam: two equal concentrated load symmetrically placed"):
                    equations.setImageResource(R.drawable.case4);
                    break;
                case ("Cantilever Beam: uniformly distributed load"):
                    equations.setImageResource(R.drawable.case5);
                    break;
                case ("Cantilever beam: concentrated load at free end"):
                    equations.setImageResource(R.drawable.case6);
                    break;
                case ("Cantilever Beam: concentrated load at any point"):
                    equations.setImageResource(R.drawable.case7);
                    break;
                case ("Beam Overhanging One Support: uniformly distributed load"):
                    equations.setImageResource(R.drawable.case8);
                    break;
                case ("Beam Overhanging One Support: concentrated load at end of overhang"):
                    equations.setImageResource(R.drawable.case9);
                    break;
                case ("Continuous Beam with Equal Overhangs: uniformly distributed load"):
                    equations.setImageResource(R.drawable.case10);
                    break;
                case ("Continuous Beam with Equal Overhangs: equal concentrated loads at ends of overhang"):
                    equations.setImageResource(R.drawable.case11);
                    break;
                case ("Continuous Beam of Two Equal Spans: equal concentrated loads at the center of each span"):
                    equations.setImageResource(R.drawable.case12);
                    break;
                case ("Continuous Beam of Two Equal Spans: uniformly distributed load"):
                    equations.setImageResource(R.drawable.case13);
                    break;
                case ("Continuous Beam of Three Equal Spans: uniformly distributed load"):
                    equations.setImageResource(R.drawable.case14);
                    break;
                case ("Continuous Beam of Four Equal Spans: uniformly distributed load"):
                    equations.setImageResource(R.drawable.case15);
                    break;

            }
        } catch (Exception e) {
        Log.i("log","Could not load image");
    }
    }
}