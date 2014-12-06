/*
 * TODO: go to line 40 and figure out if there is anything else to delete.
 */
package actions;

import businessobjects.Equipment;
import database.Database;
import java.sql.SQLException;

/**
 * an action class to delete all instances of an equipment object throughout the program
 * @author John Mason
 */
public class DeleteEquipmentAction extends BaseAction {

    /**
     * This is the ****EMAIL**** of the owner of the equipment to delete in the
     * database *
     */
    private Equipment equipmentToDelete;

    /**
     * The constructor for this action
     *
     * @param database -The database to delete the scene from
     * @param equipmentToDelete -The name of the equipment to delete from the
     * database
     */
    public DeleteEquipmentAction(Database database, Equipment equipmentToDelete) {
        super(database);
        this.equipmentToDelete = equipmentToDelete;
    }

    /**
     * Deletes the equipment from the database.
     *
     * @preconditions The given equipment cannot be null
     * @postconditions The given equipment is deleted by the database.
     */
    @Override
    protected void runImplementation() {
        if (equipmentToDelete == null) {
            setErrorMessage("The given equipment to delete was null ");
        }
        getDatabase().clearCommandList();
        getDatabase().addCommand("delete from t_equipmentAvailability where eav_equipmentName = '" + equipmentToDelete.getEquipmentName() + "';" );
        getDatabase().addCommand("delete from t_equipment where eqp_emailaddress_owner = '" + equipmentToDelete.getOwnerEmail() + "';");

      // unsure if i am supposed to delete the equipment from anywhere else
        // will it automatically delete the equipment from any scenes it is in already
      //database().addCommand("delete from t_scenevolunteer where snv_scenename = '" + sceneToDelete + "';");
        getDatabase().addCommand("delete from t_sceneequipment where sne_equipmentname = '" + equipmentToDelete.getEquipmentName() + "';");
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
