package kbtu.model.user;

import kbtu.model.research.ResearchPaper;

import java.util.Comparator;
import java.util.List;

/**
 * Interface for any entity that can conduct research.
 * Teachers (especially Professors) and Students (4th-year+) can implement this.
 * Can also stand alone as a dedicated Researcher employee.
 *
 * Design note: Choosing interface over abstract class gives maximum flexibility —
 * a Teacher, Student, or plain Employee can all become Researchers
 * without forcing a new inheritance hierarchy.
 */
public interface Researcher {

    /**
     * Calculates the h-index: the largest h such that h papers
     * each have at least h citations.
     */
    int calculateHIndex();

    /**
     * Returns all research papers in this researcher's portfolio.
     */
    List<ResearchPaper> getPortfolio();

    /**
     * Prints papers sorted by the given comparator.
     * Common comparators: by date, by citations, by page count.
     */
    void printPapers(Comparator<ResearchPaper> comparator);

    /**
     * Adds a publication to this researcher's portfolio.
     */
    void addPublication(ResearchPaper paper);
}
