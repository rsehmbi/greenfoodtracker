package com.t.teamten.GreenFoodTracker.Result_Pages;

import android.util.Pair;

import java.util.List;

import com.t.teamten.GreenFoodTracker.Food_Info.Food;

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
                    total = total + ((double) amount *1.8/100) * food.getCarbonPerKg();
                }
            }

        }
        return total;
    }
    public double calculationForMetro(double user){
        double result = 2.463 * 0.9 * user * 365;
        return result;
    }


}
