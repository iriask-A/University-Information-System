package kbtu.model.notification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A news item managed by Managers.
 * Can be pinned and categorised as research-related or general.
 */
public class News implements Serializable {
    private static final long serialVersionUID = 1L;

    private String       title;
    private String       content;
    private Date         date;
    private boolean      isPinned;
    private boolean      isResearchRelated;
    private List<String> comments;

    public News(String title, String content, boolean isResearchRelated) {
        this.title             = title;
        this.content           = content;
        this.isResearchRelated = isResearchRelated;
        this.date              = new Date();
        this.isPinned          = false;
        this.comments          = new ArrayList<>();
    }

    public void addComment(String comment) {
        if (comment != null && !comment.isEmpty()) comments.add(comment);
    }

    public void pin()   { isPinned = true; }
    public void unpin() { isPinned = false; }

    public boolean isPinned()           { return isPinned; }
    public boolean isResearchRelated()  { return isResearchRelated; }
    public String  getTitle()           { return title; }
    public String  getContent()         { return content; }
    public Date    getDate()            { return date; }
    public List<String> getComments()   { return comments; }

    @Override
    public String toString() {
        return (isPinned ? "[PINNED] " : "") +
               (isResearchRelated ? "[RESEARCH] " : "[GENERAL] ") +
               title + " (" + date + ")\n" + content +
               "\nComments: " + comments.size();
    }
}
