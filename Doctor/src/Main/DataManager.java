package com;




import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Date;

public class DataManager {
    // ObservableLists automatically update the GUI Tables when data changes
    public static ObservableList<Patient> patients = FXCollections.observableArrayList();
    public static ObservableList<Doctor> doctors = FXCollections.observableArrayList();
    public static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    
    // A default receptionist to handle operations
    public static Receptionist receptionist = new Receptionist(
        "Emma Frontdesk", "Female", new Date(), 101, 3000.0, new Date(), "555-0000", "Morning"
    );

    static {
        // --- Add Dummy Data ---
        
        // Doctors
        Doctor d1 = new Doctor("Dr. Smith", "Male", new Date(), 0, 5000, new Date(), "555-1234", "Orthodontist");
        Doctor d2 = new Doctor("Dr. Sarah", "Female", new Date(), 0, 5500, new Date(), "555-5678", "Surgeon");
        doctors.addAll(d1, d2);

        // Patients
        Patient p1 = new Patient("John Doe", new Date(), "Male", "555-9999", null, true);
        Patient p2 = new Patient("Jane Roe", new Date(), "Female", "555-8888", null, false);
        patients.addAll(p1, p2);
    }
}