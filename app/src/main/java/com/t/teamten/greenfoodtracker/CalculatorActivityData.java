package com.t.teamten.greenfoodtracker;

import android.util.Pair;

import java.util.ArrayList;

public class CalculatorActivityData {
    private Pair<String, Integer> foodEntryPair;
    private ArrayList<Pair> listFoodPairs;
    private int switchStatus;

    CalculatorActivityData(ArrayList<Pair> listFoodPairs, int switchStatus) {
        this.listFoodPairs = listFoodPairs;
        this.switchStatus = switchStatus;
    }

    public Pair getPairAtIndex(int index) {
        Pair<String, Integer> pairAtIndex;
        if (index < 0 || index > this.listFoodPairs.size()) {
            pairAtIndex = new Pair<>("ERROR", 0);
            return pairAtIndex;
        }
        pairAtIndex = listFoodPairs.get(index);
        return pairAtIndex;
    }

    public int getSwitchStatus() {
        return this.switchStatus;
    }

}
