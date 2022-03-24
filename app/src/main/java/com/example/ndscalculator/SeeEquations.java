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
    String[] dropDownCase = new String[]{"Case 1", "Case 2", "Case 3", "Case 4",
    "Case 5", "Case 6", "Case 7"};

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
                case ("Case 1"):
                    equations.setImageResource(R.drawable.case1);
                    break;
                case ("Case 2"):
                    equations.setImageResource(R.drawable.case2);
                    break;
                case ("Case 3"):
                    equations.setImageResource(R.drawable.case3);
                    break;
                case ("Case 4"):
                    equations.setImageResource(R.drawable.case4);
                    break;
                case ("Case 5"):
                    equations.setImageResource(R.drawable.case5);
                    break;
                case ("Case 6"):
                    equations.setImageResource(R.drawable.case6);
                    break;
                case ("Case 7"):
                    equations.setImageResource(R.drawable.case7);
                    break;
            }
        } catch (Exception e) {
        Log.i("log","Could not load image");
    }
    }
}