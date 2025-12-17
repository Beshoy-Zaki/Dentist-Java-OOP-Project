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
        this.medication = medication;
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
         System.out.println("the medication :  " + medication +"the dosage : " + dosage + "Instructions : "+ instructions);
    }

    public void displayRx() {
        System.out.println("the medication :  " + medication +"the dosage : " + dosage + "Instructions : "+ instructions);
    }

    @Override
    public String toString() {
        return "Prescription{" + "medication=" + medication + ", dosage=" + dosage + ", instructions=" + instructions + '}';
    }
    
}

