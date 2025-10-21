package com.cavi.modules.impact;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterResult {

    public static class Match {
        public final ZonedDateTime time;
        public final double value;
        public final String note;

        public Match(ZonedDateTime time, double value, String note) {
            this.time = time;
            this.value = value;
            this.note = note;
        }
    }

    private final List<Match> matches = new ArrayList<>();

    public void add(Match m) { matches.add(m); }

    public List<Match> getMatches() { return matches; }
}
