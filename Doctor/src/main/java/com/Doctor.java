package com;

import java.util.ArrayList;
import java.util.Date;

public class Doctor extends Staff {
    private String specialization;
    private ArrayList<Appointment> schedule; 

    public Doctor(String name, String gender, Date dob, int staffId, double salary, Date hireDate, String contact, String specialization) {
        super(name, gender, dob, staffId, salary, hireDate, contact);
        this.specialization = specialization;
        this.schedule = new ArrayList<>(); 
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public double calculateBonus() {
        return this.salary * 0.25;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        
        System.out.println("Specialization: " + specialization);
        System.out.println("Scheduled Appointments: " + schedule.size());
        
        for (Appointment a : schedule) {
            System.out.println(" - " + a.toString());
        }
    }

    public boolean isAvailable(Date date) {
        try {
            if (date == null) {
                throw new IllegalArgumentException("Date cannot be null to check availability.");
            }

            // Original Logic
            for (Appointment a : schedule) {
                if (a.getDateTime().equals(date)) {
                    return false;
                }
            }
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Availability Check Skipped: " + e.getMessage());
            return false;
        }
    }

    public void addAppointment(Appointment appointment) {
        try {
            if (appointment == null) {
                throw new NullPointerException("Cannot add a null appointment to schedule.");
            }

            // Original Logic
            if (isAvailable(appointment.getDateTime())) {
                schedule.add(appointment);
                System.out.println("Appointment added to Doctor's schedule.");
            } else {
                System.out.println("Doctor not available on " + appointment.getDateTime());
            }

        } catch (NullPointerException e) {
            System.err.println("Error adding appointment: " + e.getMessage());
        }
    }
}