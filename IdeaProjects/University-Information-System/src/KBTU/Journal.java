package KBTU;

import java.io.*;
import java.util.*;

public class Journal {
	private String name;

    private List<User> subscribers;

    private List<ResearchPaper> papers;
    
    public Journal(String name) {
        this.name = name;
        this.papers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void subscribe(User user) {
        if (!subscribers.contains(user)) {
            subscribers.add(user);
        }
    }

    public void unsubscribe(User user) {
        subscribers.remove(user);
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
        notifySubscribers(paper);
    }

    private void notifySubscribers(ResearchPaper paper) {
        for (User u : subscribers) {
            u.addNotification("New paper published in " + name + ": " + paper.getTitle());
        }
    }
    public List<ResearchPaper> getPapers() {
        return papers;
    }

}