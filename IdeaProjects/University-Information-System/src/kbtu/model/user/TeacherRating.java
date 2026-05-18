package kbtu.model.user;

/**
 * Represents a student's rating of a teacher for a course.
 * Rating is on a 1–10 scale.
 */
public class TeacherRating {

    private Student student;
    private Teacher teacher;
    private int rating;
    private String comment;

    public TeacherRating() {}

    public TeacherRating(Student student, Teacher teacher, int rating, String comment) {
        this.student = student;
        this.teacher = teacher;
        setRating(rating);
        this.comment = comment;
    }

    public void submitRating() {
        if (teacher == null || student == null) {
            System.err.println("Error: Teacher or Student is null in TeacherRating.");
            return;
        }
        System.out.println("Rating " + rating + "/10 for " + teacher.getFullName()
                           + " submitted by " + student.getFullName());
    }

    public Student getStudent(){ return student; }
    public void setStudent(Student s){ this.student = s; }
    public Teacher getTeacher(){ return teacher; }
    public void setTeacher(Teacher t){ this.teacher = t; }

    public int  getRating(){ return rating; }
    public void setRating(int rating) {
        if (rating >= 1 && rating <= 10) this.rating = rating;
        else { System.out.println("Invalid rating (must be 1-10). Set to 0."); this.rating = 0; }
    }

    public String getComment(){ return comment; }
    public void   setComment(String c){ this.comment = c; }

    @Override
    public String toString() {
        return "Rating: " + rating + "/10. Comment: " + comment;
    }
}
