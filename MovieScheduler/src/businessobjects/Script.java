/*
 * This class contains all information relevent to the script. 
*This class encapsulates the Scene class to prevent scenes from being created anywhere else. This ensures that
*-The scenes are strongly correlated with the script(there will be no cross-referencing if we decide to support multiple scripts
*-Ensures that every scene is automatically put into the script. 

TODO: add a way to keep the scenes in order?
TODO: Ensure this class complies with the businessObject class definition.
TODO: Add a method of retrieving all of the date from the database when the application starts. 
TODO: Create a test scaffold for the class
TODO: Add a way for the script to listen to the scenes for a valid state. 
 */
package businessobjects;
import java.util.Iterator;




public class Script extends BaseBusinessObject {
    //The list of scenes. scenes will be a synchronized ArrayList. 
   protected BusinessObjectList<Scene> scenes; 
   // A list of all of the volunteers working on this project. 
   protected BusinessObjectList<Volunteer> volunteers; 
   //A list of all of the equipment working on this project. 
   protected BusinessObjectList<Equipment> equipment; 
   
   protected String name;  
   
   
   private final String nullNameError = "The name of the script is invalid";
   /**The constructor for the script
     * @param name - the name of the script
    * 
    * */
   @SuppressWarnings("LeakingThisInConstructor")
   public Script(String name)
   {
       super();
       this.name = name;
       updateError(nullNameError, !name.isEmpty());
       scenes = new BusinessObjectList<>();
       volunteers = new BusinessObjectList<>();
       equipment = new BusinessObjectList<>();
   }

   
   /** EVERYTHING TO DO WITH THE SCENE PORTION OF THE SCRIPT IS LISTED BELOW*/
   
   /**
    * Returns a list of the Scenes which comprise this Script
    * @return A list of the Scenes which comprise this Script`
    */
   public BusinessObjectList<Scene> scenes()
   {
       return scenes;
   }
   
  /** @return true if the script contains scenes, false if not. **/
 public boolean hasScenes()
 {
    return !scenes.isEmpty();
 }
 /**Creates a scene with the specified name and description and adds it to the script. 
  * @preCon name != null, description != null
  * @param newScene the new scene to add to the script. 
  * @return the newly created volunteer.
  */
 
  public boolean addScene(Scene newScene)
   {
       if (newScene == null || !newScene.isValid())
       {
           return false;
       }
       /**This whole thing is necessary because we need to check whether a scene with that name already exists
       regardless of whether they are identical*/
       if(this.hasScenes())
       {
             Iterator<Scene> iter = this.sceneIterator();
            while (iter.hasNext())
             {
               Scene examinedScene = iter.next();
               if (examinedScene.name().equals(newScene.name()))
             {
                
                 return false;
             }
             }
           
           
       }
      
             scenes.add(newScene);
             return true;

            
   } 
    
  /**removes the specified volunteer from the list of volunteers
   * @param oldScene - the scene to be deleted from the list
   * @return true if successful, false if not
   * May edit it to throw an exception if the scene is not completed, not sure yet. 
   */
  public boolean removeScene(Scene oldScene)
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
  
   public boolean isEverySceneScheduled()
   {
       if (scenes.isEmpty())
       {
           return true;
       }
       for (Scene curScene: scenes)
       {
           if (curScene.isScheduled() == false)
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
   public boolean isEverySceneComplete()
   {
       for (Scene curScene: scenes)
       {
           if (curScene.isComplete() == false)
           {
               return false;
           }
       }
       return true;
   }
   /** Checks to see if the script is empty
    * @return true if the script is empty, false if not.
    */
   public boolean isSceneListEmpty()
   {
       return scenes.isEmpty();
   }
   
/**Gives you an iterator over the scenes in the script
 * 
 * @return an iterator over the scenes in the script. 
 */
   public Iterator<Scene> sceneIterator()
   {
       return scenes.iterator();
   }
   
  
/**EVERYTHING TO DO WITH THE LISTS OF VOLUNTEERS IS BELOW**/
   
   
   
   
   
   
   
     /** @return true if the script contains volunteers, false if not. **/
 public boolean hasVolunteers()
 {
    return !volunteers.isEmpty();
 }
 /**Creates a Volunteer with the specified information and adds it to the script. 
  * @precon: The given volunteer should be valid.
  * @param newVolunteer: The new volunteer to add to the script.
  * @return true if successful, false if not.
  */
 
  public boolean addVolunteer(Volunteer newVolunteer)
   {
       if ((newVolunteer == null) || (!newVolunteer.isValid()))
       {
           return false;
       }
       /**This whole thing is necessary because we need to check whether a scene with that name already exists
       regardless of whether they are identical*/
       if(this.hasVolunteers())
       {
             Iterator<Volunteer> iter = this.volunteerIterator();
            while (iter.hasNext())
             {
               Volunteer examinedVolunteer = iter.next();
               if (examinedVolunteer.getEmail().equals(newVolunteer.getEmail()))
             {
                 return false;
             }
             }
       }
    
         volunteers.add(newVolunteer);
         return true;
      
   } 
    
  /**removes the specified volunteer from the list of volunteers
   * @param oldVolunteer - the volunteer to be deleted from the list
   * @return true if successful, false if not
   * May edit it to throw an exception if the scene is not completed, not sure yet. 
   */
  public boolean removeVolunteer(Volunteer oldVolunteer)
  {
      return volunteers.remove(oldVolunteer);
  }
  
  
 
 
  
   /** Checks to see if the script is has any volunteers
    * @return true if the script has no volunteers, false if not.
    */
   public boolean isvolunteerListEmpty()
   {
       return volunteers.isEmpty();
   }
   
/**Gives you an iterator over the scenes in the script
 * 
 * @return an iterator over the scenes in the script. 
 */
   public Iterator<Volunteer> volunteerIterator()
   {
       return volunteers.iterator();
   }
   
   
   /**ALL EQUIPMENT INFORMATION IS BELOW HERE**/
   
   
   
   
        /** @return true if the script contains equipment, false if not. **/
 public boolean hasEquipment()
 {
    return !equipment.isEmpty();
 }
 
 /**Creates an equipment with the specified information and adds it to the script. 
  * @precon: The given volunteer should be valid.
  * @param newEquipment: The new equipment to add to the script.
  * @return true if successful, false if not.
  */
 
  public boolean addEquipment(Equipment newEquipment)
   {
       if ((newEquipment == null) || (!newEquipment.isValid()))
       {
          return false;
       }
      /**This whole thing is necessary because we need to check whether a scene with that name already exists
       regardless of whether they are identical*/
       if(this.hasEquipment())
       {
             Iterator<Equipment> iter = this.equipmentIterator();
            while (iter.hasNext())
             {
               Equipment examinedEquipment = iter.next();
               if (examinedEquipment.getEquipmentType().equals(newEquipment.getEquipmentType()))
             {
                 examinedEquipment.setStock(examinedEquipment.getStock() + 1);
                 return true;
                 
             }
             }
       }
    
         return equipment.add(newEquipment);
         
      
   } 
    
  /**removes the specified equipment from the list of equipment
   * @param oldEquipment - the equipment to be deleted from the list
   * @return true if successful, false if not
   * May edit it to throw an exception if the scene is not completed, not sure yet. 
   */
  public boolean removeEquipment(Equipment oldEquipment)
  {
      return equipment.remove(oldEquipment);
  }
  
  
 
 
  
   /** Checks to see if the script is has any equipment
    * @return true if the script has no equipment, false if not.
    */
   public boolean isEquipmentListEmpty()
   {
       return equipment.isEmpty();
   }
   
/**Gives you an iterator over the equipment in the script
 * 
 * @return an iterator over the equipment in the script. 
 */
   public Iterator<Equipment> equipmentIterator()
   {
       return equipment.iterator();
   }
   
   
 
 public static void main(String [] args)
    {
        Script newScript;
       newScript = new Script("Practice Script");
    }
}

