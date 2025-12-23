package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

/*
 * PersonStaffTest
 * ----------------
 * Exercises common `Person` getters via a concrete `Receptionist` instance
 * and validates `Staff` subclass business behavior (bonus calculation).
 */
public class PersonStaffTest {

    // Confirm Person getters return constructor values and that Receptionist
    // implements the Staff bonus rule (20% of salary).
    @Test
    public void testPersonGettersAndStaffBonus() {
        Receptionist r = new Receptionist("Rec", "F", new Date(0), 0, 3000.0, new Date(0), "p", "Morning");
        assertEquals("Rec", r.getName());
        assertEquals("p", r.getNumber());

        // Receptionist bonus = 20% of salary per implementation
        assertEquals(3000.0 * 0.2, r.calculateBonus(), 1e-6);
    }
}
