package kbtu;

import kbtu.enums.*;
import kbtu.exceptions.*;
import kbtu.model.course.*;
import kbtu.model.notification.*;
import kbtu.model.research.*;
import kbtu.model.user.*;
import kbtu.patterns.factory.UserFactory;
import kbtu.patterns.singleton.UniversitySystem;
import kbtu.patterns.strategy.PaperComparators;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Console demo for the KBTU Research University Information System.
 * Demonstrates: all classes, exceptions, all 4 design patterns, Comparable/Comparator.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   KBTU University Information System Demo    ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // ── 1. Singleton: get the one global system ───────────────────────────
        UniversitySystem system = UniversitySystem.getInstance();

        // ── 2. Factory: create users without touching constructors directly ────
        Admin   admin   = UserFactory.createAdmin("A1","admin","admin123","Amir Admin","admin@kbtu.kz",300_000);
        Teacher prof    = UserFactory.createTeacher("T1","drali","pass","Dr. Ali Bekov","ali@kbtu.kz",
                                                    500_000, TeacherPosition.PROFESSOR);
        Teacher tutor   = UserFactory.createTeacher("T2","tutor1","pass","Tutor Sara","sara@kbtu.kz",
                                                    200_000, TeacherPosition.TUTOR);
        Student s1      = UserFactory.createStudent("S1","zarina","pass","Zarina Nurova","z@kbtu.kz",3,"CS");
        Student s2      = UserFactory.createStudent("S2","bekzat","pass","Bekzat Abenov","b@kbtu.kz",4,"CS");
        GraduateStudent grad = UserFactory.createGraduateStudent(
                                "G1","asel","pass","Asel Masters","asel@kbtu.kz",
                                5,"CS", GraduateType.MASTER);
        Manager mgr     = UserFactory.createManager("M1","manager","pass","Manager Dulat","d@kbtu.kz",
                                                    400_000, ManagerType.OR);

        system.getUsers().addAll(Arrays.asList(admin, prof, tutor, s1, s2, grad, mgr));

        // ── 3. Authentication ─────────────────────────────────────────────────
        System.out.println("--- Authentication ---");
        User logged = system.authenticate("drali","pass");
        System.out.println("Logged in: " + (logged != null ? logged.getFullName() : "FAILED"));

        // ── 4. Course + Enrollment ─────────────────────────────────────────────
        System.out.println("\n--- Course Registration ---");
        Course oop = new Course("OOP in Java","CS101",5);
        oop.addInstructor(prof);
        oop.addLesson(new Lesson(LessonType.LECTURE, "Intro to OOP","B1-101"));
        oop.addLesson(new Lesson(LessonType.PRACTICE,"Implementing Classes","B1-Lab"));
        system.getCourses().add(oop);

        mgr.assignTeacherToCourse(prof,"CS101");

        try {
            s1.registerCourse("CS101", 5);
            Enrollment e1 = new Enrollment(s1, oop);
            e1.enroll();
            mgr.approveRegistration(s1);
        } catch (CreditLimitException | MaxFailsException ex) {
            System.out.println("Registration failed: " + ex.getMessage());
        }

        // ── 5. Marks + Transcript ─────────────────────────────────────────────
        System.out.println("\n--- Grading ---");
        prof.putMark(s1, oop, 85.0, 88.0, 90.0);
        s1.viewTranscript();

        // ── 6. Credit limit exception ─────────────────────────────────────────
        System.out.println("\n--- Credit Limit Exception ---");
        try {
            s1.registerCourse("CS102", 20);  // would exceed 21
        } catch (CreditLimitException ex) {
            System.out.println("Caught: " + ex.getMessage());
        } catch (MaxFailsException ex) {
            System.out.println("Caught: " + ex.getMessage());
        }

        // ── 7. Research papers + Observer (Journal) ───────────────────────────
        System.out.println("\n--- Research / Observer Pattern ---");
        Journal ieee = new Journal("IEEE Transactions");
        system.getJournals().add(ieee);
        // Users subscribe as observers
        ieee.subscribe(event -> s2.addNotification(event));
        ieee.subscribe(event -> grad.addNotification(event));

        ResearchPaper p1 = new ResearchPaper(
            "Deep Learning Advances", Arrays.asList("Dr. Ali Bekov"), ieee, 12, new Date());
        ResearchPaper p2 = new ResearchPaper(
            "Quantum Algorithms Review", Arrays.asList("Dr. Ali Bekov","Asel Masters"), ieee, 20,
            new Date(System.currentTimeMillis() - 86_400_000L)); // yesterday

        p1.incrementCitations(); p1.incrementCitations(); p1.incrementCitations(); // 3 citations
        p2.incrementCitations();                                                     // 1 citation

        prof.addPublication(p1);
        prof.addPublication(p2);
        grad.addPublication(p2);

        ieee.addPaper(p1);   // triggers Observer notifications
        ieee.addPaper(p2);

        // ── 8. Strategy: sort papers with different comparators ────────────────
        System.out.println("\n--- Strategy Pattern: Sorting Papers ---");
        System.out.println(">> By citations (desc):");
        prof.printPapers(PaperComparators.BY_CITATIONS);

        System.out.println(">> By date (newest first):");
        prof.printPapers(PaperComparators.BY_DATE_NEWEST_FIRST);

        System.out.println(">> By length (pages desc):");
        prof.printPapers(PaperComparators.BY_LENGTH);

        // ── 9. h-index & Supervisor assignment ───────────────────────────────
        System.out.println("\n--- h-index and Supervisor ---");
        System.out.println("Prof h-index: " + prof.calculateHIndex());
        try {
            grad.setSupervisor(prof);  // h-index=1 — will this throw?
        } catch (LowHIndexException ex) {
            System.out.println("Caught LowHIndexException: " + ex.getMessage());
        }
        // Give prof enough citations so h-index >= 3
        p1.incrementCitations(); p1.incrementCitations();  // p1 now 5 citations
        p2.incrementCitations(); p2.incrementCitations(); p2.incrementCitations(); // p2 now 4
        ResearchPaper p3 = new ResearchPaper("AI Ethics",Arrays.asList("Dr. Ali Bekov"),ieee,8,new Date());
        p3.incrementCitations(); p3.incrementCitations(); p3.incrementCitations();
        prof.addPublication(p3);
        System.out.println("Prof h-index after more papers: " + prof.calculateHIndex());
        try {
            grad.setSupervisor(prof);
        } catch (LowHIndexException ex) {
            System.out.println("Caught: " + ex.getMessage());
        }

        // ── 10. ResearchProject + NotResearcherException ──────────────────────
        System.out.println("\n--- ResearchProject + NotResearcherException ---");
        ResearchProject project = new ResearchProject("AI in Education");
        try {
            project.addParticipant(prof);
            project.addParticipant(grad);
            project.addParticipant(s1);   // s1 is a plain Student, not Researcher → exception
        } catch (NotResearcherException ex) {
            System.out.println("Caught: " + ex.getMessage());
        }
        project.publishProjectResult(p1);

        // ── 11. University-wide researcher ranking ────────────────────────────
        System.out.println("\n--- Top Researchers University-wide ---");
        system.printTopResearchers(3);

        // ── 12. Messaging ─────────────────────────────────────────────────────
        System.out.println("\n--- Messaging ---");
        prof.sendMessage(mgr, "Please approve course CS101 for next semester.");
        mgr.viewMessages();

        // ── 13. Admin actions ─────────────────────────────────────────────────
        System.out.println("\n--- Admin Actions ---");
        admin.viewAllUsers(system.getUsers());
        admin.seeLogs();

        // ── 14. Manager: reports & news ───────────────────────────────────────
        System.out.println("\n--- Manager Statistical Report ---");
        List<Student> students = Arrays.asList(s1, s2);
        mgr.createStatisticalReport(students);
        mgr.viewStudentsByGpa(students);

        News news = new News("Research Grant Awarded","KBTU received a $1M research grant.", true);
        news.pin();
        system.getNewsFeed().add(news);
        System.out.println("\nNews: " + news);

        // ── 15. Teacher rating ────────────────────────────────────────────────
        System.out.println("\n--- Teacher Rating ---");
        s1.rateTeacher(prof, 9, "Excellent explanations!");

        System.out.println("\n✓ Demo complete.");
    }
}
