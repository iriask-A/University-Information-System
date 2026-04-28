package KBTU;

import java.io.*;
import java.util.*;


public interface Researcher {
    int calculateHIndex();
    List<ResearchPaper> getPortfolio();
    void printPapers(Comparator<ResearchPaper> c);
    void addPublication(ResearchPaper p);
}

