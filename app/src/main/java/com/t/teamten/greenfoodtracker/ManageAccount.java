package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.t.teamten.greenfoodtracker.homescreenactivity.DrawerFromSide;
import com.t.teamten.greenfoodtracker.profileicon.ProfileIconList;

import java.util.Objects;

import firebaseuser.User;
//Change and display profile picture
public class ManageAccount extends AppCompatActivity {
    private DatabaseReference reference;

    private EditText firstName;
    private EditText lastName;
    private EditText birthYear;
    private EditText password;
    private EditText email;
    private Spinner genderSpinner;
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
    private User updateAndDisplayInfo;
    private String imageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        user_id = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        updateAndDisplayInfo =  new User();
        displayPicture();

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.EmailID);
        birthYear = findViewById(R.id.age);
        password = findViewById(R.id.password);
        password.setText("*****");
        genderSpinner = findViewById(R.id.genderId);
        citySpinner =  findViewById(R.id.citySpinner);
        ArrayAdapter<CharSequence> adapterforgender = ArrayAdapter.createFromResource(this,R.array.genderarray,android.R.layout.simple_spinner_item);
        adapterforgender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapterforgender);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        profileView = findViewById(R.id.profileChangeView);
        imageNameSpinner = findViewById(R.id.imageNameSpinner);
        updateButton = findViewById(R.id.updateProfileButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageName = imageNameSpinner.getSelectedItem().toString();
                int imageid = iconList.getDrawableId(imageName);
                profileView.setImageResource(imageid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    updateProfilePicture(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

            }
        });

        ArrayAdapter<CharSequence> adapterForCity = ArrayAdapter.createFromResource(this,R.array.city_name,android.R.layout.simple_spinner_item);
        adapterForCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapterForCity);
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

    private void displayPicture() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()) {

                    User user = ds.getValue(User.class);
                    if (user.getUserId().equals(user_id)) {
                        updateAndDisplayInfo = user;

                    }
                }
                profileView.setImageResource(iconList.getDrawableId(updateAndDisplayInfo.getProfileIcon()));
                firstName.setText(updateAndDisplayInfo.getFirstName());
                lastName.setText(updateAndDisplayInfo.getLastName());
                birthYear.setText(updateAndDisplayInfo.getAge());
                password.setText(updateAndDisplayInfo.getPassword());
                email.setText(updateAndDisplayInfo.getEmail());
                setSpinText(genderSpinner, updateAndDisplayInfo.getGender());
                setSpinText(citySpinner, updateAndDisplayInfo.getCity());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updateProfilePicture(DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds:dataSnapshot.getChildren()) {

            User user = ds.getValue(User.class);
            if (user.getUserId().equals(user_id)) {
                updateAndDisplayInfo = user;

            }
        }
        updateAndDisplayInfo.setFirstName(firstName.getText().toString());
        updateAndDisplayInfo.setLastName(lastName.getText().toString());
        updateAndDisplayInfo.setAge(birthYear.getText().toString());
        updateAndDisplayInfo.setProfileIcon(imageName);
        updateAndDisplayInfo.setEmail(email.getText().toString());
        updateAndDisplayInfo.setGender(genderSpinner.getSelectedItem().toString());
        updateAndDisplayInfo.setCity(citySpinner.getSelectedItem().toString());
        updateAndDisplayInfo.setPassword(password.getText().toString());
        databaseReference.child(user_id).setValue(updateAndDisplayInfo);

        Toast.makeText(ManageAccount.this,"Your data is Saved",Toast.LENGTH_SHORT).show();
        Intent moveToHome = new Intent(ManageAccount.this,DrawerFromSide.class);
        startActivity(moveToHome);
    }

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
