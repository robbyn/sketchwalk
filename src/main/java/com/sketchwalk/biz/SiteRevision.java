package com.sketchwalk.biz;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.tastefuljava.jedo.Ref;

public class SiteRevision {
    private int id;
    private final Ref<SiteRevision> baseRevision = new Ref<>();
    private final Site site;
    private final Instant timestamp = Instant.now();
    private final Set<String> languages = new HashSet<>();
    private Page rootPage;

    @Deprecated // only called via reflection
    public SiteRevision() {
        this.site = null;
    }

    public SiteRevision(Site site) {
        this.site = site;
    }

    public SiteRevision(SiteRevision baseRevision) {
        this.baseRevision.set(baseRevision);
        this.site = baseRevision.site;
        this.languages.addAll(baseRevision.languages);
        this.rootPage = baseRevision.rootPage;
    }

    public int getId() {
        return id;
    }

    public SiteRevision getBaseRevision() {
        return baseRevision.get();
    }

    public Site getSite() {
        return site;
    }

    public Instant getTimestamp() {
        return timestamp;
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
        return rootPage;
    }

    public void setRootPage(Page rootPage) {
        this.rootPage = rootPage;
    }
}
