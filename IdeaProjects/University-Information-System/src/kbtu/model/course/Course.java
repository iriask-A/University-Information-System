package kbtu.model.course;

import kbtu.model.user.Student;
import kbtu.model.user.Teacher;
import kbtu.model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an academic course.
 * A course can have multiple instructors and multiple enrolled students.
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String        courseName;
    private String        courseCode;
    private int           credits;
    private String        major;          // which major this course is intended for
    private int           targetYear;     // which year of study

    private List<Teacher> instructors;
    private List<Student> students;
    private List<Lesson>  lessons;

    public Course() {
        this.instructors = new ArrayList<>();
        this.students    = new ArrayList<>();
        this.lessons     = new ArrayList<>();
    }

    public Course(String courseName, String courseCode, int credits) {
        this();
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits    = credits;
    }

    public void addInstructor(Teacher teacher) {
        if (!instructors.contains(teacher)) instructors.add(teacher);
    }

    public void addLesson(Lesson lesson) {
        if (lesson != null) lessons.add(lesson);
    }

    /**
     * Returns combined list of all participants (teachers + students).
     */
    public List<User> getParticipants() {
        List<User> all = new ArrayList<>();
        all.addAll(instructors);
        all.addAll(students);
        return all;
    }

    // Getters & Setters
    public String        getCourseName()            { return courseName; }
    public void          setCourseName(String n)    { this.courseName = n; }
    public String        getCourseCode()            { return courseCode; }
    public void          setCourseCode(String c)    { this.courseCode = c; }
    public int           getCredits()               { return credits; }
    public void          setCredits(int cr)         { this.credits = cr; }
    public String        getMajor()                 { return major; }
    public void          setMajor(String major)     { this.major = major; }
    public int           getTargetYear()            { return targetYear; }
    public void          setTargetYear(int year)    { this.targetYear = year; }
    public List<Teacher> getInstructors()           { return instructors; }
    public List<Student> getStudents()              { return students; }
    public List<Lesson>  getLessons()               { return lessons; }

    @Override
    public String toString() {
        return "Course: " + courseName + " [" + courseCode + "], Credits: " + credits;
    }
}
