package Grep;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final boolean flagV;
    private final boolean flagI;
    private final boolean flagR;
    private String filter;
    private final String file;

    public Grep(boolean flagV, boolean flagI, boolean flagR, String filter, String file) {
        this.flagV = flagV;
        this.flagI = flagI;
        this.flagR = flagR;
        this.filter = filter;
        this.file = file;
    }

    public ArrayList<String> grep() throws IOException {

        ArrayList<String> found = new ArrayList();

        String currentFilter = filter;

        Pattern pattern;

        if (!flagR) currentFilter = Pattern.quote(currentFilter);

        if (flagI)
            pattern = Pattern.compile(currentFilter, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        else
            pattern = Pattern.compile(currentFilter);

        found.clear();

        BufferedReader fin = new BufferedReader(new FileReader(file));
        String line;

        try (fin) {
            while ((line = fin.readLine()) != null) {
                Matcher matcher;
                matcher = pattern.matcher(line);
                if (flagV && !matcher.find()) found.add(line);
                if (!flagV && matcher.find()) found.add(line);
            }
        }
        return found;
    }
}