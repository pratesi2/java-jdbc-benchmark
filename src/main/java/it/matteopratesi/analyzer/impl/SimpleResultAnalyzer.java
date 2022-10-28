package it.matteopratesi.analyzer.impl;

import it.matteopratesi.OperationType;
import it.matteopratesi.analyzer.JdbcStatistics;
import it.matteopratesi.analyzer.ResultAnalyzer;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class analyze statistics collected by {@link MapStatisticsCollector}
 */
public class SimpleResultAnalyzer implements ResultAnalyzer {

    public JdbcStatistics analyzeCollectedData(Map<String, List<Long>> collectedData) {
        JdbcStatistics stats = new JdbcStatistics();
        List<Long> inserts = collectedData.get(OperationType.INSERT.name());
        stats.setAvgInsert(inserts.stream().filter(Objects::nonNull).mapToLong(t -> t).average().getAsDouble());
        stats.setMaxInsert(inserts.stream().filter(Objects::nonNull).mapToLong(t -> t).max().getAsLong());
        stats.setMinInsert(inserts.stream().filter(Objects::nonNull).mapToLong(t -> t).min().getAsLong());
        List<Long> selects = collectedData.get(OperationType.SELECT.name());
        stats.setAvgSelect(selects.stream().filter(Objects::nonNull).mapToLong(t -> t).average().getAsDouble());
        stats.setMaxSelect(selects.stream().filter(Objects::nonNull).mapToLong(t -> t).max().getAsLong());
        stats.setMinSelect(selects.stream().filter(Objects::nonNull).mapToLong(t -> t).min().getAsLong());
        List<Long> updates = collectedData.get(OperationType.UPDATE.name());
        stats.setAvgUpdate(updates.stream().filter(Objects::nonNull).mapToLong(t -> t).average().getAsDouble());
        stats.setMaxUpdate(updates.stream().filter(Objects::nonNull).mapToLong(t -> t).max().getAsLong());
        stats.setMinUpdate(updates.stream().filter(Objects::nonNull).mapToLong(t -> t).min().getAsLong());
        List<Long> deletes = collectedData.get(OperationType.SELECT.name());
        stats.setAvgDelete(deletes.stream().filter(Objects::nonNull).mapToLong(t -> t).average().getAsDouble());
        stats.setMaxDelete(deletes.stream().filter(Objects::nonNull).mapToLong(t -> t).max().getAsLong());
        stats.setMinDelete(deletes.stream().filter(Objects::nonNull).mapToLong(t -> t).min().getAsLong());
        return stats;
    }

}