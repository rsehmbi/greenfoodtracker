package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultScreenFirst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        Intent userInputActivity = getIntent();
        CalculatorActivityData userInput = userInputActivity.getParcelableExtra(MainActivity.DATA_PASSED);
    }
}
