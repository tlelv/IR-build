
package com.cavi.impact;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ImpactResponseApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Impact Response — Standalone");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(900, 600);
            f.setLocationRelativeTo(null);

            JTabbedPane tabs = new JTabbedPane();
            tabs.addTab("Impact Response", new ImpactResponsePanel());
            tabs.addTab("Help", makeHelpPanel());
            f.setContentPane(tabs);
            f.setVisible(true);
        });
    }

    private static JComponent makeHelpPanel() {
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        StringBuilder sb = new StringBuilder();
        sb.append("How to use:\n");
        sb.append("1) Load a CSV (timestamp,value).\n");
        sb.append("2) Edit a threshold rule (operator, threshold, window).\n");
        sb.append("3) Run to see matches.\n\n");
        sb.append("A sample CSV is included in the JAR at /sample.csv.\n");
        ta.setText(sb.toString());
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JScrollPane(ta), BorderLayout.CENTER);
        return p;
    }
}
