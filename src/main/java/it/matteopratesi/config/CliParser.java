package it.matteopratesi.config;

import it.matteopratesi.JdbcBenchmarkApplication;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "jdbcBenchmark", mixinStandardHelpOptions = true, version = "jdbcBenchmark 1.0",
        description = "Execute a benchmark analisys to the given PostGRE database")
public class CliParser implements Callable<JdbcBenchmarkApplication> {

    @CommandLine.Option(names = {"-c", "--connection-url"}, description = "jdbc:postgresql://<server>:<port>/<dbName>")
    private String jdbcUrl;

    @CommandLine.Option(names = {"-u", "--user"}, description = "The db user")
    private String user;

    @CommandLine.Option(names = {"-p", "--password"}, description = "The db password")
    private String password;

    @CommandLine.Parameters(index = "0", arity = "0..1", description = "Optional. The file containing the script to be executed for testing.")
    private File testScript;

    @Override
    public JdbcBenchmarkApplication call() throws Exception {
        List<String> sql;
        if (testScript != null) {
            sql = Files.readAllLines(testScript.toPath());
        } else {
            sql = Files.readAllLines(Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("test.sql")).toURI()));
        }
        return new JdbcBenchmarkApplication(new Properties(jdbcUrl, user, password, sql));
    }
}
