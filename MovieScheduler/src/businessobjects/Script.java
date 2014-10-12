/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

TODO: add a way to keep the scenes in order?
TODO: 
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
 * TODO: Create methods for adding,removing, and iterating over scenes. 
 * add,hasNexthasPrevious,next,nextIndex,previous,previousIndex,remove,set
 */
public class Script extends BaseBusinessObject {
    //The list of scenes. scenes will be a synchronized ArrayList. 
   protected List<Scene> scenes; 
   protected String name; 
   
   
   //A cursor for iterating over the scenes in the script
   private int currentScene;
   private int previousScene;
   
   /**The constructor for the script
     * @param name - the name of the script
    * 
    * */
   public Script(String name)
   {
       super();
       this.name = name;
       scenes = Collections.synchronizedList(new ArrayList<Scene>());
       currentScene = 0;
       previousScene = -1;
   }
   
/** Traverse to the first scene in the list   
 * */
 public void goFirst()
 {
     currentScene = 0;
     previousScene = -1;
 }
  
/** Traverse to the next scene in the list
 public void goForth()
 {
          previousScene = currentScene;
          currentScene = currentScene + 1;
 }
 
 * /**Traverse to the previous scene in the list.**/
 public void goBack()
 {
     currentScene = previousScene;
     previousScene = previousScene-1;
 }
 
 /**Go to the last scene in the list**/
 public void goLast()
 {
     currentScene = scenes.size() -1;
     previousScene = currentScene -1;
 }
 
 /**checks to see if the cursor is before the list of scenes**/
 public boolean isBefore()
 {
     return currentScene < 0;
 }
 
 /**Checks to see if the cursor is after the list of scenes**/
 public boolean isAfter()
 {
     return currentScene >= scenes.size();
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
public class Scene {
    private final String name;
    private final String description;
    private final HashSet<Volunteer> necessaryVolunteers;
    private final HashSet<Equipment> necessaryEquipment;
    private boolean scheduled;
    private boolean complete;
    
    private Scene(String name, String description)
    {
        this.name = name;
        this.description = description;
        
        necessaryVolunteers = new HashSet<Volunteer>();
        necessaryEquipment = new HashSet<Equipment>();
        
    }

    public String name()
    {
        return this.name;
    }
    
    public String description()
    {
        return this.description;
    }
    
    public boolean scheduled()
    {
        return scheduled;
    }
    
    public boolean complete()
    {
        return complete;
    }
    
    
    public void setScheduled(boolean isScheduled)
    {
        scheduled = isScheduled;
    }
    
    public void setComplete(boolean isComplete)
    {
        complete = isComplete;
    }
    public Iterator<Volunteer> volunteerIterator() throws Exception
    {
        if (necessaryVolunteers.isEmpty())
        {
            throw new Exception("Cannot iterate oveer an empty volunteerList-Scene volunteerIterator");
        }
        return necessaryVolunteers.iterator();
    }

    public Iterator<Equipment> equipmentIterator() throws Exception
    {
        if (necessaryEquipment.isEmpty())
        {
            throw new Exception("Cannot iterate over an empty equipment list-Scene equipmentIterator");
        }
        return necessaryEquipment.iterator();
    }

    public void addVolunteer(Volunteer newVolunteer) throws Exception
    {
        if (necessaryVolunteers.contains(newVolunteer))
        {
            throw new Exception("The volunteer to add was already in the list-Scene addVolunteer");
        }
        necessaryVolunteers.add(newVolunteer);
    }
    
     public void addEquipment(Equipment newEquipment) throws Exception
    {
        if (necessaryEquipment.contains(newEquipment))
        {
            throw new Exception("The volunteer to add was already in the list- Scene addEquipment");
        }
        necessaryEquipment.add(newEquipment);
    }
    
     
    public void removeVolunteer(Volunteer oldVolunteer) throws Exception
    {
        if (!necessaryVolunteers.contains(oldVolunteer))
        {
            throw new Exception("The volunteer to remove is not in the list-Scene removeVolunteer");
        }
        necessaryVolunteers.remove(oldVolunteer);
    }
    
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
