package com.t.teamten.greenfoodtracker.mealposts;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.t.teamten.greenfoodtracker.R;

import java.lang.reflect.Array;

public class AddMealActivity extends AppCompatActivity {
    AutoCompleteTextView mMainProtein;
    EditText mName;
    EditText mLocation;
    EditText mDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_acitivty);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mMainProtein = findViewById(R.id.autoCompleteTextView);
        mName = findViewById(R.id.editTextName);
        mDescription = findViewById(R.id.editTextDescription);
        mLocation = findViewById(R.id.editTextLocation);
        setSupportActionBar(toolbar);
        String[] arrayAdapter = getResources().getStringArray(R.array.food_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, arrayAdapter);
        mMainProtein.setAdapter(adapter);

    }

}
