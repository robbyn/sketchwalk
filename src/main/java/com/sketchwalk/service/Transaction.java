package com.sketchwalk.service;

import com.sketchwalk.biz.Page;
import com.sketchwalk.biz.PageVersion;
import com.sketchwalk.biz.Site;
import com.sketchwalk.biz.SiteRevision;
import java.io.Closeable;
import java.util.Arrays;
import java.util.Collection;
import org.tastefuljava.jedo.Session;
import org.tastefuljava.jedo.SessionFactory;

public class Transaction implements Closeable {
    private final Session session;
    private final String username;

    public Transaction(SessionFactory factory, String username) {
        this.session = factory.openSession();
        this.username = username;
    }

    public Site createSite(String name, String rootTemplate,
            String... languages) {
        return createSite(name, rootTemplate, Arrays.asList(languages));
    }

    public Site createSite(String name, String rootTemplate,
            Collection<String> languages) {
        Site site = new Site(name);
        session.insert(site);
        site.setCurrentRevision(
                createSiteRevision(site, rootTemplate, languages));
        session.update(site);
        return site;
    }

    public SiteRevision createSiteRevision(Site site, String rootTemplate,
            Collection<String> languages) {
        SiteRevision rev = new SiteRevision(site, username);
        rev.setLanguages(languages);
        rev.setRootPage(createPage(rootTemplate, languages));
        session.insert(rev);
        return rev;
    }

    public Page createPage(String template, String...languages) {
        return createPage(template, Arrays.asList(languages));
    }

    public Page createPage(String template,
            Collection<String> languages) {
        Page page = new Page();
        page.setTemplate(template);
        PageVersion version = createPageVersion("Page title");
        for (String language: languages) {
            page.setVersion(language, version);
        }
        session.insert(page);
        return page;
    }

    public PageVersion createPageVersion(String title) {
        PageVersion ver = new PageVersion(title);
        session.insert(ver);
        return ver;
    }

    public void commit() {
        session.commit();
    }

    @Override
    public void close() {
        session.close();
    }
}
