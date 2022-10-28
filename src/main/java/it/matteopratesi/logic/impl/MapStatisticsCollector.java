package it.matteopratesi.logic.impl;

import it.matteopratesi.OperationType;
import it.matteopratesi.logic.StatisticsCollector;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *  This class collect timing of operations executed on the database
 */
public class MapStatisticsCollector implements StatisticsCollector {

    private HashMap<String, List<Long>> operations = new HashMap<>();

    public MapStatisticsCollector() {
    }

    /**
     *  Store the duration of the DML request performed
     * @param operationType type of DML request - see {@link OperationType}
     * @param duration time, in millis, consumed for the execution of DML request
     */
    @Override
    public void collect(OperationType operationType, Long duration) {
        this.operations.merge(operationType.name(), new LinkedList<>(), (o, n) -> {
            o.add(duration);
            return o;
        });
    }

    @Override
    public HashMap<String, List<Long>> getCollectedData() {
        return operations;
    }

}