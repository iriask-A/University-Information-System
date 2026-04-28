package KBTU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class Student extends User {
    private static final long serialVersionUID = 1L;
 
    private static final int MAX_CREDITS = 21;
    private static final int MAX_FAILS = 3;
 
    private double gpa;
    private int yearOfStudy;
    private String major;
    private String minor;
    private int totalCredits;
    private int failedCount;
    private List<String> organizations;
    private Map<String, Object> marks;
 
    public Student(String id, String username, String password,
                   String fullName, String email,
                   int yearOfStudy, String major) {
        super(id, username, password, fullName, email);
        this.yearOfStudy = yearOfStudy;
        this.major = major;
        this.gpa = 0.0;
        this.totalCredits = 0;
        this.failedCount = 0;
        this.organizations = new ArrayList<>();
        this.marks = new HashMap<>();
    }
 
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
 
    public void viewMarks() {
        System.out.println("Marks for " + getFullName() + "");
        if (marks.isEmpty()) System.out.println("No marks yet.");
        else marks.forEach((course, mark) ->
                System.out.println(course + ": " + mark));
    }
 
    public void viewTranscript() {
        System.out.println("Transcript");
        System.out.println("Student: " + getFullName());
        System.out.println("Major: " + major);
        System.out.println("Year: " + yearOfStudy);
        System.out.println("GPA: " + gpa);
        System.out.println("Total Credits: " + totalCredits);
        viewMarks();
    }
 
    public void joinOrganization(String orgName) {
        organizations.add(orgName);
        System.out.println(getFullName() + " joined organization: " + orgName);
    }
 
    public void leaveOrganization(String orgName) {
        organizations.remove(orgName);
        System.out.println(getFullName() + " left organization: " + orgName);
    }
 
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public int getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public String getMinor() { return minor; }
    public void setMinor(String minor) { this.minor = minor; }
    public int getTotalCredits() { return totalCredits; }
    public int getFailedCount() { return failedCount; }
    public void setFailedCount(int failedCount) { this.failedCount = failedCount; }
    public List<String> getOrganizations() { return organizations; }
    public Map<String, Object> getMarks() { return marks; }
 
    public String toString() {
        return "Student{name='" + getFullName() + "', major='" + major +
               "', year=" + yearOfStudy + ", gpa=" + gpa +
               ", credits=" + totalCredits + "}";
    }
}
 