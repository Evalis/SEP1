package gym;
/**
 * A class representing a Member does not have premium membership exception member. 
 * @author Eva Liskova
 */
public class MemberIsNotPremiumExeption extends RuntimeException

{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   public MemberIsNotPremiumExeption()
   {
      super("Member has not Premium Membership");
   }
}
