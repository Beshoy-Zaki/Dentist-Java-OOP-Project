public class Payment implements Comparable<Payment>, Printable {

    private double amount;
    private String dueDate;
    private boolean isPaid;
    private Patient patient;
    private Appointment appointment;

    // -------- Constructor --------
    public Payment(double  amount, String dueDate, boolean isPaid, Patient patient, Appointment appointment) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
        this.patient = patient;
        this.appointment = appointment;
    }
    public double calculateTotal(){
        if(this.patient.getinsuranceStatus()){
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

