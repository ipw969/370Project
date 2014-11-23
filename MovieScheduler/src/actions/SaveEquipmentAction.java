/*
*   TODO:   perhaps we should use the equipment name as the key in the database
*           one owner may have donated several peices of equipment (this would
*           overwrite them in the database due to the owner having 1 email address)
*/

package actions;

import database.Database;
import businessobjects.*;
import java.sql.SQLException;

/**
 * an action class to save a equipment object to the database
 * @author John Mason
 */
public class SaveEquipmentAction extends BaseAction {

    //equipmentToReplace is the email address of the equipment that needs to be edited
    private String equipmentToReplace;
    private Equipment equipment;

    //general constructor for equpiment not in the database
    public SaveEquipmentAction(Database database, Equipment equipment) {
        super(database);
        this.equipment = equipment;
        setBusinessObject(equipment);
    }

    //used if you want to replace equpiment in the database
    public SaveEquipmentAction(Database database, Equipment equipment, String equipmentToReplace) {
        super(database);
        this.equipment = equipment;
        this.equipmentToReplace = equipmentToReplace;
        setBusinessObject(equipment);
    }

    @Override
    protected void runImplementation() {
        getDatabase().clearCommandList();
        if (equipmentToReplace != null) {
            {
                getDatabase().addCommand("delete from t_equipment where eqp_emailaddress_owner = '" + equipmentToReplace + "';");
            }
            getDatabase().addCommand(buildInsertQueryString());
            if (getDatabase() == null) {
                setErrorMessage("Database is null");
                setWasSuccessful(false);
                return;
            }

            if (!getBusinessObject().isValid()) {
                setErrorMessage("Scene equipment object is not Valid");
                setWasSuccessful(false);
                return;
            }

            String queryString;

            if (equipment().isNew()) {
                queryString = buildInsertQueryString();

                try {
                    getDatabase().executeInsert(queryString);
                    setWasSuccessful(true);
                    equipment().setHasChanged(false);
                } catch (SQLException ex) {
                    setWasSuccessful(false);
                    setErrorMessage(ex.getMessage());
                }
            } else {
                queryString = buildUpdateQueryString();

                try {
                    getDatabase().executeUpdate(queryString);
                    setWasSuccessful(true);
                    equipment().setHasChanged(false);
                } catch (SQLException ex) {
                    setWasSuccessful(false);
                    setErrorMessage(ex.getMessage());
                }
            }

        }

    }    // Private Methods

    /**
     * Creates an SQL UPDATE string for the current equipment
     *
     * @return An SQL UPDATE string for the current equipment
     */
    private String buildInsertQueryString() {
        String returnString
                = "INSERT into t_equipment"
                + "(eqp_equipmentname, "
                + "eqp_firstname_owner,"
                + "eqp_surname_owner,"
                + "eqp_emailaddress_owner)"
                + "VALUES "
                + "('"
                + equipment().getOwnerEmail() + "','"
                + equipment().getOwnerFirstName() + "','"
                + equipment().getOwnerLastName() + "','"
                + equipment().getEquipmentName() + "');";

        return returnString;

    }

    private String buildUpdateQueryString() {
        String returnString
                = "UPDATE t_equipment"
                + "SET"
                + "eqp_equipmentname = '" + equipment().getEquipmentName() + "',"
                + "eqp_firstname_owner ='" + equipment().getOwnerFirstName() + "',"
                + "eqp_surname_owner = '" + equipment().getOwnerLastName() + "'"
                + "WHERE "
                + "(eqp_emailaddress_owner = '" + equipment().getOwnerEmail() + "')";
        return returnString;
    }

    /**
     * @return The equipment associated with this action
     */
    private Equipment equipment() {
        if (getBusinessObject() == null) {
            return null;
        }

        return (Equipment) getBusinessObject();
    }

}
