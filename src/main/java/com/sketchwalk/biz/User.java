package com.sketchwalk.biz;

import java.util.HashSet;
import java.util.Set;

public class User {
    private int id;
    private String username;
    private String email;
    private String displayName;
    private final Set<Group> groups = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<Group> getGroups() {
        return groups;
    }
}
