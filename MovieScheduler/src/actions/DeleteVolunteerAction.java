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
public class DeleteVolunteerAction extends BaseAction
{
     /**This is the ****EMAIL**** of the volunteer to  delete in the database **/
    private String volunteerToDelete;
    
    /** The constructor for this action
     * 
     * @param database -The database to delete the scene from
     * @param volunteerToDelete -The name of the volunteer to delete from the database
     */
    public DeleteVolunteerAction(Database database, String volunteerToDelete)
    {
        super(database);
        this.volunteerToDelete = volunteerToDelete;
    }
    
    
    /**Deletes the volunteer from the database.
     * @preconditions The given volunteer cannot be null
     * @postconditions The given volunteer is deleted by the database. 
     */
    @Override
    protected void runImplementation() 
    {
      getDatabase().clearCommandList();
      if (volunteerToDelete != null)
      {      
            getDatabase().addCommand("delete from t_scenevolunteer where snv_emailaddress_volunteer = '" + volunteerToDelete + "';" );
            getDatabase().addCommand("delete from t_volunteeravailability where vav_emailaddress_volunteer = '" + volunteerToDelete + "';" );
            getDatabase().addCommand("delete from t_volunteer where vol_emailaddress = '" + volunteerToDelete + "';" );
      }
       
      try
      {
          getDatabase().executeCommandList();
          this.setWasSuccessful(true);
      }
      catch (SQLException e)
      {
          this.setErrorMessage(e.getMessage());
          this.setWasSuccessful(false);
      }
      finally
      {
          getDatabase().clearCommandList();
      }
      
    }
   
}
