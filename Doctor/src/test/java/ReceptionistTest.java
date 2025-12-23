package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

/*
 * ReceptionistTest
 * -----------------
 * Integration-style tests that exercise receptionist operations which
 * coordinate multiple domain objects:
 *  - booking an appointment (updates Doctor and Patient state)
 *  - cancelling an appointment (status change)
 *  - invoice generation and payment processing
 *
 * These tests validate collaboration and state transitions rather than
 * low-level getters.
 */
public class ReceptionistTest {

    // Book an appointment when doctor is available, then cancel it, generate
    // an invoice and complete payment. Each step asserts the expected state.
    @Test
    public void testBookingCancellingAndPaymentFlow() {
        Doctor d = new Doctor("Doc","M", new Date(0), 0, 1000.0, new Date(0), "n", "Spec");
        Prescription[] meds = new Prescription[0];
        Patient p = new Patient("Pat", new Date(0), "F", "c", meds, false);
        Receptionist r = new Receptionist("Front","F", new Date(0), 0, 2000.0, new Date(0), "x", "Shift");

        Date slot = new Date(System.currentTimeMillis() + 60_000);

        // Booking should return a new appointment and add it to patient/doctor
        Appointment a = r.bookAppointment(d, p, slot);
        assertNotNull(a);
        assertEquals(1, p.getAppointments().size());
        assertFalse(d.isAvailable(slot));

        // Canceling should set the appointment status to "Cancelled"
        r.cancelAppointment(a);
        assertEquals("Cancelled", a.getStatus());

        // Invoice generation should attach a Payment to the appointment
        Payment pay = r.generateInvoice(150.0, a, new Date(), false);
        assertNotNull(pay);
        assertEquals(pay, a.getPayment());

        // Receiving payment should mark it as paid
        r.receivePayment(pay);
        assertEquals("Paid", pay.getStatus());
    }
}
