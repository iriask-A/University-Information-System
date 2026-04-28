package KBTU;

public class CreditLimitException extends Exception {
    public CreditLimitException() {
        super("Cannot register: student cannot exceed 21 credits.");
    }
}