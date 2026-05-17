package kbtu.model.research;

import kbtu.exceptions.NotResearcherException;
import kbtu.model.user.Researcher;
import kbtu.model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a research project within the university.
 * Only users who implement Researcher may join.
 * @throws NotResearcherException if a non-Researcher tries to join
 */
public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String               topic;
    private List<ResearchPaper>  publishedPapers;
    private List<Researcher>     participants;

    public ResearchProject(String topic) {
        this.topic          = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants    = new ArrayList<>();
    }

    /**
     * Adds a participant. The user must implement Researcher.
     * @throws NotResearcherException if the user is not a Researcher
     */
    public void addParticipant(User user) throws NotResearcherException {
        if (user instanceof Researcher) {
            participants.add((Researcher) user);
            System.out.println(user.getFullName() + " joined project: " + topic);
        } else {
            throw new NotResearcherException(user.getFullName());
        }
    }

    /**
     * Publishes a result paper; credits it to all participants.
     */
    public void publishProjectResult(ResearchPaper paper) {
        publishedPapers.add(paper);
        for (Researcher r : participants) {
            r.addPublication(paper);
        }
        System.out.println("Published: " + paper.getTitle());
    }

    public String              getTopic()           { return topic; }
    public List<Researcher>    getParticipants()    { return participants; }
    public List<ResearchPaper> getPublishedPapers() { return publishedPapers; }

    @Override
    public String toString() {
        return "ResearchProject{topic='" + topic + "', participants=" + participants.size() + "}";
    }
}
