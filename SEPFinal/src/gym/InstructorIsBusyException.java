package gym;
/**
 * A class representing a Instructor is busy exception , instructor is already teaching class.
 * @author Eva Liskova
 */
public class InstructorIsBusyException extends RuntimeException
{
   
   private static final long serialVersionUID = 1L;

   public InstructorIsBusyException()
   {
      super("Instructor is busy at that time");
   }
}
