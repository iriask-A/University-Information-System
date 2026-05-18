package kbtu.patterns.factory;

import kbtu.enums.GraduateType;
import kbtu.enums.ManagerType;
import kbtu.enums.TeacherPosition;
import kbtu.model.user.*;

public class UserFactory {

    private UserFactory() {}

    public static Student createStudent(String id, String username, String password, String fullName, String email, int yearOfStudy, String major) {
        return new Student(id, username, password, fullName, email, yearOfStudy, major);
    }

    public static SeniorStudent createSeniorStudent(String id, String username, String password, String fullName, String email, String major) {
        return new SeniorStudent(id, username, password, fullName, email, major);
    }

    public static GraduateStudent createGraduateStudent(String id, String username, String password, String fullName, String email, int yearOfStudy, String major, GraduateType type) {
        return new GraduateStudent(id, username, password, fullName, email, yearOfStudy, major, type);
    }

    public static Teacher createTeacher(String id, String username, String password, String fullName, String email, double salary, TeacherPosition position) {
        return new Teacher(id, username, password, fullName, email, salary, position);
    }

    public static Admin createAdmin(String id, String username, String password, String fullName, String email, double salary) {
        return new Admin(id, username, password, fullName, email, salary);
    }

    public static Manager createManager(String id, String username, String password, String fullName, String email, double salary, ManagerType type) {
        return new Manager(id, username, password, fullName, email, salary, type);
    }

    public static ResearchStaff createResearchStaff(String id, String username, String password, String fullName, String email, double salary) {
        return new ResearchStaff(id, username, password, fullName, email, salary);
    }
}
