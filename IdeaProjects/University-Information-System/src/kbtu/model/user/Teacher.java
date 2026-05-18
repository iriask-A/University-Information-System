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
 * This class handles academic actions such as grading and course management,
 * as well as research activities including publication tracking and h-index calculation.
 */
public class Teacher extends Employee implements Researcher {
    private static final long serialVersionUID = 1L;

    /** Current academic rank/position of the teacher. */
    private TeacherPosition position;

    /** List of unique course codes assigned to the teacher. */
    private List<String> courseCodes;

    /** List of research papers published by the teacher. */
    private List<ResearchPaper> portfolio;

    public Teacher(String id, String username, String password,
                   String fullName, String email,
                   double salary, TeacherPosition position) {
        super(id, username, password, fullName, email, salary);
        this.position = position;
        this.courseCodes = new ArrayList<>();
        this.portfolio = new ArrayList<>();
    }


    /**
     * Records a mark for a specific student in a given course.
     * @param student The student receiving the grade.
     * @param course The course for which the mark is assigned.
     * @param first Points for the 1st attestation.
     * @param second Points for the 2nd attestation.
     * @param finalExam Points for the final examination.
     */
    public void putMark(Student student, Course course, double first,
                        double second, double finalExam) {
        Mark mark = new Mark(first, second, finalExam, student, course);
        student.addMark(course.getCourseCode(), mark);
        System.out.println("Mark recorded for " + student.getFullName()
                + " in " + course.getCourseName()
                + " — Grade: " + mark.getLetterGrade());
    }

    /**
     * Sends a formal complaint about a student to the Dean's office.
     * @param studentName Name of the student.
     * @param urgency The severity level of the issue.
     * @param text Detailed description of the complaint.
     */
    public void sendComplaint(String studentName, UrgencyLevel urgency, String text) {
        System.out.println("COMPLAINT [" + urgency + "]");
        System.out.println("From Teacher : " + getFullName());
        System.out.println("About Student: " + studentName);
        System.out.println("Details      : " + text);
        System.out.println("Sent to Dean.");
    }

    /**
     * Displays all courses currently assigned to the teacher.
     */
    public void viewCourses() {
        System.out.println("=== Courses of " + getFullName() + " ===");
        if (courseCodes.isEmpty()) {
            System.out.println("No courses assigned.");
        } else {
            courseCodes.forEach(System.out::println);
        }
    }

    /**
     * Performs administrative tasks for a specific course.
     * @param courseCode The code of the course to manage.
     */
    public void manageCourse(String courseCode) {
        System.out.println("Managing course: " + courseCode);
    }

    public void addCourseCode(String code)    { courseCodes.add(code); }
    public void removeCourseCode(String code) { courseCodes.remove(code); }



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
    public void addPublication(ResearchPaper paper) {
        portfolio.add(paper);
    }

    public TeacherPosition getPosition() { return position; }
    public void setPosition(TeacherPosition p) { this.position = p; }
    public List<String> getCourseCodes() { return courseCodes; }

    @Override
    public String toString() {
        return "Teacher{name='" + getFullName() + "', position=" + position
               + ", salary=" + getSalary() + "}";
    }
}
