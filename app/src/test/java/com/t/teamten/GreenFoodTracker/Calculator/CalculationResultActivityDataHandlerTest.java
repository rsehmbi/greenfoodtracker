package com.t.teamten.GreenFoodTracker.Calculator;

import android.content.Context;
import android.util.Pair;

import com.t.teamten.GreenFoodTracker.BuildConfig;
import com.t.teamten.GreenFoodTracker.Result_Pages.Calculation_Result_Data_Handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.t.teamten.GreenFoodTracker.Food_Info.Food;
import com.t.teamten.GreenFoodTracker.Food_Info.Food_Data;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 27)
public class CalculationResultActivityDataHandlerTest {
    private Calculator_Data calcData;
    private Food_Data foodData;
    private Context context;
    private ArrayList<Pair<String, Integer>> testArrayData;

    @Before
    public void initializeData() throws IOException {
        testArrayData = new ArrayList<>();
        testArrayData.add(new Pair<>("Lamb", 30));
        testArrayData.add(new Pair<>("Chicken", 30));
        testArrayData.add(new Pair<>("Beef", 40));
        calcData = new Calculator_Data(testArrayData);
        context = RuntimeEnvironment.application;
        foodData = new Food_Data(context);
    }

    @Test
    public void total_emission() {
        Calculator_Data testCalcData = new Calculator_Data(testArrayData);
        int testTotal = (int) new Calculation_Result_Data_Handler().total_emission(testCalcData, foodData);
        assertEquals(testTotal, 16181);
    }

    @Test
    public void getCarbonConsumptionFromNameTest() {
        int testResult = (int) new Calculation_Result_Data_Handler().getCarbonConsumptionFromName(foodData.getFoodList(), "Lamb");
        assertEquals(testResult, 39); //just the whole digits is sufficient
    }

    @Test
    public void calculationsTest() {
        double data_to_add;
        double food;
        List<Food> listOfFoods = foodData.getFoodList();
        ArrayList<Double> actualData = new ArrayList<>();
        for (int i = 0; i < calcData.getArrayListSize(); i++) {
            double input_percentage = Double.valueOf((Integer)calcData.getPairAtIndex(i).second);
            food = new Calculation_Result_Data_Handler().getCarbonConsumptionFromName(listOfFoods, calcData.getPairAtIndex(i).first.toString());
            data_to_add = 1.8 * (input_percentage / 100) * 365 * food; // calculates the co2 impact(?)
            actualData.add(data_to_add);
        }

        ArrayList<Double> testData = new Calculation_Result_Data_Handler().calculations(calcData, listOfFoods);
        boolean status = testData.equals(actualData);
        assertEquals(status, true);
    }
}