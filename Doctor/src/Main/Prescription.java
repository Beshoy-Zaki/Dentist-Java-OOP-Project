package com;



public class Prescription implements Printable {
    private String medication;
    private String dosage;
    private String instructions;

    public Prescription(String medication, String dosage, String instructions) {
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    public void setMedication(String medication) {
        try {
            if (medication == null) {
                throw new IllegalArgumentException("Medication name cannot be empty.");
            }
            // Original Logic
            this.medication = medication;
        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMedication() {
        return medication;
    }

    public String getDosage() {
        return dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void printDetails(){
        try {
            if (medication == null || dosage == null) {
                throw new IllegalStateException("Prescription details are incomplete.");
            }
            // Original Logic
             System.out.println("the medication :  " + medication +"the dosage : " + dosage + "Instructions : "+ instructions);
        } catch (IllegalStateException e) {
            System.err.println("Printing Error: " + e.getMessage());
        }
    }

    public void displayRx() {
        System.out.println("the medication :  " + medication +"the dosage : " + dosage + "Instructions : "+ instructions);
    }

    @Override
    public String toString() {
        return "Prescription{" + "medication=" + medication + ", dosage=" + dosage + ", instructions=" + instructions + '}';
    }
}
