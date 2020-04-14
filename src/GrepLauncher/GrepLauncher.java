package GrepLauncher;

import Grep.*;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GrepLauncher {

    @Option(name = "-v", usage = "Inverse the filter condition")
    private boolean flagV;

    @Option(name = "-i", usage = "Ignore case")
    private boolean flagI;

    @Option(name = "-r", usage = "Set regular expression to search")
    private String expression;

    @Argument(usage = "Filter and input file")
    private String[] flag;

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] args) {
        String inputFile = "";
        String regex = "";
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (expression == null && flag.length != 2) System.err.println("greb [-v] [-i] [-r] word file");
            inputFile = flag[flag.length - 1];
            new File(inputFile).exists();
            if (expression != null) {
                regex = expression;
            }
            else {
                regex = flag[0];
            }
        }
        catch (CmdLineException e) {
            System.err.println("greb [-v] [-i] [-r] word file");
            parser.printUsage(System.err);
            return;
        }
        Grep grep = new Grep(flagV, flagI, expression, regex, inputFile);
        try {
            grep.grep();
        } catch (IOException e) {
            System.err.println("File error");
            e.printStackTrace();
        }
    }

}