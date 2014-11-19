/*
 * 
 */
package actions;

import businessobjects.Scene;
import database.Database;
import java.sql.SQLException;

/**
 * @author Ryan La Forge
 * This action is used to delete the given scene from the database.
 * 
 */
public class DeleteSceneAction extends BaseAction
{
    /**This is the name of the scene to  delete from the database**/
    private String sceneToDelete;
    
    /** The constructor for this action
     * 
     * @param database -The database to delete the scene from
     * @param sceneToDelete -The name of the scene to delete from the database
     */
    public DeleteSceneAction(Database database, String sceneToDelete)
    {
        super(database);
        this.sceneToDelete = sceneToDelete;
    }
    
    
    /**Deletes the scene from the database.
     * @precon The given scene cannot be null
     * @postcon The given scene is deleted by the database. 
     */
    @Override
    protected void runImplementation() 
    {
      if (sceneToDelete == null)
      {
          setErrorMessage("The given scene to delete was null ");
      }
      database().clearCommandList();
      database().addCommand("delete from t_scene where scn_sceneName = '" + sceneToDelete + "';" );
      database().addCommand("delete from t_scenevolunteer where snv_scenename = '" + sceneToDelete + "';");
      database().addCommand("delete from t_sceneequipment where sne_scenename = '" + sceneToDelete + "';");
      try
      {
          database().executeCommandList();
          this.setWasSuccessful(true);
      }
      catch (SQLException e)
      {
          this.setErrorMessage(e.getMessage());
          this.setWasSuccessful(false);
      }
      finally
      {
          database().clearCommandList();
      }
      
    }
    
}
