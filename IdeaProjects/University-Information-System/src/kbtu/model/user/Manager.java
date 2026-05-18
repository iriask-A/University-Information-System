package kbtu.model.user;

import kbtu.enums.ManagerType;
import kbtu.model.notification.News;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Academic/administrative manager.
 * Handles course assignments, student registration approval, and reports.
 */
public class Manager extends Employee {
    private static final long serialVersionUID = 1L;

    private ManagerType  managerType;
    private List<Object> signedRequests;

    public Manager(String id, String username, String password,
                   String fullName, String email,
                   double salary, ManagerType managerType) {
        super(id, username, password, fullName, email, salary);
        this.managerType    = managerType;
        this.signedRequests = new ArrayList<>();
    }

    public void assignTeacherToCourse(Teacher teacher, String courseCode) {
        teacher.addCourseCode(courseCode);
        System.out.println("Assigned " + teacher.getFullName() + " to course " + courseCode);
    }

    public void approveRegistration(Student student) {
        System.out.println("Manager " + getFullName()
                           + " approved registration for " + student.getFullName());
    }

    public String createStatisticalReport(List<Student> students) {
        if (students.isEmpty()) return "No students to report.";

        double avgGpa = students.stream()
                .mapToDouble(Student::getGpa).average().orElse(0.0);

        Student top = students.stream()
                .max(Comparator.comparingDouble(Student::getGpa)).orElse(null);

        StringBuilder sb = new StringBuilder();
        sb.append("=== Statistical Report ===\n");
        sb.append("Total students: ").append(students.size()).append("\n");
        sb.append(String.format("Average GPA: %.2f%n", avgGpa));
        if (top != null)
            sb.append("Top student: ").append(top.getFullName())
              .append(" (GPA: ").append(top.getGpa()).append(")\n");

        System.out.println(sb);
        return sb.toString();
    }

    public void viewStudentsByGpa(List<Student> students) {
        System.out.println("=== Students by GPA (desc) ===");
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .forEach(s -> System.out.println(s.getFullName() + " — " + s.getGpa()));
    }

    public void viewStudentsAlphabetically(List<Student> students) {
        System.out.println("=== Students Alphabetically ===");
        students.stream()
                .sorted(Comparator.comparing(Student::getFullName))
                .forEach(s -> System.out.println(s.getFullName()));
    }

    public void publishNews(kbtu.patterns.singleton.UniversitySystem system, News news) {
        system.publishNews(news);
        System.out.println("Manager published: " + news.getTitle());
    }

    public void viewSignedRequests() {
        System.out.println("Signed employee requests:");
        if (signedRequests.isEmpty()) {
            System.out.println("  none");
        } else {
            signedRequests.forEach(r -> System.out.println("  " + r));
        }
    }

    public void signRequest(kbtu.model.notification.Request request) {
        request.sign(this);
        signedRequests.add(request);
    }

    public ManagerType getManagerType()              { return managerType; }
    public void        setManagerType(ManagerType t) { this.managerType = t; }

    @Override
    public String toString() {
        return "Manager{name='" + getFullName() + "', type=" + managerType + "}";
    }
}
