package gym;

import java.io.Serializable;
/**
 * A class representing a class with a max number of members and class name.
 * @author Eva Liskova
 */
@SuppressWarnings("serial")
public class Class implements Serializable
{
   private int maxNumberOfMembers;
   private String className;
   /**
    * Two-argument constructor.
    * @param max number of members the class's max number of members
    * @param className of the class´s class name
    */
   public Class(int maxNumberOfMembers, String className)
   {
      this.maxNumberOfMembers = maxNumberOfMembers;
      this.className = className;
   }
   /**
    * Gets the class's max number of members.
    * @return the class´s max number of members
    */
   public int getMaxNumberOfMembers()
   {
      return maxNumberOfMembers;
   }
   /**
    * Gets the class's class name.
    * @return the class's class name
    */
   public String getClassName()
   {
      return className;
   }

   /**
    * Compares max number of members, class name of two classes.
    * @param obj the object to compare with
    * @return true if the given object is equal to this class
    */
   public boolean equals(Object obj)
   {
      if (!(obj instanceof Class))
      {
         return false;
      }

      Class other = (Class) obj;
      return maxNumberOfMembers == other.maxNumberOfMembers
            && className.equals(other.className);
   }
   /**
    * Returns a string representation of the class.
    * @return a string representation of the class in the format: "max number of members and class name"
    */
   public String toString()
   {
      return "Class name: " + className + ", Max number of members: "
            + maxNumberOfMembers;
   }
}
