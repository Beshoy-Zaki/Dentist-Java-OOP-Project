import java.util.Date;
public  class Patient extends Person {
    private Prescription prescriptions[];
    private Appointment appointments[];
    private int patientId;
    static int patientCount;
    private boolean isInsured;
    
    Patient(String name,Date birthDate, String gend, String contact,Prescription medical[],Appointment appointments[], boolean isInsured){
    super(name,birthDate,gend ,contact);
    this.prescriptions = medical;
    this.appointments = appointments;
    this.isInsured = isInsured;
    patientCount++;
    patientId=patientCount;
    }
   public int getPatientId() {
    return patientId;
}
// Getter
public boolean getinsuranceStatus() {
    return isInsured;
}

public Prescription[] getPrescriptions() {
    return prescriptions;
}
public Appointment[] getAppointments() {
    return appointments;
}
@Override
    void displayInfo(){
    System.out.println("Patient name:"+getName());
    System.out.println("Date of Birth: " + this.dateOfBirth);
    System.out.println("Gender:        " + this.gender);
    System.out.println("Contact No:    " + this.number);
    System.out.println("Patient ID:    " + this.patientId);

}
}
