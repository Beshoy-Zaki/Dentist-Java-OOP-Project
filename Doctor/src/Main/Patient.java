package com;




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
        try {
            if (a == null) {
                throw new NullPointerException("Cannot add a null appointment.");
            }
            // Original logic
            this.appointments.add(a);
            System.out.println("Appointment added successfully.");

        } catch (NullPointerException e) {
            System.err.println("Patient Record Error: " + e.getMessage());
        }
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

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient name: " + getName());
        System.out.println("Date of Birth: " + (getDateOfBirth() != null ? getDateOfBirth() : "N/A"));
        System.out.println("Gender:         " + getGender());
        System.out.println("Contact No:     " + getNumber());
        System.out.println("Patient ID:     " + this.patientId);
        
        try {
            if (this.appointments == null) {
                throw new NullPointerException("Appointment list is not initialized.");
            }
            System.out.println("Total Appointments: " + appointments.size());
        } catch (NullPointerException e) {
            System.out.println("Total Appointments: [Data Error]");
        }
    }
}