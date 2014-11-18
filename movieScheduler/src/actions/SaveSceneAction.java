/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author ryan
 */

public class SaveSceneAction extends BaseAction {
private final String replacedSceneName;
private final Script script;
    public SaveSceneAction(Database database ,Scene sceneToSave, String replacedSceneName, Script script) 
    {
        super(database);
        setBusinessObject(sceneToSave);
        this.replacedSceneName = replacedSceneName;
        this.script = script;
    }
    
    

    @Override
    protected void runImplementation() {
        Scene sceneToSave = (Scene) this.businessObject();
        if (script == null || (!script.isValid()))
        {
            this.setErrorMessage("The given script is not valid");
            this.setWasSuccessful(false);
        }
        
        if (sceneToSave == null || (!sceneToSave.isValid()))
        {
            this.setErrorMessage("This given scene to add is not valid");
            this.setWasSuccessful(false);
        }
        
        database().clearCommandList();
                
        if (replacedSceneName!= null)
        {
            database().addCommand("delete from t_scene where scnscenename = " + replacedSceneName + ";");
            database().addCommand("delete from  t_scenevolunteers where snv_scenename = " + replacedSceneName + ";");
            database().addCommand("delete from t_sceneequipment where sne_scenename = " + replacedSceneName + ";");    
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
                    + "VALUES('" + sceneToSave.name() + "' , '" + equipmentToSave.getEquipmentType() + "');" );
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
