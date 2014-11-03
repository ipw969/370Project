package businessobjects;


import java.util.HashSet;
import java.util.Iterator;

  /*
 * This class will hold all information  based on a scene.//
TODO: Add a way to find a common time availability among all volunteers and equipment.
TODO: Add a parsert to parse the description to ensure it is tidy - low priority. 
TODO: I may make this an interior class of the script class. This is because there should only be one source of a scene. 
 */


/**
 *
 * @author ryan
 */
public class Scene extends BaseBusinessObject{
    private String name;
    private String description;
    private final HashSet<Volunteer> necessaryVolunteers;
    private final HashSet<Equipment> necessaryEquipment;
    private boolean scheduled;
    private boolean complete;
    
    private final String nameNullError = "The name of the scene was not entered.";
    private final String noVolunteersError = "There are no volunteers associated with this scene";
    private final String duplicatevolunteerError = "You cannot insert a volunteer into the volunteer list with the same name as another volunteer";
    private final String volunteerAlreadyExistsError = "That volunteer already exists in the list of volunteers for this scene. Unable to add Volunteer. Note: All volunteers are expected to have a unique email.";
    private final String equipmentAlreadyExistsError = "That equipment already exists in the list of equipment";
    private final String volunteerDoesNotExistError = "The given volunteer does not exist.";
    private final String equipmentDoesNotExistError = "The given equipment does not exist";
    
    
    
    
    public Scene(String name, String description)
    {
        super();
       
       
        this.name = name;
        this.description = description;
        
        necessaryVolunteers = new HashSet<>();
        necessaryEquipment = new HashSet<>();
        
       this.updateError(nameNullError, !name.isEmpty());
        
    }

    /**set the name to the given valu
     * @return the name of the scene*/
    public String name()
    {
       
        return this.name;
        
    }
    
    /**@param newName- the new name of the scene*/

    public void setName(String newName)
    {
        name = newName;
        this.setHasChanged(true);
    }
    
    /**
     * 
     * @param newDescription the new description of the scene.
     */
    public void setDescription(String newDescription)
    {
        description = newDescription;
        this.setHasChanged(true);
    }
    /**@return the description of the scene**/
    public String description()
    {
        return this.description;
    }
    
    /**@return true if the scene is scheduled, false if not**/
    public boolean scheduled()
    {
        return scheduled;
    }
    
    /**@return true if the scene has been filmed, false otherwise**/
    public boolean complete()
    {
        return complete;
    }
    
    /**Sets whether the scene is scheduled or not
     * @param isScheduled whether or not the scene is scheduled
     */
    public void setScheduled(boolean isScheduled)
    {
        scheduled = isScheduled;
          this.setHasChanged(true);
    }
    
    /**Sets whether the scene has been filmed or not
     * (The producer may want to change the status if a mistake was found)
     * @param isComplete whether or no the scene was filmed. 
     */
    public void setComplete(boolean isComplete)
    {
        complete = isComplete;
          this.setHasChanged(true);
    }
    
    /**@return true if the scene has volunteers associated with it, false if not**/
    public boolean hasVolunteers()
    {
        return !necessaryVolunteers.isEmpty();
    }
    /**@return true if the scene has volunteers associated with it ,false if not**/
    /**@precon the list of volunteers must not be empty
     * @return an iterator for the necessary volunteers of the scene
     * @throws RuntimeException 
     */
    public Iterator<Volunteer> volunteerIterator() throws RuntimeException
    {
        if (necessaryVolunteers.isEmpty())
        {
            throw new RuntimeException("Cannot iterate over an empty volunteerList-Scene volunteerIterator");
        }
        return necessaryVolunteers.iterator();
    }

    /**@return true if the scene has equipment associated with it, false if not
     * 
     * 
     */
    public boolean hasEquipment()
    {
        return !necessaryEquipment.isEmpty();
    }
    
    /**@precon the scene must have equipment associated with it.
     * 
     * @return an iterator for the list of equipment. 
     * @throws RuntimeException 
     */
    public Iterator<Equipment> equipmentIterator() throws RuntimeException
    {
        if (necessaryEquipment.isEmpty())
        {
            throw new RuntimeException("Cannot iterate over an empty equipment list-Scene equipmentIterator");
        }
        return necessaryEquipment.iterator();
    }

    /**
     * @param e The equipment to check for
     * @return true if this scene contains the given equipment, false if not.**/
    public boolean containsEquipment(Equipment e)
    {
        return necessaryEquipment.contains(e);
    }
    
    /**
     * @param v  The volunteer to check for
     * @return true if this scene contains the given volunteer, false if not**/
    
    public boolean containsVolunteer(Volunteer v)
    {
        if (this.hasVolunteers() && necessaryVolunteers.contains(v))
        {
            return true;
        }
        else if (!this.hasVolunteers())
        {
            return false;
        }
        else
        {
            //compare the volunteer by name and email to see if they are the same person. Everyone logs in via their email, so it must be unique. 
            Iterator<Volunteer> iter = this.volunteerIterator();
            while (iter.hasNext())
            {
                  Volunteer examinedVolunteer = iter.next();      
                  if (examinedVolunteer.getEmail().equals(v.getEmail()))     
                  {
                      return true;
                  }
                 
                
            }
          }
         return false;
       }
        
    
    /** Adds the given volunteer to the list of volunteers
     * @precon the volunteer must be in the scene's list of volunteers
     * @param newVolunteer the new volunteer to add to the list of volunteers.
     */
    public void addVolunteer(Volunteer newVolunteer) 
    {
        if (this.containsVolunteer(newVolunteer))
        {
            this.updateError(volunteerAlreadyExistsError, true);
        }
        else
        { 
            necessaryVolunteers.add(newVolunteer);
              this.setHasChanged(true);
        }
    }
    
    /**Adds the given equipment to the list of equipment
     * @precon the equipment must not already be in the list of equipment.
     * @param newEquipment thew new equipment to list of equipment. 
     */
     public void addEquipment(Equipment newEquipment)
    {
        if (this.containsEquipment(newEquipment))
        {
            this.updateError(equipmentAlreadyExistsError, true);
        }
        else
        {
            necessaryEquipment.add(newEquipment);
              this.setHasChanged(true);
        }
        
    }
    
  
     /**Removes the given volunteer from the list of volunteers for this scene
      * @precon The volunteer must be in the list of volunteers associated with this scene.
      * @param oldVolunteer the volunteer to remove from the list of volunteers for this scene
      */
    public void removeVolunteer(Volunteer oldVolunteer)
    {
        if (!this.containsVolunteer(oldVolunteer))
        {
            updateError(volunteerDoesNotExistError, true);
        }
        else
        {
            necessaryVolunteers.remove(oldVolunteer);
              this.setHasChanged(true);
        }
    }
    
    /**Removes the given equipment from the list of equipment for this scene
     * @precon The equipment must be in the list of equipment for this scene.
     * @param oldEquipment the equipment to remove from the list of equipment for this scene
     * @throws Exception 
     */
    public void removeEquipment(Equipment oldEquipment) throws Exception
    {
        if (!this.containsEquipment(oldEquipment))
        {
            this.updateError(equipmentDoesNotExistError, true);
        }
        else
        {
            necessaryEquipment.remove(oldEquipment);
              this.setHasChanged(true);
        }
    }
    
    public String toString()
    {
        return this.name;
    }
    
    public static void main (int args[])
    {
        //test the 
    }
}
