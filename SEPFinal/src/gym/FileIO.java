package gym;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * A class responsible for reading and writing binary files.
 * @author Eva Liskova
 */
public class FileIO
{
   /**
    * Reads the first object from the file with the given file name and returns it.
    * Whoever calls the method will need to cast it from type Object to its real type
    * @param fileName the name and path of the file that is read
    * @return the Object read from the file
    * @throws FileNotFoundException if the file with fileName is not found
    * @throws IOException if there is an error reading the file
    * @throws ClassNotFoundException if the class of the serialized object cannot be found
    */
   public Object readObjectFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      FileInputStream fileIn = new FileInputStream(fileName);
      ObjectInputStream read = new ObjectInputStream(fileIn);

      Object temp = read.readObject();
      read.close();
      return temp;
   }
   /**
    * Writes the given object to a file with the given file name.
    * @param fileName the name and path of the file to write to
    * @param obj the object to write to the file
    * @throws FileNotFoundException if the file with fileName is not found
    * @throws IOException if there is an error writing to the file
    */
   
   public void writeToFile(String fileName, Object obj) throws FileNotFoundException, IOException
   {
      FileOutputStream fileOut = new FileOutputStream(fileName);
      ObjectOutputStream write = new ObjectOutputStream(fileOut);
      write.writeObject(obj);
      write.close();
   }
}
