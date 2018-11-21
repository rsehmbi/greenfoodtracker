package com.t.teamten.GreenFoodTracker.Pledge_Posts;

public class Pledge_Post {
    private String name;
    private String location;
    private String pledge;
    private String profileIconName;

//    public Pledge_Post() {
//
//    }
//For home page pledge display
    public Pledge_Post(String name, String location, String pledge, String profileIconName) {
        this.name = name;
        this.location = location;
        this.pledge = pledge;
        this.profileIconName = profileIconName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPledge(String pledge) {
        this.pledge = pledge;
    }

    public void setProfileIconName(String profileIconName) {
        this.profileIconName = profileIconName;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPledge() {
        return pledge;
    }

    public String getProfileIconName() {
        return profileIconName;
    }
}
