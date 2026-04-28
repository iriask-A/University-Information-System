package KBTU;

import java.io.*;
import java.util.*;

public class ResearchProject {
	private String topic;
    private List<ResearchPaper> publishedPapers;
    private List<Researcher> participants;
 
    public ResearchProject(String topic) {
        this.topic = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

 
    public void addParticipant(User u) throws NotResearcherException {
        if (u instanceof Researcher) {
            participants.add((Researcher) u);
        } else {
            throw new NotResearcherException(u.getFullName()); 
        }
    }

    public void publishProjectResult(ResearchPaper paper) {
        publishedPapers.add(paper);
        for (Researcher r : participants) {
            r.addPublication(paper);
        }
        
        System.out.println("Project result published: " + paper.getTitle());
    }
    public String getTopic() { return topic; }
    public List<Researcher> getParticipants() { return participants; }

}