package com.example.asmver2.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String gender;
    private String description;

    public User(int id, String username, String gender, String description) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.description = description;
    }

    public User(String username, String gender, String description) {
        this.username = username;
        this.gender = gender;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
