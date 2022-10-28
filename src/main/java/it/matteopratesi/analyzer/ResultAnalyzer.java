package it.matteopratesi.analyzer;

import java.util.List;
import java.util.Map;

/**
 * Analyze collected data generating a {@link JdbcStatistics} object with the result of the analysis.
 */
public interface ResultAnalyzer {

    /**
     * Analyze collected data
     * @param collectedData to be analyzed
     * @return a {@link JdbcStatistics} object with the result of the analysis
     */
    JdbcStatistics analyzeCollectedData(Map<String, List<Long>> collectedData);

}
