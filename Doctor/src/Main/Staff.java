import java.util.Date;

public abstract class Staff extends Person {
    protected int staffId;
    protected double salary;
    protected Date hireDate;
    private static int staffNumber = 0;

    public Staff(String name, String gender, Date dob, int staffId, double salary, Date hireDate, String contact) {
        super(name, dob, gender, contact);
        this.salary = salary;
        this.hireDate = hireDate;
        
        // Auto-increment logic
        staffNumber++;
        this.staffId = staffNumber; 
    }

    public static int getStaffNumber() {
        return staffNumber;
    }

    @Override
    protected void displayInfo() {
        System.out.println("Staff ID: " + this.staffId);
        System.out.println("Name: " + getName());
        System.out.println("Gender: " + getGender());
        System.out.println("Contact: " + getNumber());
        System.out.println("Salary: " + this.salary);
        
        try {
            if (this.hireDate == null) {
                throw new NullPointerException("Hire date is missing from record.");
            }
            System.out.println("Hire Date: " + this.hireDate);
        } catch (NullPointerException e) {
            System.out.println("Hire Date: [Not Available]");
        }
    }

    public abstract double calculateBonus();
}