package kbtu.model.course;

import kbtu.model.user.Student;
import kbtu.model.user.Teacher;
import kbtu.model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an academic course in the university system.
 * This class manages course details, credit value, target audience (major/year),
 * and tracks the instructors, enrolled students, and scheduled lessons.
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseName;
    private String courseCode;
    private int credits;
    private String major;
    private int targetYear;

    /** List of teachers authorized to conduct the course. */
    private List<Teacher> instructors;

    /** List of students currently enrolled in the course. */
    private List<Student> students;

    /** List of academic lessons (lectures, practices) associated with the course. */
    private List<Lesson> lessons;

    public Course() {
        this.instructors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public Course(String courseName, String courseCode, int credits) {
        this();
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
    }

    /**
     * Assigns a teacher as an instructor for this course.
     * Prevents duplicate assignments.
     * @param teacher The teacher instance to be added.
     */
    public void addInstructor(Teacher teacher) {
        if (!instructors.contains(teacher)) {
            instructors.add(teacher);
        }
    }

    /**
     * Adds a scheduled lesson to the course curriculum.
     * @param lesson The lesson object containing time and type details.
     */
    public void addLesson(Lesson lesson) {
        if (lesson != null) {
            lessons.add(lesson);
        }
    }

    /**
     * Provides a consolidated list of all users involved in the course.
     * @return A List containing both Teacher and Student objects.
     */
    public List<User> getParticipants() {
        List<User> all = new ArrayList<>();
        all.addAll(instructors);
        all.addAll(students);
        return all;
    }

    // Getters & Setters
    public String getCourseName() { return courseName; }
    public void setCourseName(String n) { this.courseName = n; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String c) { this.courseCode = c; }

    public int getCredits() { return credits; }
    public void setCredits(int cr) { this.credits = cr; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public int getTargetYear() { return targetYear; }
    public void setTargetYear(int year) { this.targetYear = year; }

    public List<Teacher> getInstructors() { return instructors; }
    public List<Student> getStudents() { return students; }
    public List<Lesson> getLessons() { return lessons; }

    @Override
    public String toString() {
        return "Course: " + courseName + " [" + courseCode + "], Credits: " + credits;
    }
}