// 9:24 PM Saturday, December 13, 2025
// at the request of Mark Sameh, I revised the code and wrote this.
// Questions to ask Bo4:
// Q1: The +sendNotification() void method exists in Mermaid but not in Java. What shall I do? Should we remove it from the mermaid text or should I make the payment class implement the notifiable interface?
// Q2: The getStatus() method says that it returns a boolean in the mermaid 🥴 p.s. it shouldn't.
// and other minimal things that shouldn't bother you.
import java.util.Date;
public class Payment implements Comparable<Payment>, Printable {

    private double amount;
    private Date dueDate; // Data type fixed to match the mermaid text ✅
    private boolean isPaid;
    private Patient patient;
    private Appointment appointment;

    // -------- Constructor --------
    public Payment(double  amount, Date dueDate, boolean isPaid, Patient patient, Appointment appointment) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
        this.patient = patient;
        this.appointment = appointment;
    }
    public double calculateTotal(){
        if(this.patient.getInsuranceStatus()){
            return this.amount * 0.9;
        }
        else return this.amount;
    }
    // -------- Business Methods --------
    public void processPayment() {
        if (!isPaid) {
            isPaid = true;
            System.out.println("Payment processed successfully.");
        } else {
            System.out.println("Payment already completed.");
        }
    }

    public String getStatus() {
        return isPaid ? "Paid" : "Pending";
    }

    // -------- Printable Interface Methods --------

    @Override
    public void printDetails() {
        System.out.println("---- Payment Details ----");
        System.out.println("Amount: " + amount);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Status: " + getStatus());
        System.out.println("Patient: " + patient);
        System.out.println("Appointment: " + appointment);
    }

    // -------- Comparable Interface Method --------
    @Override
    public int compareTo(Payment other) {
        // Compare numerically by amount
        double a1 = this.amount;
        double a2 = other.amount;

        return Double.compare(a1, a2);
    }

}


