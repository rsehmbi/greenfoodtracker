package com.t.teamten.GreenFoodTracker.Calculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.t.teamten.GreenFoodTracker.R;
import com.t.teamten.GreenFoodTracker.Result_Pages.Calculation_Result_Activity;

import java.util.ArrayList;
import java.util.LinkedList;

import com.t.teamten.GreenFoodTracker.Firebase_User.User_Profile_Activity;


public class Calculator_Activity extends AppCompatActivity {

    private static final int MAX_DROPDOWNS = 7; // from the UI elements.
    private TextView mIntroText;
    private ArrayList<Spinner> mSpinnerArray;
    private ArrayList<SeekBar> mSeekbarArray;
    private Button mResultsButton;
    private Button mAddButton;
    private Button mSubtractButton;
    public static final String DATA_PASSED_FROM_MAINACTIVITY = "ResultStoredAsObject";
    // for use in determining how data is entered by the user
    private LinkedList<Spinner> hiddenSpinnerQueue;
    private LinkedList<SeekBar> hiddenSeekbarQueue;

    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //queue implemented as java's linkedlist data type.
        hiddenSpinnerQueue = new LinkedList<>();
        hiddenSeekbarQueue = new LinkedList<>();

        mSeekbarArray = new Calc_Activity_Array_Handler(this).populateSeekBarArray();
        mSpinnerArray =  new Calc_Activity_Array_Handler(this).populateSpinnerArray();
        mAddButton = findViewById(R.id.addButton);
        mSubtractButton = findViewById(R.id.removeButton);
        final Context context = this.getApplicationContext();

        for (int i = mSeekbarArray.size() - 1; i > 0; i--)
        { //starts at 1, ignores the first
            if (mSeekbarArray.get(i).getProgress()==0&& !mSeekbarArray.isEmpty())
            {
                mSeekbarArray.get(i).setVisibility(View.GONE);
                mSpinnerArray.get(i).setVisibility(View.GONE);
                hiddenSeekbarQueue.add(mSeekbarArray.get(i));
                hiddenSpinnerQueue.add(mSpinnerArray.get(i)); //queue is FIFO
            }
            else {
                break;
            }
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.food_array, android.R.layout.simple_spinner_dropdown_item);
        for(Spinner currentSpinner : mSpinnerArray) { //sets the items to be listed in each drop down menu
            currentSpinner.setAdapter(adapter);
        }

        mIntroText = findViewById(R.id.introInstructionText);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (hiddenSeekbarQueue.isEmpty() || hiddenSpinnerQueue.isEmpty()) {
                    return;
                }
                else {
                    SeekBar seekBarFront = hiddenSeekbarQueue.removeLast();
                    Spinner spinnerFront = hiddenSpinnerQueue.removeLast();
                    seekBarFront.setVisibility(View.VISIBLE);
                    spinnerFront.setVisibility(View.VISIBLE);
                }
            }
        });

        mSubtractButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = mSeekbarArray.size() - 1; i > 0; i--) { //starts at (7), ignores the first
                    if (mSeekbarArray.get(i).getVisibility() == View.VISIBLE) {
                        mSeekbarArray.get(i).setVisibility(View.GONE);
                        mSpinnerArray.get(i).setVisibility(View.GONE);
                        mSeekbarArray.get(i).setProgress(0);
                        hiddenSeekbarQueue.add(mSeekbarArray.get(i));
                        hiddenSpinnerQueue.add(mSpinnerArray.get(i)); //queue is FIFO
                        break;
                    }

                }
            }
        });

        mResultsButton = findViewById(R.id.resultsButton);
        mResultsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //submit result function, load up the values in each and send it to the next activity.
                Calculator_Data sendToResultsActivity;
                Integer seekBarValue;
                String spinnerValue;
                ArrayList<Pair<String, Integer>> pairArraySentToActivity = new ArrayList<>();
                Pair<String, Integer> resultPair;
                for (int i = 0; i < MAX_DROPDOWNS; i++) {
                    if (mSeekbarArray.get(i).getProgress()!=0 &&
                            !mSpinnerArray.get(i).getSelectedItem().toString().isEmpty()
                            ) {
                        //unentered edittexts and spinners will not be added to the array

                        seekBarValue = mSeekbarArray.get(i).getProgress();
                        spinnerValue = mSpinnerArray.get(i).getSelectedItem().toString();
                        resultPair = new Pair<>(spinnerValue, seekBarValue);
                        pairArraySentToActivity.add(resultPair);
                    }
                }
                sendToResultsActivity = new Calculator_Data(pairArraySentToActivity);


                Intent intent = new Intent(Calculator_Activity.this, Calculation_Result_Activity.class);
                intent.putExtra(DATA_PASSED_FROM_MAINACTIVITY, sendToResultsActivity);
                startActivity(intent);
            }
        });

    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){

                }else if(x1 > x2){
                    Intent i = new Intent(Calculator_Activity.this, User_Profile_Activity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}

