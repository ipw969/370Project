/**
 * TODO: go to line 40 and figure out if there is anything else to delete.
 */

package actions;
import database.Database;
import java.sql.SQLException;
/**
 *
 * @author johnmason
 */
public class DeleteEquipmentAction extends BaseAction
{
     /**This is the ****EMAIL**** of the owner of the equipment to  delete in the database **/
    private String equipmentToDelete;
    
    /** The constructor for this action
     * 
     * @param database -The database to delete the scene from
     * @param equipmentToDelete -The name of the equipment to delete from the database
     */
    public DeleteEquipmentAction(Database database, String equipmentToDelete)
    {
        super(database);
        this.equipmentToDelete = equipmentToDelete;
    }
    
    
    /**Deletes the equipment from the database.
     * @preconditions The given equipment cannot be null
     * @postconditions The given equipment is deleted by the database. 
     */
    @Override
    protected void runImplementation() 
    {
      if (equipmentToDelete == null)
      {
          setErrorMessage("The given equipment to delete was null ");
      }
      database().clearCommandList();
      database().addCommand("delete from t_equipment where eqp_emailaddress_owner = '" + equipmentToDelete + "';" );
      
      // unsure if i am supposed to delete the equipment from anywhere else
      // will it automatically delete the equipment from any scenes it is in already
      
      //database().addCommand("delete from t_scenevolunteer where snv_scenename = '" + sceneToDelete + "';");
      //database().addCommand("delete from t_sceneequipment where sne_scenename = '" + sceneToDelete + "';");
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
