package com.t.teamten.greenfoodtracker;

import android.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 27)
public class MealPlanTest {
    @Test
    public void chickenEmpty() {
        List<Pair<String, Integer>> testArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("String", 55);
        Pair<String, Integer> testPair2 = new Pair<>("Second", 0);
        Pair<String, Integer> testPair3 = new Pair<>("Third", 322);
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);

        MealPlan testingPlan = new MealPlan(testArray);
        boolean isEmpty = testingPlan.chickenEmpty();
        assertEquals(true,isEmpty);
    }

    @Test
    public void vegeEmpty() {
        List<Pair<String, Integer>> testArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("Vegetables", 55);
        Pair<String, Integer> testPair2 = new Pair<>("Second", 0);
        Pair<String, Integer> testPair3 = new Pair<>("Third", 322);
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);

        MealPlan testingPlan = new MealPlan(testArray);
        boolean isEmpty = testingPlan.vegeEmpty();
        assertEquals(false,isEmpty);
    }

    @Test
    public void meatEaterPlan() {
        List<Pair<String, Integer>> testArray = new ArrayList<>();
        List<Pair<String, Integer>> newTestArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("Beef", 50);
        Pair<String, Integer> testPair2 = new Pair<>("Chicken", 10);
        Pair<String, Integer> testPair3 = new Pair<>("Vegetable", 322);
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);

        MealPlan testingPlan = new MealPlan(testArray);

        newTestArray = testingPlan.meatEaterPlan();

        for(Pair<String, Integer> pair:newTestArray){
            if(pair.first == "Beef"){
                int test = pair.second;
                assertEquals(25,test);
            }
            if(pair.first == "Chicken"){
                int test = pair.second;
                assertEquals(35,test);
            }
            if(pair.first == "Vegetables"){
                int test = pair.second;
                assertEquals(322,test);
            }
        }
    }

    @Test
    public void lowMeatPlan() {
        List<Pair<String, Integer>> testArray = new ArrayList<>();
        List<Pair<String, Integer>> newTestArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("Beef", 50);
        Pair<String, Integer> testPair2 = new Pair<>("Chicken", 10);
        Pair<String, Integer> testPair3 = new Pair<>("Vegetables", 30);
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);

        MealPlan testingPlan = new MealPlan(testArray);

        newTestArray = testingPlan.lowMeatPlan();

        for(Pair<String, Integer> pair:newTestArray){
            if(pair.first == "Beef"){
                int test = pair.second;
                assertEquals(25,test);
            }
            if(pair.first == "Chicken"){
                int test = pair.second;
                assertEquals(10,test);
            }
            if(pair.first == "Vegetables"){
                int test = pair.second;
                assertEquals(55,test);
            }
        }
    }

    @Test
    public void veggOnlyPlan() {
        List<Pair<String, Integer>> testArray = new ArrayList<>();
        List<Pair<String, Integer>> newTestArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("Beef", 50);
        Pair<String, Integer> testPair2 = new Pair<>("Chicken", 10);
        Pair<String, Integer> testPair3 = new Pair<>("Vegetables", 30);
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);

        MealPlan testingPlan = new MealPlan(testArray);

        newTestArray = testingPlan.veggOnlyPlan();

        for(Pair<String, Integer> pair:newTestArray){
            if(pair.first == "Beef"){
                int test = pair.second;
                assertEquals(0,test);
            }
            if(pair.first == "Chicken"){
                int test = pair.second;
                assertEquals(0,test);
            }
            if(pair.first == "Vegetables"){
                int test = pair.second;
                assertEquals(90,test);
            }
        }
    }
}