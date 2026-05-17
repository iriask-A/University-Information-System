package kbtu.patterns.factory;

import kbtu.enums.GraduateType;
import kbtu.enums.ManagerType;
import kbtu.enums.TeacherPosition;
import kbtu.model.user.*;

/**
 * DESIGN PATTERN: Factory Method
 *
 * Centralises user creation so callers don't need to know the
 * concrete class or its constructor arguments.
 *
 * Usage:
 *   User student = UserFactory.createStudent("s1","ali","pass","Ali Kh","ali@kbtu.kz",1,"CS");
 *   User teacher = UserFactory.createTeacher("t1","prof","pass","Dr.X","x@kbtu.kz",500_000, TeacherPosition.PROFESSOR);
 */
public class UserFactory {

    private UserFactory() {}   // utility class — no instances

    public static Student createStudent(String id, String username, String password,
                                        String fullName, String email,
                                        int yearOfStudy, String major) {
        return new Student(id, username, password, fullName, email, yearOfStudy, major);
    }

    public static GraduateStudent createGraduateStudent(String id, String username, String password,
                                                        String fullName, String email,
                                                        int yearOfStudy, String major,
                                                        GraduateType type) {
        return new GraduateStudent(id, username, password, fullName, email,
                                   yearOfStudy, major, type);
    }

    public static Teacher createTeacher(String id, String username, String password,
                                        String fullName, String email,
                                        double salary, TeacherPosition position) {
        return new Teacher(id, username, password, fullName, email, salary, position);
    }

    public static Admin createAdmin(String id, String username, String password,
                                    String fullName, String email, double salary) {
        return new Admin(id, username, password, fullName, email, salary);
    }

    public static Manager createManager(String id, String username, String password,
                                        String fullName, String email,
                                        double salary, ManagerType type) {
        return new Manager(id, username, password, fullName, email, salary, type);
    }

    public static TechSupportSpecialist createTechSupport(String id, String username, String password,
                                                          String fullName, String email, double salary) {
        return new TechSupportSpecialist(id, username, password, fullName, email, salary);
    }
}
