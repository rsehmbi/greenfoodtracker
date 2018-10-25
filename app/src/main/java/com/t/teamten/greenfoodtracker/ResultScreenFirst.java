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


        try {
            generate_graph(userInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Integer total_emission = (int) total_emission(userInput);
            Integer km_driven = total_emission*9/2;

            equvalence.setText("Driving " + km_driven.toString() + " on road");
            Integer hours_of_air_condition = total_emission/2;
            equvalence1.setText("Keeping air condition on for " +
            hours_of_air_condition.toString() + " hours");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mNext_Activity.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                //Intent intent = new Intent(ResultScreenFirst.this, ResultScreenSecond.class);
                //startActivity(intent);
            }

        });

    }

    public double total_emission(CalculatorActivityData userInput) throws IOException {
        double total = 0.0;
        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            total = total + calculations(userInput, foodData.getFoodList()).get(i);
        }

        return total;
    }

    public ArrayList<Double> calculations(CalculatorActivityData userInput, List<Food> listOfFoods) throws IOException {
        double data_to_add;
        double food;
        ArrayList<Double> Data = new ArrayList<>();
        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            double input_percentage = Double.valueOf((Integer)userInput.getPairAtIndex(i).second);
            food = new ResultScreenFirstDataHandler(this).getCarbonConsumptionFromName(listOfFoods, userInput.getPairAtIndex(i).first.toString());
            data_to_add = 1.8 * (input_percentage / 100) * 365 * food; // calculates the co2 impact(?)
            Data.add(data_to_add);
        }
        return Data;
    }

    public void generate_graph(CalculatorActivityData userInput) throws IOException {
        ArrayList<String> X_food_name = new ArrayList<>();
        ArrayList<BarEntry> Y_barEntries = new ArrayList<>();

        for (int i = 0; i < userInput.getArrayListSize(); i++)
            X_food_name.add(userInput.getPairAtIndex(i).first.toString());

        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            float value = 1.0f * calculations(userInput,foodData.getFoodList()).get(i).floatValue();
            Y_barEntries.add(new BarEntry(value, i));
        }

        BarChart emission_chart = (BarChart) findViewById(R.id.emission_bar_chart);
        BarDataSet Y_emission = new BarDataSet(Y_barEntries, "CO2 Emission per Year kg");
        BarData barData = new BarData(X_food_name, Y_emission);
        emission_chart.setData(barData);
        emission_chart.setDescription("This is your carbon emission per year based on your current diet");

    }


}
