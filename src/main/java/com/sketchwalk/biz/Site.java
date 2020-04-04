package com.sketchwalk.biz;

import org.tastefuljava.jedo.Ref;

public class Site {
    private int id;
    private String name;
    private final Ref<SiteRevision> currentRevision = new Ref<>();

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
        return currentRevision.get();
    }

    public void setCurrentRevision(SiteRevision rev) {
        this.currentRevision.set(rev);
    }
}
