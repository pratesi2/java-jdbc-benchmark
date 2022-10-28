package it.matteopratesi;

import it.matteopratesi.config.CliParser;
import picocli.CommandLine;

public class JdbcBenchmarkApplicationLauncher {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CliParser()).execute(args);
        System.exit(exitCode);
    }
}
