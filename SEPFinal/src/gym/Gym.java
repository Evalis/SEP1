package gym;

import java.io.Serializable;

/**
* A class containing object  of Class List, Schedule and Person list 
* @author Eva Liskova
*/
@SuppressWarnings("serial")
public class Gym implements Serializable
{
private ClassList classList;
private Schedule schedule;
private PersonList personList;

/**
 * No-argument constructor initializing the StudentList.
 */
public Gym()
{
   this.classList = new ClassList();
   this.schedule = new Schedule();
   this.personList = new PersonList();
}

/**
 * Gets a CLassList object.
 * @param classList the  classList of the ClassList object
 */
public ClassList getClassList()
{
   return classList;
}

/**
 * Gets a Schedule object.
 * @param schedule the schedule of the Schedule object
 */
public Schedule getSchedule()
{
   return schedule;
}

/**
 * Gets a PersonList object.
 * @param personList the  personList of the PesonList object
 */
public PersonList getPersonList()
{
   return personList;
}
/**
 * Returns a string representation of the class.
 * @return a string representation of the class in the format: "Person list classList and schedule"
 */
public String toString()
{
   return personList.toString() + "\n" + classList.toString() + "\n" + schedule.toString();
}

}
