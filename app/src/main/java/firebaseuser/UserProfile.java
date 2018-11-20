package firebaseuser;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.t.teamten.greenfoodtracker.loginactivities.HomeScreen;
import com.t.teamten.greenfoodtracker.loginactivities.UserRegisteration;
import com.t.teamten.greenfoodtracker.pledgeposts.PledgePost;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import firebaseuser.User;
//Edit and save, view user profile
public class UserProfile extends AppCompatActivity {
    //Edit text and save, edit button
    private EditText first_name;
    private EditText last_name;
    private EditText birth_year;
    private EditText email;
    private EditText password;
    private Spinner city;
    private Spinner gender;
    private Button save;
    private Button edit;
    float x1,x2,y1,y2;
    String gender_s;
    String city_s;
    //Four headlines
    private TextView my_pledge;
    private TextView count_num_of_ppl;
    private TextView average_pledge;
    private TextView total_pledge;

    //

    private String calculate_average_amount;

    //for retriving user information
    private String user_id;
    private User user_info_update_and_dispay;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //firebase access
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        user_id = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        user_info_update_and_dispay =  new User();

        //editable user information
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        birth_year = findViewById(R.id.age);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        city = findViewById(R.id.city);
        gender = findViewById(R.id.genderdisplay);

        //headlines
        my_pledge  = findViewById(R.id.pledge_amount);
        count_num_of_ppl = findViewById(R.id.ppl_count);
        average_pledge = findViewById(R.id.average_pledge);
        total_pledge = findViewById(R.id.total_pledge);



        //set un editable unless edit button is pressed
        first_name.setEnabled(false);
        last_name.setEnabled(false);
        birth_year.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        city.setEnabled(false);
        gender.setEnabled(false);
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






        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Double> count_firbase_data = count_and_display(dataSnapshot);
                DecimalFormat df = new DecimalFormat("0.00");
                DecimalFormat df1 = new DecimalFormat("0.0000");
                DecimalFormat df2 = new DecimalFormat("0");
                double format_average_amount = count_firbase_data.get(1)/count_firbase_data.get(0);
                if (format_average_amount>0.0)
                    calculate_average_amount = df.format(format_average_amount);
                else
                    calculate_average_amount = df1.format(format_average_amount);
                average_pledge.setText(calculate_average_amount);
                total_pledge.setText(df2.format(count_firbase_data.get(1)));
                count_num_of_ppl.setText(df2.format(count_firbase_data.get(0)));

                /*count_num_of_ppl.setText(String.valueOf((int)(count_firbase_data.get(0)/1.0)));
                total_pledge.setText(String.valueOf((int)(count_firbase_data.get(1)/1.0)));
                calculate_average_amount = String.valueOf(count_firbase_data.get(1)/count_firbase_data.get(0));
                average_pledge.setText(calculate_average_amount);*/


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    Intent i = new Intent(UserProfile.this,CalcActivity.class);
                    startActivity(i);
                }else if(x1 > x2){
                    Intent i = new Intent(UserProfile.this,HomeScreen.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

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
        birth_year.setText(user_info_update_and_dispay.getAge());
        password.setText("****************");

        my_pledge.setText(user_info_update_and_dispay.getPledge());
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
        birth_year.setEnabled(true);
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
        user_info_update_and_dispay.setAge(birth_year.getText().toString());
        databaseReference.child(user_id).setValue(user_info_update_and_dispay);

        first_name.setEnabled(false);
        last_name.setEnabled(false);
        birth_year.setEnabled(false);
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
