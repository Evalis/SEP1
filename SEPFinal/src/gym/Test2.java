package gym;

public class Test2
{

   public static void main(String[] args)
   {
      Member m1 = new Member("Bob","Boord", "Horsens", "987", "bob@bob.dk", "Regular");
      Member m2 = new Member("Karin","Apple" ,"Horsens", "876", "karin@karin.dk",
            "Premium");
      Member m3 = new Member("Peter", "Bed", "Horsens", "123", "peter@peter.dk",
            "Regular");
      Member m4 = new Member("Kate"," Mad", "Horsens", "345", "kate@kate.dk",
            "Premium");
      Member m5 = new Member("Tim", "Red", "Horsens", "345", "tim@tim.dk","Regular");

      Instructor i1 = new Instructor("Simon", "Sisir","Horsens", "8765",
            "simon@simon.dk", "Java");
      Instructor i2 = new Instructor("Sofia", "Beer","Horsens", "8765",
            "sofia@sofia.dk", "Zumba");
      Instructor i3 = new Instructor("Petra", "Holek","Horsens", "8765",
            "petra@petra.dk", "Spinning");
      Instructor i4 = new Instructor("John", "Dook","Horsens", "8765",
            "john@john.dk", "Karate");
      Instructor i5 = new Instructor("Mark", "Empty","Horsens", "8765",
            "mark@mark.dk", "Yoga");
      Instructor i6 = new Instructor("Tor", "Lake","Horsens", "8765",
            "lake@lake.dk", "Kik box");
      
      Class c1 = new Class(15, "Zumba");
      Class c2 = new Class(20, "Java");
      Class c3 = new Class(10, "Spinning");
      Class c4 = new Class(15, "Karate");
      Class c5 = new Class(20, "Yoga");
      Class c6 = new Class(25, "Kik box");
      
     
      
      GymFileAdapter gfa = new GymFileAdapter("gym.bin");

      
      
      Gym gym = new Gym();
      gym.getPersonList().addNewMember(m1);
      gym.getPersonList().addNewMember(m2);
      gym.getPersonList().addNewMember(m3);
      gym.getPersonList().addNewMember(m4);
      gym.getPersonList().addNewMember(m5);
      gym.getPersonList().addNewInstructor(i1);
      gym.getPersonList().addNewInstructor(i2);
      gym.getPersonList().addNewInstructor(i3);
      gym.getPersonList().addNewInstructor(i4);
      gym.getPersonList().addNewInstructor(i5);
      gym.getPersonList().addNewInstructor(i6);
      gym.getClassList().addNewClass(c1);
      gym.getClassList().addNewClass(c2);
      gym.getClassList().addNewClass(c3);
      gym.getClassList().addNewClass(c4);
      gym.getClassList().addNewClass(c5);
      gym.getClassList().addNewClass(c6);
      
     
      
      
      System.out.println(gym.toString());
      
      gfa.saveGym(gym);
      

   }

}
