package it.matteopratesi.analyzer;

import it.matteopratesi.OperationType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public abstract class ResultAnalyzer {

    protected static Logger logger = Logger.getLogger(ResultAnalyzer.class.getName());
    private HashMap<String, List<Long>> timing = new HashMap<>();

    public ResultAnalyzer() {
    }

    public void log(OperationType operationType, Long duration) {
        this.timing.merge(operationType.name(), new LinkedList<>(), (o, n) -> {
            o.add(duration);
            return o;
        });
    }

    public void printResult() {
        JdbcStatistics stats = new JdbcStatistics();
        List<Long> inserts = timing.get(OperationType.INSERT.name());
        stats.setAvgInsert(inserts.stream().filter(Objects::nonNull).mapToLong(t -> t).average().getAsDouble());
        stats.setMaxInsert(inserts.stream().filter(Objects::nonNull).mapToLong(t -> t).max().getAsLong());
        stats.setMinInsert(inserts.stream().filter(Objects::nonNull).mapToLong(t -> t).min().getAsLong());
        List<Long> selects = timing.get(OperationType.SELECT.name());
        stats.setAvgSelect(selects.stream().filter(Objects::nonNull).mapToLong(t -> t).average().getAsDouble());
        stats.setMaxSelect(selects.stream().filter(Objects::nonNull).mapToLong(t -> t).max().getAsLong());
        stats.setMinSelect(selects.stream().filter(Objects::nonNull).mapToLong(t -> t).min().getAsLong());
        List<Long> updates = timing.get(OperationType.UPDATE.name());
        stats.setAvgUpdate(updates.stream().filter(Objects::nonNull).mapToLong(t -> t).average().getAsDouble());
        stats.setMaxUpdate(updates.stream().filter(Objects::nonNull).mapToLong(t -> t).max().getAsLong());
        stats.setMinUpdate(updates.stream().filter(Objects::nonNull).mapToLong(t -> t).min().getAsLong());
        List<Long> deletes = timing.get(OperationType.SELECT.name());
        stats.setAvgDelete(deletes.stream().filter(Objects::nonNull).mapToLong(t -> t).average().getAsDouble());
        stats.setMaxDelete(deletes.stream().filter(Objects::nonNull).mapToLong(t -> t).max().getAsLong());
        stats.setMinDelete(deletes.stream().filter(Objects::nonNull).mapToLong(t -> t).min().getAsLong());
        print(stats);
    }

    protected abstract void print(JdbcStatistics stats);
}