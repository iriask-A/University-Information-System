package kbtu.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private List<String> notifications;

    public User(String id, String username, String password, String fullName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.notifications = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logOut() {
        System.out.println(fullName + " logged out.");
    }

    public void addNotification(String note) {
        notifications.add(note);
        System.out.println("[notify] " + note);
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public String getId(){ return id; }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getFullName(){ return fullName; }
    public void setFullName(String fullName){ this.fullName = fullName; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + "{id='" + id + "', name='" + fullName + "'}";
    }
}
