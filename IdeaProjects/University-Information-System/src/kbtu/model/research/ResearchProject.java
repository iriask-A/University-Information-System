package kbtu.model.research;

import kbtu.exceptions.NotResearcherException;
import kbtu.model.user.Researcher;
import kbtu.model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collective research project within the university.
 * This class manages collaborative research topics, tracks participating researchers,
 * and handles the publication of collective results.
 */
public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 1L;

    /** The main subject or field of study for this project. */
    private String topic;

    /** List of research papers published as a direct result of this project. */
    private List<ResearchPaper> publishedPapers;

    /** Collection of university members (Researchers) contributing to the project. */
    private List<Researcher> participants;

    public ResearchProject(String topic) {
        this.topic          = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants    = new ArrayList<>();
    }

    /**
     * Adds a participant to the project. The user must implement the Researcher interface.
     * @param user The system user attempting to join the project.
     * @throws NotResearcherException if the user does not have researcher status.
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
     * Publishes a result paper and automatically adds it to the portfolio
     * of every registered participant.
     * @param paper The ResearchPaper object representing the project findings.
     */
    public void publishProjectResult(ResearchPaper paper) {
        if (paper != null) {
            publishedPapers.add(paper);
            for (Researcher r : participants) {
                r.addPublication(paper);
            }
            System.out.println("Published: " + paper.getTitle());
        }
    }

    /** @return The current research topic. */
    public String getTopic() { return topic; }

    /** @return The list of researchers involved in this project. */
    public List<Researcher> getParticipants() { return participants; }

    /** @return The collection of papers published by this project. */
    public List<ResearchPaper> getPublishedPapers() { return publishedPapers; }

    @Override
    public String toString() {
        return "ResearchProject{topic='" + topic + "', participants=" + participants.size() + "}";
    }
}