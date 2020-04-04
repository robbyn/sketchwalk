package com.sketchwalk.service;

import java.io.Closeable;
import org.tastefuljava.jedo.SessionFactory;
import org.tastefuljava.jedo.conf.SessionFactoryBuilder;

public class Application implements Closeable {
    private final SessionFactory factory
            = SessionFactoryBuilder.loadFrom(
                "com/sketchwalk/biz/jedo-conf.xml").build();

    @Override
    public void close() {
    }

    public SessionHelper openSession() {
        return new SessionHelper(factory);
    }
}
