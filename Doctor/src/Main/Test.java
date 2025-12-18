import java.util.*;

public class Test {
    public static void main(String[] args) {
        // POLYMORPHISM: Create different Person subclasses
        Person doctor = new Doctor("Dr Ayman", "Male", new GregorianCalendar(1980, Calendar.JANUARY, 15).getTime(), 101, 10000.0,new GregorianCalendar(2010, Calendar.JANUARY, 10).getTime(), "+20-1012345678", "Orthodontics");
        Person receptionist = new Receptionist("Sara", "Female", new GregorianCalendar(1990, Calendar.SEPTEMBER, 27).getTime(), 102, 4000.0, new GregorianCalendar(2016, Calendar.JUNE, 1).getTime(), "+20-1578990922", "Morning");

        Prescription[] patientPrescriptions = new Prescription[2];
        patientPrescriptions[0] = new Prescription("Paracetamol", "500mg", "3 times a day");
        patientPrescriptions[1] = new Prescription("profin", "200mg", "2 times a day");
        Person patient1 = new Patient("Ali", new GregorianCalendar(1988, Calendar.NOVEMBER, 22).getTime(), "Male", "+20-1055667788", patientPrescriptions, true);
        Person patient2 = new Patient("Ahmed",  new GregorianCalendar(1998, Calendar.APRIL, 15).getTime(), "Male", "+20-1089907788", patientPrescriptions, false);
        
        // Polymorphism
        List<Person> people = new ArrayList<>();
        people.add(doctor);
        people.add(receptionist);
        people.add(patient1);
        people.add(patient2);

        for (Person person : people) {
            person.displayInfo();
        }

        // encapsulation
        System.out.println("Dr. " + doctor.getName() + " age: " + doctor.calculateAge());
        System.out.println("Patient " + patient1.getName() + " age: " + patient1.calculateAge());

        // INTERFACES: Schedulable and Notifiable and printable
        Date appointmentDate1 = new GregorianCalendar(2025, Calendar.DECEMBER, 27, 10, 30).getTime();
        Appointment a1 = new Appointment(appointmentDate1, (Doctor) doctor, (Patient) patient1);

        ((Doctor) doctor).addAppointment(a1);
        ((Patient) patient1).addAppointment(a1);
          
        System.out.println("Notification: " + a1.getNotificationMessage());// notifiable interface
        a1.sendNotification();// notifiable interface

        System.out.println("Appointment created: " + a1.getDateTime());// schedulable interface
        System.out.println("Is upcoming: " + a1.isUpcoming());    // schedulable interface
        Schedulable sched = a1;
        Date newDate = new GregorianCalendar(2025, Calendar.DECEMBER, 29, 11, 0).getTime();// schedulable interface
        sched.reschedule(newDate);
        System.out.println("New date: " + sched.getDateTime());

        Payment payment1 = new Payment(500.0, new GregorianCalendar(2025, Calendar.DECEMBER, 30).getTime(), false, (Patient) patient1, a1);
        payment1.printDetails();// printable interface

        // generic sort
        System.out.println("\n=== GENERIC SORTING: Appointments by DateTime ===");
        List<Appointment> appointments = new ArrayList<>();
        Appointment a2 = new Appointment(new GregorianCalendar(2025, Calendar.DECEMBER, 25, 9, 0).getTime(), (Doctor) doctor, (Patient) patient1);
        
        appointments.add(a1);
        appointments.add(a2);

        Collections.sort(appointments);  // Uses Comparable<Appointment>
        for (Appointment a : appointments) {
            System.out.println(a.getDateTime() + " - Patient: " + a.getPatient().getName());
        }

        // Insurance function 
    
        Payment paymentInsured = new Payment(1000.0, new GregorianCalendar(2025, Calendar.DECEMBER, 30).getTime(), false, (Patient) patient1, a1);
        Payment paymentUninsured = new Payment(1000.0, new GregorianCalendar(2025, Calendar.DECEMBER, 30).getTime(), false, (Patient) patient2, a1);
        
        System.out.println("INSURED Patient (" + patient1.getName() + "):");
        System.out.println(  paymentInsured.calculateTotal());
        
        System.out.println("UNINSURED Patient (" + patient2.getName() + "):");
        System.out.println( paymentUninsured.calculateTotal());

        // ABSTRACT CLASSES: calculateBonus() 
        System.out.println("Doctor bonus: " + ((Staff) doctor).calculateBonus() );
        System.out.println("Receptionist bonus: " + ((Staff) receptionist).calculateBonus() );
    }
}

