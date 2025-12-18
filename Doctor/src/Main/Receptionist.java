import java.util.Date;

public class Receptionist extends Staff {
    
    private String shiftTime;

    public Receptionist(String name, String gender, Date dob, int staffId, double salary, Date hireDate, String contact, String shiftTime) {
        super(name, gender, dob, staffId, salary, hireDate, contact);
        this.shiftTime = shiftTime;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.2; 
    }

    @Override
    protected void displayInfo() {
        super.displayInfo(); 
        System.out.println("Shift Time: " + shiftTime);
    }

    public void receivePayment(Payment p) {
        try {
            if (p == null) {
                throw new NullPointerException("No payment provided to receive.");
            }
            // Original Logic
            p.processPayment();
            System.out.println("Payment received and recorded by " + this.getName());
            
        } catch (NullPointerException e) {
            System.err.println("Transaction Failed: " + e.getMessage());
        }
    }

    public Payment generateInvoice(double amount, Appointment a, Date dueDate, boolean isPaid) {
        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Invoice amount cannot be negative.");
            }
            if (a == null) {
                throw new IllegalArgumentException("Cannot generate invoice for null appointment.");
            }

            // Original Logic
            Payment p = new Payment(amount, dueDate, isPaid, a.getPatient(), a);
            a.setPayment(p);
            return p;

        } catch (IllegalArgumentException e) {
            System.out.println("Error Generating Invoice: " + e.getMessage());
            return null;
        }
    }

    public void cancelAppointment(Appointment a) {
        if (a != null) {
            a.setStatus("Cancelled");
            System.out.println("The appointment is cancelled.");
        }
    }

    public Appointment bookAppointment(Doctor d, Patient p, Date date) {
        try {
            // Validate that inputs are not null before proceeding
            if (d == null || p == null || date == null) {
                throw new IllegalArgumentException("Doctor, Patient, and Date are required to book.");
            }

            // Original Logic
            if (d.isAvailable(date)) {
                Appointment a = new Appointment(date, d, p);
                d.addAppointment(a);
                p.addAppointment(a);
                System.out.println("Appointment booked successfully.");
                return a;
            } else {
                System.out.println("Doctor is not available at this time.");
                return null;
            }

        } catch (IllegalArgumentException e) {
            // Handles missing data
            System.out.println("Booking Error: " + e.getMessage());
            return null;
        }
    }
}