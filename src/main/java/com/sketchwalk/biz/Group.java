package com.sketchwalk.biz;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Group implements Iterable<User> {
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

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }

    public boolean containsUser(User u) {
        return users.contains(u);
    }

    public int getUserCount() {
        return users.size();
    }

    public boolean addUser(User u) {
        return users.add(u);
    }

    public boolean removeUser(User u) {
        return users.remove(u);
    }

    public void clearUsers() {
        users.clear();
    }
}
