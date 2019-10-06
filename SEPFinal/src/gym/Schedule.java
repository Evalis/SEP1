package gym;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
/**
 * A class containing a list of TimeLots objects.
 * @author Eva Liskova
 */
@SuppressWarnings("serial")
public class Schedule implements Serializable
{
   private ArrayList<TimeSlot> timeSlots;

   public Schedule()
   {
      timeSlots = new ArrayList<TimeSlot>();
   }
   
   /**
    * The boolean method check if instructor is busy at the given time.
    * return true when instructor is busy
    */ 
   private boolean isInstuctorBusy(TimeSlot ts) 
   {
      for (int i = 0; i < timeSlots.size(); i++)
      {
         if (timeSlots.get(i).getDate().equals(ts.getDate())
               && timeSlots.get(i).getInstructor().equals(ts.getInstructor())
               && ((ts.getStartTime().isBefore(timeSlots.get(i).getStartTime())||
                     ts.getStartTime().equals(timeSlots.get(i).getStartTime()))
                     && timeSlots.get(i).getStartTime()
                           .isBefore(ts.getEndTime()))

                     || (ts.getStartTime().isBefore(timeSlots.get(i).getEndTime())) 
                                 
                           && timeSlots.get(i).getEndTime()
                                 .isBefore(ts.getEndTime()) || timeSlots.get(i).getEndTime().equals(ts.getEndTime())

                     || (timeSlots.get(i).getStartTime()
                           .isBefore(ts.getStartTime())) 
                           && ts.getEndTime().isBefore(timeSlots.get(i).getEndTime()))
                                     
                           return true;
         }
      
      return false;
   }
   /**
    * Adds a timeSlot to the list.
    * @param the timeSlot to add to the list
    */ 
   
   public void addTimeSlot(TimeSlot timeSlot)
   {
     if(isInstuctorBusy(timeSlot))
     {
        throw new InstructorIsBusyException();
     }
     else
     {
        timeSlots.add(timeSlot);        
     }
   }
   /**
    * return a list of today´s schedule.
    */ 
   public ArrayList<TimeSlot> getTodaysSchedule()
   {
      LocalDate today = LocalDate.now();
      ArrayList<TimeSlot> todaySchedule = new ArrayList<TimeSlot>();

      for (int i = 0; i < timeSlots.size(); i++)
      {
         if (timeSlots.get(i).getDate().equals(today))
         {
            todaySchedule.add(timeSlots.get(i));
         }
      }
      Collections.sort(todaySchedule);
      return todaySchedule;
   }
   /**
    * return a list of month´s schedule .
    */
   public ArrayList<TimeSlot> getMonthSchedule(LocalDate date)
   {
      LocalDate today = date;
      LocalDate lastMonth = today.minusMonths(1);
      LocalDate endOfLastMonth = LocalDate.of(lastMonth.getYear(),
            lastMonth.getMonth(), lastMonth.lengthOfMonth());

      today = date;
      LocalDate nextMonth = today.plusMonths(1);
      LocalDate startOfnextMonth = LocalDate.of(nextMonth.getYear(),
            nextMonth.getMonth(), 1);

      ArrayList<TimeSlot> monthSchedules = new ArrayList<TimeSlot>();
      LocalDate scheduleDate = null;

      for (int i = 0; i < timeSlots.size(); i++)
      {
         scheduleDate = timeSlots.get(i).getDate();

         if (scheduleDate.isAfter(endOfLastMonth)
               && scheduleDate.isBefore(startOfnextMonth))
         {
            monthSchedules.add(timeSlots.get(i));
         }
      }

      Collections.sort(monthSchedules);
      return monthSchedules;
   }

   public String toString()
   {
      return timeSlots.toString();
   }

}
