package com.t.teamten.greenfoodtracker;

public class loginactivity {

    private String username;
    private int age;

    loginactivity()
    {
        this.username="";
        this.age = 0;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean validateName(String nametobevalidated)
    {
        return nametobevalidated.length() >= 1 ;
    }
    public boolean validateAge(int agetobevalidated)
    {
        return agetobevalidated <= 100 && agetobevalidated > 0 ;
    }

}
