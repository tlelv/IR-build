package com.cavi.modules.impact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSeriesLoader {

    /**
     * Load a CSV with header: timestamp,value where timestamp is ISO-8601 (e.g., 2025-01-01T00:00:00Z).
     */
    public static TimeSeries loadCsv(File csvFile, String seriesId) throws IOException {
        TimeSeries ts = new TimeSeries(seriesId);
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String header = br.readLine(); // header
            if (header == null) return ts;
            String line;
            DateTimeFormatter fmt = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 2) continue;
                String tsStr = parts[0].trim();
                String valStr = parts[1].trim();
                if (tsStr.isEmpty() || valStr.isEmpty()) continue;
                ZonedDateTime t = ZonedDateTime.parse(tsStr, fmt);
                Double v = Double.parseDouble(valStr);
                ts.add(t, v);
            }
        }
        return ts;
    }
}
