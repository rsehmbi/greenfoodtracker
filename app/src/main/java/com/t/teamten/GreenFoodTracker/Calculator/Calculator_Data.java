package com.t.teamten.GreenFoodTracker.Calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import java.util.ArrayList;

public class Calculator_Data implements Parcelable {
    private ArrayList<Pair<String, Integer>> listFoodPairs;


    public Calculator_Data(ArrayList<Pair<String, Integer>> listFoodPairs) {
        this.listFoodPairs = listFoodPairs;
    }


    public Calculator_Data(Parcel in) {
        final int size = in.readInt();
        listFoodPairs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String first = in.readString();
            Integer second = in.readInt();
            Pair<String, Integer> addPair = new Pair(first, second);
            listFoodPairs.add(addPair);
        }

    }

    public static final Creator<Calculator_Data> CREATOR = new Creator<Calculator_Data>() {
        @Override
        public Calculator_Data createFromParcel(Parcel in) {
            return new Calculator_Data(in);
        }

        @Override
        public Calculator_Data[] newArray(int size) {
            return new Calculator_Data[size];
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
