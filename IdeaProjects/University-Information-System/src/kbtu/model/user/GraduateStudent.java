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

    private GraduateType        graduateType;
    private Researcher          supervisor;
    private List<ResearchPaper> portfolio;
    private List<Object>        diplomaPapers;

    public GraduateStudent(String id, String username, String password,
                           String fullName, String email,
                           int yearOfStudy, String major,
                           GraduateType graduateType) {
        super(id, username, password, fullName, email, yearOfStudy, major);
        this.graduateType  = graduateType;
        this.portfolio     = new ArrayList<>();
        this.diplomaPapers = new ArrayList<>();
    }

    /**
     * Assigns a supervisor. The supervisor's h-index must be >= 3.
     * @throws LowHIndexException if supervisor's h-index < 3
     */
    public void setSupervisor(Researcher supervisor) throws LowHIndexException {
        int hIndex = supervisor.calculateHIndex();
        if (hIndex < 3) throw new LowHIndexException(hIndex);
        this.supervisor = supervisor;
        System.out.println("Supervisor assigned (h-index=" + hIndex + ")");
    }

    public void addDiplomaPaper(Object paper) {
        diplomaPapers.add(paper);
        System.out.println("Diploma paper added for " + getFullName());
    }

    public void viewDiplomaPapers() {
        System.out.println("=== Diploma Papers of " + getFullName() + " ===");
        if (diplomaPapers.isEmpty()) System.out.println("No papers yet.");
        else diplomaPapers.forEach(System.out::println);
    }

    // ── Researcher implementation ─────────────────────────────────────────────

    @Override
    public int calculateHIndex() {
        List<Integer> citations = new ArrayList<>();
        for (ResearchPaper p : portfolio) citations.add(p.getCitations());
        citations.sort(Comparator.reverseOrder());
        int h = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) h = i + 1;
            else break;
        }
        return h;
    }

    @Override
    public List<ResearchPaper> getPortfolio() { return portfolio; }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        System.out.println("=== Papers of " + getFullName() + " ===");
        portfolio.stream().sorted(comparator).forEach(System.out::println);
    }

    @Override
    public void addPublication(ResearchPaper paper) { portfolio.add(paper); }

    // ── Getters / Setters ────────────────────────────────────────────────────

    public GraduateType getGraduateType()                 { return graduateType; }
    public void         setGraduateType(GraduateType t)   { this.graduateType = t; }
    public Researcher   getSupervisor()                   { return supervisor; }
    public List<Object> getDiplomaPapers()                { return diplomaPapers; }

    @Override
    public String toString() {
        return "GraduateStudent{name='" + getFullName() +
               "', type=" + graduateType +
               ", hIndex=" + calculateHIndex() +
               ", gpa=" + getGpa() + "}";
    }
}
