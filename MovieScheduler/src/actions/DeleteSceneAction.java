
package actions;

import businessobjects.Scene;
import database.Database;
import java.sql.SQLException;

/**
 * @author Ryan La Forge This action is used to delete the given scene from the
 * database.
 *
 */
public class DeleteSceneAction extends BaseAction {

    /**
     * This is the name of the scene to delete from the database*
     */
    private String sceneToDelete;

    /**
     * The name of the script that the scene is involved in*
     */
    private String sceneScriptName;

    /**
     * The constructor for this action
     *
     * @precon A connection to the database must have been established
     * @param database -The database to delete the scene from
     * @param sceneToDelete -The name of the scene to delete from the database
     * @author Ryan
     */
    public DeleteSceneAction(Database database, String sceneToDelete) {
        super(database);
        this.sceneToDelete = sceneToDelete;

    }

    /**
     * Deletes the scene from the database.
     * @postcon The given scene is deleted by the database.
     * @author Ryan
     */
    @Override
    protected void runImplementation() {
        if (sceneToDelete == null) {
            setErrorMessage("The given scene to delete was null ");
        }
        getDatabase().clearCommandList();
        getDatabase().addCommand("delete from t_scenevolunteer where snv_scenename = '" + sceneToDelete + "';");
        getDatabase().addCommand("delete from t_sceneequipment where sne_scenename = '" + sceneToDelete + "';");
        getDatabase().addCommand("delete from t_schedule where sch_scenename = '" + sceneToDelete + "';");
        getDatabase().addCommand("delete from t_scene where scn_sceneName = '" + sceneToDelete + "';");

        try {
            getDatabase().executeCommandList();
            this.setWasSuccessful(true);
        } catch (SQLException e) {
            this.setErrorMessage(e.getMessage());
            this.setWasSuccessful(false);
        } finally {
            getDatabase().clearCommandList();
        }

    }

}
