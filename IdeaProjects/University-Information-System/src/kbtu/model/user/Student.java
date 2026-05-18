package kbtu.model.user;

import kbtu.exceptions.CreditLimitException;
import kbtu.exceptions.MaxFailsException;
import kbtu.model.course.Mark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a bachelor student in the university system.
 * This class handles course registration, academic performance tracking,
 * GPA calculation, and extracurricular activities.
 */
public class Student extends User {
    private static final long serialVersionUID = 1L;

    /** Maximum allowed credits per semester. */
    public static final int MAX_CREDITS = 21;

    /** Maximum allowed failed attempts before registration is blocked. */
    public static final int MAX_FAILS = 3;

    private double gpa;
    private int yearOfStudy;
    private String major;
    private String minor;
    private int totalCredits;
    private int failedCount;

    /** List of organizations the student is a member of. */
    private final List<String> organizations;

    /** Map storing marks where the key is the course code. */
    private final Map<String, Mark> marks;

    public Student(String id, String username, String password, String fullName, String email, int yearOfStudy, String major) {
        super(id, username, password, fullName, email);
        this.yearOfStudy = yearOfStudy;
        this.major = major;
        this.gpa = 0.0;
        this.totalCredits = 0;
        this.failedCount = 0;
        this.organizations = new ArrayList<>();
        this.marks = new HashMap<>();
    }

    /**
     * Registers student for a course after validating credit and fail limits.
     * @param courseCode The unique identifier of the course.
     * @param credits The number of credits the course provides.
     * @return true if registration is successful.
     * @throws CreditLimitException if adding credits would exceed MAX_CREDITS.
     * @throws MaxFailsException if student already failed this course MAX_FAILS times.
     */
    public boolean registerCourse(String courseCode, int credits)
            throws CreditLimitException, MaxFailsException {

        if (totalCredits + credits > MAX_CREDITS)
            throw new CreditLimitException();

        if (failedCount >= MAX_FAILS)
            throw new MaxFailsException();

        totalCredits += credits;
        System.out.println(getFullName() + " registered for course: " + courseCode);
        return true;
    }

    /**
     * Adds a mark for a course and triggers GPA recalculation.
     * Increments failedCount if the letter grade is "F".
     * @param courseCode Code of the course to add the mark for.
     * @param mark The Mark object containing evaluation details.
     */
    public void addMark(String courseCode, Mark mark) {
        marks.put(courseCode, mark);
        if (mark.getLetterGrade().equals("F")) {
            failedCount++;
        }
        recalculateGpa();
    }

    /**
     * Internal method to calculate GPA based on all existing marks.
     * Rounds the result to two decimal places.
     */
    private void recalculateGpa() {
        if (marks.isEmpty()) {
            gpa = 0.0;
            return;
        }
        double total = marks.values().stream().mapToDouble(Mark::getDigitGrade).average().orElse(0.0);
        this.gpa = Math.round(total * 100.0) / 100.0;
    }

    /**
     * Prints a detailed view of all course marks, letter grades, and total points.
     */
    public void viewMarks() {
        System.out.println("=== Marks for " + getFullName() + " ===");
        if (marks.isEmpty()) {
            System.out.println("No marks yet.");
            return;
        }
        marks.forEach((code, mark) ->
                System.out.printf("%-10s | %s | Total: %.1f%n",
                        code, mark.getLetterGrade(), mark.calculateTotal()));
    }

    /**
     * Displays the full academic transcript including GPA, Major, and Year of Study.
     */
    public void viewTranscript() {
        System.out.println("=== Transcript ===");
        System.out.println("Student : " + getFullName());
        System.out.println("Major   : " + major);
        System.out.println("Year    : " + yearOfStudy);
        System.out.printf("GPA     : %.2f%n", gpa);
        System.out.println("Credits : " + totalCredits);
        viewMarks();
    }

    /**
     * Submits a rating and comment for a specific teacher.
     */
    public void rateTeacher(Teacher teacher, int rating, String comment) {
        TeacherRating tr = new TeacherRating(this, teacher, rating, comment);
        tr.submitRating();
    }

    /**
     * Adds the student to an organization.
     * @param orgName Name of the organization to join.
     */
    public void joinOrganization(String orgName) {
        organizations.add(orgName);
        System.out.println(getFullName() + " joined: " + orgName);
    }

    /**
     * Removes the student from an organization.
     * @param orgName Name of the organization to leave.
     */
    public void leaveOrganization(String orgName) {
        organizations.remove(orgName);
        System.out.println(getFullName() + " left: " + orgName);
    }

    // Getters & Setters
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public int getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(int y) { this.yearOfStudy = y; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getMinor() { return minor; }
    public void setMinor(String minor) { this.minor = minor; }

    public int getTotalCredits() { return totalCredits; }

    public int getFailedCount() { return failedCount; }
    public void setFailedCount(int c) { this.failedCount = c; }

    public List<String> getOrganizations() { return organizations; }

    public Map<String, Mark> getMarks() { return marks; }

    @Override
    public String toString() {
        return "Student{name='" + getFullName() + "', major='" + major +
                "', year=" + yearOfStudy + ", gpa=" + gpa +
                ", credits=" + totalCredits + "}";
    }
}