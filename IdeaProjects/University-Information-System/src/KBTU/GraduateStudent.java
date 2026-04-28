package KBTU;

import java.util.ArrayList;
import java.util.List;

public class GraduateStudent extends Student {
    private static final long serialVersionUID = 1L;

    private GraduateType graduateType;   
    private User supervisor;         
    private List<Object> diplomaPapers;

    public GraduateStudent(String id, String username, String password, String fullName, String email, int yearOfStudy, String major, GraduateType graduateType) {
        super(id, username, password, fullName, email, yearOfStudy, major);
        this.graduateType = graduateType;
        this.diplomaPapers = new ArrayList<>();
    }

    public void setSupervisor(User supervisor, int supervisorHIndex)
            throws LowHIndexException {
        if (supervisorHIndex < 3)
            throw new LowHIndexException(supervisorHIndex);
        this.supervisor = supervisor;
        System.out.println("Supervisor assigned: " + supervisor.getFullName());
    }

    public void addDiplomaPaper(Object paper) {
        diplomaPapers.add(paper);
        System.out.println("Diploma paper added for " + getFullName());
    }

    public void viewDiplomaPapers() {
        System.out.println("Diploma Papers of " + getFullName() + "");
        if (diplomaPapers.isEmpty()) System.out.println("No papers yet.");
        else diplomaPapers.forEach(System.out::println);
    }

    public GraduateType getGraduateType() { return graduateType; }
    public void setGraduateType(GraduateType graduateType) {
        this.graduateType = graduateType;
    }
    public User getSupervisor() { return supervisor; }
    public List<Object> getDiplomaPapers() { return diplomaPapers; }

    public String toString() {
        return "GraduateStudent{name='" + getFullName() +
               "', type=" + graduateType +
               ", supervisor=" + (supervisor != null ? supervisor.getFullName() : "none") +
               ", gpa=" + getGpa() + "}";
    }
}
