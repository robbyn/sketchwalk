package com.sketchwalk.biz;

import com.sketchwalk.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Page {
    private int id;
    private final Map<String,Page> children = new HashMap<>();
    private String template;
    private final Map<String,PageVersion> versions = new HashMap<>();

    public Page() {
    }

    public Page(Page original) {
        this.children.putAll(original.children);
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

    public Set<String> getChildrenNames() {
        Set<String> result = new TreeSet<>((a,b)->Util.compareStrings(a, b));
        result.addAll(children.keySet());
        return result;
    }

    public Page setChild(String name, Page child) {
        return children.put(name, child);
    }

    public Page removeChild(String name) {
        return children.remove(name);
    }

    public PageVersion getVersion(String language) {
        return versions.get(language);
    }

    public void setVersion(String language, PageVersion version) {
        versions.put(language, version);
    }
}
