package com.t.teamten.greenfoodtracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import foodandco2.Food;

public class ResultScreenFirst extends AppCompatActivity {
    private TextView testText;
    public static Context resultScreenFirstContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        resultScreenFirstContext = getApplicationContext();
        Intent userInputActivity = getIntent();
        CalculatorActivityData userInput = userInputActivity.getParcelableExtra(MainActivity.DATA_PASSED_FROM_MAINACTIVITY);
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

    }
}
