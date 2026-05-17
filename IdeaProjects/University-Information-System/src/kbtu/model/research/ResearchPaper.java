package kbtu.model.research;

import kbtu.enums.Format;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Represents a published research paper.
 * Fields sourced from IEEE metadata standards.
 *
 * BUG FIXED: original had two fields named 'journal' — one String and one Journal object.
 * Removed the orphan String field; only the Journal object is kept.
 */
public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {
    private static final long serialVersionUID = 1L;

    private String       title;
    private List<String> authors;
    private Journal      journal;     // ← single Journal reference (bug fix)
    private int          pages;
    private Date         datePublished;
    private int          citations;
    private String       doi;

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

    /** Returns a formatted citation string in the requested format. */
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

    public void incrementCitations() { this.citations++; }

    // Comparable: natural ordering by citations descending
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
