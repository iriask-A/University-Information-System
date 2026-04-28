package KBTU;

import java.io.*;
import java.util.*;

public class Course {

    private String courseName;
    private String courseCode;
    private int credits;
    private String major;
    
    private List<Teacher> instructors = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();

    public Course() {
    }

    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
    }

    public List<User> getParticipants() {
        List<User> participants = new ArrayList<>();
        participants.addAll(instructors);
        participants.addAll(students);
        return participants;
    }

    public void addLesson(Lesson l) {
        if (l != null) {
            this.lessons.add(l);
        }
    }

    @Override
    public String toString() {
        return "Course: " + courseName + " [" + courseCode + "], Credits: " + credits;
    }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public List<Teacher> getInstructors() { return instructors; }
    public List<Student> getStudents() { return students; }
    public List<Lesson> getLessons() { return lessons; }
}
