package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

/*
 * AppointmentTest
 * ----------------
 * Tests for `Appointment` verify core behaviors:
 *  - construction and default status
 *  - upcoming/past detection
 *  - rescheduling changes date and status
 *  - comparison ordering via `compareTo`
 *  - attaching a `Payment`
 *  - notification message formatting
 */
public class AppointmentTest {

    // Verify constructor sets patient and default status, and that an appointment
    // scheduled in the future is considered upcoming.
    @Test
    public void testConstructorAndIsUpcomingAndStatus() {
        Date future = new Date(System.currentTimeMillis() + 60_000);
        Doctor doc = new Doctor("John Doe","M", new Date(0), 1, 1000.0, new Date(0), "123", "General");
        Prescription[] meds = new Prescription[0];
        Patient pat = new Patient("Jane", new Date(0), "F", "456", meds, false);
        Appointment a = new Appointment(future, doc, pat);

        // Expect patient reference preserved
        assertEquals(pat, a.getPatient());

        // New appointments default to "Scheduled"
        assertEquals("Scheduled", a.getStatus());

        // Future date => upcoming
        assertTrue(a.isUpcoming());
    }

    // Verify rescheduling updates date and status, and that compareTo reflects ordering.
    // Also verify a Payment object can be attached and retrieved.
    @Test
    public void testRescheduleAndCompareToAndPayment() {
        Date future = new Date(System.currentTimeMillis() + 60_000);
        Date later = new Date(System.currentTimeMillis() + 120_000);
        Doctor doc = new Doctor("John Doe","M", new Date(0), 1, 1000.0, new Date(0), "123", "General");
        Prescription[] meds = new Prescription[0];
        Patient pat = new Patient("Jane", new Date(0), "F", "456", meds, false);
        Appointment a = new Appointment(future, doc, pat);
        Appointment b = new Appointment(later, doc, pat);

        // a is earlier than b
        assertTrue(a.compareTo(b) < 0);

        // Reschedule a to the later date: status must change and dates must match
        a.reschedule(later);
        assertEquals("Rescheduled", a.getStatus());
        assertEquals(later, a.getDateTime());

        // Now they should be equal in ordering
        assertEquals(0, a.compareTo(b));

        // Attaching a payment should be retrievable
        Payment p = new Payment(100.0, later, false, pat, a);
        a.setPayment(p);
        assertEquals(p, a.getPayment());
    }

    // Verify notification message contains the expected prefix. We do not assert
    // the entire date string (locale-dependent), only the presence of the label.
    @Test
    public void testNotificationMessage() {
        Date future = new Date(System.currentTimeMillis() + 60_000);
        Doctor doc = new Doctor("John Doe","M", new Date(0), 1, 1000.0, new Date(0), "123", "General");
        Prescription[] meds = new Prescription[0];
        Patient pat = new Patient("Jane", new Date(0), "F", "456", meds, false);
        Appointment a = new Appointment(future, doc, pat);

        assertNotNull(a.getNotificationMessage());
        assertTrue(a.getNotificationMessage().contains("Appointment scheduled on"));
    }
}
