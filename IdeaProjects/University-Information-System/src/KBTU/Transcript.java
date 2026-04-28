package KBTU;

import java.util.*;

public class Transcript {
    private Student student;
    private List<Mark> marks = new ArrayList<>();


    public Transcript() {
    }

    public Transcript(Student student) {
        this.student = student;
    }

    public double calculateGPA() {
        if (marks.isEmpty()) return 0.0;

        double totalGradePoints = 0;
        int totalCredits = 0;

        for (Mark mark : marks) {
            int courseCredits = mark.getCourse().getCredits();
            totalGradePoints += mark.getDigitGrade() * courseCredits;
            totalCredits += courseCredits;
        }

        if (totalCredits == 0) return 0.0;
        
        return totalGradePoints / totalCredits;
    }

    public String getTranscript() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Academic Transcript ---\n");
        sb.append("Student: ").append(student != null ? student.getFullName() : "Unknown").append("\n");
        sb.append("Courses:\n");

        for (Mark mark : marks) {
            sb.append(String.format("- %-20s | Grade: %s | Total: %.2f\n", 
                mark.getCourse().getCourseName(), 
                mark.getLetterGrade(), 
                mark.calculateTotal()));
        }

        sb.append("---------------------------\n");
        sb.append(String.format("Final GPA: %.2f\n", calculateGPA()));
        
        return sb.toString();
    }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public List<Mark> getMarks() { return marks; }
    public void addMark(Mark mark) {
        this.marks.add(mark);
    }
}
