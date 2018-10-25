package com.t.teamten.greenfoodtracker;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import java.util.ArrayList;

public class CalculatorActivityData implements Parcelable {
    private ArrayList<Pair<String, Integer>> listFoodPairs;
    private int switchStatus;

    CalculatorActivityData(ArrayList<Pair<String, Integer>> listFoodPairs, int switchStatus) {
        this.listFoodPairs = listFoodPairs;
        this.switchStatus = switchStatus;
    }


    private CalculatorActivityData(Parcel in) {
        this.switchStatus = in.readInt();
        final int size = in.readInt();
        listFoodPairs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String first = in.readString();
            Integer second = in.readInt();
            Pair<String, Integer> addPair = new Pair(first, second);
            listFoodPairs.add(addPair);
        }

    }

    public static final Creator<CalculatorActivityData> CREATOR = new Creator<CalculatorActivityData>() {
        @Override
        public CalculatorActivityData createFromParcel(Parcel in) {
            return new CalculatorActivityData(in);
        }

        @Override
        public CalculatorActivityData[] newArray(int size) {
            return new CalculatorActivityData[size];
        }
    };


    public Pair getPairAtIndex(int index) {
        Pair<String, Integer> pairAtIndex;
        if (index < 0 || index > this.listFoodPairs.size()) {
            pairAtIndex = new Pair<>("ERROR", 0);
            return pairAtIndex;
        }
        pairAtIndex = listFoodPairs.get(index);
        return pairAtIndex;
    }



    public int getArrayListSize() {
        return this.listFoodPairs.size();
    }

    public ArrayList<Pair<String, Integer>> getArrayListOfPair() {
        return this.listFoodPairs;
    }

    public int getSwitchStatus() {
        return this.switchStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(switchStatus);
        final int size = this.listFoodPairs.size();
        dest.writeInt(size);
        if (size > 0) {
            for (Pair<String, Integer> currentPair : listFoodPairs) {
                dest.writeString(currentPair.first);
                dest.writeInt(currentPair.second);
            }
        }
    }
}
