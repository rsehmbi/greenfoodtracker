package com.t.teamten.greenfoodtracker.pledgeposts;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PledgePostTest {
    private PledgePost testPost;
    private String name;
    private String location;
    private String pledge;
    private String profileIconName;

    @Before
    public void setUp() throws Exception {
        name = "John. L";
        location = "Surrey";
        pledge = "107";
        profileIconName = "dog";
        testPost = new PledgePost(name, location, pledge, profileIconName);
    }

    @Test
    public void testSetName() {
        testPost.setName("Yolly. Z");

        String expectedName = "Yolly. Z";
        String actualName = testPost.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetLocation() {
        testPost.setLocation("Burnaby");

        String expectedLocation = "Burnaby";
        String actualLocation = testPost.getLocation();
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void testSetPledge() {
        testPost.setPledge("221.45");

        String expectedLocation = "221.45";
        String actualLocation = testPost.getPledge();

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void testSetProfileIconName() {
        testPost.setProfileIconName("cat");

        String expectedProfileIconName = "cat";
        String actualProfileIconName = testPost.getProfileIconName();

        assertEquals(expectedProfileIconName, actualProfileIconName);
    }
}