
package com.t.teamten.greenfoodtracker.calcactivities;

import android.app.Activity;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.t.teamten.greenfoodtracker.R;

import java.util.ArrayList;

public class CalcActivityArrayHandler {
    private Activity activity;
    public CalcActivityArrayHandler(Activity activity) {
        this.activity = activity;
    }
    //pertains to the initialization of ui elements into array and setting to their respective ids
    public ArrayList<Spinner> populateSpinnerArray() { //this function allows for looping code in the spinners.
        ArrayList<Spinner> populatedArray = new ArrayList<>();
        Spinner spinnerforfirstfood = activity.findViewById(R.id.spinner1);
        populatedArray.add(spinnerforfirstfood);
        Spinner spinnerforsecondfood = activity.findViewById(R.id.spinner2);
        populatedArray.add(spinnerforsecondfood);
        Spinner spinnerforthirdfood = activity.findViewById(R.id.spinner3);
        populatedArray.add(spinnerforthirdfood);
        Spinner spinnerforfourthfood = activity.findViewById(R.id.spinner4);
        populatedArray.add(spinnerforfourthfood);
        Spinner spinnerforfirftfood = activity.findViewById(R.id.spinner5);
        populatedArray.add(spinnerforfirftfood);
        Spinner spinnerforsixthfood = activity.findViewById(R.id.spinner6);
        populatedArray.add(spinnerforsixthfood);
        Spinner spinnerforseventhfood = activity.findViewById(R.id.spinner7);
        populatedArray.add(spinnerforseventhfood);

        return populatedArray;
    }

    public ArrayList<SeekBar> populateSeekBarArray() {
        ArrayList<SeekBar> populatedArray = new ArrayList<>();
        SeekBar seekbarforfirstfood = activity.findViewById(R.id.seekBar1);
        populatedArray.add(seekbarforfirstfood);
        SeekBar seekbarforsecondfood = activity.findViewById(R.id.seekBar2);
        populatedArray.add(seekbarforsecondfood);
        SeekBar seekbarforthirdfood = activity.findViewById(R.id.seekBar3);
        populatedArray.add(seekbarforthirdfood);
        SeekBar seekbarforfourthfood = activity.findViewById(R.id.seekBar4);
        populatedArray.add(seekbarforfourthfood);
        SeekBar seekbarforfifthfood = activity.findViewById(R.id.seekBar5);
        populatedArray.add(seekbarforfifthfood);
        SeekBar seekbarforsixthfood = activity.findViewById(R.id.seekBar6);
        populatedArray.add(seekbarforsixthfood);
        SeekBar seekbarforseventhfood = activity.findViewById(R.id.seekBar7);
        populatedArray.add(seekbarforseventhfood);

        return populatedArray;
    }


}