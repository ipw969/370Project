/*
*   TODO:   perhaps we should use the equipment name as the key in the database
*           one owner may have donated several peices of equipment (this would
*           overwrite them in the database due to the owner having 1 email address)
*/

package actions;

import database.Database;
import businessobjects.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * an action class to save a equipment object to the database
 * @author John Mason
 */
public class SaveEquipmentAction extends BaseAction {

    //equipmentToReplace is the email address of the equipment that needs to be edited
    private Equipment equipmentToReplace;
    private Equipment equipment;

    //general constructor for equpiment not in the database
    public SaveEquipmentAction(Database database, Equipment equipment) {
        super(database);
        this.equipment = equipment;
        setBusinessObject(equipment);
    }

    //used if you want to replace equpiment in the database
    public SaveEquipmentAction(Database database, Equipment equipment, Equipment equipmentToReplace) {
        super(database);
        this.equipment = equipment;
        if (equipmentToReplace == null || equipmentToReplace.getEquipmentName() == null || equipmentToReplace.getOwnerFirstName() == null || equipmentToReplace.getOwnerLastName() == null)
        {
            this.equipmentToReplace = null;
        }
        else
        {
             this.equipmentToReplace = equipmentToReplace;
        }
       
        setBusinessObject(equipment);
    }

    @Override
    protected void runImplementation() {
        if (getDatabase() == null) {
            setErrorMessage("Database is null");
            setWasSuccessful(false);
            return;
        }
        
        getDatabase().clearCommandList();
        if (equipmentToReplace != null) {
            getDatabase().addCommand("delete from t_equipmentavailability where eav_equipmentname = '" + equipmentToReplace.getEquipmentName() + "';");
            getDatabase().addCommand("delete from t_equipment where eqp_emailaddress_owner = '" + equipmentToReplace.getOwnerEmail() + "';");
        }
         getDatabase().addCommand("delete from t_equipmentowner where own_firstname = '" + equipment.getOwnerFirstName() + "' and own_surname = '" + equipment.getOwnerLastName() + "' and own_emailaddress = '" + equipment.getOwnerEmail() + "';");
        
        getDatabase().addCommand("insert into t_equipmentowner(own_firstname, own_surname, own_emailaddress) VALUES('" + equipment.getOwnerFirstName() + "','" + equipment.getOwnerLastName() + "','" + equipment.getOwnerEmail() + "');");
        getDatabase().addCommand(buildInsertQueryString());

        for (TimeInterval interval : equipment.getAvailability()) {
            getDatabase().addCommand("insert into t_equipmentavailability(eav_equipmentname, eav_availability_start, eav_availability_end) VALUES('"
                    + equipment.getEquipmentName() + "','"
                    + interval.getStartIsoDate() + "','"
                    + interval.getEndIsoDate() + "');");
        }
        if (!getBusinessObject().isValid()) {
            setErrorMessage("Scene equipment object is not Valid");
            setWasSuccessful(false);
            return;
        }
        
        try {
            getDatabase().executeCommandList();
            this.setWasSuccessful(true);
        } catch (SQLException ex) {
            this.setWasSuccessful(false);
            System.out.println("failed to save the equipment.\n Error:" + ex.getMessage());
        }
        equipment.setIsNew(false);
    }  

    /**
     * Creates an SQL UPDATE string for the current equipment
     *
     * @return An SQL UPDATE string for the current equipment
     */
    private String buildInsertQueryString() {
        String returnString
                = "INSERT into t_equipment"
                + "(eqp_emailaddress_owner, "
                + "eqp_firstname_owner,"
                + "eqp_surname_owner,"
                + "eqp_equipmentname)"
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
                + " SET "
                + "eqp_equipmentname = '" + equipment().getEquipmentName() + "',"
                + "eqp_firstname_owner ='" + equipment().getOwnerFirstName() + "',"
                + "eqp_surname_owner = '" + equipment().getOwnerLastName() + "'"
                + " WHERE "
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
