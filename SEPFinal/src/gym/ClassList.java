package gym;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class containing a list of Class objects.
 * @author Eva Liskova
 */
@SuppressWarnings("serial")
public class ClassList implements Serializable
{
   private ArrayList<Class> classes;
   /**
    * No-argument constructor initialising the StudentList.
    */
   public ClassList()
   {
      classes = new ArrayList<Class>();
   }
   /**
    * return a list with all classes.
    */
   public ArrayList<Class> getClassList()
   {
      return classes;
   }
   /**
    * Adds a class to the list.
    * @param the class to add to the list
    */ 
   public void addNewClass(Class clas)
   {
      for (int i = 0; i < classes.size(); i++)
      {
         if (classes.get(i).equals(clas))
         {
            throw new AlreadyExistExeption();

         }
      }
      classes.add(clas);
   }
   /**
    * Returns a string representation of the Class.
    * @return a string representation of the Class class 
    */
   public String toString()
   {
      return classes.toString();
   }
}
