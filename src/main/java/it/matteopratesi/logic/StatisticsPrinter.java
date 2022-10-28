package it.matteopratesi.logic;

/**
 * Prints the statistics
 */
public interface StatisticsPrinter {

    /**
     * Print the statistics
     * @param statistics to be printed
     */
    void print(JdbcStatistics statistics);

}
