package com.t.teamten.greenfoodtracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import userdata.FireBaseUserData;

import static java.lang.Integer.parseInt;

public class UserRegisteration extends AppCompatActivity {

    private FirebaseAuth Auth;
    private EditText Username;
    private EditText Password;
    private EditText City;
    private EditText Age;
    private EditText First_Name;
    private EditText Last_Name;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registeration);

        Username =(EditText) findViewById(R.id.username);
        Password=(EditText) findViewById(R.id.userpassword);
        City = (EditText) findViewById(R.id.city);
        Age = findViewById(R.id.age);
        First_Name = findViewById(R.id.first_name);
        Last_Name = findViewById(R.id.last_name);
        Auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void RegisterActivity(View view) {
        final ProgressDialog progressDialog =ProgressDialog.show(UserRegisteration.this,"Please wait..","Processing..",true);
        Auth.createUserWithEmailAndPassword(Username.getText().toString(),Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            progressDialog.dismiss();

            if (task.isSuccessful())
            {
                Toast.makeText(UserRegisteration.this,"Successful",Toast.LENGTH_SHORT).show();

                FirebaseUser curren_user = Auth.getCurrentUser();
                FireBaseUserData fireBaseUserData = new FireBaseUserData();
                fireBaseUserData.set_age(parseInt(Age.getText().toString()));
                fireBaseUserData.set_city(City.getText().toString());
                fireBaseUserData.set_first_name(First_Name.getText().toString());
                fireBaseUserData.set_last_name(Last_Name.getText().toString());
                databaseReference.child(curren_user.getUid()).setValue(fireBaseUserData);
                Intent movetoLogin = new Intent(UserRegisteration.this,FirebaseLogin.class);
                startActivity(movetoLogin);
            }
            else
            {
                Toast.makeText(UserRegisteration.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
            }
            }
        });

    }
}
