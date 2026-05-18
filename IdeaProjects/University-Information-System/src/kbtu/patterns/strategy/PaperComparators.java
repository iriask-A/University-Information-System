package kbtu.patterns.strategy;

import kbtu.model.research.ResearchPaper;

import java.util.Comparator;

public final class PaperComparators {

    private PaperComparators() {}

    public static final Comparator<ResearchPaper> BY_CITATIONS =
            Comparator.comparingInt(ResearchPaper::getCitations).reversed();

    public static final Comparator<ResearchPaper> BY_DATE_NEWEST_FIRST =
            Comparator.comparing(ResearchPaper::getDatePublished).reversed();

    public static final Comparator<ResearchPaper> BY_LENGTH =
            Comparator.comparingInt(ResearchPaper::getPages).reversed();
}
