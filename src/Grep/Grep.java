package Grep;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final boolean flagV;
    private final boolean flagI;
    private final boolean flagR;
    private final String filter;
    private final String file;

    public Grep(boolean flagV, boolean flagI, boolean flagR, String filter, String file) {
        this.flagV = flagV;
        this.flagI = flagI;
        this.flagR = flagR;
        this.filter = filter;
        this.file = file;
    }

    public static ArrayList<String> found =  new ArrayList();

    public ArrayList<String> grep() throws IOException {

        found.clear();

        String currentFilter = filter;
        BufferedReader fin = new BufferedReader(new FileReader(file));
        String line;
        Pattern pattern = Pattern.compile(currentFilter);
        try (fin) {
            while ((line = fin.readLine()) != null) {
                Matcher matcher;
                String checkLine = line;

                if (flagI) {
                    currentFilter = filter.toLowerCase();
                    pattern = Pattern.compile(currentFilter);
                    checkLine = checkLine.toLowerCase();
                }

                if (!flagR) {
                    pattern.quote(currentFilter);
                }

                matcher = pattern.matcher(checkLine);

                if (flagV) {
                    if (!matcher.find()) {found.add(line);}
                }
                else {
                    if (matcher.find()) {found.add(line);}
                }
            }
        }
        return found;
    }
}