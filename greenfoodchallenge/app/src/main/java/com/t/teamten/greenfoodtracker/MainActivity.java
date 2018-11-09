package com.t.teamten.greenfoodtracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import userdata.UserData;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_DROPDOWNS = 7; // from the UI elements.
    private TextView mIntroText;
    private ArrayList<Spinner> mSpinnerArray;
    private ArrayList<EditText> mEditTextArray;
    private Button mResultsButton;
    private Button mAddButton;
    private Button mSubtractButton;
    public static final String DATA_PASSED_FROM_MAINACTIVITY = "ResultStoredAsObject";
    // for use in determining how data is entered by the user
    private LinkedList<Spinner> hiddenSpinnerQueue;
    private LinkedList<EditText> hiddenEditTextQueue;
    private UserData userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //queue implemented as java's linkedlist data type.
        hiddenSpinnerQueue = new LinkedList<>();
        hiddenEditTextQueue = new LinkedList<>();

        mEditTextArray = new MainActivityArrayHandler(this).populateEditTextArray();
        mSpinnerArray =  new MainActivityArrayHandler(this).populateSpinnerArray();
        mAddButton = findViewById(R.id.addButton);
        mSubtractButton = findViewById(R.id.removeButton);
        final Context context = this.getApplicationContext();

        for (int i = mEditTextArray.size() - 1; i > 0; i--) { //starts at 1, ignores the first
            if (mEditTextArray.get(i).getText().toString().isEmpty() && !mEditTextArray.isEmpty()) {
                mEditTextArray.get(i).setVisibility(View.GONE);
                mSpinnerArray.get(i).setVisibility(View.GONE);
                hiddenEditTextQueue.add(mEditTextArray.get(i));
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
                if (hiddenEditTextQueue.isEmpty() || hiddenSpinnerQueue.isEmpty()) {
                    return;
                }
                else {
                    EditText editTextFront = hiddenEditTextQueue.removeLast();
                    Spinner spinnerFront = hiddenSpinnerQueue.removeLast();
                    editTextFront.setVisibility(View.VISIBLE);
                    spinnerFront.setVisibility(View.VISIBLE);
                }
            }
        });

        mSubtractButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = mEditTextArray.size() - 1; i > 0; i--) { //starts at (7), ignores the first
                    if (mEditTextArray.get(i).getVisibility() == View.VISIBLE) {
                        mEditTextArray.get(i).setVisibility(View.GONE);
                        mSpinnerArray.get(i).setVisibility(View.GONE);
                        mEditTextArray.get(i).setText("");
                        hiddenEditTextQueue.add(mEditTextArray.get(i));
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
                CalculatorActivityData sendToResultsActivity;
                Integer editTextValue;
                String spinnerValue;
                ArrayList<Pair<String, Integer>> pairArraySentToActivity = new ArrayList<>();
                Pair<String, Integer> resultPair;
                for (int i = 0; i < MAX_DROPDOWNS; i++) {
                    if (!mEditTextArray.get(i).getText().toString().isEmpty() &&
                            !mSpinnerArray.get(i).getSelectedItem().toString().isEmpty()
                            && mEditTextArray.get(i).getText() != null) {
                        //unentered edittexts and spinners will not be added to the array

                        editTextValue = Integer.valueOf(mEditTextArray.get(i).getText().toString());
                        spinnerValue = mSpinnerArray.get(i).getSelectedItem().toString();
                        resultPair = new Pair<>(spinnerValue, editTextValue);
                        pairArraySentToActivity.add(resultPair);
                    }
                }
                sendToResultsActivity = new CalculatorActivityData(pairArraySentToActivity);
                userdata = new UserData(context);
                for (Pair currentPair : sendToResultsActivity.getArrayListOfPair()) {
                    userdata.addUserList(currentPair.first.toString(), (int) currentPair.second);
                }

                try {
                    userdata.overWriteCsvFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, ResultScreenFirst.class);
                intent.putExtra(DATA_PASSED_FROM_MAINACTIVITY, sendToResultsActivity);
                startActivity(intent);
            }
        });

    }
}
