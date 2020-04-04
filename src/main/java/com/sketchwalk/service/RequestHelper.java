package com.sketchwalk.service;

import java.io.Closeable;
import org.tastefuljava.jedo.SessionFactory;

public class RequestHelper implements Closeable {
    private final SessionFactory factory;
    private final String username;

    public RequestHelper(SessionFactory factory, String username) {
        this.factory = factory;
        this.username = username;
    }

    public Transaction startTransaction() {
        return new Transaction(factory, username);
    }

    @Override
    public void close() {
    }
}
