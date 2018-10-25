package com.t.teamten.greenfoodtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
    private Button mMeat;
    private Button mLow_Meat;
    private Button mPlant;
    private FoodData foodData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        mMeat = findViewById(R.id.Meat_Eater_Plan);
        mLow_Meat = findViewById(R.id.Low_Meat_Plan);
        mPlant = findViewById(R.id.Plant_Based_Plan);

        final CalculatorActivityData userInput = getIntent().getParcelableExtra(MainActivity.DATA_PASSED_FROM_MAINACTIVITY);
        try {
            foodData = new FoodData(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    generate_graph(userInput);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        mLow_Meat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    generate_graph(userInput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mPlant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    generate_graph(userInput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ArrayList<Double> calculations(CalculatorActivityData userInput, List<Food> listOfFoods) throws IOException {
        double data;
        double food;
        ArrayList<Double> Data = new ArrayList<>();
        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            double input_percentage = Double.valueOf((Integer)userInput.getPairAtIndex(i).second);
            food = new ResultScreenFirstDataHandler(this).getCarbonConsumptionFromName(listOfFoods, userInput.getPairAtIndex(i).first.toString());
            data = 4 * input_percentage * 52 * food; // calculates the co2 impact(?)
            Data.add(data);
        }
        return Data;
    }

    public void generate_graph(CalculatorActivityData userInput) throws IOException {
        ArrayList<String> X_food_name = new ArrayList<>();
        for(int i = 0; i < userInput.getArrayListSize(); i++)
            X_food_name.add(userInput.getPairAtIndex(i).first.toString());
        ArrayList<BarEntry> barEntries= new ArrayList<>();
        ArrayList<Double> calculated_data = new ArrayList<>();
        for (int i = 0; i<calculations(userInput, foodData.getFoodList()).size();i++)
            calculated_data.add(calculations(userInput, foodData.getFoodList()).get(i));
        for(int i = 0; i<userInput.getArrayListSize();i++)
        {
            float value = 1.0f * calculated_data.get(i).floatValue();
            barEntries.add(new BarEntry(value,i));
        }
        BarChart emission_chart = findViewById(R.id.emission_bar_chart);
        BarDataSet barDataSet = new BarDataSet(barEntries,"CO2 Emission per Year kg");
        BarData barData = new BarData(X_food_name,barDataSet);
        emission_chart.setData(barData);
        emission_chart.setDescription("");

    }
}
