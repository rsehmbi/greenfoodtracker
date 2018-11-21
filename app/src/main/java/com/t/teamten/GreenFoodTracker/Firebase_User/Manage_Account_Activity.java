package com.t.teamten.GreenFoodTracker.Firebase_User;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.GreenFoodTracker.R;
import com.t.teamten.GreenFoodTracker.Profile_Icon.ProfileIconList;

import java.util.Objects;

//Change and display profile picture
public class Manage_Account_Activity extends AppCompatActivity {
    private DatabaseReference reference;

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
        user_info_update_and_dispay.setProfileIcon(imageName);
        databaseReference.child(user_id).setValue(user_info_update_and_dispay);


    }

    public void upDateInitial() {

    }

    public void displayCurrentProfile(View view) {
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
}
