package it.matteopratesi;

import it.matteopratesi.logic.*;
import it.matteopratesi.config.Properties;
import it.matteopratesi.db.ConnectionFactory;

import java.util.List;
import java.util.logging.Logger;

/**
 * The class executing the benchmark analysis.
 */
public class JdbcBenchmarkApplication {
    private static Logger logger = Logger.getLogger(ConnectionFactory.class.getName());
    private Properties props;
    private StatisticsCollector collector;
    private ResultAnalyzer analyzer;
    private StatisticsPrinter printer;

    public JdbcBenchmarkApplication(Properties props, StatisticsCollector collector, ResultAnalyzer analyzer, StatisticsPrinter printer) {
        this.props = props;
        this.collector = collector;
        this.analyzer = analyzer;
        this.printer = printer;
    }

    /**
     *  This method first acquire a connection from the {@link ConnectionFactory}, then execute every single statement to
     *  the database, recording the execution time and passing it to the {@link StatisticsCollector}.
     *  With the data collected invoke the analysis of {@link ResultAnalyzer}, and then pass the result to the
     *  {@link StatisticsPrinter}
     */
    public void executeBenchmark() throws Exception {
        ConnectionFactory factory = new ConnectionFactory(props);
        factory.acquireConnection(conn -> {
            logger.info("Collecting statistics");
            List<String> statements = props.getSql();
            for (int i = 0; i < statements.size(); i++) {
                String stmt = statements.get(i);
                //Assume the script contains each DML statement in a distinct line
                OperationType operationType = OperationType.valueOf(stmt.substring(0, stmt.indexOf(' ')));
                //need to call ConnectionFactory passing its connection - to improve
                Long executionTime = factory.executeStatement(conn, stmt, i % props.getCommitPeriod() == 0);
                collector.collect(operationType, executionTime);
            }
            logger.info("Analyzing statistics");
            JdbcStatistics statistics = analyzer.analyzeCollectedData(collector.getCollectedData());
            logger.info("Printing statistics");
            printer.print(statistics);
        });
    }
}
