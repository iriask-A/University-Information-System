package KBTU;

public class NotResearcherException extends Exception {
    public NotResearcherException(String name) {
        super(name + " is not a Researcher and cannot join a research project.");
    }
}