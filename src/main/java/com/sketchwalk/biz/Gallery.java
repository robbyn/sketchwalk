package com.sketchwalk.biz;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Gallery {
    private final int id = 0;
    private final Set<Media> content = new HashSet<>();

    public int getId() {
        return id;
    }

    public Set<Media> getContent() {
        return Collections.unmodifiableSet(content);
    }
}
