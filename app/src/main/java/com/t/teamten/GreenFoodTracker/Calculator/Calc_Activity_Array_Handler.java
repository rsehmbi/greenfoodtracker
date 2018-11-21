
package com.t.teamten.GreenFoodTracker.Calculator;

import android.app.Activity;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.t.teamten.GreenFoodTracker.R;

import java.util.ArrayList;

public class Calc_Activity_Array_Handler {
    private Activity activity;
    public Calc_Activity_Array_Handler(Activity activity) {
        this.activity = activity;
    }
    //pertains to the initialization of ui elements into array and setting to their respective ids
    public ArrayList<Spinner> populateSpinnerArray() { //this function allows for looping code in the spinners.
        ArrayList<Spinner> populatedArray = new ArrayList<>();
        Spinner spinner1 = activity.findViewById(R.id.spinner1);
        populatedArray.add(spinner1);
        Spinner spinner2 = activity.findViewById(R.id.spinner2);
        populatedArray.add(spinner2);
        Spinner spinner3 = activity.findViewById(R.id.spinner3);
        populatedArray.add(spinner3);
        Spinner spinner4 = activity.findViewById(R.id.spinner4);
        populatedArray.add(spinner4);
        Spinner spinner5 = activity.findViewById(R.id.spinner5);
        populatedArray.add(spinner5);
        Spinner spinner6 = activity.findViewById(R.id.spinner6);
        populatedArray.add(spinner6);
        Spinner spinner7 = activity.findViewById(R.id.spinner7);
        populatedArray.add(spinner7);

        return populatedArray;
    }

    public ArrayList<SeekBar> populateSeekBarArray() {
        ArrayList<SeekBar> populatedArray = new ArrayList<>();
        SeekBar seekBar1 = activity.findViewById(R.id.seekBar1);
        populatedArray.add(seekBar1);
        SeekBar seekBar2 = activity.findViewById(R.id.seekBar2);
        populatedArray.add(seekBar2);
        SeekBar seekBar3 = activity.findViewById(R.id.seekBar3);
        populatedArray.add(seekBar3);
        SeekBar seekBar4 = activity.findViewById(R.id.seekBar4);
        populatedArray.add(seekBar4);
        SeekBar seekBar5 = activity.findViewById(R.id.seekBar5);
        populatedArray.add(seekBar5);
        SeekBar seekBar6 = activity.findViewById(R.id.seekBar6);
        populatedArray.add(seekBar6);
        SeekBar seekBar7 = activity.findViewById(R.id.seekBar7);
        populatedArray.add(seekBar7);

        return populatedArray;
    }


}