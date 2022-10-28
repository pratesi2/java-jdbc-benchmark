package it.matteopratesi.config;

import java.util.List;

/**
 * Properties class containing all configuration needed for the program to run
 */
public class Properties {

    private String jdbcUrl;
    private String user;
    private String password;
    private List<String> sql;
    private int commitPeriod;

    public Properties(String jdbcUrl, String user, String password, int commitPeriod, List<String> sql) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
        this.commitPeriod = commitPeriod;
        this.sql = sql;
    }

    /**
     * @return the jdbc connection url, in format of "jdbc:postgresql://<server>:<port>/<dbName>"
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    /**
     * @return the binding username
     */
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the binding password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the list of strings containing the statements to be executed on the db for the benchmark
     */
    public List<String> getSql() {
        return sql;
    }

    public void setSql(List<String> sql) {
        this.sql = sql;
    }

    /**
     * @return the number of statements to be executed before execute a commit
     */
    public int getCommitPeriod() {
        return commitPeriod;
    }

    public void setCommitPeriod(int commitPeriod) {
        this.commitPeriod = commitPeriod;
    }
}
