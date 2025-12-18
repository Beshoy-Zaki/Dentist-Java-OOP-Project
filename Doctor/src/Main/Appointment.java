import java.util.Date;

public class Appointment implements Schedulable, Notifiable, Comparable<Appointment> {

    private Date date;
    private String status;
    private Doctor doctor;
    private Patient patient;
    private Payment payment;

    public Appointment(Date date, Doctor doctor, Patient patient) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.status = "Scheduled";
    }

    public Patient getPatient(){
        return this.patient;
    }

    public void setPayment(Payment p) {
        this.payment = p;
    }

    public Payment getPayment() {
        return this.payment;
    }

    // schedulable
    @Override
    public Date getDateTime() {
        return this.date;
    }

    @Override
    public void reschedule(Date newDate) {
        try {
            // Validate that the new date is not null
            if (newDate == null) {
                throw new NullPointerException("The new date cannot be null.");
            }
            // Validate that the new date is not in the past
            Date today = new Date();
            if (newDate.before(today)) {
                throw new IllegalArgumentException("Cannot reschedule to a date in the past.");
            }

            // Original Logic
            this.date = newDate;
            this.status = "Rescheduled";
            System.out.println("Appointment rescheduled successfully");

        } catch (NullPointerException | IllegalArgumentException e) {
            // Handle the exception gracefully
            System.err.println("Reschedule Failed: " + e.getMessage());
        }
    }

    @Override
    public boolean isUpcoming() {
        Date now = new Date();
        return this.date.after(now);
    }

    // notifiable
    @Override
    public void sendNotification() {
        System.out.println("Notification to " + patient.getName() + " : Upcoming appointment with Dr. " + doctor.getName());
        System.out.println("Notification to Dr. " + doctor.getName() + " : You have an appointment with Mr./Ms. " + patient.getName());
    }

    @Override
    public String getNotificationMessage() {
        return "Appointment scheduled on " + this.date.toString();
    }

    @Override
    public int compareTo(Appointment other) {
        try {
            if (other == null) {
                throw new NullPointerException("Cannot compare with a null appointment.");
            }
            if (this.date == null || other.getDateTime() == null) {
                throw new NullPointerException("Cannot compare appointments with missing dates.");
            }
            return this.date.compareTo(other.getDateTime());
            
        } catch (NullPointerException e) {
            System.err.println("Comparison Error: " + e.getMessage());
            return 0; // Treat as equal to prevent crash
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}