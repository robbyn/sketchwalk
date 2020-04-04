package com.sketchwalk.biz;

import com.sketchwalk.util.Util;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Page {
    public static final Comparator<Page> BY_NAME
            = (a,b)->Util.compareStrings(a.name,b.name);

    private int id;
    private Page parent;
    private final Set<Page> children = new HashSet<>();
    private String name;
    private String template;
    private final Map<String,PageVersion> versions = new HashMap<>();

    public Page() {
    }

    public Page(Page original) {
        this.parent = original.parent;
        this.children.addAll(original.children);
        this.name = original.name;
        this.template = original.template;
        this.versions.putAll(original.versions);
    }

    public int getId() {
        return id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Page getParent() {
        return parent;
    }

    public void setParent(Page parent) {
        this.parent = parent;
    }

    public Set<Page> getChildren() {
        Set<Page> result = new TreeSet<>(BY_NAME);
        result.addAll(children);
        return result;
    }

    public boolean addChild(Page child) {
        return children.add(child);
    }

    public boolean removeChild(Page child) {
        return children.remove(child);
    }

    public PageVersion getVersion(String language) {
        return versions.get(language);
    }

    public void setVersion(String language, PageVersion version) {
        versions.put(language, version);
    }
}
