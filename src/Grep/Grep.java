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

    private ArrayList<String> found = new ArrayList();


    public ArrayList<String> grep() throws IOException {

        Pattern pattern = null;
        String currentFilter = null;
        if (flagR) {
            if (flagI) pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
            else pattern = Pattern.compile(filter);
        } else if (flagI) currentFilter = filter.toLowerCase();
        else currentFilter = filter;

        found.clear();

        BufferedReader fin = new BufferedReader(new FileReader(file));
        String line;
        try (fin) {
            while ((line = fin.readLine()) != null) {

                String checkLine = line;
                if (flagI) { checkLine = checkLine.toLowerCase(); }

                if (pattern != null) {
                    Matcher matcher = pattern.matcher(checkLine);
                    if (flagV) {
                        if (!matcher.find()) found.add(line);
                    } else if (matcher.find()) found.add(line);

                } else if (flagV) {
                    if (!checkLine.contains(currentFilter)) found.add(line);
                } else if (checkLine.contains(currentFilter)) found.add(line);
            }
        }
        return found;
    }
}