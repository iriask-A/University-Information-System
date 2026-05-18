package kbtu.model.course;

import java.io.Serializable;
import kbtu.model.user.Student;


public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;

    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;
    private Student student;
    private Course course;

    public Mark() {}

    public Mark(double first, double second, double exam, Student student, Course course) {
        this.firstAttestation = first;
        this.secondAttestation = second;
        this.finalExam = exam;
        this.student = student;
        this.course = course;
    }

   
    public double calculateTotal() {
        return firstAttestation * 0.3 + secondAttestation * 0.3 + finalExam * 0.4;
    }

    public String getLetterGrade() {
        double t = calculateTotal();
        if (t >= 95) return "A";
        if (t >= 90) return "A-";
        if (t >= 85) return "B+";
        if (t >= 80) return "B";
        if (t >= 75) return "B-";
        if (t >= 70) return "C+";
        if (t >= 65) return "C";
        if (t >= 60) return "C-";
        if (t >= 55) return "D+";
        if (t >= 50) return "D";
        return "F";
    }

    public double getDigitGrade() {
        double t = calculateTotal();
        if (t >= 95) return 4.0;
        if (t >= 90) return 3.67;
        if (t >= 85) return 3.33;
        if (t >= 80) return 3.0;
        if (t >= 75) return 2.67;
        if (t >= 70) return 2.33;
        if (t >= 65) return 2.0;
        if (t >= 60) return 1.67;
        if (t >= 55) return 1.33;
        if (t >= 50) return 1.0;
        return 0.0;
    }

    
    public double  getFirstAttestation(){ return firstAttestation; }
    public void    setFirstAttestation(double v){ this.firstAttestation = v; }
    public double  getSecondAttestation(){ return secondAttestation; }
    public void    setSecondAttestation(double v){ this.secondAttestation = v; }
    public double  getFinalExam(){ return finalExam; }
    public void    setFinalExam(double v){ this.finalExam = v; }
    public Student getStudent(){ return student; }
    public Course  getCourse(){ return course; }

    @Override
    public String toString() {
        return String.format("Mark{%s | ATT1=%.1f ATT2=%.1f FINAL=%.1f => %s (%.1f)}",
            course != null ? course.getCourseCode() : "?",
            firstAttestation, secondAttestation, finalExam,
            getLetterGrade(), calculateTotal());
    }
}
