package kbtu.model.course;

import java.io.Serializable;
import kbtu.enums.EnrollmentStatus;
import kbtu.model.user.Student;


public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student          student;
    private Course           course;
    private EnrollmentStatus status;

    public Enrollment() {
        this.status = EnrollmentStatus.NEW;
    }

    public Enrollment(Student student, Course course) {
        this();
        this.student = student;
        this.course  = course;
    }

    public boolean enroll() {
        if (student == null || course == null) return false;
        this.status = EnrollmentStatus.ACCEPTED;
        if (!course.getStudents().contains(student)) {
            course.getStudents().add(student);
        }
        System.out.println(student.getFullName() + " enrolled in " + course.getCourseName());
        return true;
    }

    public void drop() {
        if (course == null || student == null) return;
        course.getStudents().remove(student);
        this.status = EnrollmentStatus.DROPPED;
        System.out.println(student.getFullName() + " dropped course " + course.getCourseName());
    }

    public Student          getStudent() { return student; }
    public void             setStudent(Student s) { this.student = s; }
    public Course           getCourse()  { return course; }
    public void             setCourse(Course c)   { this.course = c; }
    public EnrollmentStatus getStatus()  { return status; }
    public void             setStatus(EnrollmentStatus s) { this.status = s; }
}
