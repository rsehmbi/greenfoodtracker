package com.t.teamten.greenfoodtracker.resultscreenactivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.loginactivities.HomeScreen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import foodandco2.Food;
import foodandco2.FoodData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import userdata.UserData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;

public class ResultScreenSecond extends AppCompatActivity {
    PieChartView pieChartView;
    TextView mResView;
    TextView intro;
    TextView resultText;
    private UserData userDietPlan;
    private FoodData foodData;
    private TextView testing;
    private List<Pair<String, Integer>> lists = new ArrayList<>();
    private List<Pair<String, Integer>> newlist = new ArrayList<>();
    private List<Food> foodList = new ArrayList<>();
    private double totalAmount;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private double currentSaved = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_second);

        //get users info
        userDietPlan = new UserData(this.getApplicationContext());
        try {
            userDietPlan.saveCsvFileToList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lists = userDietPlan.getUserList();

        //get food data from csv
        try {
            foodData = new FoodData(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        foodList = foodData.getFoodList();

        //get co2e from users input
        NewPlanCalculator newCalculator = new NewPlanCalculator(lists,foodList);
        totalAmount = newCalculator.calculateNewMealPlan();

        mResView = findViewById(R.id.text_view_result2_2);
        intro = findViewById(R.id.text_view_result2_info);
        intro.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                String message = getResources().getString(R.string.mealPlansInfo);
                Toast.makeText(ResultScreenSecond.this,message,LENGTH_SHORT).show();
            }
        });
        pieChartView = findViewById(R.id.chart);

        //initialize with a pie chart that has a slice value of 0
        //so that it won't show anything at the beginning
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(0, Color.GRAY).setLabel(""));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartView.setPieChartData(pieChartData);

        final Button button1 = findViewById(R.id.button_view_1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MealPlan newPlan = new MealPlan(lists);
                newlist = newPlan.meatEaterPlan();
                mResView.setText("You choose Meat-Eater Plan!");
                updateMealPlan(newlist,R.string.meal1);
            }
        });

        final Button button2 = findViewById(R.id.button_view_2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MealPlan newPlan = new MealPlan(lists);
                newlist = newPlan.lowMeatPlan();
                mResView.setText("You choose Low Eater Plan!");
                updateMealPlan(newlist,R.string.meal2);
            }
        });

        final Button button3 = findViewById(R.id.button_view_3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MealPlan newPlan = new MealPlan(lists);
                newlist = newPlan.veggOnlyPlan();
                NewPlanCalculator newCalculator = new NewPlanCalculator(newlist,foodList);
                mResView.setText("You choose Plant-based Plan!");
                updateMealPlan(newlist,R.string.meal3);
            }
        });

        //button to next page
        final Button next = findViewById(R.id.toMainPage);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ResultScreenSecond.this,HomeScreen.class);
                startActivity(intent);
            }
        });
        //button to pledge page
        //Send this pledge data to pledge Interac activity
        final Button pledgeButton = findViewById(R.id.pledge);
        pledgeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mUser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = mUser.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("users").child(uid).child("pledge").setValue(String.format("%.2f", currentSaved));
            }
        });
    }
    public void updateMealPlan(List<Pair<String, Integer>> updatedList,int newPlan){
        NewPlanCalculator newCalculator = new NewPlanCalculator(updatedList,foodList);
        double amount = newCalculator.calculateNewMealPlan();
        int co2e = (int)amount;
        creatPieChart(newPlan,co2e);
        double saved = totalAmount - amount;
        currentSaved = saved;
        double metroSaved = newCalculator.calculationForMetro(saved);
        printResult(saved,metroSaved);
    }

    public void creatPieChart(int meal, int co2Val){
        String new_meal = getResources().getString(meal);
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(((int) totalAmount), Color.BLUE).setLabel("Your plan: " + String.format("%.2f", totalAmount) + "kg"));
        pieData.add(new SliceValue(co2Val, Color.GREEN).setLabel(new_meal + ": " + co2Val + "kg"));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(12);
        pieChartView.setPieChartData(pieChartData);
    }

    public void printResult(double saved,double metroSaved){
        String result = "By changing your meal plan you have reduced " +
                "<font color='#EE0000'>"+ String.format("%.2f", saved)+ "</font>" + " kg of CO2e!\n" +
                "if the residents in Metro Vancouver made the same change," +
                "the CO2e will reduced by "+(int)metroSaved+" million kg!\n"+
                "This is equivalent to saving "+ (int)(metroSaved/23)+ " trees!";
        resultText = findViewById(R.id.text_view_result2_3);
        resultText.setText(Html.fromHtml(result));
    }
}
