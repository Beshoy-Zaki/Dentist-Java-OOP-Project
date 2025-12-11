import java.util.Date;
public abstract class Staff extends Person {
    protected int staffId;
    protected double salary;
    protected Date hireDate;
    private static int staffNumber = 0 ;

    public Staff(int id, String name, String phone, Date dob, int staffId, double salary, Date hireDate) {
        super(id, name, phone, dob);
        this.staffId = staffId;
        this.salary = salary;
        this.hireDate = hireDate;
        staffNumber++;              
        this.staffId = staffNumber; 
    }
    public static int getStaffNumber() {
        return staffNumber;
    }
    // @override Display Info
    public abstract double calculateBonus();
}
