/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClinicData {

    // 1. List for Patients (Used in Patient Tab & Booking)
    public static ObservableList<Patient> patients = FXCollections.observableArrayList();
    
    // 2. List for Doctors (Used in Booking Dropdown)
    public static ObservableList<Doctor> doctors = FXCollections.observableArrayList();
    
    // 3. NEW: List for All Staff (Used in Staff Tab for Polymorphism/Bonus)
    // This list can hold both Doctor and Receptionist objects because they extend Staff.
    public static ObservableList<Staff> allStaff = FXCollections.observableArrayList();
    
    // 4. List for Appointments (Used in Appointment Tab)
    public static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    // 5. A default Receptionist to handle administrative actions (like booking method calls)
    public static Receptionist mainReceptionist = new Receptionist(
        "Admin", "F", new java.util.Date(), 0, 3000, new java.util.Date(), "555-0100", "Day"
    );
}