package it.matteopratesi.config;

import java.io.IOException;
import java.io.InputStream;

public class Properties {

    private static Properties instance;
    private java.util.Properties props;

    private Properties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("configuration.properties")) {
            props = new java.util.Properties();
            props.load(input);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static Properties get() {
        if (instance == null)
            instance = new Properties();
        return instance;
    }

    public String getJdbcUrl() {
        return props.getProperty("jdbcUrl");
    }

    public String getUser() {
        return props.getProperty("user");
    }

    public String getPassword() {
        return props.getProperty("password");
    }
}
