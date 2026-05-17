package kbtu.model.user;

import kbtu.enums.TeacherPosition;
import kbtu.enums.UrgencyLevel;
import kbtu.model.course.Course;
import kbtu.model.course.Mark;
import kbtu.model.research.ResearchPaper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a teacher/instructor in the university.
 * Professors (TeacherPosition.PROFESSOR) automatically implement Researcher.
 *
 * Note: A Teacher can optionally implement Researcher if their position
 * is PROFESSOR, or voluntarily if they conduct research at other levels.
 * This class implements Researcher to cover all teacher-researchers;
 * subclasses may restrict by position if needed.
 */
public class Teacher extends Employee implements Researcher {
    private static final long serialVersionUID = 1L;

    private TeacherPosition   position;
    private List<String>      courseCodes;
    private List<ResearchPaper> portfolio;

    public Teacher(String id, String username, String password,
                   String fullName, String email,
                   double salary, TeacherPosition position) {
        super(id, username, password, fullName, email, salary);
        this.position    = position;
        this.courseCodes = new ArrayList<>();
        this.portfolio   = new ArrayList<>();
    }

    // ── Teaching actions ─────────────────────────────────────────────────────

    public void putMark(Student student, Course course, double first,
                        double second, double finalExam) {
        Mark mark = new Mark(first, second, finalExam, student, course);
        student.addMark(course.getCourseCode(), mark);
        System.out.println("Mark recorded for " + student.getFullName()
                           + " in " + course.getCourseName()
                           + " — Grade: " + mark.getLetterGrade());
    }

    public void sendComplaint(String studentName, UrgencyLevel urgency, String text) {
        System.out.println("COMPLAINT [" + urgency + "]");
        System.out.println("From Teacher : " + getFullName());
        System.out.println("About Student: " + studentName);
        System.out.println("Details      : " + text);
        System.out.println("Sent to Dean.");
    }

    public void viewCourses() {
        System.out.println("=== Courses of " + getFullName() + " ===");
        if (courseCodes.isEmpty()) System.out.println("No courses assigned.");
        else courseCodes.forEach(System.out::println);
    }

    public void manageCourse(String courseCode) {
        System.out.println("Managing course: " + courseCode);
    }

    public void addCourseCode(String code)    { courseCodes.add(code); }
    public void removeCourseCode(String code) { courseCodes.remove(code); }

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
    public void addPublication(ResearchPaper paper) {
        portfolio.add(paper);
    }

    // ── Getters / Setters ────────────────────────────────────────────────────

    public TeacherPosition getPosition()              { return position; }
    public void            setPosition(TeacherPosition p) { this.position = p; }
    public List<String>    getCourseCodes()           { return courseCodes; }

    @Override
    public String toString() {
        return "Teacher{name='" + getFullName() + "', position=" + position
               + ", salary=" + getSalary() + "}";
    }
}
