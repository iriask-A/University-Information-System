package KBTU;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private Employee sender;
    private Employee receiver;
    private String content;
    private Date timestamp;

    public Message(Employee sender, Employee receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = new Date();
    }

    public Employee getSender() { return sender; }
    public Employee getReceiver() { return receiver; }
    public String getContent() { return content; }
    public Date getTimestamp() { return timestamp; }

    public String toString() {
        return "[" + timestamp + "] From: " + sender.getFullName() + " -> To: " + receiver.getFullName() + " | " + content;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message m = (Message) o;
        return Objects.equals(sender, m.sender) &&
               Objects.equals(receiver, m.receiver) &&
               Objects.equals(timestamp, m.timestamp);
    }

    public int hashCode() {
        return Objects.hash(sender, receiver, timestamp);
    }
}