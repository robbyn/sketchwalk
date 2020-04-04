package com.sketchwalk.service;

import java.io.Closeable;
import org.tastefuljava.jedo.SessionFactory;

public class RequestHelper implements Closeable {
    private final SessionFactory factory;

    public RequestHelper(SessionFactory factory) {
        this.factory = factory;
    }

    public Transaction startTransaction() {
        return new Transaction(factory);
    }

    @Override
    public void close() {
    }
}
