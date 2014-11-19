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
public class SaveSceneAction extends BaseAction
{
    // Private Member Variables
    private final String replacedSceneName;
    private final Script script;

    // Constructor
    public SaveSceneAction(Database database, 
            Scene sceneToSave, String replacedSceneName, Script script)
    {
        super(database);
        setBusinessObject(sceneToSave);
        this.replacedSceneName = replacedSceneName;
        this.script = script;
    }

    // Protected Methods
    @Override
    protected void runImplementation()
    {
        Scene sceneToSave = (Scene) this.getBusinessObject();
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

        getDatabase().clearCommandList();

        if (replacedSceneName != null)
        {
            getDatabase().addCommand(
                    "delete from t_scene "
                            + "where scnscenename = " + replacedSceneName + ";");
            getDatabase().addCommand(
                    "delete from t_scenevolunteers "
                            + "where snv_scenename = " + replacedSceneName + ";");
            getDatabase().addCommand(
                    "delete from t_sceneequipment "
                            + "where sne_scenename = " + replacedSceneName + ";");
        }

        boolean isFilmed = false;
        if (sceneToSave.isScheduled())
        {
            isFilmed = true;
        }

        getDatabase().addCommand("insert into t_scene"
                + "(scn_sceneName, "
                + "scn_sceneIsFilmed,  "
                + "scn_sceneDescription, "
                + "scn_scriptname) "
                + "VALUES('" 
                + sceneToSave.name() + "' ," + "'" 
                + isFilmed + "' ," + "'" 
                + sceneToSave.getDescription() + "' ," + "'" 
                + this.script.getName() + "');");
        
        if (sceneToSave.hasEquipment())
        {
            Iterator<Equipment> iter = sceneToSave.equipmentIterator();
            while (iter.hasNext())
            {
                /**
                 * This needs to be changed because eventually the equipment
                 * will change to fit the database*
                 */
                Equipment equipmentToSave = iter.next();
                getDatabase().addCommand("insert into t_sceneEquipment"
                        + "(sne_sceneName, sne_equipmentName) "
                        + "VALUES('" 
                        + sceneToSave.name() + "' , '" 
                        + equipmentToSave.getEquipmentType() + "');");
            }
        }

        if (sceneToSave.hasVolunteers())
        {
            Iterator<Volunteer> iter = sceneToSave.volunteerIterator();
            while (iter.hasNext())
            {
                Volunteer volunteerToSave = iter.next();
                getDatabase().addCommand("insert into t_scenevolunteer"
                        + "(snv_sceneName, snv_emailaddress_volunteer) "
                        + "VALUES('" 
                        + sceneToSave.name() + "' , '" 
                        + volunteerToSave.getEmail() + "');");
            }
        }

        try
        {
            getDatabase().executeCommandList();
            this.setWasSuccessful(true);

        } catch (SQLException e)
        {
            this.setWasSuccessful(false);
            this.setErrorMessage(e.getMessage());
        } finally
        {
            getDatabase().clearCommandList();
        }

    }

}
