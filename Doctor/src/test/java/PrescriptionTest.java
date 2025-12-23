package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * PrescriptionTest
 * ----------------
 * Lightweight tests for the `Prescription` model to ensure getters/setters
 * and the `toString()` method behave as expected. These are simple but
 * help catch regressions in the data model.
 */
public class PrescriptionTest {

    // Verify that setters update fields and that toString contains field names
    @Test
    public void testGettersSettersAndToString() {
        Prescription r = new Prescription("Med", "1 pill", "After food");
        assertTrue(r.getMedication().contains("Med"));
        r.setDosage("2 pills");
        assertEquals("2 pills", r.getDosage());
        r.setInstructions("Before food");

        // toString should include medication field name and value representation
        assertTrue(r.toString().contains("medication"));
    }
}
