package com.t.teamten.greenfoodtracker.calcactivities;

import android.app.Activity;
import android.widget.EditText;
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

    public ArrayList<EditText> populateEditTextArray() {
        ArrayList<EditText> populatedArray = new ArrayList<>();
        EditText editText1 = activity.findViewById(R.id.editText1);
        populatedArray.add(editText1);
        EditText editText2 = activity.findViewById(R.id.editText2);
        populatedArray.add(editText2);
        EditText editText3 = activity.findViewById(R.id.editText3);
        populatedArray.add(editText3);
        EditText editText4 = activity.findViewById(R.id.editText4);
        populatedArray.add(editText4);
        EditText editText5 = activity.findViewById(R.id.editText5);
        populatedArray.add(editText5);
        EditText editText6 = activity.findViewById(R.id.editText6);
        populatedArray.add(editText6);
        EditText editText7 = activity.findViewById(R.id.editText7);
        populatedArray.add(editText7);

        return populatedArray;
    }


}
