package com.t.teamten.GreenFoodTracker.Result_Pages;

import android.app.Activity;

import com.t.teamten.GreenFoodTracker.Calculator.Calculator_Data;

import java.util.ArrayList;
import java.util.List;

import com.t.teamten.GreenFoodTracker.Food_Info.Food;
import com.t.teamten.GreenFoodTracker.Food_Info.Food_Data;
//functions to handle calculations for the first result screen activity
public class Calculation_Result_Data_Handler {
    private Activity activity;
    public Calculation_Result_Data_Handler(Activity activity) {
        this.activity = activity;
    }
    public Calculation_Result_Data_Handler() {

    }


    public double total_emission(Calculator_Data userInput, Food_Data foodData) {
        double total = 0.0;
        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            total = total + calculations(userInput, foodData.getFoodList()).get(i);
        }

        return total;
    }

    public double total_emission(ArrayList<String> array_name,ArrayList<Integer>array_data, Food_Data foodData) {
        double total = 0.0;
        for (int i = 0; i < array_data.size(); i++) {
            total = total + calculations(array_name,array_data, foodData.getFoodList()).get(i);
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

    public ArrayList<Double> calculations(Calculator_Data userInput, List<Food> listOfFoods) {
        double data_to_add;
        double food;
        double total_input = 0;
        ArrayList<Double> Data = new ArrayList<>();
        for (int i = 0; i < userInput.getArrayListSize(); i++)
            total_input = Double.valueOf((Integer) userInput.getPairAtIndex(i).second) + total_input;
        for (int i = 0; i < userInput.getArrayListSize(); i++)
        {
            double input_freq = Double.valueOf((Integer)userInput.getPairAtIndex(i).second);
            food = getCarbonConsumptionFromName(listOfFoods, userInput.getPairAtIndex(i).first.toString());
            data_to_add = 1.8 * (input_freq / total_input) * 365 * food; // calculates the co2 impact, assume each person's intake 1.8kg
            Data.add(data_to_add);
        }
        return Data;
    }

    public ArrayList<Double> calculations(ArrayList<String> array_name,ArrayList<Integer>array_data, List<Food> listOfFoods) {
        double data_to_add;
        double food;
        double total_input = 0;
        ArrayList<Double> Data = new ArrayList<>();
        for (int i = 0; i < array_data.size(); i++)
            total_input = array_data.get(i) + total_input;
        for (int i = 0; i < array_data.size(); i++)
        {
            double input_freq = array_data.get(i);
            food = getCarbonConsumptionFromName(listOfFoods, array_name.get(i));
            data_to_add = 1.8 * (input_freq / total_input) * 365 * food; // calculates the co2 impact, assume each person's intake 1.8kg
            Data.add(data_to_add);
        }
        return Data;
    }


   /* public ArrayList<Double> calculations(Calculator_Data userInput, List<Food> listOfFoods) {
        double data_to_add;
        double food;
        ArrayList<Double> Data = new ArrayList<>();
        for (int i = 0; i < userInput.getArrayListSize(); i++) {
            double input_percentage = Double.valueOf((Integer)userInput.getPairAtIndex(i).second);
            food = getCarbonConsumptionFromName(listOfFoods, userInput.getPairAtIndex(i).first.toString());
            data_to_add = 1.8 * (input_percentage / 100) * 365 * food; // calculates the co2 impact, assume each person's intake 1.8kg
            Data.add(data_to_add);
        }
        return Data;
    }*/

}
