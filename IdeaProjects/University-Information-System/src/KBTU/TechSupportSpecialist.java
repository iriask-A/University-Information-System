package KBTU;

import java.io.*;
import java.util.*;

public class TechSupportSpecialist extends Employee {
	private List<Request> activeRequests;
	
	public TechSupportSpecialist(String id, String username, String password, String fullName, String email, double salary) {
	    super(id, username, password, fullName, email, salary);
	    this.activeRequests = new ArrayList<>();
	}

	public void viewNewRequests() {
        if (activeRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        for (Request r : activeRequests) {
            System.out.println(r.viewRequestDetails());
            System.out.println("--------------------");
        }
    }

    public void acceptRequest(Request r) {
        r.updateStatus(RequestStatus.ACCEPTED);
        if (!activeRequests.contains(r)) {
            activeRequests.add(r);
        }
    }
    
    public void markAsDone(Request r) {
        r.updateStatus(RequestStatus.DONE);
        activeRequests.remove(r);
        System.out.println("Request " + r.getId() + " has been closed.");
    }

    
    public void addRequest(Request r) {
        this.activeRequests.add(r);
        r.setAssignedTo(this);
    }
    
    public void finishRequest(Request r) {
    	if (activeRequests.contains(r)) {
            r.updateStatus(RequestStatus.DONE);
            activeRequests.remove(r);
            System.out.println("Request finalized.");
        } else {
            System.out.println("Error: This request is not in your active list.");
        }
    }

}