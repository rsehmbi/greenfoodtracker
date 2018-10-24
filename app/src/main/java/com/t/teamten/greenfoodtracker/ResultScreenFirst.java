package com.t.teamten.greenfoodtracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import foodandco2.Food;
import foodandco2.FoodData;

public class ResultScreenFirst extends AppCompatActivity {
    private TextView testText;
    private FoodData foodData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        Intent userInputActivity = getIntent();
        CalculatorActivityData userInput = userInputActivity.getParcelableExtra(MainActivity.DATA_PASSED_FROM_MAINACTIVITY);
        try {
            foodData = new FoodData(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Food> foods = foodData.getFoodList();
        testText = findViewById(R.id.resultTextView);
        String testDataString = "";
        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            Pair<String, Integer> currentPair = userInput.getPairAtIndex(i); // the purpose of using Pair<> is to have simplistic array handling, no juggling two index variables and potential mismatching
            testDataString = testDataString + i + ") Food: " + currentPair.first + " Number: " + currentPair.second
                    + " CarbonConsumption: " + new ResultScreenFirstDataHandler(this).getCarbonConsumptionFromName(foods, currentPair.first)+ "\n"; //calculation functions go here.
        }

        testText.setText(testDataString);

    }
}
