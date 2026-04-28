package KBTU;

public class TeacherRating {

    private Student student;
    private Teacher teacher;
    private int rating;
    private String comment;

    public TeacherRating() {
    }

    public TeacherRating(Student student, Teacher teacher, int rating, String comment) {
        this.student = student;
        this.teacher = teacher;
        setRating(rating);
        this.comment = comment;
    }

    public void submitRating() {
        if (teacher != null && student != null) {
            System.out.println("Rating for " + teacher.getFullName() + " submitted by " + student.getFullName());
        } else {
            System.err.println("Error: Teacher or Student is missing in TeacherRating.");
        }
    }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public int getRating() { return rating; }
    
    public void setRating(int rating) {
        if (rating >= 1 && rating <= 10) {
            this.rating = rating;
        } else {
            System.out.println("Invalid rating. Please use scale 1-10.");
            this.rating = 0; 
        }
    }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return "Rating: " + rating + "/10. Comment: " + comment;
    }
}
