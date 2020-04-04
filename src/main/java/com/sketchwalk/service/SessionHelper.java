package com.sketchwalk.service;

import java.io.Closeable;
import org.tastefuljava.jedo.SessionFactory;

public class SessionHelper implements Closeable {
    private final SessionFactory factory;
    private int siteRevisionId;

    SessionHelper(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void close() {
    }

    public RequestHelper openRequest() {
        return new RequestHelper(factory);
    }
}
