package KBTU;

import java.io.*;
import java.util.*;

public class News implements Serializable {
    private String title;
    private String content;
    private Date date;
    private boolean isPinned;
    private boolean isResearchRelated;
    private List<String> comments;
    
    public News(String title, String content, boolean isResearchRelated) {
        this.title = title;
        this.content = content;
        this.isResearchRelated = isResearchRelated;
        this.date = new Date();
        this.isPinned = false;
        this.comments = new ArrayList<>();
    }

    public void addComment(String comment) {
        if (comment != null && !comment.isEmpty()) {
            this.comments.add(comment);
        }
    }

    public void pinNews() {
        this.isPinned = true;
    }

    public void unpinNews() {
        this.isPinned = false;
    }

    @Override
    public String toString() {
        String prefix = isPinned ? "[PINNED] " : "";
        String type = isResearchRelated ? "[RESEARCH] " : "[GENERAL] ";
        return prefix + type + title + " (" + date + ")\n" + content + 
               "\nComments: " + comments.size();
    }

    // Getters
    public boolean isPinned() { return isPinned; }
    public boolean isResearchRelated() { return isResearchRelated; }
}