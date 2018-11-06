package com.t.teamten.greenfoodtracker;

import android.util.Pair;

import com.t.teamten.greenfoodtracker.resultscreenactivities.NewPlanCalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import foodandco2.Food;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 27)

public class NewPlanCalculatorTest {

    @Test
    public void calculateNewMealPlan() {
        List<Pair<String, Integer>> testArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("Beef", 50);
        Pair<String, Integer> testPair2 = new Pair<>("Chicken", 10);
        Pair<String, Integer> testPair3 = new Pair<>("Vegetables", 30);
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);
        List<Food> testFoodList = new ArrayList<>();
        Food food1 = new Food ("Beef",3);
        Food food2 = new Food ("Chicken",1);
        Food food3 = new Food ("Vegetables",5);
        testFoodList.add(food1);
        testFoodList.add(food2);
        testFoodList.add(food3);

        NewPlanCalculator testResult= new NewPlanCalculator(testArray,testFoodList);
        //total = total + ((double) amount *1.8/100) * food.getCarbonPerKg();
        double ExpectedResult = 50*3*1.8/100 + 10*1*1.8/100 + 30*5*1.8/100;
        double TestResult = testResult.calculateNewMealPlan();
        assertEquals(ExpectedResult,TestResult,0);
    }

    @Test
    public void calculationForMetro() {
        List<Pair<String, Integer>> testArray = new ArrayList<>();

        List<Food> testFoodList = new ArrayList<>();

        NewPlanCalculator testResult= new NewPlanCalculator(testArray,testFoodList);
        double actualResult = testResult.calculationForMetro(10);
        double expectedResult = 10 * 2.463 * 0.9 * 365;
        assertEquals(expectedResult,actualResult,0);

    }
}