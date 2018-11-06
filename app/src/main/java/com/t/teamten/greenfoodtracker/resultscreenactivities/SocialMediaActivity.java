package com.t.teamten.greenfoodtracker.resultscreenactivities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.t.teamten.greenfoodtracker.R;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

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
