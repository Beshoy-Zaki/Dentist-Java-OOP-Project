import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

/*
 * PaymentTest
 * -----------
 * Validates payment calculations and processing state transitions:
 *  - discounted total for insured patients
 *  - status transitions from "Pending" to "Paid" after processing
 */
public class PaymentTest {

    // Ensure calculateTotal applies insurance discount and processPayment flips status
    @Test
    public void testCalculateTotalAndProcess() {
        Prescription[] meds = new Prescription[0];
        Patient insured = new Patient("Ins", new Date(0), "F", "n", meds, true);
        Appointment a = new Appointment(new Date(System.currentTimeMillis()+60000), new Doctor("D","M", new Date(0), 0, 100.0, new Date(0), "c", "s"), insured);
        Payment p = new Payment(200.0, new Date(), false, insured, a);

        // Insured patient should get 10% discount
        assertEquals(200.0 * 0.9, p.calculateTotal(), 1e-6);

        // Status initially pending, then paid after processing
        assertEquals("Pending", p.getStatus());
        p.processPayment();
        assertEquals("Paid", p.getStatus());
    }
}
