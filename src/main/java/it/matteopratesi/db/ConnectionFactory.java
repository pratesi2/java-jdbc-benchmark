package it.matteopratesi.db;

import it.matteopratesi.config.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static Logger logger = Logger.getLogger(ConnectionFactory.class.getName());
    private Properties props;

    public ConnectionFactory(Properties props) {
        this.props = props;
    }

    public void acquireConnection(Consumer<Connection> job) {
        try (Connection conn = DriverManager.getConnection(
                props.getJdbcUrl(), props.getUser(), props.getPassword())) {
            if (conn != null) {
                conn.setAutoCommit(false);
                job.accept(conn);
            }
        } catch (SQLException e) {
            logger.severe(String.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage()));
        } catch (Exception e) {
            logger.severe("Java Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Long executeStatement(Connection conn, String op) {
        try (PreparedStatement stmt = conn.prepareStatement(op)) {
            long start = System.nanoTime();
            stmt.execute();
            long end = System.nanoTime();
            return end - start;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}