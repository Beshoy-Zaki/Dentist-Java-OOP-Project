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
        this.date = newDate;
        this.status = "Rescheduled";
        System.out.println("Appointment rescheduled successfully");
    }
/* 
    @Override
    public boolean isUpcoming() {
        Date now = new Date();
        return this.date.after(now);
    }
*/


    // notifiable
    @Override
    public void sendNotification() {

        System.out.println("Notification to " + patient.getName() + " : Upcoming appointment with Dr. " + doctor.getName());
        
        System.out.println("Notification to Dr. " + doctor.getName() + " : You have an appointment with Mr./Ms. " + patient.getName());
    }

    // For notifiable
    @Override
    public String getNotificationMessage() {
        return "Appointment scheduled on " + this.date.toString();
    }
    @Override
    public int compareTo(Appointment other) {
        return this.date.compareTo(other.getDateTime());
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}