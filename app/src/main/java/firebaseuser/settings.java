package firebaseuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.loginactivities.FirebaseLogin;
import com.t.teamten.greenfoodtracker.loginactivities.aboutactivity;

public class settings extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mAuth = FirebaseAuth.getInstance();
    }

    public void movetoaboutpage(View view) {
        Intent movetoaboutpage = new Intent(settings.this,aboutactivity.class);
        startActivity(movetoaboutpage);
    }

    public void movetomanagepledgepage(View view) {
    }

    public void signoutmethod(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent movetologin = new Intent(settings.this,FirebaseLogin.class);
        startActivity(movetologin);
        finish();
    }

    public void deleteprofile(View view) {
        Intent movetodeleteprofile = new Intent (settings.this, deleteuser.class);
        startActivity(movetodeleteprofile);
    }

    public void movetoeditprofile(View view) {
    }
}
