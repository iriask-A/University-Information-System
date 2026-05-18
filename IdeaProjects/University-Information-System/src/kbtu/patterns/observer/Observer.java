package kbtu.patterns.observer;

@FunctionalInterface
public interface Observer {
    void update(String event);
}
