package com.sketchwalk.biz;

import com.sketchwalk.service.Transaction;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class SiteTest extends BizTestBase {
    private Transaction trans;

    @Before
    public void setUp() throws IOException, SQLException, ClassNotFoundException {
        super.initialize();
        trans = new Transaction(factory,"myself");
    }

    @After
    public void tearDown() throws SQLException, IOException {
        trans.close();
        super.terminate();
    }

    @Test
    public void testSiteCreation() {
        Site site = trans.createSite("my-site", "basic-page", "en", "fr");
        assertNotNull(site);
        trans.commit();
    }

    @Test
    public void testPageCreation() {
        Page page = trans.createPage("basic-page", "en", "fr");
        assertNotNull(page);
        trans.commit();
    }
}
