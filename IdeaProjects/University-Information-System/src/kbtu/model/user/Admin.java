package kbtu.model.user;

import java.util.ArrayList;
import java.util.List;

/**
 * System administrator — manages users and views audit logs.
 * This class provides functionality to add, remove, and update users
 * while maintaining a history of these actions.
 */
public class Admin extends Employee {
    private static final long serialVersionUID = 1L;

    /** Stores log entries of administrative actions with timestamps. */
    private List<String> logFiles;

    public Admin(String id, String username, String password, String fullName, String email, double salary) {
        super(id, username, password, fullName, email, salary);
        this.logFiles = new ArrayList<>();
    }

    /**
     * Adds a new user to the provided list and records the action.
     * @param users The global list of system users.
     * @param user The user instance to be added.
     */
    public void addUser(List<User> users, User user) {
        if (user != null) {
            users.add(user);
            log("Added user: " + user.getUsername());
            System.out.println("User added: " + user.getFullName());
        }
    }

    /**
     * Removes a user from the system by their unique identifier.
     * @param users The list from which the user will be removed.
     * @param userId The ID of the user to remove.
     */
    public void removeUser(List<User> users, String userId) {
        boolean removed = users.removeIf(u -> u.getId().equals(userId));
        if (removed) {
            log("Removed user with id: " + userId);
            System.out.println("User removed: " + userId);
        } else {
            System.out.println("User not found: " + userId);
        }
    }

    /**
     * Updates the basic profile information of a specific user.
     * @param user The user object to update.
     * @param newFullName The new full name to assign.
     * @param newEmail The new email address to assign.
     */
    public void updateUser(User user, String newFullName, String newEmail) {
        if (user != null) {
            user.setFullName(newFullName);
            user.setEmail(newEmail);
            log("Updated user: " + user.getUsername());
            System.out.println("User updated: " + user.getUsername());
        }
    }

    /**
     * Displays all users currently registered in the system.
     */
    public void viewAllUsers(List<User> users) {
        System.out.println("=== All Users ===");
        if (users.isEmpty()) {
            System.out.println("No users.");
        } else {
            users.forEach(System.out::println);
        }
    }

    /**
     * Prints all recorded administrative log entries to the console.
     */
    public void seeLogs() {
        System.out.println("=== System Logs ===");
        if (logFiles.isEmpty()) {
            System.out.println("No logs.");
        } else {
            logFiles.forEach(System.out::println);
        }
    }

    /** * Records an internal log entry with a current timestamp.
     */
    private void log(String entry) {
        String logEntry = "[LOG] " + new java.util.Date() + " — " + entry;
        logFiles.add(logEntry);
    }

    /**
     * Returns the raw list of log files.
     * @return A list of strings representing the action history.
     */
    public List<String> getLogFiles() {
        return logFiles;
    }

    @Override
    public String toString() {
        return "Admin{name='" + getFullName() + "'}";
    }
}