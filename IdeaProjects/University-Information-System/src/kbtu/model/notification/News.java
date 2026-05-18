package kbtu.model.notification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a news item within the university system.
 * News can be categorized, pinned to the top of the feed,
 * and includes a section for user comments.
 */
public class News implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String content;
    private Date date;

    private boolean isPinned;

    /** Flag to distinguish between academic research news and general updates. */
    private boolean isResearchRelated;

    /** Collection of user-submitted strings for the news post. */
    private List<String> comments;

    public News(String title, String content, boolean isResearchRelated) {
        this.title = title;
        this.content = content;
        this.isResearchRelated = isResearchRelated;
        this.date = new Date();
        this.isPinned = false;
        this.comments = new ArrayList<>();
    }

    /**
     * Appends a comment to the news item if the input is valid.
     * @param comment The text of the comment to add.
     */
    public void addComment(String comment) {
        if (comment != null && !comment.isEmpty()) {
            comments.add(comment);
        }
    }

    /** Sets the news item as pinned. */
    public void pin(){ isPinned = true; }

    /** Sets the news item as unpinned. */
    public void unpin(){ isPinned = false; }

    /** @return true if the news is pinned. */
    public boolean isPinned(){ return isPinned; }

    /** @return true if the news is related to research. */
    public boolean isResearchRelated(){ return isResearchRelated; }

    public String getTitle(){ return title; }
    public String getContent(){ return content; }
    public Date getDate(){ return date; }

    /** @return The list of all comments for this news item. */
    public List<String> getComments(){ return comments; }

    @Override
    public String toString() {
        return (isPinned ? "[PINNED] " : "") +
                (isResearchRelated ? "[RESEARCH] " : "[GENERAL] ") +
                title + " (" + date + ")\n" + content +
                "\nComments: " + comments.size();
    }
}