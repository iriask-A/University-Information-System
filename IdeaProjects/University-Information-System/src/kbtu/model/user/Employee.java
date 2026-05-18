package kbtu.model.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing all university employees.
 * Extends User and adds messaging and salary management.
 */
public abstract class Employee extends User {
    private static final long serialVersionUID = 1L;

    private double salary;
    private List<Message> messages;

    public Employee(String id, String username, String password, String fullName, String email, double salary) {
        super(id, username, password, fullName, email);
        this.salary = salary;
        this.messages = new ArrayList<>();
    }

    public void sendMessage(Employee receiver, String text) {
        Message msg = new Message(this, receiver, text);
        this.messages.add(msg);
        receiver.receiveMessage(msg);
        System.out.println("Message sent to " + receiver.getFullName());
    }

    public void receiveMessage(Message msg) {
        messages.add(msg);
    }

    public void viewMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages.");
            return;
        }
        System.out.println("=== Messages for " + getFullName() + " ===");
        for (Message m : messages) {
            System.out.println(m);
        }
    }

    public double getSalary(){ return salary; }
    public void   setSalary(double salary){ this.salary = salary; }
    public List<Message> getMessages(){ return messages; }

    @Override
    public String toString() {
        return "Employee{" + super.toString() + ", salary=" + salary + "}";
    }
}
