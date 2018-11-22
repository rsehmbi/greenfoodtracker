package com.t.teamten.greenfoodtracker;

import android.content.Context;
import android.util.Pair;

import com.t.teamten.greenfoodtracker.calcactivities.CalculatorActivityData;
import com.t.teamten.greenfoodtracker.resultscreenactivities.ResultScreenFirstDataHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import foodandco2.Food;
import foodandco2.FoodData;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 27)
public class ResultScreenFirstDataHandlerTest {
    private CalculatorActivityData calcData;
    private FoodData foodData;
    private Context context;
    private ArrayList<Pair<String, Integer>> testArrayData;

    @Before
    public void initializeData() throws IOException {
        testArrayData = new ArrayList<>();
        testArrayData.add(new Pair<>("Lamb", 30));
        testArrayData.add(new Pair<>("Chicken", 30));
        testArrayData.add(new Pair<>("Beef", 40));
        calcData = new CalculatorActivityData(testArrayData);
        context = RuntimeEnvironment.application;
        foodData = new FoodData(context);
    }

    @Test
    public void total_emission() {
        CalculatorActivityData testCalcData = new CalculatorActivityData(testArrayData);
        int testTotal = (int) new ResultScreenFirstDataHandler().totalEmission(testCalcData, foodData);
        assertEquals(testTotal, 16181);
    }

    @Test
    public void getCarbonConsumptionFromNameTest() {
        int testResult = (int) new ResultScreenFirstDataHandler().getCarbonConsumptionFromName(foodData.getFoodList(), "Lamb");
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
            food = new ResultScreenFirstDataHandler().getCarbonConsumptionFromName(listOfFoods, calcData.getPairAtIndex(i).first.toString());
            data_to_add = 1.8 * (input_percentage / 100) * 365 * food; // calculates the co2 impact(?)
            actualData.add(data_to_add);
        }

        ArrayList<Double> testData = new ResultScreenFirstDataHandler().calculations(calcData, listOfFoods);
        boolean status = testData.equals(actualData);
        assertEquals(status, true);
    }
}