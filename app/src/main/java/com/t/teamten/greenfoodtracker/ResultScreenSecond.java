package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

import static android.widget.Toast.LENGTH_SHORT;

public class ResultScreenSecond extends AppCompatActivity {

    PieChartView pieChartView;
    TextView mResView;
    TextView intro;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_second);

        mResView = (TextView)findViewById(R.id.text_view_result2_2);
        intro = (TextView)findViewById(R.id.text_view_result2_info);
        intro.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                String message = getResources().getString(R.string.mealPlansInfo);
                Toast.makeText(ResultScreenSecond.this,message,LENGTH_SHORT).show();
            }
        });
        pieChartView = findViewById(R.id.chart);

        //initialize with a pie chart that has a slice value of 0
        //so that it won't shown anything at the beginning
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(0, Color.GRAY).setLabel(""));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartView.setPieChartData(pieChartData);

        final Button button1 = findViewById(R.id.button_view_1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int co2e = 30;
                creatPieChart(R.string.meal1,co2e);
                mResView.setText("You choose Meat-Eater Plan!");
                printResult();
            }

        });

        final Button button2 = findViewById(R.id.button_view_2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int co2e = 20;
                creatPieChart(R.string.meal2,co2e);
                mResView.setText("You choose Low Eater Plan!");
                printResult();

            }

        });
        final Button button3 = findViewById(R.id.button_view_3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int co2e = 10;
                creatPieChart(R.string.meal3,co2e);
                mResView.setText("You choose Plant-based Plan!");
                printResult();
            }

        });

    }

    public void creatPieChart(int meal, int co2Val){
        String new_meal = getResources().getString(meal);
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(60, Color.RED).setLabel("Your plan"));
        pieData.add(new SliceValue(co2Val, Color.BLUE).setLabel(new_meal));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(12);
        pieChartView.setPieChartData(pieChartData);

    }

    public void printResult(){
        String result = "By changing your meal plan to XX you have reduced XX kg of CO2e!\n" +
                "if the residents in Metro Vancouver made the same change," +
                "the CO2e will reduced XX kg!";
        resultText = (TextView)findViewById(R.id.text_view_result2_3);
        resultText.setText(result);
    }
}
