package it.matteopratesi.logic.impl;

import it.matteopratesi.logic.JdbcStatistics;
import it.matteopratesi.logic.StatisticsPrinter;

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