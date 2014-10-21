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




public class Script extends BaseBusinessObject implements BusinessObjectListener {
    //The list of scenes. scenes will be a synchronized ArrayList. 
   protected BusinessObjectList<Scene> scenes; 
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
       scenes.addListener(this);
   }

  /** @return true if the script contains scenes, false if not. **/
 public boolean hasScenes()
 {
    return !scenes.isEmpty();
 }
 /**Creates a scene with the specified name and description and adds it to the script. 
  * @preCon name != null, description != null
  * @param name  the name of the new scene
  * @param description the description of the scene
  * @return the newly created scene 
  * TODO ensure there is not a scene with the same name in the script. 
  */
 
  public Scene createAndAddScene(String name, String description)
   {
       if(this.hasScenes())
       {
             Iterator<Scene> iter = this.sceneIterator();
            while (iter.hasNext())
             {
               Scene examinedScene = iter.next();
               if (examinedScene.name().equals(name))
             {
                 this.updateError("A scene with that name already exists in the list of scenes", true);
                 return null;
             }
             }
            Scene newScene = new Scene(name, description);
              scenes.add(newScene);
              return newScene;
       }
      else
       {
            Scene newScene = new Scene(name, description);
              scenes.add(newScene);
              return newScene;
       }
      
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
   public Iterator<Scene> sceneIterator()
   {
       return scenes.iterator();
   }
   
  

    @Override
    public void validStateAltered(boolean newState, BaseBusinessObject sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changedStateAltered(boolean newState, BaseBusinessObject sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
 public static void main(String [] args)
    {
        Script newScript;
       newScript = new Script("Practice Script");
    }
}

