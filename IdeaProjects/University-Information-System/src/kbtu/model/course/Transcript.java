package kbtu.model.course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kbtu.model.user.Student;


public class Transcript implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student;
    private List<Mark> marks;

    public Transcript() { this.marks = new ArrayList<>(); }

    public Transcript(Student student) {
        this();
        this.student = student;
    }

    public double calculateGPA() {
        if (marks.isEmpty()) return 0.0;
        double totalPoints  = 0;
        int totalCredits = 0;
        for (Mark mark : marks) {
            int credits = mark.getCourse().getCredits();
            totalPoints  += mark.getDigitGrade() * credits;
            totalCredits += credits;
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    public String getTranscriptText() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Academic Transcript ===\n");
        sb.append("Student : ").append(student != null ? student.getFullName() : "Unknown").append("\n");
        sb.append("Courses :\n");
        for (Mark mark : marks) {
            sb.append(String.format("  %-20s | %s | Total: %.2f%n",
                mark.getCourse().getCourseName(),
                mark.getLetterGrade(),
                mark.calculateTotal()));
        }
        sb.append("===========================\n");
        sb.append(String.format("GPA: %.2f%n", calculateGPA()));
        return sb.toString();
    }

    public void print(){ System.out.println(getTranscriptText()); }

    public Student getStudent(){ return student; }
    public void setStudent(Student s){ this.student = s; }
    public List<Mark> getMarks(){ return marks; }
    public void addMark(Mark m){ marks.add(m); }
}
