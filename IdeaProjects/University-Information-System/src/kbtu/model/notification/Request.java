package kbtu.model.notification;

import kbtu.enums.RequestStatus;
import kbtu.model.user.TechSupportSpecialist;
import kbtu.model.user.User;

import java.io.Serializable;
import java.util.Date;

/**
 * A user request (e.g., IT support, complaint) that goes through
 * a lifecycle: NEW → VIEWED → ACCEPTED → DONE / REJECTED.
 */
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    private String                 id;
    private String                 description;
    private RequestStatus          status;
    private Date                   timestamp;
    private User                   sender;
    private User                   signedBy;
    private TechSupportSpecialist  assignedTo;

    public Request(String id, String description, User sender) {
        this.id          = id;
        this.description = description;
        this.status      = RequestStatus.NEW;
        this.timestamp   = new Date();
        this.sender      = sender;
    }

    public void updateStatus(RequestStatus newStatus) {
        if (status == RequestStatus.DONE || status == RequestStatus.REJECTED) {
            System.out.println("Cannot update a closed or rejected request.");
        } else {
            this.status = newStatus;
        }
    }

    public void addSignature(User official) {
        this.signedBy = official;
        updateStatus(RequestStatus.ACCEPTED);
    }

    public String viewRequestDetails() {
        return String.format(
            "Request ID : %s%nStatus     : %s%nSent by    : %s%nSigned by  : %s%nAssigned to: %s%nDescription: %s",
            id, status,
            sender      != null ? sender.getUsername()      : "Unknown",
            signedBy    != null ? signedBy.getUsername()    : "Not signed",
            assignedTo  != null ? assignedTo.getUsername()  : "Unassigned",
            description);
    }

    public String                getId()            { return id; }
    public RequestStatus         getStatus()        { return status; }
    public User                  getSender()        { return sender; }
    public TechSupportSpecialist getAssignedTo()    { return assignedTo; }
    public void setAssignedTo(TechSupportSpecialist s) { this.assignedTo = s; }
}
