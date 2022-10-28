package it.matteopratesi.analyzer;

import com.google.gson.GsonBuilder;

/**
 * Object containing the statistic of the analysis
 */
public class JdbcStatistics {
    private Long minInsert;
    private Double avgInsert;
    private Long maxInsert;
    private Long minSelect;
    private Double avgSelect;
    private Long maxSelect;
    private Long minUpdate;
    private Double avgUpdate;
    private Long maxUpdate;
    private Long minDelete;
    private Double avgDelete;
    private Long maxDelete;

    public JdbcStatistics() {
    }

    public Long getMinInsert() {
        return minInsert;
    }

    public void setMinInsert(Long minInsert) {
        this.minInsert = minInsert;
    }

    public Double getAvgInsert() {
        return avgInsert;
    }

    public void setAvgInsert(Double avgInsert) {
        this.avgInsert = avgInsert;
    }

    public Long getMaxInsert() {
        return maxInsert;
    }

    public void setMaxInsert(Long maxInsert) {
        this.maxInsert = maxInsert;
    }

    public Long getMinSelect() {
        return minSelect;
    }

    public void setMinSelect(Long minSelect) {
        this.minSelect = minSelect;
    }

    public Double getAvgSelect() {
        return avgSelect;
    }

    public void setAvgSelect(Double avgSelect) {
        this.avgSelect = avgSelect;
    }

    public Long getMaxSelect() {
        return maxSelect;
    }

    public void setMaxSelect(Long maxSelect) {
        this.maxSelect = maxSelect;
    }

    public Long getMinUpdate() {
        return minUpdate;
    }

    public void setMinUpdate(Long minUpdate) {
        this.minUpdate = minUpdate;
    }

    public Double getAvgUpdate() {
        return avgUpdate;
    }

    public void setAvgUpdate(Double avgUpdate) {
        this.avgUpdate = avgUpdate;
    }

    public Long getMaxUpdate() {
        return maxUpdate;
    }

    public void setMaxUpdate(Long maxUpdate) {
        this.maxUpdate = maxUpdate;
    }

    public Long getMinDelete() {
        return minDelete;
    }

    public void setMinDelete(Long minDelete) {
        this.minDelete = minDelete;
    }

    public Double getAvgDelete() {
        return avgDelete;
    }

    public void setAvgDelete(Double avgDelete) {
        this.avgDelete = avgDelete;
    }

    public Long getMaxDelete() {
        return maxDelete;
    }

    public void setMaxDelete(Long maxDelete) {
        this.maxDelete = maxDelete;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }
}
