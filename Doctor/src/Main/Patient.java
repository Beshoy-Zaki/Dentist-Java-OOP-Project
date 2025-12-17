import java.util.ArrayList;
import java.util.Date;

public class Patient extends Person {
    private Prescription[] prescriptions; 
    private ArrayList<Appointment> appointments;
    
    private int patientId;
    static int patientCount = 0;
    private boolean isInsured;

    // Constructor
    public Patient(String name, Date birthDate, String gend, String contact, Prescription[] medical, boolean isInsured) {
        super(name, birthDate, gend, contact);
        
        this.prescriptions = medical;
        this.appointments = new ArrayList<Appointment>(); 
        
        this.isInsured = isInsured;
        patientCount++;
        this.patientId = patientCount;
    }
    public void addAppointment(Appointment a) {
        // No need for loops or resizing! ArrayList handles it.
        this.appointments.add(a);
        System.out.println("Appointment added successfully.");
    }
    public int getPatientId() {
        return patientId;
    }

    public boolean getInsuranceStatus() {
        return isInsured;
    }

    public Prescription[] getPrescriptions() {
        return prescriptions;
    }

    // Updated Getter to return ArrayList
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    @Override
    public void displayInfo() {
        System.out.println("Patient name: " + getName());
        System.out.println("Date of Birth: " + (getDateOfBirth() != null ? getDateOfBirth() : "N/A"));
        System.out.println("Gender:        " + getGender());
        System.out.println("Contact No:    " + getNumber());
        System.out.println("Patient ID:    " + this.patientId);
        System.out.println("Total Appointments: " + appointments.size());
    }
}