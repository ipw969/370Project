/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
/**
 *
 * @author ryan
 * TODO: Create methods for adding,removing, and iterating over scenes. 
 * add,hasNexthasPrevious,next,nextIndex,previous,previousIndex,remove,set
 */
public class Script extends BaseBusinessObject implements ListIterator {
    //The list of scenes. scenes will be a synchronized ArrayList. 
   protected List scenes; 
    
   
   public Script()
   {
       super();
       scenes = Collections.synchronizedList(new ArrayList());
   }
   
   
  /**Checks to see if all of the scenes in this script have been successfully scheduled. 
   * @Return: true if all scenes have been scheduled or if the script is empty, false if not. 
   * 
   **/
  
   public boolean isScheduled()
   {
       if (scenes.isEmpty())
       {
           return true;
       }
       boolean scheduled = true;
       for (Scene currentScene: scenes)
       {
           if (currentScene.isScheduled() == false)
           {
               scheduled = false;
           }
       }
       return scheduled;
   }
   
   /** Checks to see if the script is empty
    * @Return: true if the script is empty, false if not.
    */
   public boolean isEmpty()
   {
       return scenes.isEmpty();
   }
   
   

}
