import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Doctor extends Staff {
    private String specialization;
    private List<Appointment> schedule;

    public Doctor(int id, String name, String phone, Date dob,
                  int staffId, double salary, Date hireDate,
                  String specialization) {
        super(id, name,  phone,  dob,  staffId,  salary,  hireDate);
        this.specialization = specialization;
        this.schedule = new ArrayList<>();
    }
    // Override Calculate Bonus
    public double calculateBonus(){return 0;}

    @Override
    public void displayInfo() {
        System.out.println("Doctor Info:");
        System.out.println("Specialization: " + specialization);
        System.out.println("Appointments:");
        for (Appointment a : schedule) {
            System.out.println(a);
        }
    }

    public boolean isAvailable(Date date) {
        for (Appointment a : schedule) {
            if (a.getDateTime().equals(date)) {
                return false;
            }
        }
        return true;
    }

    public void addAppointment(Appointment appointment) {
        if (isAvailable(appointment.getDateTime())) {
            schedule.add(appointment);
        } else {
            System.out.println("Doctor not available on " + appointment.getDateTime());
        }
    }
}
