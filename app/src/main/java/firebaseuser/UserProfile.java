package firebaseuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;

import java.util.ArrayList;
import java.util.Objects;

public class UserProfile extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText age;
    EditText email;
    EditText password;
    TextView pledge;
    Spinner city;
    Spinner gender;
    ImageView profile_pic;
    String gender_s;
    String city_s;


    //for retriving user information
    private String user_id;
    private User userInfoUpdate;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        user_id = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        userInfoUpdate =  new User();

        //editable user information
        firstName = findViewById(R.id.first_name_editable_profile_page);
        lastName = findViewById(R.id.last_name_editable_profile_page);
        age = findViewById(R.id.age_editable_profile_page);
        email = findViewById(R.id.email_editable_profile_page);
        password = findViewById(R.id.password_editable_profile_page);
        city = findViewById(R.id.city_spinner_profile_page);
        gender = findViewById(R.id.gender_spinner_profile_page);
        profile_pic = findViewById(R.id.profile_pic_profile_page);
        pledge = findViewById(R.id.my_pledge_profile_page);


        //set un editable unless edit button is pressed
        firstName.setEnabled(false);
        lastName.setEnabled(false);
        age.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        city.setEnabled(false);
        gender.setEnabled(false);
        profile_pic.setEnabled(false);


        ArrayAdapter<CharSequence> adapterforcity = ArrayAdapter.createFromResource(this,R.array.city_name,android.R.layout.simple_spinner_item);
        adapterforcity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapterforcity);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city_s = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserProfile.this,"City field is Empty", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<CharSequence> adapterforgender = ArrayAdapter.createFromResource(this,R.array.genderarray,android.R.layout.simple_spinner_item);
        adapterforgender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapterforgender);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender_s = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserProfile.this,"Gender field is Empty", Toast.LENGTH_SHORT).show();
            }
        });
    }//end of oncreat


    private ArrayList<Double> countAndDisplay(DataSnapshot dataSnapshot)
    {
        double count_num_of_user = 0;
        double count_total_pledge_amount = 0;
        ArrayList<Double> array = new ArrayList<>();
        for(DataSnapshot ds:dataSnapshot.getChildren())
        {

            User user = ds.getValue(User.class);
            if(user.getUserId().equals(user_id))
            {
                userInfoUpdate = user;

            }
            count_num_of_user++;
            count_total_pledge_amount = count_total_pledge_amount+Double.parseDouble(user.getPledge());

        }
        array.add(count_num_of_user);
        array.add(count_total_pledge_amount/1000.0);
        firstName.setText(userInfoUpdate.getFirstName());
        lastName.setText(userInfoUpdate.getLastName());
        email.setText(userInfoUpdate.getEmail());
        age.setText(userInfoUpdate.getAge());
        password.setText("****************");
        pledge.setText(userInfoUpdate.getPledge());
        setSpinText(city, userInfoUpdate.getCity());
        setSpinText(gender, userInfoUpdate.getGender());
        return array;

    }

    public void editProfile(View view)
    {
        firstName.setEnabled(true);
        lastName.setEnabled(true);
        age.setEnabled(true);
        email.setEnabled(true);
        password.setEnabled(true);
        city.setEnabled(true);
        gender.setEnabled(true);

    }

    public void save_profile(View view)
    {
        userInfoUpdate.setPassword(password.getText().toString());
        userInfoUpdate.setEmail(email.getText().toString());
        userInfoUpdate.setCity(city.getSelectedItem().toString());
        userInfoUpdate.setGender(gender.getSelectedItem().toString());
        userInfoUpdate.setFirstName(firstName.getText().toString());
        userInfoUpdate.setLastName(lastName.getText().toString());
        userInfoUpdate.setAge(age.getText().toString());
        databaseReference.child(user_id).setValue(userInfoUpdate);
        firstName.setEnabled(false);
        lastName.setEnabled(false);
        age.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        city.setEnabled(false);
        gender.setEnabled(false);
    }

    public void direct_to_calculator(View view)
    {
        Intent intent = new Intent(this, CalcActivity.class);
        startActivity(intent);
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
