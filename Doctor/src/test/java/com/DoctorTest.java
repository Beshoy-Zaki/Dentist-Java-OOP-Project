package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

/*
 * DoctorTest
 * ----------
 * Tests for `Doctor` behavior focusing on:
 *  - bonus calculation (business rule)
 *  - availability and schedule management (no double-booking)
 */
public class DoctorTest {

    // Validate bonus calculation uses 25% of salary for `Doctor`.
    // Also verify that adding an appointment marks the slot as unavailable.
    @Test
    public void testCalculateBonusAndAvailability() {
        Date now = new Date();
        Doctor d = new Doctor("Dr Test","M", new Date(0), 0, 4000.0, now, "n/a", "General");

        // Business rule: bonus is 25% of salary
        assertEquals(4000.0 * 0.25, d.calculateBonus(), 1e-6);

        Patient p = new Patient("P", new Date(0), "F", "n", new Prescription[0], false);
        Date slot = new Date(System.currentTimeMillis() + 60_000);
        Appointment a = new Appointment(slot, d, p);

        // Doctor should be available before adding appointment
        assertTrue(d.isAvailable(slot));

        // After adding, the same slot must be unavailable
        d.addAppointment(a);
        assertFalse(d.isAvailable(slot));
    }
}
