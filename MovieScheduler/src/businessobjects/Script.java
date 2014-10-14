/*
 * This class contains all information relevent to the script. 
*This class encapsulates the Scene class to prevent scenes from being created anywhere else. This ensures that
*-The scenes are strongly correlated with the script(there will be no cross-referencing if we decide to support multiple scripts
*-Ensures that every scene is automatically put into the script. 

TODO: add a way to keep the scenes in order?
TODO: Ensure this class complies with the businessObject class definition.
TODO: Add a method of retrieving all of the date from the database when the application starts. 
TODO: Change the general exceptions into proper exceptions. 
TODO: Create a test scaffold for the class
TODO: Add a way for the script to listen to the scenes for a valid state. 
 */
package businessobjects;
import datatypes.Equipment;
import datatypes.Volunteer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author ryan
 * 
 */
public class Script extends BaseBusinessObject {
    //The list of scenes. scenes will be a synchronized ArrayList. 
   protected List<Scene> scenes; 
   protected String name;  
   /**The constructor for the script
     * @param name - the name of the script
    * 
    * */
   public Script(String name)
   {
       super();
       this.name = name;
       scenes = Collections.synchronizedList(new ArrayList<Scene>());

   }

 
 /**Creates a scene with the specified name and description and adds it to the script. 
     * @throws java.lang.Exception
  * @preCon name != null, description != null
  * @param name  the name of the new scene
  * @param description the description of the scene
  * @return the newly created scene 
  * Throws Exception: Throws an exception if the scene is already in the list
  * TODO ensure there is not a scene with the same name in teh script. 
  */
  public Scene createAndAddScene(String name, String description) throws Exception
   {
       if (name == null || description == null)
       {
            throw new Exception("The scene must not have a null name or description\n");  
       }
       Scene newScene = new Scene(name, description);
       scenes.add(newScene);
       return newScene;
   }
   
  /**Adds the specified scene at the current cursor position
   * @PreCon The cursor must be at a valid cursor location.
  
  /**removes the specified scene from the list of scenes
   * 
   * @param oldScene - the scene to be deleted from the list
   * @return true if successful, false if not
   * May edit it to throw an exception if the scene is not completed, not sure yet. 
   */
  public boolean remove(Scene oldScene)
  {
      return scenes.remove(oldScene);
  }
  
  /**Tells you the number of scenes in the script.
   * @return the number of scenes in the script
   * */
  public int numberOfScenes()
  {
      return scenes.size();
  }
  
  
  /**Checks to see if all of the scenes in this script have been successfully scheduled. 
   * @return true if all scenes have been scheduled or if the script is empty, false if not. 
   * 
   **/
  
   public boolean scheduled()
   {
       if (scenes.isEmpty())
       {
           return true;
       }
       for (Scene curScene: scenes)
       {
           if (curScene.scheduled() == false)
           {
               return false;
           }
       }
       return true;
   }
   
   /** Checks to see if the script is complete. This is primarily to check if the script
    * can be removed. 
    * @return true if all of the scenes are complete, false if not.
    */
   public boolean complete()
   {
       for (Scene curScene: scenes)
       {
           if (curScene.complete() == false)
           {
               return false;
           }
       }
       return true;
   }
   /** Checks to see if the script is empty
    * @return true if the script is empty, false if not.
    */
   public boolean isEmpty()
   {
       return scenes.isEmpty();
   }
   
/**Gives you an iterator over the scenes in the script
 * 
 * @return an iterator over the scenes in the script. 
 */
   public Iterator<Scene> getSceneIterator()
   {
       return scenes.iterator();
   }
   
   
   private void loadFromDatabase()
   {
       throw new RuntimeException("Command not operational yet -Script loadFromDatabase");
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   /*
 * This class will hold all information  based on a scene.
TODO: Add a way to find a common time availability among all volunteers and equipment.
TODO: Add a parsert to parse the description to ensure it is tidy - low priority. 
TODO: I may make this an interior class of the script class. This is because there should only be one source of a scene. 
 */


/**
 *
 * @author ryan
 */
public class Scene extends BaseBusinessObject{
    private final String name;
    private final String description;
    private final HashSet<Volunteer> necessaryVolunteers;
    private final HashSet<Equipment> necessaryEquipment;
    private boolean scheduled;
    private boolean complete;
    
    private Scene(String name, String description)
    {
        super();
        this.name = name;
        this.description = description;
        
        necessaryVolunteers = new HashSet<Volunteer>();
        necessaryEquipment = new HashSet<Equipment>();
        
    }

    /**@return the name of the scene**/
    public String name()
    {
        return this.name;
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
    }
    
    /**Sets whether the scene has been filmed or not
     * (The producer may want to change the status if a mistake was found)
     * @param isComplete whether or no the scene was filmed. 
     */
    public void setComplete(boolean isComplete)
    {
        complete = isComplete;
    }
    
    /**@return true if the scene has volunteers associated with it, false if not**/
    public boolean hasVolunteers()
    {
        return !necessaryVolunteers.isEmpty();
    }
    
    /**@precon the list of volunteers must not be empty
     * @return an iterator for the necessary volunteers of the scene
     * @throws Exception 
     */
    public Iterator<Volunteer> volunteerIterator() throws Exception
    {
        if (necessaryVolunteers.isEmpty())
        {
            throw new Exception("Cannot iterate oveer an empty volunteerList-Scene volunteerIterator");
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
     * @throws Exception 
     */
    public Iterator<Equipment> equipmentIterator() throws Exception
    {
        if (necessaryEquipment.isEmpty())
        {
            throw new Exception("Cannot iterate over an empty equipment list-Scene equipmentIterator");
        }
        return necessaryEquipment.iterator();
    }

    /** Adds the given volunteer to the list of volunteers
     * @precon the volunteer must be in the scene's list of volunteers
     * @param newVolunteer the new volunteer to add to the list of volunteers.
     * @throws Exception 
     */
    public void addVolunteer(Volunteer newVolunteer) throws Exception
    {
        if (necessaryVolunteers.contains(newVolunteer))
        {
            throw new Exception("The volunteer to add was already in the list-Scene addVolunteer");
        }
        necessaryVolunteers.add(newVolunteer);
    }
    
    /**Adds the given equipment to the list of equipment
     * @precon the equipment must not already be in the list of equipment.
     * @param newEquipment thew new equipment to list of equipment. 
     * @throws Exception 
     */
     public void addEquipment(Equipment newEquipment) throws Exception
    {
        if (necessaryEquipment.contains(newEquipment))
        {
            throw new Exception("The volunteer to add was already in the list- Scene addEquipment");
        }
        necessaryEquipment.add(newEquipment);
    }
    
     /**Removes the given volunteer from the list of volunteers for this scene
      * @precon The volunteer must be in the list of volunteers associated with this scene.
      * @param oldVolunteer the volunteer to remove from the list of volunteers for this scene
      * @throws Exception 
      */
    public void removeVolunteer(Volunteer oldVolunteer) throws Exception
    {
        if (!necessaryVolunteers.contains(oldVolunteer))
        {
            throw new Exception("The volunteer to remove is not in the list-Scene removeVolunteer");
        }
        necessaryVolunteers.remove(oldVolunteer);
    }
    
    /**Removes the given equipment from the list of equipment for this scene
     * @precon The equipment must be in the list of equipment for this scene.
     * @param oldEquipment the equipment to remove from the list of equipment for this scene
     * @throws Exception 
     */
    public void removeEquipment(Equipment oldEquipment) throws Exception
    {
        if (!necessaryEquipment.contains(oldEquipment))
        {
            throw new Exception("The equipment to remove is not in the list-Scene removeEquipment");
        }
        necessaryEquipment.remove(oldEquipment);
    }
    
}
}
