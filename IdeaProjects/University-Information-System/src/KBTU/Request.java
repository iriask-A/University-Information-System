package KBTU;
import KBTU.RequestStatus;
import java.io.*;
import java.util.*;

public class Request implements Serializable {
	private String id;
    private String description;
    private RequestStatus status;
    private Date timestamp;
    private User sender;
    private User signedBy;
    private TechSupportSpecialist assignedTo;
    public Request(String id,String description,RequestStatus status,Date timestamp,User sender) {
    	this.id=id;
    	this.description=description;
    	this.status=RequestStatus.NEW;
    	this.timestamp =new Date();
    	this.sender=sender;
    }
    

    public void updateStatus(RequestStatus newStatus) {
    	if (this.status != RequestStatus.DONE && this.status != RequestStatus.REJECTED) {
            this.status = newStatus;
        } else {
            System.out.println("Cannot update a closed or rejected request.");
        }
    }
    public void addSignature(User official) {
    	this.signedBy = official;
        this.updateStatus(RequestStatus.ACCEPTED);
    }
    public String viewRequestDetails() {
        return String.format(
            "Request ID: %s\nStatus: %s\nSent by: %s\nSigned by: %s\nAssigned to: %s\nDescription: %s",
            id, status, 
            (sender != null ? sender.getUsername() : "Unknown"),
            (signedBy != null ? signedBy.getUsername() : "Not signed"),
            (assignedTo != null ? assignedTo.getUsername() : "Unassigned"),
            description
        );
    }
    
    public void setAssignedTo(TechSupportSpecialist specialist) {
        this.assignedTo = specialist;
    }

}