package kbtu.model.research;

import kbtu.model.user.User;
import kbtu.patterns.observer.Observable;
import kbtu.patterns.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Journal implements Observable, Serializable {
    private static final long serialVersionUID = 1L;

    private String              name;
    private List<ResearchPaper> papers;
    private transient List<Observer> subscribers;

    public Journal(String name) {
        this.name = name;
        this.papers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (subscribers == null) {
            subscribers = new ArrayList<>();
        }
    }

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

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
        notifyObservers("New paper in '" + name + "': " + paper.getTitle());
    }

    public void subscribe(User user) {
        subscribe((Observer) event -> user.addNotification(event));
    }

    public String              getName()   { return name; }
    public List<ResearchPaper> getPapers() { return papers; }

    @Override
    public String toString() { return "Journal{'" + name + "', papers=" + papers.size() + "}"; }
}
