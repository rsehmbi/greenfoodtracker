package com.t.teamten.greenfoodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.io.IOException;
import java.util.ArrayList;

import foodandco2.Food;
import foodandco2.FoodData;

public class Result_Page extends AppCompatActivity
{
    private Button mMeat;
    private Button mLow_Meat;
    private Button mPlant;
    private CalculatorActivityData data_passed;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__page);
        mMeat = findViewById(R.id.Meat_Eater_Plan);
        mLow_Meat = findViewById(R.id.Low_Meat_Plan);
        mPlant = findViewById(R.id.Plant_Based_Plan);
        data_passed  = getIntent().getParcelableExtra("DATA_PASSED");


        mMeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    generate_graph();

            }
        });

        mLow_Meat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generate_graph();
            }
        });

        mPlant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generate_graph();
            }
        });

    }

    public ArrayList<Double> calculations() throws IOException {
        double data;
        ArrayList<Double>Data = new ArrayList<>();
        FoodData fooddata =  new FoodData();
        Food food;

        if(data_passed.getSwitchStatus() == 0)//percentage
        {
            for(int i = 0; i<data_passed.getSize() ; i++)
            {
                double input_percentage = (double)data_passed.getPairAtIndex(i).second;
                food = fooddata.getCarbonPerKgByFoodName(data_passed.getPairAtIndex(i).first.toString());
                data = 4* input_percentage*52*food.getCarbonPerKg();
                Data.add(data);
            }

        }
        else if (data_passed.getSwitchStatus() == 1)
        {
            double total = 0;
            for(int i = 0; i<data_passed.getSize(); i++)
            {
                double input_number = (double)data_passed.getPairAtIndex(i).second;
                total = total + input_number;
            }
            if (total == 0)
                return Data;
            for(int i = 0; i< data_passed.getSize() ; i++)
            {
                double input_number = (double)data_passed.getPairAtIndex(i).second;
                food = fooddata.getCarbonPerKgByFoodName(data_passed.getPairAtIndex(i).toString());
                data = input_number*52*food.getCarbonPerKg()/total;
                Data.add(data);
            }
        }

        return Data;
    }



    public void generate_graph() //Not Working
    {
        /*ArrayList<String> X_food_name = new ArrayList<>();
        for(int i = 0; i < data_passed.getSize(); i++)
            X_food_name.add(data_passed.getPairAtIndex(i).first.toString());
        ArrayList<BarEntry> Y_data= new ArrayList<>();
        for(int i = 0; i<data_passed.getSize();i++)

        BarChart emission_chart = (BarChart) findViewById(R.id.emission_bar_chart);
        BarData barData = new BarData(X_food_name,Y_data);
        emission_chart.setData(barData);*/

    }
}
