package gym;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * A class representing a superclass Person  with a first name, last name and address, phone and email.
 * @author Eva Liskova
  */
public class Person implements Serializable
{
   private String firstName, lastName, address, phone, email;
   /**
    * Five-argument constructor.
    * @param firstName the person's first name
    * @param lastName the peeson's last name
    * @param address the pesons's address
    * @param phone the pesons's phone
    * @param email the pesons's email
    */
   public Person(String firstName, String lastName, String address,
         String phone, String email)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.phone = phone;
      this.email = email;

   }
   /**
    * Gets the person's first name.
    * @return the person's first name
    */
   public String getFirstName()
   {
      return firstName;
   }

   /**
    * Gets the person's last name.
    * @return the person's last name
    */
   public String getLastName()
   {
      return lastName;
   }

   /**
    * Gets the person's address.
    * @return the person's address
    */
   public String getAddress()
   {
      return address;
   }
   /**
    * Gets the person's phone.
    * @return the person's phone
    */
   public String getPhone()
   {
      return phone;
   }
   /**
    * Gets the person's email.
    * @return the person's email
    */

   public String getEmail()
   {
      return email;
   }

   /**
    * Compares first name, last name, address, phone and email of two persons.
    * @param obj the object to compare with
    * @return true if the given object is equal to this student
    */

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Person))
      {
         return false;
      }

      Person other = (Person) obj;

      return firstName.equals(other.firstName)
            && lastName.equals(other.lastName) && address.equals(other.address)
            && phone.equals(other.phone) && email.equals(other.email);

   }
   /**
    * Returns a string representation of the person.
    * @return a string representation of the person in the format: "firstName" "lastName" "Address" "Phone" "Email"
    */
   public String toString()
   {
      return "First name: "+ firstName+", Last mame: "+lastName+", Address: "+address+ ", Phone: "+ phone+ ", Email: "+ email;
   }
}
