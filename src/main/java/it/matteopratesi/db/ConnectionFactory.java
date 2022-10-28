package it.matteopratesi.db;

import it.matteopratesi.config.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * This class act as an interface for the database, creating the connection and execute each statement
 */
public class ConnectionFactory {
    private static Logger logger = Logger.getLogger(ConnectionFactory.class.getName());
    private Properties props;

    public ConnectionFactory(Properties props) {
        this.props = props;
    }

    /**
     * Connect to the database with the provided configuration. In case of success execute the consumer.
     *
     * @param job to be executed if connection is acquired successfully
     * @throws Exception if any occurs during the connection initialization
     */
    public void acquireConnection(Consumer<Connection> job) throws Exception {
        try (Connection conn = DriverManager.getConnection(
                props.getJdbcUrl(), props.getUser(), props.getPassword())) {
            if (conn != null) {
                logger.info("Connected to database");
                conn.setAutoCommit(false);
                job.accept(conn);
            }
        }
    }

    /**
     *
     * @param conn the connection used to prepare the statement
     * @param stmt the statement to execute
     * @param commit indicate if there is need to send a commit
     * @return the execution time of the statement
     */
    public Long executeStatement(Connection conn, String stmt, Boolean commit) {
        try (PreparedStatement s = conn.prepareStatement(stmt)) {
            long start = System.nanoTime();
            s.execute();
            long end = System.nanoTime();
            if (commit) {
                conn.commit();
            }
            return end - start;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}