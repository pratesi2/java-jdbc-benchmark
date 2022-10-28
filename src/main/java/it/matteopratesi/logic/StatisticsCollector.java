package it.matteopratesi.logic;

import it.matteopratesi.OperationType;

import java.util.List;
import java.util.Map;

/**
 *  Add recorded duration of DML requests to a Map, returning it when needed
 */
public interface StatisticsCollector {

    /**
     * Add the duration to the map for the recorded DML request
     * @param operationType the DML request type
     * @param duration in nanoseconds of the operation
     */
    void collect(OperationType operationType, Long duration);

    /**
     * @return the collected data as a map, with the DML operation types as keys, and a list of duration of the single execution
     * as value
     */
    Map<String, List<Long>> getCollectedData();
}
