package kbtu.model.user;

import kbtu.enums.GraduateType;
import kbtu.exceptions.LowHIndexException;
import kbtu.model.research.ResearchPaper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A graduate student (Master or PhD).
 * 4th-year+ students can be assigned a research supervisor who must have h-index >= 3.
 * Graduate students also implement Researcher.
 */
public class GraduateStudent extends Student implements Researcher {
    private static final long serialVersionUID = 1L;

    private GraduateType graduateType;
    private Researcher supervisor;
    private final List<ResearchPaper> portfolio = new ArrayList<>();

    public GraduateStudent(String id, String username, String password,
                           String fullName, String email,
                           int yearOfStudy, String major,
                           GraduateType graduateType) {
        super(id, username, password, fullName, email, yearOfStudy, major);
        this.graduateType = graduateType;
    }

    /**
     * Assigns a supervisor. The supervisor's h-index must be >= 3.
     * @throws LowHIndexException if supervisor's {@code h-index < 3}
     */
    public void setSupervisor(Researcher supervisor) throws LowHIndexException {
        int hIndex = supervisor.calculateHIndex();
        if (hIndex < 3) throw new LowHIndexException(hIndex);
        this.supervisor = supervisor;
        System.out.println("Supervisor assigned (h-index=" + hIndex + ")");
    }

    @Override
    public int calculateHIndex() {
        return Researcher.hIndexFrom(portfolio);
    }

    @Override
    public List<ResearchPaper> getPortfolio() { return portfolio; }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        Researcher.printSorted(getFullName(), portfolio, comparator);
    }

    @Override
    public void addPublication(ResearchPaper paper) { portfolio.add(paper); }

    public GraduateType getGraduateType() { return graduateType; }
    public Researcher getSupervisor() { return supervisor; }

    @Override
    public String toString() {
        return "GraduateStudent{name='" + getFullName() +
               "', type=" + graduateType +
               ", hIndex=" + calculateHIndex() +
               ", gpa=" + getGpa() + "}";
    }
}
