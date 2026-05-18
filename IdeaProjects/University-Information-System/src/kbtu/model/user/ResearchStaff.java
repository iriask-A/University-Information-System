package kbtu.model.user;

import kbtu.model.research.ResearchPaper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Employee who is not a teacher or student but still conducts research. */
public class ResearchStaff extends Employee implements Researcher {
    private static final long serialVersionUID = 1L;

    private final List<ResearchPaper> portfolio = new ArrayList<>();

    public ResearchStaff(String id, String username, String password,
                         String fullName, String email, double salary) {
        super(id, username, password, fullName, email, salary);
    }
    @Override
    public int calculateHIndex() {
        return Researcher.hIndexFrom(portfolio);
    }
    @Override
    public List<ResearchPaper> getPortfolio() {
        return portfolio;
    }
    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        Researcher.printSorted(getFullName(), portfolio, comparator);
    }
    @Override
    public void addPublication(ResearchPaper paper) {
        portfolio.add(paper);
    }
    @Override
    public String toString() {
        return "ResearchStaff{name='" + getFullName() + "', h-index=" + calculateHIndex() + "}";
    }
}
