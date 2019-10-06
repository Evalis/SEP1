package gym;

import java.io.Serializable;
/**
 * A class representing a subclass Member  with a membership.
 * @author Eva Liskova
  */
@SuppressWarnings("serial")
public class Member extends Person implements Serializable
{
   private String membership;
   /**
    * Six-argument constructor.
    * @param firstName the member's first name
    * @param lastName the member's last name
    * @param address the member's address
    * @param phone the member's phone
    * @param email the member's email
    * @param membership the member's membership
    */
   public Member(String firstName, String lastName, String address,
         String phone, String email, String membership)
   {
      super(firstName, lastName, address, phone, email);
      this.membership = membership;
   }
   /**
    * Gets the member's membership.
    * @return the member's membership 
    */
   public String getMembership()
   {
      return membership;
   }

   /**
    * Compares first name, last name, address, phone, email and membership of two members.
    * @param obj the object to compare with
    * @return true if the given object is equal to this member
    */
   public boolean equals(Object obj)
   {
      if (!(obj instanceof Member))
      {
         return false;
      }
      Member other = (Member) obj;
      return super.equals(other) && membership.equals(other.membership);
   }
   /**
    * Returns a string representation of the member.
    * @return a string representation of the member in the format: "firstName lastName address phone email membership "
    */
   public String toString()
   {
      return super.toString() + ", Membership: " + membership;
   }
}
