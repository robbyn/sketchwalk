package com.sketchwalk.biz;

import com.sketchwalk.util.Util;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tastefuljava.jedo.Ref;

public class User {
    private static final Charset DIGEST_ENCODING = StandardCharsets.UTF_8;
    private static final String DIGEST_ALGORITHM = "SHA-256";

    private int id;
    private String username;
    private String email;
    private String passwordHash;
    private String displayName;
    private final Set<Group> groups = new HashSet<>();
    private Ref<Media> profilePicture;
    private Ref<Media> coverPicture;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkPassword(String password) {
        return hash(password).equals(passwordHash);
    }

    public void setPassword(String password) {
        this.passwordHash = hash(password);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Media getProfilePicture() {
        return profilePicture == null ? null : profilePicture.get();
    }

    public void setProfilePicture(Media pic) {
        profilePicture = pic == null ? null : new Ref<>(pic);
    }

    public Media getCoverPicture() {
        return coverPicture == null ? null : coverPicture.get();
    }

    public void setCoverPicture(Media pic) {
        coverPicture = pic == null ? null : new Ref<>(pic);
    }

    public Set<Group> getGroups() {
        return new HashSet<>(groups);
    }

    public int getGroupCount() {
        return groups.size();
    }

    public boolean isInGroup(Group g) {
        return groups.contains(g);
    }

    public boolean addGroup(Group g) {
        return groups.add(g);
    }

    public boolean removeGroup(Group g) {
        return groups.remove(g);
    }

    public void clearGroups() {
        groups.clear();
    }

    private static String hash(String password) {
        try {
            byte[] bytes = password.getBytes(DIGEST_ENCODING);
            return Util.hex(Util.hash(DIGEST_ALGORITHM, bytes));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
