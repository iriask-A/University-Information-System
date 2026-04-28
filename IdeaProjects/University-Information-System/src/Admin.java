package KBTU;

import java.util.ArrayList;
import java.util.List;
 
public class Admin extends Employee {
    private static final long serialVersionUID = 1L;
 
    private List<String> logFiles;
 
    public Admin(String id, String username, String password,
                 String fullName, String email, double salary) {
        super(id, username, password, fullName, email, salary);
        this.logFiles = new ArrayList<>();
    }
 
    public void addUser(List<User> users, User user) {
        users.add(user);
        log("Added user: " + user.getUsername());
        System.out.println("User added: " + user.getFullName());
    }

    public void removeUser(List<User> users, String userId) {
        boolean removed = users.removeIf(u -> u.getId().equals(userId));
        if (removed) {
            log("Removed user with id: " + userId);
            System.out.println("User removed: " + userId);
        } else {
            System.out.println("User not found: " + userId);
        }
    }
 
    public void updateUser(User user, String newFullName, String newEmail) {
        user.setFullName(newFullName);
        user.setEmail(newEmail);
        log("Updated user: " + user.getUsername());
        System.out.println("User updated: " + user.getUsername());
    }
 
    public void viewAllUsers(List<User> users) {
        System.out.println("All Users");
        if (users.isEmpty()) System.out.println("No users.");
        else users.forEach(System.out::println);
    }
 
    public void seeLogs() {
        System.out.println("System Logs");
        if (logFiles.isEmpty()) System.out.println("No logs.");
        else logFiles.forEach(System.out::println);
    }

    private void log(String entry) {
        String logEntry = "[LOG] " + new java.util.Date() + " — " + entry;
        logFiles.add(logEntry);
    }
 
    public List<String> getLogFiles() { return logFiles; }
 
    public String toString() {
        return "Admin{name='" + getFullName() + "'}";
    }
}
 