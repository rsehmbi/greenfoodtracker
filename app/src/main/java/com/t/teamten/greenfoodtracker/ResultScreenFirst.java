package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import foodandco2.Food;

public class ResultScreenFirst extends AppCompatActivity {
    private TextView testText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        Intent userInputActivity = getIntent();
        CalculatorActivityData userInput = userInputActivity.getParcelableExtra(MainActivity.DATA_PASSED);
        ArrayList<Food> foodArray = new ArrayList<>();
        int calculatorWhichTypeOfMetric = userInput.getSwitchStatus();
        try {
            foodArray = new ResultScreenFirstDataHandler(this).populateListOfFoodFromInput(userInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        testText = findViewById(R.id.resultTextView);
        String testDataString = "";
        for(Food food : foodArray) {
            testDataString = testDataString + "Name: " + food.getFoodName() + " food consumption: " + food.getNumberOfConsumptionPerWeek() + "\n";
        }

        testText.setText(testDataString);



        //button to next page
        final Button next = findViewById(R.id.nextPage);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ResultScreenFirst.this,ResultScreenSecond.class);
                startActivity(intent);
            }

        });


    }
}
