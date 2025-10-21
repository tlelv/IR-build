package com.cavi.modules.impact;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

public class ImpactResponseApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Impact Response — Standalone");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(900, 600);
            f.setLocationRelativeTo(null);

            JTabbedPane tabs = new JTabbedPane();
            tabs.addTab("Impact Response", new ImpactResponsePanel());
            JTextArea ta = new JTextArea(
                "How to run:\n" +
                "- Windows: double-click run.bat\n" +
                "- macOS/Linux: ./run.sh\n\n" +
                "Usage:\n" +
                "1) Load a CSV (timestamp,value).\n" +
                "2) Edit a threshold rule (operator, threshold, optional window).\n" +
                "3) Run to see matches."
            );
            ta.setEditable(false);
            tabs.addTab("Help", new JScrollPane(ta));

            f.getContentPane().setLayout(new BorderLayout());
            f.setContentPane(tabs);
            f.setVisible(true);
        });
    }
}
