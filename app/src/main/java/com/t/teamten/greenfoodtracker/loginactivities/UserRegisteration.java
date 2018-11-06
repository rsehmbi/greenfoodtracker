package com.t.teamten.greenfoodtracker.loginactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.R;

import firebase_user.User;

public class UserRegisteration extends AppCompatActivity {
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
    private EditText genderText;
    private EditText ageText;
    private EditText cityText;
    private EditText firstNameText;
    private EditText lastNameText;

    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registeration);

        emailText = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        genderText = (EditText) findViewById(R.id.genderText);
        ageText = (EditText) findViewById(R.id.ageText);
        cityText = (EditText) findViewById(R.id.cityText);
        firstNameText = (EditText) findViewById(R.id.firstNameText);
        lastNameText = (EditText) findViewById(R.id.lastNameText);

        signUpButton = (Button) findViewById(R.id.signUpButton);

        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("users");
    }

    public void RegisterActivity(View view) {

        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        gender = genderText.getText().toString();
        age = ageText.getText().toString();
        city = cityText.getText().toString();
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
}
