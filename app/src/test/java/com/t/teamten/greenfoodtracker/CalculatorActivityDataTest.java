package com.t.teamten.greenfoodtracker;


import android.util.Pair;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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
        CalculatorActivityData testData = new CalculatorActivityData(testArray, switchstatus);
        assertEquals(testData.getPairAtIndex(1).first, "Second");
        assertEquals(testData.getPairAtIndex(2).second, 322);
        assertEquals(testData.getPairAtIndex(22).first, "ERROR");

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
        CalculatorActivityData testData = new CalculatorActivityData(testArray, switchstatus);
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

        assertEquals(isEqual, true);
    }

    @Test
    public void getSwitchStatus() {
        ArrayList<Pair<String, Integer>> testArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("String", 55);
        Pair<String, Integer> testPair2 = new Pair<>("Second", 0);
        Pair<String, Integer> testPair3 = new Pair<>("Third", 322);
        int switchstatus = 0;
        testArray.add(testPair1);
        testArray.add(testPair2);
        testArray.add(testPair3);
        CalculatorActivityData testData = new CalculatorActivityData(testArray, switchstatus);
        assertEquals(testData.getSwitchStatus(), 0);
    }

    @Test
    public void describeContents() {
        ArrayList<Pair<String, Integer>> testArray = new ArrayList<>();
        Pair<String, Integer> testPair1 = new Pair<>("String", 55);
        int switchstatus = 0;
        testArray.add(testPair1);
        CalculatorActivityData testData = new CalculatorActivityData(testArray, switchstatus);
        assertEquals(testData.describeContents(), 0);
    }
}