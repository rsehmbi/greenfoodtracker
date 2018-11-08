package com.t.teamten.greenfoodtracker.loginactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.t.teamten.greenfoodtracker.R;

import firebaseuser.User;

public class UserRegisteration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth auth;
    private DatabaseReference ref;

    private String email;
    private String password;
    private String gender;
    private String age;
    private String city;
    private String firstName;
    private String lastName;
    private String pledge;


    private EditText emailText;
    private EditText passwordText;
    private EditText ageText;
    private EditText firstNameText;
    private EditText lastNameText;
    private Spinner citySpinner;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registeration);

        radioGroup = findViewById(R.id.group);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        ageText = findViewById(R.id.ageText);
        citySpinner =  findViewById(R.id.citySpinner);
        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        signUpButton = findViewById(R.id.signUpButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.city_name,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);
        citySpinner.setOnItemSelectedListener(this);
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("users");
    }

    public void RegisterActivity(View view) {

        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        gender = radioButton.getText().toString();
        age = ageText.getText().toString();
        firstName = firstNameText.getText().toString();
        lastName = lastNameText.getText().toString();
        pledge = "";


        final ProgressDialog progressDialog =ProgressDialog.show(UserRegisteration.this,"Please wait..","Processing..",true);
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            progressDialog.dismiss();

            if (task.isSuccessful())
            { Toast.makeText(UserRegisteration.this,"Successful",Toast.LENGTH_SHORT).show();
                storeUserToDatabase();
                Intent moveToHomeScreen = new Intent(UserRegisteration.this, HomeScreen.class);
                startActivity(moveToHomeScreen);
            } else {
                Toast.makeText(UserRegisteration.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    public void storeUserToDatabase() {
        String userId = auth.getCurrentUser().getUid();
        User user = new User(userId, email, password, gender, age, city, firstName, lastName, pledge);

        ref.child(userId).setValue(user);
    }

    public void  rbClick(View view)
    {
        int rbId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(rbId);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        city= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
