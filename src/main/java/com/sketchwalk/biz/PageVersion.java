package com.sketchwalk.biz;

public class PageVersion {
    private int id;
    private String title;

    public PageVersion() {
    }

    public PageVersion(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
