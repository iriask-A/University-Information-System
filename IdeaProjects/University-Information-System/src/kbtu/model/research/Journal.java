package kbtu.model.research;

import kbtu.model.user.User;
import kbtu.patterns.observer.Observable;
import kbtu.patterns.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Academic journal that publishes research papers.
 *
 * DESIGN PATTERN: Observer
 * Users subscribe to the journal. When a new paper is added,
 * all subscribers are automatically notified — without the journal
 * knowing anything about the concrete subscriber types.
 */
public class Journal implements Observable, Serializable {
    private static final long serialVersionUID = 1L;

    private String              name;
    private List<ResearchPaper> papers;
    private List<Observer>      subscribers;   // Observer pattern list

    public Journal(String name) {
        this.name        = name;
        this.papers      = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    // ── Observable (Observer pattern) ────────────────────────────────────────

    @Override
    public void subscribe(Observer observer) {
        if (!subscribers.contains(observer)) subscribers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer o : subscribers) o.update(event);
    }

    // ── Journal-specific behaviour ────────────────────────────────────────────

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
        notifyObservers("New paper in '" + name + "': " + paper.getTitle());
    }

    // Keep legacy User-based subscription (convenience)
    public void subscribe(User user) {
        subscribe((Observer) event -> user.addNotification(event));
    }

    public String              getName()   { return name; }
    public List<ResearchPaper> getPapers() { return papers; }

    @Override
    public String toString() { return "Journal{'" + name + "', papers=" + papers.size() + "}"; }
}
