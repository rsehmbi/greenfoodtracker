package com.t.teamten.GreenFoodTracker.Login_And_Registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.t.teamten.GreenFoodTracker.R;
import com.t.teamten.GreenFoodTracker.Homescreen.HomeScreen;

//Login class for firebase so that the user can Login in to user the application.
public class Firebase_User_Login_Activity extends AppCompatActivity {

    private FirebaseAuth Auth;
    private EditText Username;
    private EditText Password;

    String user;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);

        Username =(EditText) findViewById(R.id.UserID);
        Password=(EditText) findViewById(R.id.Password);
        Auth = FirebaseAuth.getInstance();

    }

    public void Registeruser(View view) {
        user = Username.getText().toString();
        password = Password.getText().toString();

        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            Toast.makeText(Firebase_User_Login_Activity.this, "Please check Credentials!!", Toast.LENGTH_SHORT).show();

        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(Firebase_User_Login_Activity.this, "Please wait..", "Processing..", true);

            Auth.signInWithEmailAndPassword(Username.getText().toString(), Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        Intent movetoHomescreen = new Intent(Firebase_User_Login_Activity.this, HomeScreen.class);
                        startActivity(movetoHomescreen);
                          Toast.makeText(Firebase_User_Login_Activity.this, "Succesfull", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Firebase_User_Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }

    public void movetoregisterationpage(View view) {
        Intent movetoregister = new Intent(Firebase_User_Login_Activity.this,User_Registeration_Activity.class);
        startActivity(movetoregister);
    }
}
