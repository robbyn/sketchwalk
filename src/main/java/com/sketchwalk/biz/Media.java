package com.sketchwalk.biz;

public class Media {
    private int id;
    private String mimeType;
    private byte[] data;

    public int getId() {
        return id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
