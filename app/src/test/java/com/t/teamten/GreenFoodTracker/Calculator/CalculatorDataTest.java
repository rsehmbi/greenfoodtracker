package com.t.teamten.GreenFoodTracker.Calculator;

import android.util.Pair;

import com.t.teamten.GreenFoodTracker.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 27)

public class CalculatorDataTest {



    @Test
    public void getPairAtIndex() {
        ArrayList<Pair<String, Integer>> testArray = new ArrayList<>();

        Pair<String, Integer> testPair1 = new Pair<>("String", 55);
        Pair<String, Integer> testPair2 = new Pair<>("Second", 0);
        Pair<String, Integer> testPair3 = new Pair<>("Third", 322);
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);
        Calculator_Data testData = new Calculator_Data(testArray);
        assertEquals("Second", testData.getPairAtIndex(1).first);
        assertEquals(322, testData.getPairAtIndex(2).second);
        assertEquals("ERROR",testData.getPairAtIndex(22).first);

    }

    @Test
    public void getArrayListOfPair() {
        ArrayList<Pair<String, Integer>> testArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("String", 55);
        Pair<String, Integer> testPair2 = new Pair<>("Second", 0);
        Pair<String, Integer> testPair3 = new Pair<>("Third", 322);
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);
        Calculator_Data testData = new Calculator_Data(testArray);
        ArrayList<Pair<String, Integer>> comparisonArray = testData.getArrayListOfPair();
        boolean isEqual = true;
        for (int i = 0; i < comparisonArray.size(); i++) {
            if (!comparisonArray.get(i).first.equals(testArray.get(i).first)) {
                isEqual = false;
            }

            if (!(comparisonArray.get(i).second == testArray.get(i).second)) {
                isEqual = false;
            }
        }

        assertTrue(isEqual);
    }


    @Test
    public void describeContents() {
        ArrayList<Pair<String, Integer>> testArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("String", 55);
        testArray.add(testPair1);
        Calculator_Data testData = new Calculator_Data(testArray);
        assertEquals(0, testData.describeContents());
    }
}