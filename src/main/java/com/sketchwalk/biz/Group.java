package com.sketchwalk.biz;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private int id;
    private String name;
    private String displayName;
    private String description;
    private final Set<User> users = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }
}
