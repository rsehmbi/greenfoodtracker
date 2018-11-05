package com.t.teamten.greenfoodtracker.loginactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.t.teamten.greenfoodtracker.R;

public class UserRegisteration extends AppCompatActivity {

    private FirebaseAuth Auth;
    private EditText Username;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registeration);

        Username =(EditText) findViewById(R.id.username);
        Password=(EditText) findViewById(R.id.userpassword);
        Auth = FirebaseAuth.getInstance();
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
