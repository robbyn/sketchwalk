package com.sketchwalk.biz;

public class Site {
    private int id;
    private String name;
    private SiteRevision currentRevision;

    public Site() {
    }

    public Site(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SiteRevision getCurrentRevision() {
        return currentRevision;
    }

    public void setCurrentRevision(SiteRevision rev) {
        this.currentRevision = rev;
    }
}
