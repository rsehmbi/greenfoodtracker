package com.t.teamten.greenfoodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.t.teamten.greenfoodtracker.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_DROPDOWNS = 7;
    private TextView mIntroText;
    private Switch mDataEntrySwapSwitch;
    private ArrayList<Spinner> mSpinnerArray;
    private ArrayList<EditText> mEditTextArray;
    private Button mResultsButton;
    private static final int SET_TO_NUMBER = 1;
    private static final int SET_TO_PERCENT = 0;
    private int switchStatus = SET_TO_PERCENT;
    // for use in determining how data is entered by the user
    private ArrayList<Spinner> populateSpinnerArray() { //this function allows for looping code in the spinners.
        ArrayList<Spinner> populatedArray = new ArrayList<>();
        Spinner spinner1 = findViewById(R.id.spinner1);
        populatedArray.add(spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        populatedArray.add(spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        populatedArray.add(spinner3);
        Spinner spinner4 = findViewById(R.id.spinner4);
        populatedArray.add(spinner4);
        Spinner spinner5 = findViewById(R.id.spinner5);
        populatedArray.add(spinner5);
        Spinner spinner6 = findViewById(R.id.spinner6);
        populatedArray.add(spinner6);
        Spinner spinner7 = findViewById(R.id.spinner7);
        populatedArray.add(spinner7);

        return populatedArray;
    }

    private ArrayList<EditText> populateEditTextArray() {
        ArrayList<EditText> populatedArray = new ArrayList<>();
        EditText editText1 = findViewById(R.id.editText1);
        populatedArray.add(editText1);
        EditText editText2 = findViewById(R.id.editText2);
        populatedArray.add(editText2);
        EditText editText3 = findViewById(R.id.editText3);
        populatedArray.add(editText3);
        EditText editText4 = findViewById(R.id.editText4);
        populatedArray.add(editText4);
        EditText editText5 = findViewById(R.id.editText5);
        populatedArray.add(editText5);
        EditText editText6 = findViewById(R.id.editText6);
        populatedArray.add(editText6);
        EditText editText7 = findViewById(R.id.editText7);
        populatedArray.add(editText7);

        return populatedArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinnerArray = new ArrayList<>();

        mResultsButton = findViewById(R.id.resultsButton);
        mResultsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //submit result function, load up the values in each and send it to the next activity.
                CalculatorActivityData sendToResultsActivity;
            }
        });

        mIntroText = findViewById(R.id.introInstructionText);
        mDataEntrySwapSwitch = findViewById(R.id.dataEntrySwapSwitch);
        mDataEntrySwapSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIntroText.setText(R.string.intro_description_percentage);
                    switchStatus = SET_TO_PERCENT;
                }
                else {
                    mIntroText.setText(R.string.intro_description_number);
                    switchStatus = SET_TO_NUMBER;
                }
            }
        });

        mSpinnerArray = populateSpinnerArray();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.food_array, android.R.layout.simple_spinner_dropdown_item);
        for(Spinner currentSpinner : mSpinnerArray) { //sets the items to be listed in each drop down menu
            currentSpinner.setAdapter(adapter);
        }
    }
}
