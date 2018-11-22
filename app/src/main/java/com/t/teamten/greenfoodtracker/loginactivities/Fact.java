package com.t.teamten.greenfoodtracker.loginactivities;

import java.util.Arrays;
import java.util.Collections;
//Helper fact class for facts activity.
public class Fact{

    private String[] textData;
    private int intCount;

    Fact()
    {
        textData = new String[intCount];
        intCount = 0;
    }

    public void initializeArray(int index)
    {
        this.textData = new String[index];
    }
    public String getTextData(int index) {
        return textData[index];
    }

    public int getIntCount() {
        return intCount;
    }

    public void setIntCount(int intCount) {
        this.intCount = intCount;
    }

    public void setTextData(String line, int index) {
        this.textData[index]=line;
    }

    public String[] getTextData() {
        return textData;
    }

     public void pickRandomFacts() {
       Collections.shuffle(Arrays.asList(getTextData()));
     }
}
