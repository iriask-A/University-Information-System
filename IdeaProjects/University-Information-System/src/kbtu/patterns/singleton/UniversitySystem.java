package kbtu.patterns.singleton;

import kbtu.model.course.Course;
import kbtu.model.notification.News;
import kbtu.model.research.Journal;
import kbtu.model.research.ResearchPaper;
import kbtu.model.user.*;
import kbtu.patterns.observer.Observer;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** Central registry for users, courses, journals, and news. */
public class UniversitySystem implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DATA_FILE = "university_data.ser";

    private static UniversitySystem instance;

    private final List<User> users = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Journal> journals = new ArrayList<>();
    private final List<News> newsFeed = new ArrayList<>();
    private transient List<Observer> newsSubscribers = new ArrayList<>();

    private UniversitySystem() {
        newsSubscribers = new ArrayList<>();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (newsSubscribers == null) {
            newsSubscribers = new ArrayList<>();
        }
    }

    public static UniversitySystem getInstance() {
        if (instance == null) {
            instance = load();
        }
        return instance;
    }

    public void subscribeToNews(Observer observer) {
        if (!newsSubscribers.contains(observer)) {
            newsSubscribers.add(observer);
        }
    }

    public void publishNews(News news) {
        if (news == null || newsFeed.contains(news)) {
            return;
        }
        newsFeed.add(news);
        String message = "News: " + news.getTitle();
        for (Observer observer : newsSubscribers) {
            observer.update(message);
        }
    }

    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        System.out.println("All university papers:");
        getAllResearchers().stream()
                .flatMap(r -> r.getPortfolio().stream())
                .distinct()
                .sorted(comparator)
                .forEach(p -> System.out.println("  " + p));
    }

    public void printTopResearchers(int topN) {
        System.out.println("Top " + topN + " researchers:");
        getAllResearchers().stream()
                .sorted(Comparator.comparingInt(Researcher::calculateHIndex).reversed())
                .limit(topN)
                .forEach(r -> {
                    String name = r instanceof User ? ((User) r).getFullName() : r.toString();
                    System.out.println("  " + name + " (h-index " + r.calculateHIndex() + ")");
                });
    }

    public void printTopResearchersBySchool(String schoolMajor, int topN) {
        System.out.println("Top " + topN + " in " + schoolMajor + ":");
        getAllResearchers().stream()
                .filter(r -> r instanceof Teacher
                        && ((Teacher) r).getCourseCodes().stream()
                        .anyMatch(code -> code.startsWith(schoolMajor)))
                .sorted(Comparator.comparingInt(Researcher::calculateHIndex).reversed())
                .limit(topN)
                .forEach(r -> System.out.println("  " + ((User) r).getFullName()
                        + " (h-index " + r.calculateHIndex() + ")"));
    }

    private List<Researcher> getAllResearchers() {
        return users.stream()
                .filter(u -> u instanceof Researcher)
                .map(u -> (Researcher) u)
                .collect(Collectors.toList());
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.login(username, password)) {
                return user;
            }
        }
        return null;
    }

    public static void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(instance);
            System.out.println("Data saved to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Save failed: " + e.getMessage());
        }
    }

    private static UniversitySystem load() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new UniversitySystem();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (UniversitySystem) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Load failed, starting fresh: " + e.getMessage());
            return new UniversitySystem();
        }
    }

    public List<User> getUsers() { return users; }
    public List<Course> getCourses() { return courses; }
    public List<Journal> getJournals() { return journals; }
    public List<News> getNewsFeed() { return newsFeed; }
}
