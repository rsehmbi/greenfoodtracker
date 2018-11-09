package com.t.teamten.greenfoodtracker.pledgeposts;

public class ProfileIcon {
    private String profileIconTitle;
    private int profileIconId;

    public ProfileIcon(String profileIconTitle, int profileIconId) {
        this.profileIconTitle = profileIconTitle;
        this.profileIconId = profileIconId;
    }

    public String getProfileIconTitle() {
        return profileIconTitle;
    }

    public int getProfileIconId() {
        return profileIconId;
    }
}
