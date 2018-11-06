package com.t.teamten.greenfoodtracker.resultscreenactivities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.R;

import java.util.List;

import firebaseuser.User;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SocialMediaActivity extends AppCompatActivity {
    Twitter twitter;
    Button twitterButton;
    final String ACCESS_TOKEN_SECRET = getString(R.string.ACCESS_TOKEN_SECRET);
    final String CONSUMER_TOKEN_SECRET = getString(R.string.CONSUMER_API_SECRET);
    final String CONSUMER_TOKEN = getString(R.string.CONSUMER_API_TOKEN);
    final String ACCESS_TOKEN = getString(R.string.ACCESS_TOKEN);
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private User user;
    List<User> userList;

    public void retrieveAllData() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapShot: dataSnapshot.getChildren()) {
                    User userFromFireBase = snapShot.getValue(User.class);
                    userList.add(userFromFireBase);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        String userId = auth.getCurrentUser().getUid();
        retrieveAllData();

        String tweet = ""; // TODO: put user data, and what they want to share into the tweet.
        twitterButton = findViewById(R.id.buttonTwitter);
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
                configurationBuilder.setDebugEnabled(true)
                        .setOAuthConsumerKey(CONSUMER_TOKEN)
                        .setOAuthConsumerSecret(CONSUMER_TOKEN_SECRET)
                        .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
                        .setOAuthAccessToken(ACCESS_TOKEN);
                try {
                    TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
                    Twitter twitter = twitterFactory.getInstance();
                    twitter.updateStatus("Pledge text.");
                }
                catch(IllegalStateException e) {
                    if (!twitter.getAuthorization().isEnabled()) {
                        Context context = getApplicationContext();
                        CharSequence text = "Authorization Failed.";
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                        toast.show();
                    }
                } catch (TwitterException e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Twitter Exception.";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });

    }
}
