package firebaseuser;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;
import com.t.teamten.greenfoodtracker.homescreenactivity.HomeScreen;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class User_Profile extends AppCompatActivity {
    EditText first_name;
    EditText last_name;
    EditText age;
    EditText email;
    EditText password;
    TextView pledge;
    Spinner city;
    Spinner gender;
    ImageView profile_pic;
    Button save;
    float x1,x2,y1,y2;
    String gender_s;
    String city_s;


    //for retriving user information
    private String user_id;
    private User user_info_update_and_dispay;
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
        user_info_update_and_dispay =  new User();

        //editable user information
        first_name = findViewById(R.id.first_name_editable_profile_page);
        last_name = findViewById(R.id.last_name_editable_profile_page);
        age = findViewById(R.id.age_editable_profile_page);
        email = findViewById(R.id.email_editable_profile_page);
        password = findViewById(R.id.password_editable_profile_page);
        city = findViewById(R.id.city_spinner_profile_page);
        gender = findViewById(R.id.gender_spinner_profile_page);
        profile_pic = findViewById(R.id.profile_pic_profile_page);
        pledge = findViewById(R.id.my_pledge_profile_page);


        //set un editable unless edit button is pressed
        first_name.setEnabled(false);
        last_name.setEnabled(false);
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
                Toast.makeText(User_Profile.this,"City field is Empty", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(User_Profile.this,"Gender field is Empty", Toast.LENGTH_SHORT).show();
            }
        });
    }//end of oncreat



    /*public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    Intent i = new Intent(Realtime_Pledge_Data.this,CalcActivity.class);
                    startActivity(i);
                }else if(x1 > x2){
                    Intent i = new Intent(Realtime_Pledge_Data.this,HomeScreen.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }*/

    private ArrayList<Double> count_and_display(DataSnapshot dataSnapshot)
    {
        double count_num_of_user = 0;
        double count_total_pledge_amount = 0;
        ArrayList<Double> array = new ArrayList<>();
        for(DataSnapshot ds:dataSnapshot.getChildren())
        {

            User user = ds.getValue(User.class);
            if(user.getUserId().equals(user_id))
            {
                user_info_update_and_dispay = user;

            }
            count_num_of_user++;
            count_total_pledge_amount = count_total_pledge_amount+Double.parseDouble(user.getPledge());

        }
        array.add(count_num_of_user);
        array.add(count_total_pledge_amount/1000.0);
        first_name.setText(user_info_update_and_dispay.getFirstName());
        last_name.setText(user_info_update_and_dispay.getLastName());
        email.setText(user_info_update_and_dispay.getEmail());
        age.setText(user_info_update_and_dispay.getAge());
        password.setText("****************");
        pledge.setText(user_info_update_and_dispay.getPledge());
        setSpinText(city,user_info_update_and_dispay.getCity());
        setSpinText(gender,user_info_update_and_dispay.getGender());
        return array;

    }



    /*public void enable_email(View view)
    {
        first_name.setEnabled(false);
        last_name.setEnabled(false);
        birth_year.setEnabled(false);
        email.setEnabled(true);
        password.setEnabled(false);
        city.setEnabled(false);
        gender.setEnabled(false);
        user_info_update_and_dispay.setEmail(email.getText().toString());
    }
    public void enable_password(View view)
    {
        first_name.setEnabled(false);
        last_name.setEnabled(false);
        birth_year.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(true);
        city.setEnabled(false);
        gender.setEnabled(false);
        user_info_update_and_dispay.setPassword(password.getText().toString());
    }
    public void enable_last_name(View view)
    {
        first_name.setEnabled(false);
        last_name.setEnabled(true);
        birth_year.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        city.setEnabled(false);
        gender.setEnabled(false);
        user_info_update_and_dispay.setLastName(last_name.getText().toString());
    }

    public void enable_first_name(View view)
    {
        first_name.setEnabled(true);
        last_name.setEnabled(false);
        birth_year.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        city.setEnabled(false);
        gender.setEnabled(false);
        user_info_update_and_dispay.setFirstName(first_name.getText().toString());
    }

    public void enable_city(View view)
    {
        first_name.setEnabled(false);
        last_name.setEnabled(false);
        birth_year.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        city.setEnabled(true);
        gender.setEnabled(false);
        user_info_update_and_dispay.setCity(city.getSelectedItem().toString());
    }

    public void enable_gender(View view)
    {
        first_name.setEnabled(false);
        last_name.setEnabled(false);
        birth_year.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        city.setEnabled(false);
        gender.setEnabled(true);
        user_info_update_and_dispay.setGender(gender.getSelectedItem().toString());
    }

    public void enable_age(View view)
    {
        first_name.setEnabled(false);
        last_name.setEnabled(false);
        birth_year.setEnabled(true);
        email.setEnabled(false);
        password.setEnabled(false);
        city.setEnabled(false);
        gender.setEnabled(false);
        user_info_update_and_dispay.setAge(birth_year.getText().toString());
    }*/
    public void edit_profile(View view)
    {
        first_name.setEnabled(true);
        last_name.setEnabled(true);
        age.setEnabled(true);
        email.setEnabled(true);
        password.setEnabled(true);
        city.setEnabled(true);
        gender.setEnabled(true);

    }

    public void save_profile(View view)
    {
        user_info_update_and_dispay.setPassword(password.getText().toString());
        user_info_update_and_dispay.setEmail(email.getText().toString());
        user_info_update_and_dispay.setCity(city.getSelectedItem().toString());
        user_info_update_and_dispay.setGender(gender.getSelectedItem().toString());
        user_info_update_and_dispay.setFirstName(first_name.getText().toString());
        user_info_update_and_dispay.setLastName(last_name.getText().toString());
        user_info_update_and_dispay.setAge(age.getText().toString());
        databaseReference.child(user_id).setValue(user_info_update_and_dispay);
        first_name.setEnabled(false);
        last_name.setEnabled(false);
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

    public void change_profile_pic()
    {

    }
}
