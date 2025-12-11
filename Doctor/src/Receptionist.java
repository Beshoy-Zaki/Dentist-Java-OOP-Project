import java.util.Date;
public class Receptionist extends Staff {
    
    private String shiftTime;
    public Receptionist (int id, String name, String phone, Date dob, int staffId, double salary, Date hireDate,String shiftTime ){
    super(id, name, phone, dob, staffId, salary, hireDate);
    this.shiftTime = shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public int getStaffId() {
        return staffId;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDate() {
        return hireDate;
    }
    @Override
     public double calculateBonus() {
        return salary * 0.2;
    }
    @Override
    public void displayInfo(){
        System.out.println("receptionist name: "+ name + "shift time : " + shiftTime);
    }
    public void receivePayment(Payment p) {
        //exception handling ?
        p.processPayment();
        System.out.println("the payement is received");
    }
        
    public Payment generateInvoice(Appointment a) {//insurance?

        Payment p = new Payment(a.getPayment().calculateTotal(), a.getPatient(), a);
        a.setPayment(p);
        return p;
    }
    
    public void cancelAppointment(Appointment a) {
        a.setStatus("Cancelled");
        System.out.println("the appointement is cancelled");
    }
    public Appointment bookAppointment(Doctor d, Patient p, Date date) {//?
    if(d.isAvailable(date)){
        Appointment a = new Appointment(date, d, p);
        d.addAppointment(a);
        p.addAppointment(a);
        return a;
   }
    }
}
   
    
    

    
