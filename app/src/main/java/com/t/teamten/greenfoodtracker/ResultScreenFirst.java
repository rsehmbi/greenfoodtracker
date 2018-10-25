package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import userdata.UserData;

public class ResultScreenFirst extends AppCompatActivity {
    private TextView testText;
    private UserData user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        user = new UserData(this.getApplicationContext());
        try {
            user.saveCsvFileToList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Pair<String, Integer>> lists = new ArrayList<>();
        lists = user.getUserList();
        //Intent userInputActivity = getIntent();
        //CalculatorActivityData userInput = userInputActivity.getParcelableExtra(MainActivity.DATA_PASSED_FROM_MAINACTIVITY);
        //ArrayList<Food> foodArray = new ArrayList<>();
        //int calculatorWhichTypeOfMetric = userInput.getSwitchStatus();
//        try {
//            foodArray = new ResultScreenFirstDataHandler(this).populateListOfFoodFromInput(userInput);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //proof of concept code - temporary display results.

        testText = findViewById(R.id.resultTextView);
        String testDataString = "";
        for(Pair<String, Integer> pair: lists) {
            testDataString = testDataString + pair.first + ", " + pair.second + "\n";
        }
//        for (int i = 0; i < userInput.getArrayListSize(); i++) {
//            Pair<String, Integer> currentPair = userInput.getPairAtIndex(i); // the purpose of using Pair<> is to have simplistic array handling, no juggling two index variables and potential mismatching
//            testDataString = testDataString + i + ") Food: " + currentPair.first + " Number: " + currentPair.second + "\n"; //calculation functions go here.
//        }



        //----------------------------------------------------

//        for(Food food : foodArray) {
//            testDataString = testDataString + "Name: " + food.getFoodName() + " food consumption: " + food.getNumberOfConsumptionPerWeek() + "\n";
//        }

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
