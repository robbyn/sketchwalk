package com.sketchwalk.service;

import com.sketchwalk.biz.Page;
import com.sketchwalk.biz.PageVersion;
import com.sketchwalk.biz.Site;
import com.sketchwalk.biz.SiteRevision;
import java.io.Closeable;
import java.util.Collection;
import org.tastefuljava.jedo.Session;
import org.tastefuljava.jedo.SessionFactory;

public class Transaction implements Closeable {
    private final Session session;

    public Transaction(SessionFactory factory) {
        this.session = factory.openSession();
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
        SiteRevision rev = new SiteRevision(site);
        rev.setLanguages(languages);
        rev.setRootPage(createPage("/", rootTemplate, languages));
        session.insert(rev);
        return rev;
    }

    private Page createPage(String name, String template,
            Collection<String> languages) {
        Page page = new Page();
        page.setName(name);
        page.setTemplate(template);
        PageVersion version = createPageVersion("Page title");
        for (String language: languages) {
            page.setVersion(language, version);
        }
        session.insert(page);
        return page;
    }

    private PageVersion createPageVersion(String title) {
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
