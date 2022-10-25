package it.matteopratesi;

import it.matteopratesi.db.ConnectionFactory;

import java.util.logging.Logger;

public class JdbcBenchmark {
    private static Logger logger = Logger.getLogger(JdbcBenchmark.class.getName());

    public static void main(String[] args) {
        ConnectionFactory.acquireConnection(conn -> {
            logger.info("Connected to database");

            logger.info("Executing benchmark for insert statements");

            logger.info("Executing benchmark for select statements");

            logger.info("Executing benchmark for update statements");

            logger.info("Executing benchmark for delete statements");
        });
    }
}
