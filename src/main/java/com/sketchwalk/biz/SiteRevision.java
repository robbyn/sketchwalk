package com.sketchwalk.biz;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.tastefuljava.jedo.Ref;

public class SiteRevision {
    private int id;
    private final Instant timestamp = Instant.now();
    private final String username;
    private final Ref<SiteRevision> baseRevision = new Ref<>();
    private final Set<String> languages = new HashSet<>();
    private final Ref<Page> rootPage = new Ref<>();

    @Deprecated // only called via reflection
    public SiteRevision() {
        this.username = null;
    }

    public SiteRevision(String username) {
        this.username = username;
    }

    public SiteRevision(SiteRevision baseRevision, String username) {
        this.username = username;
        this.baseRevision.set(baseRevision);
        this.languages.addAll(baseRevision.languages);
        this.rootPage.set(baseRevision.rootPage.get());
    }

    public int getId() {
        return id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public SiteRevision getBaseRevision() {
        return baseRevision.get();
    }

    public Set<String> getLanguages() {
        return new HashSet<>(languages);
    }

    public void setLanguages(Collection<String> col) {
        this.languages.clear();
        addLanguages(col);
    }

    public void addLanguages(Collection<String> col) {
        if (col != null) {
            this.languages.addAll(col);
        }
    }

    public void addLanguage(String language) {
        languages.add(language);
    }

    public Page getRootPage() {
        return rootPage.get();
    }

    public void setRootPage(Page rootPage) {
        this.rootPage.set(rootPage);
    }
}

