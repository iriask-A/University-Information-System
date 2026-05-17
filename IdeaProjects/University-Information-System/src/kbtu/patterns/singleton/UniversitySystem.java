package kbtu.patterns.singleton;

import kbtu.model.course.Course;
import kbtu.model.notification.News;
import kbtu.model.research.Journal;
import kbtu.model.research.ResearchPaper;
import kbtu.model.user.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DESIGN PATTERN: Singleton
 *
 * The central university system — only one instance may exist at runtime.
 * Holds the master lists of users, courses, journals, and news.
 *
 * Thread-safe via double-checked locking.
 */
public class UniversitySystem {

    private static volatile UniversitySystem instance;

    private final List<User>    users;
    private final List<Course>  courses;
    private final List<Journal> journals;
    private final List<News>    newsFeed;


    private UniversitySystem() {
        users    = new ArrayList<>();
        courses  = new ArrayList<>();
        journals = new ArrayList<>();
        newsFeed = new ArrayList<>();
    }

    public static UniversitySystem getInstance() {
        if (instance == null) {
            synchronized (UniversitySystem.class) {
                if (instance == null) {
                    instance = new UniversitySystem();
                }
            }
        }
        return instance;
    }


    /**
     * Prints all research papers across all researchers, sorted by the given comparator.
     */
    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        System.out.println("=== All Research Papers (University-wide) ===");
        getAllResearchers().stream()
            .flatMap(r -> r.getPortfolio().stream())
            .distinct()
            .sorted(comparator)
            .forEach(System.out::println);
    }

    /**
     * Prints top-cited researchers within a given school/department.
     */
    public void printTopResearchersBySchool(String schoolMajor, int topN) {
        System.out.println("=== Top " + topN + " Researchers in School: " + schoolMajor + " ===");
        getAllResearchers().stream()
            .filter(r -> {
                if (r instanceof Teacher) return ((Teacher) r).getCourseCodes().stream()
                    .anyMatch(code -> code.startsWith(schoolMajor));
                return false;
            })
            .sorted(Comparator.comparingInt(Researcher::calculateHIndex).reversed())
            .limit(topN)
            .forEach(r -> {
                String name = r instanceof User ? ((User) r).getFullName() : r.toString();
                System.out.println(name + " | h-index=" + r.calculateHIndex());
            });
    }

    /**
     * Prints top-cited researchers university-wide.
     */
    public void printTopResearchers(int topN) {
        System.out.println("=== Top " + topN + " Researchers (University-wide) ===");
        getAllResearchers().stream()
            .sorted(Comparator.comparingInt(Researcher::calculateHIndex).reversed())
            .limit(topN)
            .forEach(r -> {
                String name = r instanceof User ? ((User) r).getFullName() : r.toString();
                System.out.println(name + " | h-index=" + r.calculateHIndex());
            });
    }

    private List<Researcher> getAllResearchers() {
        return users.stream()
            .filter(u -> u instanceof Researcher)
            .map(u -> (Researcher) u)
            .collect(Collectors.toList());
    }

    // ── Authentication ───────────────────────────────────────────────────────

    public User authenticate(String username, String password) {
        return users.stream()
            .filter(u -> u.login(username, password))
            .findFirst()
            .orElse(null);
    }

    // ── Accessors ────────────────────────────────────────────────────────────

    public List<User>    getUsers()    { return users; }
    public List<Course>  getCourses()  { return courses; }
    public List<Journal> getJournals() { return journals; }
    public List<News>    getNewsFeed() { return newsFeed; }
}
