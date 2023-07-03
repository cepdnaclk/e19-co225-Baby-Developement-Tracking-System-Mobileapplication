package com.example.babyone;

public class Guardian {
    private String babyName;
    private String parentName;
    private String email;

    public Guardian(String babyName, String parentName, String email) {
        this.babyName = babyName;
        this.parentName = parentName;
        this.email = email;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
