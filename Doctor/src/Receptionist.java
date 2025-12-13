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
        if (p != null) {
            p.processPayment();
            System.out.println("Payment received and recorded by " + this.getName());
        }
    }

    public Payment generateInvoice(double amount, Appointment a, Date dueDate, boolean isPaid) {
        Payment p = new Payment(amount, dueDate, isPaid, a.getPatient(), a);
        a.setPayment(p);
        return p;
    }

    public void cancelAppointment(Appointment a) {
        if (a != null) {
            a.setStatus("Cancelled");
            System.out.println("The appointment is cancelled.");
        }
    }

    public Appointment bookAppointment(Doctor d, Patient p, Date date) {
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
    }
}