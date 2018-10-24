package com.t.teamten.greenfoodtracker;

import android.app.Activity;
import android.util.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import foodandco2.Food;
import foodandco2.FoodData;

public class ResultScreenFirstDataHandler {
    private Activity activity;
    public ResultScreenFirstDataHandler(Activity activity) {
        this.activity = activity;
    }
    public double getCarbonConsumptionFromName(List<Food> foods, String name) {
        for (Food currentFood : foods) {
            if (currentFood.getFoodName().equalsIgnoreCase(name)) {
                return currentFood.getCarbonPerKg();
            }
        }

        return 0; //fallback.
    }
}
