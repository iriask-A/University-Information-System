package kbtu.patterns.observer;

/**
 * DESIGN PATTERN: Observer
 *
 * Observer interface — all subscribers implement this.
 * Called by Observable when something noteworthy happens.
 *
 * Used by: Journal → notifies subscribed Users when a new paper is published.
 */
@FunctionalInterface
public interface Observer {
    void update(String event);
}
