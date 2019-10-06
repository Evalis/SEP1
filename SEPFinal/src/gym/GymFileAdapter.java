package gym;

import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * A user interface that allows for displaying and modifying information about gym.
 * @author Eva Liskova
 * @version 5.0
 */
public class GymFileAdapter
{
   private FileIO fio;
   private String fileName;
   /**
    * 1-argument constructor setting the file name.
    * @param fileName the name and path of the file where students will be saved and retrieved
    */
   public GymFileAdapter(String fileName)
   {
      fio = new FileIO();
      this.fileName = fileName;
   }
   /**
    * Uses the FileIO class to retrieve a Gym object with all gym.
    * @return a Gym object with all stored classes
    */
   public Gym getGym()
   {
      Gym gym = null;

      try
      {
         gym = (Gym) fio.readObjectFromFile(fileName);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found for Gym");
      }
      catch (IOException e)
      {
         System.out.println("IO Error reading file for Gym");
      }
      catch (ClassNotFoundException e)
      {
         System.out.println("Class Not Found in Gym Adapter");

      }
      return gym;
   }

   /**
    * Use the FileIO class to save gym.
    * @param gym the   gym that will be saved
    */
   public void saveGym(Gym gym)
   {
      try
      {
         fio.writeToFile(fileName, gym);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Gym File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error writing to Gym file");
      }
   }
}
