package com.cavi.modules.impact;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class FilterEngine {

    public static FilterResult evaluate(TimeSeries ts, ThresholdRule rule) {
        FilterResult res = new FilterResult();
        List<ZonedDateTime> times = ts.getTimes();
        List<Double> vals = ts.getValues();
        Duration window = rule.getWindow();

        if (window == null || window.isZero()) {
            // Pointwise evaluation
            for (int i = 0; i < vals.size(); i++) {
                double v = vals.get(i);
                if (matches(rule.getOp(), v, rule.getThreshold())) {
                    res.add(new FilterResult.Match(times.get(i), v, "point match"));
                }
            }
        } else {
            // Sliding window average example
            for (int i = 0; i < vals.size(); i++) {
                ZonedDateTime t = times.get(i);
                ZonedDateTime start = t.minus(window);
                double sum = 0.0;
                int count = 0;
                for (int j = 0; j <= i; j++) {
                    if (!times.get(j).isBefore(start)) {
                        sum += vals.get(j);
                        count++;
                    }
                }
                if (count == 0) continue;
                double avg = sum / count;
                if (matches(rule.getOp(), avg, rule.getThreshold())) {
                    res.add(new FilterResult.Match(t, avg, "window avg match"));
                }
            }
        }
        return res;
    }

    private static boolean matches(ThresholdRule.Op op, double value, double threshold) {
        switch (op) {
            case GREATER: return value > threshold;
            case GE:      return value >= threshold;
            case LESS:    return value < threshold;
            case LE:      return value <= threshold;
            case EQUAL:   return value == threshold;
            default:      return false;
        }
    }
}
