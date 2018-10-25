package com.t.teamten.greenfoodtracker;

import android.util.Pair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 27)

public class CalculatorActivityDataTest {

    @Test
    public void getPairAtIndex() {
        ArrayList<Pair<String, Integer>> testArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("String", 55);
        Pair<String, Integer> testPair2 = new Pair<>("Second", 0);
        Pair<String, Integer> testPair3 = new Pair<>("Third", 322);
        int switchstatus = 0;
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);
        CalculatorActivityData testData = new CalculatorActivityData(testArray);
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
        int switchstatus = 0;
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);
        CalculatorActivityData testData = new CalculatorActivityData(testArray);
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
        int switchstatus = 0;
        testArray.add(testPair1);
        CalculatorActivityData testData = new CalculatorActivityData(testArray);
        assertEquals(0, testData.describeContents());
    }
}