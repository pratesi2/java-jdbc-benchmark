package it.matteopratesi;

import it.matteopratesi.analyzer.ConsoleAnalyzer;
import it.matteopratesi.analyzer.ResultAnalyzer;
import it.matteopratesi.config.Properties;
import it.matteopratesi.db.ConnectionFactory;

import java.util.logging.Logger;

public class JdbcBenchmarkApplication {
    private static Logger logger = Logger.getLogger(ConnectionFactory.class.getName());

    public JdbcBenchmarkApplication(Properties props) {
        ConnectionFactory factory = new ConnectionFactory(props);
        factory.acquireConnection(conn -> {
            logger.info("Connected to database");
            ResultAnalyzer analyzer = new ConsoleAnalyzer();
            logger.info("Collecting statistics");
            props.getSql().forEach(op -> {
                OperationType operationType = OperationType.valueOf(op.substring(0, op.indexOf(' ')));
                Long executionTime = factory.executeStatement(conn, op);
                analyzer.log(operationType, executionTime);
            });
            logger.info("Printing statistics");
            analyzer.printResult();
        });
    }
}
