package kbtu.model.user;

import kbtu.model.research.ResearchPaper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface Researcher {

    int calculateHIndex();

    List<ResearchPaper> getPortfolio();

    void printPapers(Comparator<ResearchPaper> comparator);

    void addPublication(ResearchPaper paper);

    static int hIndexFrom(List<ResearchPaper> papers) {
        List<Integer> citations = new ArrayList<>();
        for (ResearchPaper paper : papers) {
            citations.add(paper.getCitations());
        }
        citations.sort(Comparator.reverseOrder());
        int h = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }

    static void printSorted(String ownerName, List<ResearchPaper> papers,
                            Comparator<ResearchPaper> comparator) {
        System.out.println("Papers of " + ownerName + ":");
        if (papers.isEmpty()) {
            System.out.println("  (none)");
            return;
        }
        List<ResearchPaper> copy = new ArrayList<>(papers);
        copy.sort(comparator);
        copy.forEach(p -> System.out.println("  " + p));
    }
}
