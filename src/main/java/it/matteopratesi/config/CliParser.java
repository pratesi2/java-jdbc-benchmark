package it.matteopratesi.config;

import it.matteopratesi.JdbcBenchmarkApplication;
import it.matteopratesi.analyzer.ResultAnalyzer;
import it.matteopratesi.analyzer.StatisticsCollector;
import it.matteopratesi.analyzer.StatisticsPrinter;
import it.matteopratesi.analyzer.impl.ConsoleStatisticPrinter;
import it.matteopratesi.analyzer.impl.MapStatisticsCollector;
import it.matteopratesi.analyzer.impl.SimpleResultAnalyzer;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * This utility class retrieve and parse from command line config properties, building a {@link Properties} object to pass
 * to the main Application.
 * Also this class initialize {@link StatisticsCollector}, {@link ResultAnalyzer}
 * and {@link StatisticsPrinter} to use in the process
 */
@CommandLine.Command(name = "jdbcBenchmark", mixinStandardHelpOptions = true, version = "jdbcBenchmark 1.0",
        description = "Execute a benchmark analisys to the given PostGRE database")
public class CliParser implements Callable<Integer> {

    @CommandLine.Option(names = {"-c", "--connection-url"}, description = "jdbc:postgresql://<server>:<port>/<dbName>")
    private String jdbcUrl;

    @CommandLine.Option(names = {"-u", "--user"}, description = "The db user")
    private String user;

    @CommandLine.Option(names = {"-p", "--password"}, description = "The db password")
    private String password;

    @CommandLine.Option(names = {"-x", "--commit-period"}, defaultValue = "1", description = "The number of statements to be executed before a commit")
    private int commitPeriod;

    @CommandLine.Parameters(index = "0", arity = "0..1", description = "Optional. The file containing the script to be executed for testing.")
    private File testScript;

    @Override
    public Integer call() throws Exception {
        //for the moment Collector, Analyzer and Printer are simply initialized. Future changes may implement reflection
        //to load custom extensions as well
        JdbcBenchmarkApplication jdbcBenchmarkApplication = new JdbcBenchmarkApplication(new Properties(jdbcUrl, user, password, commitPeriod, retrieveAllStatementsFromFile()),
                new MapStatisticsCollector(), new SimpleResultAnalyzer(), new ConsoleStatisticPrinter());
        jdbcBenchmarkApplication.executeBenchmark();
        return 1;
    }

    /**
     * This method allows retrieving sql statements from a custom file passed in the config, allowing fallback using
     * provided demo one.
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    private List<String> retrieveAllStatementsFromFile() throws IOException, URISyntaxException {
        List<String> sql;
        if (testScript != null) {
            sql = Files.readAllLines(testScript.toPath());
        } else {
            URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource("test.sql")).toURI();
            final Path path;
            //in case I'm running a standalone jar file, the filesystem starts from the inside of the jar
            if (uri.toString().contains("!")) {
                final Map<String, String> env = new HashMap<>();
                final String[] array = uri.toString().split("!");
                final FileSystem fs = FileSystems.newFileSystem(URI.create(array[0]), env);
                path = fs.getPath(array[1]);
            } else {
                path = Path.of(uri);
            }
            sql = Files.readAllLines(path);
        }
        return sql;
    }
}
