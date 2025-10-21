
package com.cavi.impact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSeriesLoader {
    // Expects CSV with header: timestamp,value (ISO-8601 timestamp)
    public static TimeSeries loadCsv(File csvFile, String seriesId) throws Exception {
        TimeSeries ts = new TimeSeries(seriesId);
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String header = br.readLine(); // consume header
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
