package gym;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 * A class represent a TimeLots 
 * @author Eva Liskova
 */
@SuppressWarnings("serial")
public class TimeSlot implements Comparable<TimeSlot>, Serializable
{
   private LocalDate date;
   private LocalTime startTime, endTime;
   private int numberOfMembers;
   private ArrayList<Member> members;
   private Instructor instructor;
   private Class clas;
   /**
    * Five-argument constructor.
    * @param date the timeSlot´s  date
    * @param startTime the timeSlot's startTime
    * @param endTime the timeSlot's endTime
    * @param instructor the timeSlot's instructor
    * @param class the timeSlot's class
    */
   public TimeSlot(LocalDate date, LocalTime startTime, LocalTime endTime, Instructor instructor, Class clas)
   {
      this.date = date;
      this.startTime = startTime;
      this.endTime = endTime;
      this.numberOfMembers = 0;
      this.instructor = instructor;
      this.clas = clas;
      members = new ArrayList<Member>();
   }
   /**
    * Gets the timeSlot's date.
    * @return the timeSlot's date
    */
   public LocalDate getDate()
   {
      return date;
   }
   /**
    * Gets the timeSlot's gymClass.
    * @return the timeSlot's gymClass
    */
   public Class getGymClass()
   {
      return clas;
   }
   /**
    * Gets the timeSlot's startTime.
    * @return the timeSlot's startTime
    */
   public LocalTime getStartTime()
   {
      return startTime;
   }
   /**
    * Gets the timeSlot's endTime
    * @return the timeSlot's endTime
    */
   public LocalTime getEndTime()
   {
      return endTime;
   }
   /**
    * Gets the timeSlot's numberOfMember.
    * @return the timeSlot's numberOfMembers
    */
   public int getNumberOfMembers()
   {
      return numberOfMembers;
   }
   /**
    * Gets the timeSlot's instructor.
    * @return the timeSlot's instructor
    */

   public Instructor getInstructor()
   {
      return instructor;
   }
   /**
    * Gets the timeSlot's instructor.
    * @return the timeSlot's instructor
    */
   public void assignMemberToTimeSlot(Member member) throws ClassFullException
   {
      if (numberOfMembers < clas.getMaxNumberOfMembers())
      {
         if (member.getMembership().equalsIgnoreCase("Premium"))
         {

            if (!members.contains(member))
            {
               members.add(member);
               numberOfMembers++;
            }
            else
            {
               throw new AlreadyExistExeption();
            }

         }
         else
         {
            throw new MemberIsNotPremiumExeption();
         }
      }
      else
      {
         throw new ClassFullException();
      }
   }
   /**
    * Gets the  members.
    * @return the timeSlot's members
    */
   public ArrayList<Member> getMembers()
   {
      return members;
   }

   @Override
   public int compareTo(TimeSlot s)
   {
      if (date.compareTo(s.date) > 0)
      {
         return 1;
      }
      else if (date.compareTo(s.date) == 0)
      {
         if (startTime.compareTo(s.startTime) > 0)
         {
            return 1;
         }
         else if (startTime.compareTo(s.startTime) == 0)
         {
            return 0;
         }
      }
      return -1;

   }
   /**
    * Returns a string representation of the timeSlot.
    * @return a string representation of the timeSlot in the format: "date startTime endTime class instructor and members "
    */
   public String toString()
   {
      return date.toString() + ", " + startTime.toString() + ", "
            + endTime.toString() + ", " + numberOfMembers + ", "
            + clas.toString() + ", " + instructor.toString() + ", "
            + members.toString() + ", \n";
   }

}
