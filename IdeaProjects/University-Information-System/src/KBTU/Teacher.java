package KBTU;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee {
    private static final long serialVersionUID = 1L;

    private TeacherPosition position;
    private List<String> courseCodes;
    private List<Message> messages;

    public Teacher(String id, String username, String password,
                   String fullName, String email,
                   double salary, TeacherPosition position) {
        super(id, username, password, fullName, email, salary);
        this.position = position;
        this.courseCodes = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void sendComplaint(String studentName, UrgencyLevel urgency, String text) {
        System.out.println("COMPLAINT [" + urgency + "]");
        System.out.println("From Teacher: " + getFullName());
        System.out.println("About Student: " + studentName);
        System.out.println("Details: " + text);
        System.out.println("Sent to Dean.");
    }

    public void putMark(String studentName, String courseCode, double grade) {
        System.out.println("Teacher " + getFullName() + " assigned grade " + grade + " to " + studentName + " in course " + courseCode);
    }

    public void viewCourses() {
        System.out.println("Courses of " + getFullName() + "");
        if (courseCodes.isEmpty()) System.out.println("No courses assigned.");
        else courseCodes.forEach(System.out::println);
    }

    public void manageCourse(String courseCode) {
        System.out.println("Managing course: " + courseCode);
    }

    public void addCourseCode(String courseCode) {
        courseCodes.add(courseCode);
    }

    public void removeCourseCode(String courseCode) {
        courseCodes.remove(courseCode);
    }

    public TeacherPosition getPosition() { return position; }
    public void setPosition(TeacherPosition position) { this.position = position; }
    public List<String> getCourseCodes() { return courseCodes; }

    public String toString() {
        return "Teacher{name='" + getFullName() + "', position=" + position + ", salary=" + getSalary() + "}";
    }
}