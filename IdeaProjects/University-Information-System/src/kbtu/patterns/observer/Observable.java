package kbtu.patterns.observer;

/**
 * DESIGN PATTERN: Observer
 *
 * Observable interface — publishers implement this.
 * Used by: Journal (notifies subscribers when papers are added).
 */
public interface Observable {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers(String event);
}
