package com.t.teamten.greenfoodtracker;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUser extends AppCompatActivity {

    EditText Name;
    public  EditText Age;
    private Button Login;
    SharedPreferences myprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        myprefs = getPreferences(MODE_PRIVATE);

        iniialization();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String name = Name.getText().toString();
                    int age = Integer.parseInt(Age.getText().toString());

                    SharedPreferences.Editor editor = myprefs.edit();
                    editor.putString("namekey",name);
                    editor.putInt("agekey",age);
                    editor.commit();
                    NextPage();


            }
        });
    }

    private void iniialization()
    {
        Name = (EditText) findViewById(R.id.NameID);
        Age =(EditText)findViewById(R.id.AgeID);
        Login = (Button)findViewById(R.id.Login);
        readPreferences();
    }

    public void NextPage()
    {
        Intent intent = new Intent(this,HomeScreen.class);
        startActivity (intent);
    }

    public void readPreferences(){

        String st1 = myprefs.getString("namekey","");
        Name.setText(st1);

        int val1 = myprefs.getInt("agekey",0);
        Age.setText(String.valueOf(val1));

    }

}