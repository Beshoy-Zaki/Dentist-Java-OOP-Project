package com;

/**
 * MainLauncher
 * ------------
 * A tiny launcher class that starts the JavaFX application. Using a
 * dedicated launcher makes configuration for `exec:java` and other
 * tooling more stable than relying on a class with heavy static GUI
 * initialization.
 */
public class MainLauncher {
    public static void main(String[] args) {
        // Delegate to the existing ClinicGUI Application entry point
        ClinicGUI.main(args);
    }
}
