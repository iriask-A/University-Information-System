package KBTU;

public class MaxFailsException extends Exception {
    public MaxFailsException() {
        super("Cannot register: student has failed this course 3 or more times.");
    }
}