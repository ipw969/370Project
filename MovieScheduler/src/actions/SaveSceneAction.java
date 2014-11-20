/* @author Ryan La Forge
 
 */
package actions;

import businessobjects.Equipment;
import businessobjects.Scene;
import businessobjects.Script;
import businessobjects.Volunteer;
import database.Database;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * @author ryan
 * This action is used to save the given scene to the database. 
 * This includes all information regarding the scene as well as all of the volunteers 
 * and equipment associated with it.
 */

public class SaveSceneAction extends BaseAction {
//* This is the scene, if any that will be replaced. This is used if the scene to be saved is to replace another scene(when a scene is edited)*/  
private final String replacedSceneName;

/**The script that the scene to be saved belongs to. This is required because of the structure of the database.**/
private final Script script;

/**The constructor for the saveSceneAction.
 * 
 * @param database -The database that contains all of the information for this project.
 * @param sceneToSave -The scene to save to the database.
 * @param replacedSceneName - The scene to be replaced, if any
 * @param script -The script that the sceneToSave belongs to.
 */
    public SaveSceneAction(Database database ,Scene sceneToSave, String replacedSceneName, Script script) 
    {
        super(database);
        setBusinessObject(sceneToSave);
        this.replacedSceneName = replacedSceneName;
        this.script = script;
    }
    
    
    
    
/**This will save the scene to the database. 
 * @precondition The script that was given must not be null.
 * @postcondition wasSuccessful is set to true if the scene was successfully saved.
 *                wasSuccessful is set to false if the scene wasn't successfully saved.
 */
    @Override
    protected void runImplementation() {
        Scene sceneToSave = (Scene) this.businessObject();
        if (script == null || (!script.isValid()))
        {
            this.setErrorMessage("The given script is not valid");
            this.setWasSuccessful(false);
        }
        
        
        database().clearCommandList();
                
        if (replacedSceneName!= null)
        {
            
            database().addCommand("delete from  t_scenevolunteer where snv_scenename = '" + replacedSceneName + "';");
            database().addCommand("delete from t_sceneequipment where sne_scenename = '" + replacedSceneName + "';"); 
            database().addCommand("update t_scene set scn_scenename = '" + sceneToSave.name() + "' where scn_scenename = '" + replacedSceneName + "';");
        }
        
        boolean isFilmed = false;
        if (sceneToSave.isScheduled())
        {
            isFilmed = true;
        }
        
        database().addCommand("insert into t_scene(scn_sceneName, scn_sceneIsFilmed,  scn_sceneDescription, scn_scriptname) "
                + "VALUES('" + sceneToSave.name() + "' ," + "'" + isFilmed + "' ," + "'" + sceneToSave.description() + "' ," + "'" + this.script.name() + "');" );
       if (sceneToSave.hasEquipment())
       {
             Iterator<Equipment> iter = sceneToSave.equipmentIterator();
        while(iter.hasNext())
        {
            /**This needs to be changed because eventually the equipment will change to fit the database**/
            Equipment equipmentToSave = iter.next();
            database().addCommand("insert into t_sceneEquipment(sne_sceneName, sne_equipmentName) "
                    + "VALUES('" + sceneToSave.name() + "' , '" + equipmentToSave.getEquipmentName() + "');" );
        }
       }
      
        if (sceneToSave.hasVolunteers())
        {
           Iterator<Volunteer> iter = sceneToSave.volunteerIterator();
           while (iter.hasNext())
           {
               Volunteer volunteerToSave = iter.next();
               database().addCommand("insert into t_scenevolunteer(snv_sceneName, snv_emailaddress_volunteer) "
                       + "VALUES('" + sceneToSave.name() + "' , '" + volunteerToSave.getEmail() + "');");
           }
        }
        
        try
        {
             database().executeCommandList();
             this.setWasSuccessful(true);
             
        } catch (SQLException e)
        {
            this.setWasSuccessful(false);
            this.setErrorMessage(e.getMessage()); 
        } finally
        {
           database().clearCommandList(); 
        }
       
        
        
        
    }
    
}
