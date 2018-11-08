package com.t.teamten.greenfoodtracker.resultscreenactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.R;

import firebaseuser.User;

public class SocialMediaActivity extends AppCompatActivity {
    private Button twitterButton;
    private Button facebookButton;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private String tweet = ""; // TODO: put user data, and what they want to share into the tweet.
    private String pledge;
    private String city;

    public void retrieveAllData() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userId = auth.getCurrentUser().getUid();
                for(DataSnapshot snapShot: dataSnapshot.getChildren()) {
                    User userFromFireBase = snapShot.getValue(User.class);
                    if (userId.equals(userFromFireBase.getUserId())) {
                        pledge = userFromFireBase.getPledge();
                        city = userFromFireBase.getCity();
                        break;
                    }
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
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(SocialMediaActivity.this);
        builder.setTitle(getString(R.string.shareoption));
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("users");
        retrieveAllData();
        facebookButton = findViewById(R.id.buttonFacebook);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(SocialMediaActivity.this);
                input.setHint(R.string.edittexthint);
                builder.setView(input);
                final ShareDialog shareDialog = new ShareDialog(SocialMediaActivity.this);

                builder.setPositiveButton("Use my own text!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tweet = input.getText().toString();
                        ShareLinkContent content = new ShareLinkContent.Builder()
                                .setQuote(tweet)
                                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                                .build();
                        shareDialog.show(content);
                    }
                });

                builder.setNegativeButton("Share the app!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tweet = getString(R.string.sharedefault);
                        if (city.isEmpty()) {
                            city = "Greater Vancouver";
                        }
                        tweet = tweet + " I am from " + city + ",";

                        if (!pledge.equals("0")) {
                            tweet = tweet + " and I pledge to save " + pledge + "tons of CO2 emissions!";
                        }
                        else {
                            tweet = tweet + " and I support reducing CO2 emissions!";
                        }
                        ShareLinkContent content = new ShareLinkContent.Builder()
                                .setQuote(tweet)
                                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                                .build();
                        shareDialog.show(content);
                    }
                });

                builder.create().show();


            }

        });

        twitterButton = findViewById(R.id.buttonTwitter);
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(SocialMediaActivity.this);
                input.setHint(R.string.edittexthint);
                builder.setView(input);
                builder.setPositiveButton("Use my own text!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tweet = input.getText().toString();
                        Intent tweetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text=" + tweet));
                        startActivity(tweetIntent);
                    }
                });

                builder.setNegativeButton("Share the app!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tweet = getString(R.string.sharedefault);
                        if (city.isEmpty()) {
                            city = "Greater Vancouver";
                        }
                        if (pledge.equals("0")) {
                            Intent tweetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text=" + tweet + " I am in " + city + ", and I pledge to save the environment by saving on CO2 emissions!"));
                            startActivity(tweetIntent);

                        }
                        else {
                            Intent tweetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text=" + tweet + " I am in " + city + ", and I pledge to save " + pledge + " tons of CO2 emissions!"));
                            startActivity(tweetIntent);
                        }
                    }
                });

                builder.create().show();


            }
        });

    }
}
