package com;



// 9:24 PM Saturday, December 13, 2025
// at the request of Mark Sameh, I revised the code and wrote this.
// Questions to ask Bo4:
// Q1: The +sendNotification() void method exists in Mermaid but not in Java. What shall I do? Should we remove it from the mermaid text or should I make the payment class implement the notifiable interface?
// Q2: The getStatus() method says that it returns a boolean in the mermaid 🥴 p.s. it shouldn't.
// and other minimal things that shouldn't bother you.
import java.util.Date;

public class Payment implements Comparable<Payment>, Printable {

    private double amount;
    private Date dueDate; 
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
        try {
            if (this.amount < 0) {
                throw new IllegalArgumentException("Payment amount cannot be negative.");
            }

            // Original Logic
            if(this.patient.getInsuranceStatus()){
                return this.amount * 0.9;
            }
            else return this.amount;

        } catch (IllegalArgumentException e) {
            System.err.println("Calculation Error: " + e.getMessage());
            return 0.0;
        }
    }

    // -------- Business Methods --------
    public void processPayment() {
        try {
            if (isPaid) {
                throw new IllegalStateException("Payment has already been processed.");
            }
            
            // Original Logic
            isPaid = true;
            System.out.println("Payment processed successfully.");
            
        } catch (IllegalStateException e) {
            System.out.println("Payment Alert: " + e.getMessage());
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
        double a1 = this.amount;
        double a2 = other.amount;
        return Double.compare(a1, a2);
    }
}
