package com.cavi.modules.impact;

import javax.swing.JComponent;

public class CaviIntegrationAdapter {

    // Replace with your real CAVI API when integrating.
    public interface SetupTabRegistry {
        void addTab(String name, JComponent component);
    }

    public static void register(SetupTabRegistry registry) {
        registry.addTab("Impact Response", new ImpactResponsePanel());
    }
}
