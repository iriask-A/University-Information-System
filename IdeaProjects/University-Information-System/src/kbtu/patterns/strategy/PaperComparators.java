package kbtu.patterns.strategy;

import kbtu.model.research.ResearchPaper;

import java.util.Comparator;

/**
 * DESIGN PATTERN: Strategy
 *
 * Encapsulates interchangeable sorting algorithms for ResearchPaper.
 * Each constant IS a strategy (a Comparator<ResearchPaper>).
 *
 * Used by: Researcher.printPapers(Comparator<ResearchPaper> c)
 *          UniversitySystem.printAllResearchPapers(Comparator<ResearchPaper> c)
 *
 * Usage:
 *   teacher.printPapers(PaperComparators.BY_CITATIONS);
 *   teacher.printPapers(PaperComparators.BY_DATE_NEWEST_FIRST);
 *   teacher.printPapers(PaperComparators.BY_LENGTH);
 */
public final class PaperComparators {

    private PaperComparators() {}

    /** Highest citations first. */
    public static final Comparator<ResearchPaper> BY_CITATIONS =
        Comparator.comparingInt(ResearchPaper::getCitations).reversed();

    /** Newest publications first. */
    public static final Comparator<ResearchPaper> BY_DATE_NEWEST_FIRST =
        Comparator.comparing(ResearchPaper::getDatePublished).reversed();

    /** Oldest publications first. */
    public static final Comparator<ResearchPaper> BY_DATE_OLDEST_FIRST =
        Comparator.comparing(ResearchPaper::getDatePublished);

    /** Longest papers first (by page count). */
    public static final Comparator<ResearchPaper> BY_LENGTH =
        Comparator.comparingInt(ResearchPaper::getPages).reversed();

    /** Alphabetical by title. */
    public static final Comparator<ResearchPaper> BY_TITLE =
        Comparator.comparing(ResearchPaper::getTitle);
}
