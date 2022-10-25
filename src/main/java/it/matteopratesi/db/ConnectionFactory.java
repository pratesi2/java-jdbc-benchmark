package it.matteopratesi.db;

import it.matteopratesi.config.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static Logger logger = Logger.getLogger(ConnectionFactory.class.getName());

    public static void acquireConnection(Consumer<Connection> job) {
        try (Connection conn = DriverManager.getConnection(
                Properties.get().getJdbcUrl(), Properties.get().getUser(), Properties.get().getPassword())) {
            if (conn != null) {
                job.accept(conn);
            }
        } catch (SQLException e) {
            logger.severe(String.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage()));
        } catch (Exception e) {
            logger.severe("Java Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}