package KBTU;

import java.io.*;
import java.util.*;

public class Enrollment {

    private Student student;
    private Course course;
    private EnrollmentStatus status;

    public Enrollment() {
        this.status = EnrollmentStatus.NEW;
    }

    public Enrollment(Student student, Course course) {
        this();
        this.student = student;
        this.course = course;
    }

    public boolean enroll() {
        if (student != null && course != null) {
            this.status = EnrollmentStatus.ACCEPTED;
            if (!course.getStudents().contains(student)) {
                course.getStudents().add(student);
            }
            return true;
        }
        return false;
    }

    public void drop() {
        if (course != null && student != null) {
            course.getStudents().remove(student);
            this.status = EnrollmentStatus.DROPPED;
            System.out.println("Student " + student.getFullName() + " dropped course " + course.getCourseName());
        }
    }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }
}
