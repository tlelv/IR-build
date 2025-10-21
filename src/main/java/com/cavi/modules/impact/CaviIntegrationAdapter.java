
package com.cavi.modules.impact;

import javax.swing.*;

/**
 * Template-only class showing how to register ImpactResponsePanel into CAVI.
 * Replace the pseudo types with your actual CAVI APIs when integrating.
 */
public class CaviIntegrationAdapter {

    // Pseudo CAVI registry type (replace with real one)
    public interface SetupTabRegistry {
        void addTab(String name, JComponent component);
    }

    /** Example entry point invoked by CAVI module loader. */
    public static void register(SetupTabRegistry registry) {
        registry.addTab("Impact Response", new ImpactResponsePanel());
    }
}
