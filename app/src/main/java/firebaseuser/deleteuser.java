package firebaseuser;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.loginactivities.FirebaseLogin;
//delete user
public class deleteuser extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText user;
    EditText password;
    Button deleteaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteuser);
        mAuth = FirebaseAuth.getInstance();

        user = (EditText) findViewById(R.id.nameid);
        password = (EditText) findViewById(R.id.passwordtodelete);

        deleteaccount = (Button) findViewById(R.id.DeleteID);
        deleteaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(deleteuser.this,"Userdeleted",Toast.LENGTH_LONG).show();
                                    Intent movetoLoginScreen = new Intent(deleteuser.this,FirebaseLogin.class);
                                    startActivity(movetoLoginScreen);
                                }
                            }
                        });
            }
        });
    }

}

