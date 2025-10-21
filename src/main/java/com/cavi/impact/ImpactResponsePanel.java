
package com.cavi.impact;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ImpactResponsePanel extends JPanel {
    private final JButton loadBtn = new JButton("Load CSV");
    private final JComboBox<String> seriesCombo = new JComboBox<>();
    private final JButton ruleBtn = new JButton("Edit Rule");
    private final JButton runBtn = new JButton("Run");
    private final ResultsTableModel resultsModel = new ResultsTableModel();

    private final List<TimeSeries> loadedSeries = new ArrayList<>();
    private ThresholdRule currentRule = new ThresholdRule(ThresholdRule.Op.GREATER, 100.0, Duration.ZERO);

    public ImpactResponsePanel() {
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(loadBtn); top.add(new JLabel("Series:")); top.add(seriesCombo);
        top.add(ruleBtn); top.add(runBtn);
        add(top, BorderLayout.NORTH);

        JTable results = new JTable(resultsModel);
        add(new JScrollPane(results), BorderLayout.CENTER);

        loadBtn.addActionListener(e -> onLoad());
        ruleBtn.addActionListener(e -> onEditRule());
        runBtn.addActionListener(e -> onRun());
    }

    private void onLoad() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try {
                String id = f.getName();
                TimeSeries ts = TimeSeriesLoader.loadCsv(f, id);
                loadedSeries.add(ts);
                seriesCombo.addItem(ts.getId());
                if (seriesCombo.getSelectedIndex() < 0) seriesCombo.setSelectedIndex(0);
                JOptionPane.showMessageDialog(this, "Loaded "+ts.size()+" rows from "+id);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to load: "+ex.getMessage());
            }
        }
    }

    private void onEditRule() {
        JPanel p = new JPanel(new GridLayout(3,2,8,8));
        JComboBox<ThresholdRule.Op> opC = new JComboBox<>(ThresholdRule.Op.values());
        opC.setSelectedItem(currentRule.getOp());
        JTextField thr = new JTextField(Double.toString(currentRule.getThreshold()));
        JTextField windowSec = new JTextField("0");
        p.add(new JLabel("Operator:")); p.add(opC);
        p.add(new JLabel("Threshold:")); p.add(thr);
        p.add(new JLabel("Window (sec, 0=point):")); p.add(windowSec);
        int ok = JOptionPane.showConfirmDialog(this, p, "Edit Rule", JOptionPane.OK_CANCEL_OPTION);
        if (ok == JOptionPane.OK_OPTION) {
            ThresholdRule.Op op = (ThresholdRule.Op)opC.getSelectedItem();
            double v = Double.parseDouble(thr.getText());
            long secs = Long.parseLong(windowSec.getText());
            Duration d = secs <= 0 ? Duration.ZERO : Duration.ofSeconds(secs);
            currentRule = new ThresholdRule(op, v, d);
        }
    }

    private void onRun() {
        int idx = seriesCombo.getSelectedIndex();
        if (idx < 0) { JOptionPane.showMessageDialog(this, "No series selected"); return; }
        TimeSeries ts = loadedSeries.get(idx);
        FilterResult res = FilterEngine.evaluate(ts, currentRule);
        resultsModel.setResults(res.getMatches());
        JOptionPane.showMessageDialog(this, "Found "+res.getMatches().size()+" match(es).");
    }
}
