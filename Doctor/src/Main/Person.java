package com;



 
import java.util.Calendar;
import java.util.Date;

public abstract class Person {
    protected String name;
    protected Date dateOfBirth;
    protected String gender;
    protected String contactInfo; 

    public Person(String name, Date dateOfBirth, String gender, String contactInfo) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

    protected abstract void displayInfo();

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() { 
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getNumber() {
        return contactInfo;
    }

    public int calculateAge() {
        try {
            if (dateOfBirth == null) {
                throw new NullPointerException("Date of birth is not set.");
            }

            // Original Logic
            Calendar dob = Calendar.getInstance();
            dob.setTime(dateOfBirth);

            Calendar today = Calendar.getInstance();

            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }
            return age;

        } catch (NullPointerException e) {
            System.err.println("Age Calculation Error: " + e.getMessage());
            return 0;
        }
    }
}