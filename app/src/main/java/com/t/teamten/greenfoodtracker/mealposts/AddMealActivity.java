package com.t.teamten.greenfoodtracker.mealposts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.t.teamten.greenfoodtracker.R;
// java class for addMealActivity
public class AddMealActivity extends AppCompatActivity {
    AutoCompleteTextView mMainProtein;
    EditText mName;
    AutoCompleteTextView mLocation;
    EditText mRestaurant;
    EditText mDescription;
    Button mImageAddButton;
    Button mFinishButton;
    Meal userMeal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_acitivty);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mMainProtein = findViewById(R.id.autoCompleteTextView);
        mName = findViewById(R.id.editTextName);
        mDescription = findViewById(R.id.editTextDescription);
        mLocation = findViewById(R.id.editTextLocation);
        mRestaurant = findViewById(R.id.editTextRestaurant);
        mImageAddButton = findViewById(R.id.imageButton);
        mFinishButton = findViewById(R.id.buttonFinish);
        String[] arrayAdapter = getResources().getStringArray(R.array.food_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, arrayAdapter);
        mMainProtein.setAdapter(adapter);
        String[] locationArray = getResources().getStringArray(R.array.city_name);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, locationArray);
        mLocation.setAdapter(cityAdapter);
        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: add meal data, and images to firebase.
                if (mDescription.getText().toString().isEmpty()) {
                    userMeal = new Meal(mName.getText().toString(), mLocation.getText().toString(), mRestaurant.getText().toString(),
                            mMainProtein.getText().toString(), "");
                }
                else {
                    userMeal = new Meal(mName.getText().toString(), mLocation.getText().toString(), mRestaurant.getText().toString(),
                            mMainProtein.getText().toString(), mDescription.getText().toString());
                }
            }
        });



    }

}
