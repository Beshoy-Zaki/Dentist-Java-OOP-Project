import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

/*
 * PatientTest
 * -----------
 * Tests for `Patient` cover:
 *  - insurance flag behavior
 *  - appointment list management when appointments are added
 */
public class PatientTest {

    // Verify a newly created patient exposes the insurance status and that
    // adding an appointment increases their appointments list.
    @Test
    public void testAddAppointmentAndInsurance() {
        Prescription[] meds = new Prescription[0];
        Patient p = new Patient("Alice", new Date(0), "F", "000", meds, true);

        // Insurance flag must reflect constructor argument
        assertTrue(p.getInsuranceStatus());

        // Initially no appointments
        assertEquals(0, p.getAppointments().size());

        Doctor d = new Doctor("Dr A","M", new Date(0), 0, 1000.0, new Date(0), "n", "Spec");
        Appointment a = new Appointment(new Date(System.currentTimeMillis()+60000), d, p);

        // After adding, the appointments list should contain the new appointment
        p.addAppointment(a);
        assertEquals(1, p.getAppointments().size());
    }
}
