package kbtu.model.notification;

import java.io.Serializable;
import java.util.Date;
import kbtu.enums.RequestStatus;
import kbtu.model.user.User;


public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String description;
    private RequestStatus status;
    private Date timestamp;
    private User sender;
    private User signedBy;

    public Request(String id, String description, User sender) {
        this.id = id;
        this.description = description;
        this.status = RequestStatus.NEW;
        this.timestamp = new Date();
        this.sender = sender;
    }

    public void updateStatus(RequestStatus newStatus) {
        if (status == RequestStatus.DONE || status == RequestStatus.REJECTED) {
            System.out.println("Request is already closed.");
            return;
        }
        this.status = newStatus;
    }

    public void sign(User official) {
        this.signedBy = official;
        updateStatus(RequestStatus.ACCEPTED);
    }

    @Override
    public String toString() {
        return "Request{" + id + ", status=" + status
                + ", from=" + (sender != null ? sender.getUsername() : "?")
                + ", signedBy=" + (signedBy != null ? signedBy.getUsername() : "pending")
                + ", text='" + description + "'}";
    }

    public String getId(){ return id; }
    public RequestStatus getStatus(){ return status; }
    public User getSender(){ return sender; }
}
