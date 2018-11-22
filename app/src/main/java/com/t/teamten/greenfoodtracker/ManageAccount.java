package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.homescreenactivity.drawerfromside;
import com.t.teamten.greenfoodtracker.loginactivities.UserRegisteration;
import com.t.teamten.greenfoodtracker.profileicon.ProfileIconList;

import java.util.Objects;

import firebaseuser.User;
//Change and display profile picture
public class ManageAccount extends AppCompatActivity {
    private DatabaseReference reference;

    private EditText first_name;
    private EditText last_name;
    private EditText birth_year;
    private EditText password;
    private EditText email;
    private Spinner genderspinner;
    private Spinner citySpinner;
    private String gender;
    private String city;

    private ImageView profileView;
    private Button updateButton;
    private Spinner imageNameSpinner;
    private ProfileIconList iconList;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String user_id;
    private String user_id2;
    private User user_info_update_and_dispay;
    private String imageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        user_id = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        user_info_update_and_dispay =  new User();
        displaypic();

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.EmailID);
        birth_year = findViewById(R.id.age);
        password = findViewById(R.id.password);
        password.setText("*****");
        genderspinner = (Spinner) findViewById(R.id.genderId);
        citySpinner =  findViewById(R.id.citySpinner);
        ArrayAdapter<CharSequence> adapterforgender = ArrayAdapter.createFromResource(this,R.array.genderarray,android.R.layout.simple_spinner_item);
        adapterforgender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(adapterforgender);

        genderspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ManageAccount.this,"Gender field is Empty", Toast.LENGTH_SHORT).show();
            }
        });

        iconList = new ProfileIconList(this);

        profileView = (ImageView) findViewById(R.id.profileChangeView);
        imageNameSpinner = (Spinner) findViewById(R.id.imageNameSpinner);
        updateButton = (Button) findViewById(R.id.updateProfileButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageName = imageNameSpinner.getSelectedItem().toString();
                int imageid = iconList.getDrawableId(imageName);
                profileView.setImageResource(imageid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    updata_profilepic(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

            }
        });

        ArrayAdapter<CharSequence> adapterforcity = ArrayAdapter.createFromResource(this,R.array.city_name,android.R.layout.simple_spinner_item);
        adapterforcity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapterforcity);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ManageAccount.this,"City field is Empty", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void displaypic() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()) {

                    User user = ds.getValue(User.class);
                    if (user.getUserId().equals(user_id)) {
                        user_info_update_and_dispay = user;

                    }
                }
                profileView.setImageResource(iconList.getDrawableId(user_info_update_and_dispay.getProfileIcon()));
                first_name.setText(user_info_update_and_dispay.getFirstName());
                last_name.setText(user_info_update_and_dispay.getLastName());
                birth_year.setText(user_info_update_and_dispay.getAge());
                password.setText(user_info_update_and_dispay.getPassword());
                email.setText(user_info_update_and_dispay.getEmail());
                setSpinText(genderspinner,user_info_update_and_dispay.getGender());
                setSpinText(citySpinner,user_info_update_and_dispay.getCity());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updata_profilepic(DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds:dataSnapshot.getChildren()) {

            User user = ds.getValue(User.class);
            if (user.getUserId().equals(user_id)) {
                user_info_update_and_dispay = user;

            }
        }
        user_info_update_and_dispay.setFirstName(first_name.getText().toString());
        user_info_update_and_dispay.setLastName(last_name.getText().toString());
        user_info_update_and_dispay.setAge(birth_year.getText().toString());
        user_info_update_and_dispay.setProfileIcon(imageName);
        user_info_update_and_dispay.setEmail(email.getText().toString());
        user_info_update_and_dispay.setGender(genderspinner.getSelectedItem().toString());
        user_info_update_and_dispay.setCity(citySpinner.getSelectedItem().toString());
        user_info_update_and_dispay.setPassword(password.getText().toString());
        databaseReference.child(user_id).setValue(user_info_update_and_dispay);

        Toast.makeText(ManageAccount.this,"Your data is Save",Toast.LENGTH_SHORT).show();
        Intent movetoHome = new Intent(ManageAccount.this,drawerfromside.class);
        startActivity(movetoHome);
    }

  /*  public void displayCurrentProfile(View view) {
        databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()) {

                    User user = ds.getValue(User.class);
                    if (user.getUserId().equals(user_id)) {
                        user_info_update_and_dispay = user;

                    }
                }
                profileView.setImageResource(iconList.getDrawableId(user_info_update_and_dispay.getProfileIcon()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    */
  public void setSpinText(Spinner spin, String text)
  {
      for(int i= 0; i < spin.getAdapter().getCount(); i++)
      {
          if(spin.getAdapter().getItem(i).toString().contains(text))
          {
              spin.setSelection(i);
          }
      }

  }
}
