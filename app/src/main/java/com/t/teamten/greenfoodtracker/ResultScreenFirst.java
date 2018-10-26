package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import foodandco2.Food;
import foodandco2.FoodData;

public class ResultScreenFirst extends AppCompatActivity {
    private Button mNext_Activity;
    private TextView equvalence;
    private TextView equvalence1;
    private FoodData foodData;
    private BarChart emission_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        emission_chart = findViewById(R.id.emission_bar_chart);
        equvalence = findViewById(R.id.km_driven);
        equvalence1 = findViewById(R.id.another_one);
        mNext_Activity = findViewById(R.id.go_to_next_activity);
        final CalculatorActivityData userInput = getIntent().getParcelableExtra(MainActivity.DATA_PASSED_FROM_MAINACTIVITY);

        try {
            foodData = new FoodData(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        generate_graph(userInput);

        Integer totalEmission = (int) new ResultScreenFirstDataHandler(this).total_emission(userInput, foodData);
        Integer km_driven = totalEmission*9/2;

        equvalence.setText("Driving " + km_driven.toString() + "km on road");
        Integer hours_of_air_condition = totalEmission/2;
        equvalence1.setText("Keeping air condition on for " +
                hours_of_air_condition.toString() + " hours");


        mNext_Activity.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent intent = new Intent(ResultScreenFirst.this, ResultScreenSecond.class);
                startActivity(intent);
            }

        });

    }


    public void generate_graph(CalculatorActivityData userInput) {
        ArrayList<String> X_food_name = new ArrayList<>();
        ArrayList<BarEntry> Y_barEntries = new ArrayList<>();

        for (int i = 0; i < userInput.getArrayListSize(); i++)
            X_food_name.add(userInput.getPairAtIndex(i).first.toString());

        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            float value = 1.0f * new ResultScreenFirstDataHandler(this).calculations(userInput,foodData.getFoodList()).get(i).floatValue();
            Y_barEntries.add(new BarEntry(value, i));
        }

        BarChart emission_chart = findViewById(R.id.emission_bar_chart);
        BarDataSet Y_emission = new BarDataSet(Y_barEntries, "CO2 Emission per Year kg");
        BarData barData = new BarData(X_food_name, Y_emission);
        emission_chart.setData(barData);


    }


}
