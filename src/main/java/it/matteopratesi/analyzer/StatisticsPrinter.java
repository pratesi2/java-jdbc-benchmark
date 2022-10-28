package it.matteopratesi.analyzer;

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
