package KBTU;

import java.io.*;
import java.util.*;

public class ResearchPaper implements Serializable {
	private String title;
    private List<String> authors;
    private String journal;

    private int pages;

    private Date date;

    private int citations;

    private Journal journal;

    public ResearchPaper(String title, List<String> authors, Journal journal, int pages, Date date) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.pages = pages;
        this.date = date;
        this.citations = 0; 
    }
    
   
    public String getCitation(Format f) {
        String authorsList = String.join(", ", authors);
        if (f == Format.BIBTEX) {
            return String.format("@article{authors={%s}, title={%s}, journal={%s}, year={%tY}}", 
                                 authorsList, title, (journal != null ? journal.getName() : "Unknown"), date);
        }
        return String.format("%s. \"%s\". %s. Pages: %d. Citations: %d.", 
                             authorsList, title, (journal != null ? journal.getName() : "N/A"), pages, citations);
    }


    public void incrementCitations() {
        this.citations++;
    }
    
    public int getCitations() {
        return citations;
    }

    public String getTitle() {
        return title;
    }

}