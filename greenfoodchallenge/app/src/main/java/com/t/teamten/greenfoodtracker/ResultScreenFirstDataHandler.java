package com.t.teamten.greenfoodtracker;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import foodandco2.Food;
import foodandco2.FoodData;
//functions to handle calculations for the first result screen activity
public class ResultScreenFirstDataHandler {
    private Activity activity;
    public ResultScreenFirstDataHandler(Activity activity) {
        this.activity = activity;
    }
    public ResultScreenFirstDataHandler() {

    }


    public double total_emission(CalculatorActivityData userInput, FoodData foodData) {
        double total = 0.0;
        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            total = total + calculations(userInput, foodData.getFoodList()).get(i);
        }

        return total;
    }

    public double getCarbonConsumptionFromName(List<Food> foods, String name) {
        for (Food currentFood : foods) {
            if (currentFood.getFoodName().equalsIgnoreCase(name)) {
                return currentFood.getCarbonPerKg();
            }
        }

        return 0; //fallback.
    }

    public ArrayList<Double> calculations(CalculatorActivityData userInput, List<Food> listOfFoods) {
        double data_to_add;
        double food;
        ArrayList<Double> Data = new ArrayList<>();
        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            double input_percentage = Double.valueOf((Integer)userInput.getPairAtIndex(i).second);
            food = getCarbonConsumptionFromName(listOfFoods, userInput.getPairAtIndex(i).first.toString());
            data_to_add = 1.8 * (input_percentage / 100) * 365 * food; // calculates the co2 impact(?)
            Data.add(data_to_add);
        }
        return Data;
    }
}
