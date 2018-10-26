package com.t.teamten.greenfoodtracker;

import android.util.Pair;

import java.util.List;

import foodandco2.Food;

public class NewPlanCalculator {
    private List<Pair<String, Integer>> userLists;
    private List<Food> foodList;

    public NewPlanCalculator(List<Pair<String, Integer>> userList, List<Food> foodList){
        this.userLists = userList;
        this.foodList = foodList;
    }
    public double calculateNewMealPlan(){
        double total = 0;
        for (Pair<String,Integer> pair:userLists){
            String name = pair.first;
            int amount = pair.second;
            for(Food food : foodList){
                String foodName = food.getFoodName();
                if(foodName.equals(name)){
                    total = total + ((double) amount) * food.getCarbonPerKg();
                }
            }

        }
        return total;
    }
    public double calculationForMetro(double user){
        double result = 2.463 * 0.9 * user * 52;
        return result;
    }


}
