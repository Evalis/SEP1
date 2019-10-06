package gym;
/**
 * A class representing a Already exist exception member, instructor or class already exist in the system 
 * @author Eva Liskova
 */
public class AlreadyExistExeption extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public AlreadyExistExeption()
   {
      super("Already Exist");
   }
}
