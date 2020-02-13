package com.sketchwalk.biz;

import com.sketchwalk.util.Files;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.tools.RunScript;
import org.tastefuljava.jedo.Session;
import org.tastefuljava.jedo.SessionFactory;
import org.tastefuljava.jedo.conf.SessionFactoryBuilder;
import org.tastefuljava.jedo.conf.SessionImpl;

public abstract class BizTestBase {
    protected static final File TESTDB_DIR
            = new File(System.getProperty("user.home"), "sketchwalk-testdb");
    protected static final File TESTDB = new File(TESTDB_DIR, "test");
    protected static final SessionFactory factory
            = SessionFactoryBuilder.loadFrom(
                "com/sketchwalk/biz/jedo-conf.xml").build();

    protected Session session;

    protected BizTestBase() {
    }

    protected void initialize()
            throws IOException, SQLException, ClassNotFoundException {
        try {
            Files.deleteIfExists(TESTDB_DIR);
            open();
            runScript("initdb.sql");
        } catch (IOException | SQLException
                | ClassNotFoundException | RuntimeException ex) {
            Logger.getLogger(BizTestBase.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    protected void open()
            throws ClassNotFoundException, SQLException, IOException {
        session = factory.openSession();
    }

    protected void terminate() throws SQLException, IOException {
        if (session != null) {
            session.close();
        }
    }

    protected void runScript(String name) throws IOException, SQLException {
        try (InputStream stream = getClass().getResourceAsStream(name);
                Reader in = new InputStreamReader(stream, "UTF-8")) {
            Connection cnt = ((SessionImpl)session).getConnection();
            RunScript.execute(cnt, in);
        }
    }
}
