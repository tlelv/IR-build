package com.cavi.modules.impact;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ResultsTableModel extends AbstractTableModel {
    private final List<FilterResult.Match> rows = new ArrayList<>();
    private static final String[] COLS = {"Time", "Value", "Note"};

    public void setResults(List<FilterResult.Match> matches) {
        rows.clear();
        rows.addAll(matches);
        fireTableDataChanged();
    }

    @Override public int getRowCount() { return rows.size(); }
    @Override public int getColumnCount() { return COLS.length; }
    @Override public String getColumnName(int c) { return COLS[c]; }

    @Override public Object getValueAt(int r, int c) {
        FilterResult.Match m = rows.get(r);
        switch (c) {
            case 0: return m.time.toString();
            case 1: return m.value;
            case 2: return m.note;
            default: return null;
        }
    }
}
