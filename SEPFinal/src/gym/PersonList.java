package gym;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class containing a list of Person objects.
 * @author Eva Liskova
 */
@SuppressWarnings("serial")
public class PersonList implements Serializable
{
   private ArrayList<Person> persons;
   /**
    * No-argument constructor initialising the StudentList.
    */
   public PersonList()
   {
      persons = new ArrayList<Person>();
   }
   /**
    * Adds a member to the list.
    * @param the member to add to the list
    */ 
   public void addNewMember(Member member)
   {

      for (int i = 0; i < persons.size(); i++)
      {
         if (persons.get(i).equals(member))
         {
            throw new AlreadyExistExeption();

         }
      }
      persons.add(member);
   }
   /**
    * Adds a instructor to the list.
    * @param the instructor to add to the list
    */ 
   public void addNewInstructor(Instructor instructor)
   {

      for (int i = 0; i < persons.size(); i++)
      {
         if (persons.get(i).equals(instructor))
         {
            throw new AlreadyExistExeption();

         }
      }
      persons.add(instructor);
   }
   /**
    * return a list of all members.
    */ 
   public ArrayList<Member> getAllMember()
   {
      ArrayList<Member> temp = new ArrayList<Member>();

      for (int i = 0; i < persons.size(); i++)
      {
         if (persons.get(i) instanceof Member)
         {
            temp.add((Member) persons.get(i));
         }
      }
      return temp;
   }
   /**
    * return a list of all instructors.
    */ 
   public ArrayList<Instructor> getAllInstructor()
   {
      ArrayList<Instructor> temp = new ArrayList<Instructor>();

      for (int i = 0; i < persons.size(); i++)
      {
         if (persons.get(i) instanceof Instructor)
         {
            temp.add((Instructor) persons.get(i));
         }
      }
      return temp;
   }
   /**
    * Returns a string representation of the Person.
    * @return a string representation of the person class 
    */
   public String toString()
   {
      return persons.toString();
   }
}
