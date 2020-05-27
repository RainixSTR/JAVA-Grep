package GrepLauncher;

import Grep.*;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class GrepLauncher {

    @Option(name = "-v", usage = "Inverse the filter condition")
    private boolean flagV;

    @Option(name = "-i", usage = "Ignore case")
    private boolean flagI;

    @Option(name = "-r", usage = "Set regular expression to search")
    private boolean flagR;

    @Argument(usage = "Filter")
    private String filter;

    @Argument(required = true, index = 1, usage = "Input file")
    private String file;

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] args) { 
        try {
            CmdLineParser parser = new CmdLineParser(this);
            parser.parseArgument(args);
        }
        catch (CmdLineException e) {
            System.err.println("greb [-v] [-i] [-r] word file");
            return;
        }
        try {
            Grep grep = new Grep(flagV, flagI, flagR, filter, file);
            grep.grep();
        } catch (IOException e) {
            System.err.println("File error");
            e.printStackTrace();
        }
    }
}