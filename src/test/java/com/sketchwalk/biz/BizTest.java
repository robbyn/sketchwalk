package com.sketchwalk.biz;

import com.sketchwalk.util.Util;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BizTest extends BizTestBase {
    
    public BizTest() {
    }
    
    @Before
    public void setUp()
            throws IOException, SQLException, ClassNotFoundException {
        super.initialize();
    }
    
    @After
    public void tearDown() throws SQLException, IOException {
        super.terminate();
    }

    @Test
    public void createUser() throws IOException {
        User user = new User();
        user.setUsername("test");
        user.setDisplayName("Maurice Perry");
        user.setEmail("maurice@perry.ch");
        user.setPassword("abcdef123456");
        user.setProfilePicture(fromJpegResource("profile.jpg"));
        user.setCoverPicture(fromJpegResource("cover.jpg"));
        session.insert(user);
        int id = user.getId();
        session.commit();
        User user2 = session.load(User.class, id);
        assertEquals(user.getUsername(), user2.getUsername());
        assertEquals(user.getDisplayName(), user2.getDisplayName());
        assertEquals(user.getEmail(), user2.getEmail());
        assertNotNull(user2.getProfilePicture());
        assertArrayEquals(
                user2.getProfilePicture().getData(), loadResource("profile.jpg"));
        assertNotNull(user2.getCoverPicture());
        assertArrayEquals(
                user2.getCoverPicture().getData(), loadResource("cover.jpg"));
        long tm = System.nanoTime();
        boolean ok = user2.checkPassword("abcdef123456");
        tm = System.nanoTime()-tm;
        assertTrue(ok);
        assertTrue(tm < 1000000); // 1ms
    }

    @Test
    public void createGroup() {
        Group group = new Group();
        group.setName("sketchwalk");
        group.setDisplayName("Sketchwalk group");
        group.setDescription("Members of the Sketchwalk group");
        session.insert(group);
        int id = group.getId();
        session.commit();
        Group group2 = session.load(Group.class, id);
        assertEquals(group.getName(), group2.getName());
        assertEquals(group.getDisplayName(), group2.getDisplayName());
        assertEquals(group.getDescription(), group2.getDescription());
    }

    @Test
    public void addGroups() {
        User user = new User();
        user.setUsername("robby");
        user.setDisplayName("Maurice Perry");
        user.setEmail("maurice@perry.ch");
        session.insert(user);
        int userId = user.getId();
        Group group = new Group();
        group.setName("sketchwalk");
        group.setDisplayName("Sketchwalk group");
        group.setDescription("Members of the Sketchwalk group");
        session.insert(group);
        int groupId = group.getId();
        user.addGroup(group);
        session.commit();
        user = session.load(User.class, userId);
        group = session.load(Group.class, groupId);
        assertEquals(1, user.getGroupCount());
        assertEquals(1, group.getUserCount());
        assertTrue(group.containsUser(user));
        assertTrue(user.isInGroup(group));
    }

    private Media fromJpegResource(String name) throws IOException {
        Media media = new Media();
        media.setMimeType("image/jpeg");
        media.setData(loadResource(name));
        session.insert(media);
        return media;
    }

    public static byte[] loadResource(String name) throws IOException {
        return Util.loadBinary(BizTest.class.getResource(name));
    }
}
