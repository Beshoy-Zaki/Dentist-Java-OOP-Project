import java.util.Date;

public abstract class Person  {
protected String name;
protected Date dateOfBirth;
protected String gender;
protected String number;
abstract void displayInfo();
Person(String name,Date birthDate, String gend, String contact){
this.name=name;
this.dateOfBirth=birthDate;
this.gender=gend;
this.number=contact;
}
String getName(){
return name;
}
Date getDateOfBirth(){
return dateOfBirth;
}
String getGender(){
return gender;
}
String getNumber(){
return number;
}
int calculateAge(){
Date today = new Date();
int age =today.getYear()-this.dateOfBirth.getYear();
return age;

        
}
}
   

