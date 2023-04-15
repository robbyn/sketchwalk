package com.sketchwalk.biz;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Event {
    private int id;
    private String label;
    private String description;
    private Media picture;
    private final Set<Group> groups = new HashSet<>();
    private Date fromDate;
    private Date toDate;

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Media getPicture() {
        return picture;
    }

    public void setPicture(Media picture) {
        this.picture = picture;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Set<Group> getGroups() {
        return groups;
    }
}
