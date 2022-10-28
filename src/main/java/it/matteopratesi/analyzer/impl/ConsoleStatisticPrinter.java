package it.matteopratesi.analyzer.impl;

import it.matteopratesi.analyzer.JdbcStatistics;
import it.matteopratesi.analyzer.StatisticsPrinter;

import java.util.logging.Logger;

/**
 * Implementatio
 */
public class ConsoleStatisticPrinter implements StatisticsPrinter {

    private static Logger logger = Logger.getLogger(ConsoleStatisticPrinter.class.getName());

    @Override
    public void print(JdbcStatistics statistics) {
        logger.info(statistics.toString());
    }
}