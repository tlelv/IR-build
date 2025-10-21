
package com.cavi.impact;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSeries {
    private final String id;
    private final List<ZonedDateTime> times = new ArrayList<>();
    private final List<Double> values = new ArrayList<>();

    public TimeSeries(String id) { this.id = id; }
    public String getId() { return id; }
    public List<ZonedDateTime> getTimes() { return times; }
    public List<Double> getValues() { return values; }

    public void add(ZonedDateTime t, Double v) {
        times.add(t);
        values.add(v);
    }

    public int size() { return values.size(); }
}
