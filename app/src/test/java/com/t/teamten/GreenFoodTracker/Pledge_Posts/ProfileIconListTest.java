package com.t.teamten.GreenFoodTracker.Pledge_Posts;

import android.content.Context;

import com.t.teamten.GreenFoodTracker.BuildConfig;
import com.t.teamten.GreenFoodTracker.R;
import com.t.teamten.GreenFoodTracker.Profile_Icon.ProfileIconList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 27)
public class ProfileIconListTest {
    private Context context;
    private ProfileIconList testIconList;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application;
        testIconList = new ProfileIconList(context);
    }

    @Test
    public void testGetDrawableId() {
        String dog = "dog";
        int expectedDogImageId = testIconList.getDrawableId(dog);
        int actualDogImageId = R.drawable.dog;

        assertEquals(expectedDogImageId, actualDogImageId);
    }

    @Test
    public void testFailedGetDrawableId() {
        String snake = "snake";
        int expectedDogImageId = testIconList.getDrawableId(snake);

        assertEquals(expectedDogImageId, 0);
    }
}