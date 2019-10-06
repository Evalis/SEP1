package gym;

import java.io.Serializable;
/**
 * A class representing a subclass Instructor  with a gymClass.
 * @author Eva Liskova
  */
@SuppressWarnings("serial")
public class Instructor extends Person implements Serializable
{
   private String gymClass;
   /**
    * Six-argument constructor.
    * @param firstName the person's first name
    * @param lastName the person's last name
    * @param address the person`s address
    * @param phone the person`s phone
    * @param email the person`s email
    *  @param gymClass the instructor`s gymClass
    */
   public Instructor(String firstName, String lastName, String address,
         String phone, String email, String gymClass)
   {
      super(firstName, lastName, address, phone, email);
      this.gymClass = gymClass;
   }
   /**
    * Gets the instructor's gymClass.
    * @return the instructor's gymClass
    */
   public String getGymClass()
   {
      return gymClass;
   }
   /**
    * Compares first name, last name, address, phone, email and gymClass of two instructors.
    * @param obj the object to compare with
    * @return true if the given object is equal to this instructor
    */

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Instructor))
      {
         return false;
      }

      Instructor other = (Instructor) obj;
      return super.equals(other) && gymClass.equals(other.gymClass);
   }
   /**
    * Returns a string representation of the instructor.
    * @return a string representation of the instructor in the format: "firstName lastName address phone email gymClass"
    */
   public String toString()
   {
      return super.toString() + ", Gym class: " + gymClass;
   }
}
