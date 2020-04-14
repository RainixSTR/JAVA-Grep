package Grep;

import java.io.*;

public class Grep {

    private final boolean flagV;
    private final boolean flagI;
    private final String expression;
    private final String regex;
    private final String inputFile;

    public Grep(boolean flagV, boolean flagI, String expression, String regex, String inputFile) {
        this.flagV = flagV;
        this.flagI = flagI;
        this.expression = expression;
        this.regex = regex;
        this.inputFile = inputFile;
    }

    public void grep() throws IOException {

        File file = new File(inputFile);
        BufferedReader fin = new BufferedReader(new FileReader(file));
        String filter = regex;
        String line;
        while ((line = fin.readLine()) != null) {
            String checkLine = line;
            if (flagI) {
                filter = filter.toLowerCase();
                checkLine = checkLine.toLowerCase();
            }

            int currentIndex = 0;
            int foundIndex = checkLine.indexOf(filter, currentIndex);
            if (flagV) {
                if (foundIndex == -1) {
                    System.out.println(line);
                }
            } else {
                if (foundIndex != -1) {
                    System.out.println(line);
                }
            }
        }
    }
}