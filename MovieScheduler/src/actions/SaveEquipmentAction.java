package actions;

/**
 * @author john mason
 */

import database.Database;
import businessobjects.*;
import java.sql.SQLException;

/**
 *
 * @author johnmason
 */
public class SaveEquipmentAction extends BaseAction{
      
    public SaveEquipmentAction(Database database, Equipment equipment)
    {
        super(database);
        setBusinessObject(equipment);
    }
 
     @Override
    protected void runImplementation()
    {
        if(database() == null)
        {
            setErrorMessage("Database is null");
            setWasSuccessful(false);
            return;
        }

        if(!businessObject().isValid())
        {
            setErrorMessage("Equipment is not valid");
            setWasSuccessful(false);
            return;
        }
        
        String queryString;
        
        if(equipment().isNew())
        {
            queryString = buildInsertQueryString();
            
            try
            {
                database().executeInsert(queryString);
                setWasSuccessful(true);
                equipment().setHasChanged(false);
            }
            catch(SQLException ex)
            {
                setWasSuccessful(false);
                setErrorMessage(ex.getMessage());
            }
        }
        else
        {
            queryString = buildUpdateQueryString();
            
            try
            {
                database().executeUpdate(queryString);
                setWasSuccessful(true);
                equipment().setHasChanged(false);
            }
            catch(SQLException ex)
            {
                setWasSuccessful(false);
                setErrorMessage(ex.getMessage());
            }
        }
       
        
    
    
    
}    // Private Methods
    
    /**
     * Creates an SQL UPDATE string for the current Volunteer
     * @return An SQL UPDATE string for the current Volunteer
     */
    private String buildInsertQueryString()
    {
        String returnString = 
                "INSERT into t_equipment"
                + "(eqp_equipmentname, "
                + "eqp_firstname_owner,"
                + "eqp_surname_owner,"
                + "eqp_emailaddress_owner)" 
                + "VALUES "
                + "('" 
                + equipment().getOwnerEmail() + "','"
                + equipment().getOwnerFirstName() + "','"
                + equipment().getOwnerLastName()+ "','"
                + equipment().getEquipmentName() + "');";
        
        return returnString;

    }
    
    private String buildUpdateQueryString()
    {
        String returnString = 
                "UPDATE t_equipment"
                + "SET"
                + "eqp_equipmentname = '" + equipment().getEquipmentName() + "',"
                + "eqp_firstname_owner ='" + equipment().getOwnerFirstName() + "'," 
                + "eqp_surname_owner = '"  + equipment().getOwnerLastName() + "'"
                
                + "WHERE "
                + "(eqp_emailaddress_owner = '" + equipment().getOwnerEmail() + "')";
        return returnString;
    }
 
   
    
    /**
     * Returns the Volunteer associated with this action
     * @return The Volunteer associated with this action
     */ 
    private Equipment equipment()
    {
        if(businessObject() == null)
            return null;
        
        return (Equipment)businessObject();
    }
    
    
  
}
