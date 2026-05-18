package kbtu.model.research;

import kbtu.enums.Format;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Represents a published research paper within the university's research system.
 * This class stores metadata compliant with IEEE standards and provides
 * functionality for citation generation and h-index tracking.
 */
public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {
    private static final long serialVersionUID = 1L;

    private String title;
    private List<String> authors;

    /** The scientific journal where the paper was published. */
    private Journal journal;

    private int pages;
    private Date datePublished;

    /** Number of times this work has been cited by other researchers. */
    private int citations;

    /** Digital Object Identifier (DOI) for unique identification of the paper. */
    private String doi;

    public ResearchPaper(String title, List<String> authors, Journal journal,
                         int pages, Date datePublished) {
        this.title         = title;
        this.authors       = authors;
        this.journal       = journal;
        this.pages         = pages;
        this.datePublished = datePublished;
        this.citations     = 0;
    }

    public ResearchPaper(String title, List<String> authors, Journal journal,
                         int pages, Date datePublished, String doi) {
        this(title, authors, journal, pages, datePublished);
        this.doi = doi;
    }

    /**
     * Generates a formatted citation string based on the specified format.
     * @param format The desired citation style (e.g., BIBTEX or PLAIN_TEXT).
     * @return A formatted string representing the paper's citation.
     */
    public String getCitation(Format format) {
        String authorsList   = String.join(", ", authors);
        String journalName   = (journal != null) ? journal.getName() : "Unknown Journal";
        if (format == Format.BIBTEX) {
            return String.format(
                    "@article{authors={%s}, title={%s}, journal={%s}, year={%tY}, doi={%s}}",
                    authorsList, title, journalName, datePublished, doi != null ? doi : "N/A");
        }
        // PLAIN_TEXT default
        return String.format("%s. \"%s\". %s. Pages: %d. Citations: %d.",
                authorsList, title, journalName, pages, citations);
    }

    /** Increments the citation count by one when the paper is referenced. */
    public void incrementCitations() { this.citations++; }

    /**
     * Compares this paper with another based on the number of citations.
     * Provides natural ordering in descending order of citations.
     * @param other The other ResearchPaper to compare against.
     * @return A negative integer, zero, or a positive integer as this paper
     * has more, equal, or fewer citations than the specified paper.
     */
    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations);
    }

    // Getters
    public String       getTitle()         { return title; }
    public List<String> getAuthors()       { return authors; }
    public Journal      getJournal()       { return journal; }
    public int          getPages()         { return pages; }
    public Date         getDatePublished() { return datePublished; }
    public int          getCitations()     { return citations; }
    public String       getDoi()           { return doi; }
    public void         setDoi(String doi) { this.doi = doi; }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s | %s | Pages: %d | Citations: %d",
                title, String.join(", ", authors),
                journal != null ? journal.getName() : "N/A",
                pages, citations);
    }
}