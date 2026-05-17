package kbtu.model.user;

import kbtu.enums.Language;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract base class for all users in the KBTU university system.
 * Implements Serializable for data persistence.
 */
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Language language;
    private List<String> notifications;

    public User(String id, String username, String password, String fullName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.language = Language.EN;
        this.notifications = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logOut() {
        System.out.println(fullName + " has logged out.");
    }

    public void switchLanguage(Language lang) {
        this.language = lang;
        System.out.println("Language switched to: " + lang);
    }

    public void addNotification(String note) {
        this.notifications.add(note);
        System.out.println("[NOTIFICATION] " + note);
    }

    public List<String> getNotifications() {
        return notifications;
    }

    // Getters & Setters
    public String getId()                        { return id; }
    public String getUsername()                  { return username; }
    public void   setUsername(String username)   { this.username = username; }
    public String getPassword()                  { return password; }
    public void   setPassword(String password)   { this.password = password; }
    public String getFullName()                  { return fullName; }
    public void   setFullName(String fullName)   { this.fullName = fullName; }
    public String getEmail()                     { return email; }
    public void   setEmail(String email)         { this.email = email; }
    public Language getLanguage()                { return language; }
    public void   setLanguage(Language language) { this.language = language; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', username='" + username +
               "', fullName='" + fullName + "', email='" + email + "'}";
    }
}
