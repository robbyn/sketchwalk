package com.sketchwalk.biz;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private final int id = 0;
    private String displayName;
    private String description;
    private final Set<User> members = new HashSet<>();

    public int getId() {
        return id;
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

    public Set<User> getMembers() {
        return members;
    }
}
