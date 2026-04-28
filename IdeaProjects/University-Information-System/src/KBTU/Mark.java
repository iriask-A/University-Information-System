package KBTU;

public class Mark {

    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;
    private Student student;
    private Course course;

    public Mark() {
    }

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
        double total = calculateTotal();
        
        if (total >= 95) return "A";
        if (total >= 90) return "A-";
        if (total >= 85) return "B+";
        if (total >= 80) return "B";
        if (total >= 75) return "B-";
        if (total >= 70) return "C+";
        if (total >= 65) return "C";
        if (total >= 60) return "C-";
        if (total >= 55) return "D+";
        if (total >= 50) return "D";
        return "F";
    }

    public double getDigitGrade() {
        double total = calculateTotal();
        if (total >= 95) return 4.0;
        if (total >= 90) return 3.67;
        if (total >= 85) return 3.33;
        if (total >= 80) return 3.0;
        if (total >= 75) return 2.67;
        if (total >= 70) return 2.33;
        if (total >= 65) return 2.0;
        if (total >= 60) return 1.67;
        if (total >= 55) return 1.33;
        if (total >= 50) return 1.0;
        return 0.0;
    }

    public double getFirstAttestation() { return firstAttestation; }
    public void setFirstAttestation(double val) { this.firstAttestation = val; }

    public double getSecondAttestation() { return secondAttestation; }
    public void setSecondAttestation(double val) { this.secondAttestation = val; }

    public double getFinalExam() { return finalExam; }
    public void setFinalExam(double val) { this.finalExam = val; }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
}
