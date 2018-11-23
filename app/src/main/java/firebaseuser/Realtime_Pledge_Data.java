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
import com.t.teamten.greenfoodtracker.homescreenactivity.DrawerFromSide;
import com.t.teamten.greenfoodtracker.homescreenactivity.HomeScreen;
import com.t.teamten.greenfoodtracker.loginactivities.FactsActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

//Edit and save, view user profile
public class Realtime_Pledge_Data extends AppCompatActivity {
    //Four headlines
    private TextView my_pledge;
    private TextView count_num_of_ppl;
    private TextView average_pledge;
    private TextView total_pledge;
    float x1,x2,y1,y2;

    //Average amount
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
        setContentView(R.layout.activity_realtime_pledge_data);


        //firebase access
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        user_id = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        user_info_update_and_dispay =  new User();


        //headlines
        my_pledge  = findViewById(R.id.pledge_amount);
        count_num_of_ppl = findViewById(R.id.ppl_count);
        average_pledge = findViewById(R.id.average_pledge);
        total_pledge = findViewById(R.id.total_pledge);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Double> count_firebase_data = count(dataSnapshot);
                DecimalFormat df = new DecimalFormat("0.00");
                DecimalFormat df1 = new DecimalFormat("0.0000");
                DecimalFormat df2 = new DecimalFormat("0");
                double format_average_amount = count_firebase_data.get(1)/count_firebase_data.get(0);
                if (format_average_amount>0.0)
                    calculate_average_amount = df.format(format_average_amount);
                else
                    calculate_average_amount = df1.format(format_average_amount);
                average_pledge.setText(calculate_average_amount);
                total_pledge.setText(df2.format(count_firebase_data.get(1)));
                count_num_of_ppl.setText(df2.format(count_firebase_data.get(0)));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private ArrayList<Double> count(DataSnapshot dataSnapshot)
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
            if (user.getPledge().equals(""))
                continue;
            else
                count_total_pledge_amount = count_total_pledge_amount+Double.parseDouble(user.getPledge());

        }
        array.add(count_num_of_user);
        array.add(count_total_pledge_amount/1000.0);
        my_pledge.setText(user_info_update_and_dispay.getPledge());
        return array;

    }


    public void direct_to_calculator(View view)
    {
        Intent intent = new Intent(this, CalcActivity.class);
        startActivity(intent);
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
                    Intent intent = new Intent(Realtime_Pledge_Data.this, CalcActivity.class);
                    startActivity(intent);

                }else if(x1 > x2){
                    Intent i = new Intent(Realtime_Pledge_Data.this, FactsActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

}
