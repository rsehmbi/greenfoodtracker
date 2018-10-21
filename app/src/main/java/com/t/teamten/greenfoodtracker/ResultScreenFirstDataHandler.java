package com.t.teamten.greenfoodtracker;

import android.app.Activity;
import android.util.Pair;
import java.io.IOException;
import java.util.ArrayList;

import foodandco2.Food;
import foodandco2.FoodData;

public class ResultScreenFirstDataHandler {
    private Activity activity;
    public ResultScreenFirstDataHandler(Activity activity) {
        this.activity = activity;
    }

    //this function takes the package and coverts it into food data type
    public ArrayList<Food> populateListOfFoodFromInput(CalculatorActivityData userInput) throws IOException {
        ArrayList<Pair<String, Integer>> pairList = userInput.getArrayListOfPair();
        ArrayList<Food> returnList = new ArrayList<>();
        FoodData foodData = new FoodData();
        for (Pair<String, Integer> currentPair : pairList) {
            String name = currentPair.first;
            Integer consumption = currentPair.second;
            Food currentFood = foodData.getCarbonPerKgByFoodName(name);
            currentFood.setNumberOfConsumptionPerWeek(consumption);
            returnList.add(currentFood);
        }

        return returnList;
    }

}
