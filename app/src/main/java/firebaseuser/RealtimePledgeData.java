package firebaseuser;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

//Edit and save, view user profile
public class RealtimePledgeData extends AppCompatActivity {
    //Four headlines
    private TextView myPledge;
    private TextView countNumberOfPeople;
    private TextView averagePledge;
    private TextView totalPledge;

    //Average amount
    private String calculateAverageAmount;

    //for retriving user information
    private String userId;
    private User updatedUser;
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
        userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        updatedUser =  new User();


        //headlines
        myPledge = findViewById(R.id.pledge_amount);
        countNumberOfPeople = findViewById(R.id.ppl_count);
        averagePledge = findViewById(R.id.average_pledge);
        totalPledge = findViewById(R.id.total_pledge);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Double> count_firebase_data = count(dataSnapshot);
                DecimalFormat df = new DecimalFormat("0.00");
                DecimalFormat df1 = new DecimalFormat("0.0000");
                DecimalFormat df2 = new DecimalFormat("0");
                double format_average_amount = count_firebase_data.get(1)/count_firebase_data.get(0);
                if (format_average_amount>0.0)
                    calculateAverageAmount = df.format(format_average_amount);
                else
                    calculateAverageAmount = df1.format(format_average_amount);
                averagePledge.setText(calculateAverageAmount);
                totalPledge.setText(df2.format(count_firebase_data.get(1)));
                countNumberOfPeople.setText(df2.format(count_firebase_data.get(0)));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private ArrayList<Double> count(DataSnapshot dataSnapshot)
    {
        double countNumberOfUsers = 0;

        double countTotalPledgeAmount = 0;
        ArrayList<Double> array = new ArrayList<>();
        for(DataSnapshot ds:dataSnapshot.getChildren())
        {

            User user = ds.getValue(User.class);
            if(user.getUserId().equals(userId))
            {
                updatedUser = user;

            }
            countNumberOfUsers++;
            if (user.getPledge().equals(""))
                continue;
            else
                countTotalPledgeAmount = countTotalPledgeAmount+Double.parseDouble(user.getPledge());

        }
        array.add(countNumberOfUsers);
        array.add(countTotalPledgeAmount/1000.0);
        myPledge.setText(updatedUser.getPledge());
        return array;

    }


    public void direct_to_calculator(View view)
    {
        Intent intent = new Intent(this, CalcActivity.class);
        startActivity(intent);
    }

}
