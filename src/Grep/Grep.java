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

    ArrayList<String> found = new ArrayList();

    public ArrayList<String> grep() throws IOException {

        Pattern pattern;

        if (flagI)
            pattern = Pattern.compile(filter.toLowerCase(), Pattern.UNICODE_CASE & Pattern.CASE_INSENSITIVE);
        else
            pattern = Pattern.compile(filter);

        if (!flagR) pattern = Pattern.compile(Pattern.quote(pattern.toString()));

        found.clear();

        BufferedReader fin = new BufferedReader(new FileReader(file));
        String line;

        try (fin) {
            while ((line = fin.readLine()) != null) {
                Matcher matcher;
                if (flagI) {
                    matcher = pattern.matcher(line.toLowerCase());
                } else {
                    matcher = pattern.matcher(line);
                }
                if (flagV && !matcher.find()) found.add(line);
                if (!flagV && matcher.find()) found.add(line);
            }

        }
        return found;
    }
}