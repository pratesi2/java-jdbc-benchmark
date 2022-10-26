package it.matteopratesi.analyzer;

public class ConsoleAnalyzer extends ResultAnalyzer {

    @Override
    protected void print(JdbcStatistics stats) {
//        logger.info("Statistics:");
//        logger.info("\t - Min insert time " + stats.getMinInsert());
//        logger.info("\t - Max insert time " + stats.getMaxInsert());
//        logger.info("\t - Avg insert time " + stats.getAvgInsert());
//        logger.info("\n");
//        logger.info("\t - Min select time " + stats.getMinSelect());
//        logger.info("\t - Max select time " + stats.getMaxSelect());
//        logger.info("\t - Avg select time " + stats.getAvgSelect());
//        logger.info("\n");
//        logger.info("\t - Min update time " + stats.getMinUpdate());
//        logger.info("\t - Max update time " + stats.getMaxUpdate());
//        logger.info("\t - Avg update time " + stats.getAvgUpdate());
//        logger.info("\n");
//        logger.info("\t - Min delete time " + stats.getMinDelete());
//        logger.info("\t - Max delete time " + stats.getMaxDelete());
//        logger.info("\t - Avg delete time " + stats.getAvgDelete());
        logger.info(stats.toString());
    }
}