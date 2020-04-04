package com.sketchwalk.service;

import java.io.Closeable;
import org.tastefuljava.jedo.Session;
import org.tastefuljava.jedo.SessionFactory;

public class Transaction implements Closeable {
    private final Session session;

    public Transaction(SessionFactory factory) {
        this.session = factory.openSession();
    }

    public void commit() {
        session.commit();
    }

    @Override
    public void close() {
        session.close();
    }
}
