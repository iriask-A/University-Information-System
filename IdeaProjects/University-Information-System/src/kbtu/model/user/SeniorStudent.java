package kbtu.model.user;

import kbtu.exceptions.LowHIndexException;
import kbtu.model.research.ResearchPaper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** 4th-year bachelor who can conduct research and has a supervisor. */
public class SeniorStudent extends Student implements Researcher {
    private static final long serialVersionUID = 1L;

    private Researcher supervisor;
    private final List<ResearchPaper> portfolio = new ArrayList<>();

    public SeniorStudent(String id, String username, String password,
                         String fullName, String email, String major) {
        super(id, username, password, fullName, email, 4, major);
    }

    public void assignSupervisor(Researcher supervisor) throws LowHIndexException {
        if (supervisor.calculateHIndex() < 3) {
            throw new LowHIndexException(supervisor.calculateHIndex());
        }
        this.supervisor = supervisor;
        System.out.println(getFullName() + " now supervised by "
                + (supervisor instanceof User ? ((User) supervisor).getFullName() : "researcher"));
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

    public Researcher getSupervisor() {
        return supervisor;
    }
}
