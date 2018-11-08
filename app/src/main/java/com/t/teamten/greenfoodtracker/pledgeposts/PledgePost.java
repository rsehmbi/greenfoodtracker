package com.t.teamten.greenfoodtracker.pledgeposts;

public class PledgePost {
    private String name;
    private String location;
    private String pledge;

    public PledgePost() {

    }

    public PledgePost(String name, String location, String pledge) {
        this.name = name;
        this.location = location;
        this.pledge = pledge;
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

    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }

    public String getPledge() {
        return pledge;
    }
}
