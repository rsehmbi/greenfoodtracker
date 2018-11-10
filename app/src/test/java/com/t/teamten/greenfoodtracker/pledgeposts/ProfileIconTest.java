package com.t.teamten.greenfoodtracker.pledgeposts;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileIconTest {
    private String profileIconTitle;
    private int profileIconId;
    private ProfileIcon testIcon;

    @Before
    public void setUp() {
        profileIconTitle = "cat";
        profileIconId = 1233219828;

        testIcon = new ProfileIcon(profileIconTitle, profileIconId);
    }

    @Test
    public void getProfileIconTitle() {
        String expectedProfileIconTitle = "cat";
        String actualProfileIconTitle = testIcon.getProfileIconTitle();

        assertEquals(expectedProfileIconTitle, actualProfileIconTitle);
    }

    @Test
    public void getProfileIconId() {
        int expectedProfileIconId = 1233219828;
        int actualProfileIconId = testIcon.getProfileIconId();

        assertEquals(expectedProfileIconId, actualProfileIconId);
    }
}