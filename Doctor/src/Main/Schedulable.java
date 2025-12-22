package com;




import java.util.Date;

public interface Schedulable {
    Date getDateTime();
    void reschedule(Date newDate);
    boolean isUpcoming();
}
