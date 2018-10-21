package com.t.teamten.greenfoodtracker;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import java.util.ArrayList;

public class CalculatorActivityData implements Parcelable {
    private Pair<String, Integer> foodEntryPair;
    private ArrayList<Pair> listFoodPairs;
    private int switchStatus;

    CalculatorActivityData(ArrayList<Pair> listFoodPairs, int switchStatus) {
        this.listFoodPairs = listFoodPairs;
        this.switchStatus = switchStatus;
    }

    protected CalculatorActivityData(Parcel in) {
        switchStatus = in.readInt();
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

    public int getSize()
    {
        return this.listFoodPairs.size();
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
    }
}
