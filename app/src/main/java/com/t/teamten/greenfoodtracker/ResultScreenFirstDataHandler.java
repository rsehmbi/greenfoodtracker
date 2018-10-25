package com.t.teamten.greenfoodtracker;

import android.app.Activity;

import java.util.List;

import foodandco2.Food;

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
