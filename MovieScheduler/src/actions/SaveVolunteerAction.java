/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import database.Database;
import businessobjects.*;
import java.sql.SQLException;
import businessobjects.TimeInterval;
import java.util.Calendar;
import java.util.GregorianCalendar;
import database.JdbcDatabase;
/**
 *
 * @author johnmason
 */
public class SaveVolunteerAction extends BaseAction{
    
    
    public SaveVolunteerAction(Database database, Volunteer volunteer)
    {
        super(database);
        setBusinessObject(volunteer);
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
            setErrorMessage("Scene Filming Date is not Valid");
            setWasSuccessful(false);
            return;
        }
        
        String queryString;
        
        if(volunteer().isNew())
        {
            queryString = buildInsertQueryString();
            
            try
            {
                database().executeInsert(queryString);
                setWasSuccessful(true);
                volunteer().setHasChanged(false);
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
                volunteer().setHasChanged(false);
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
                "INSERT into t_volunteer"
                + "(vol_emailaddress, "
                + "vol_firstname,"
                + "vol_surname,"
                + "vol_phone )" 
                + "VALUES "
                + "('" + volunteer().getEmail() + "','"
                + volunteer().getFirstName() + "','"
                + volunteer().getLastName()+ "','"
                + volunteer().getPhone() + "');";
        
        return returnString;

    }
    
    private String buildUpdateQueryString()
    {
        String returnString = 
                "UPDATE t_volunteer"
                + "SET"
                + "vol_firstname ='" + volunteer().getFirstName() + "',"
                + "vol_surname ='" + volunteer().getLastName() + "'," 
                + "vol_phone = '"  + volunteer().getPhone() + "'"
                
                + "WHERE "
                + "(vol_emailaddress = '" + volunteer().getEmail() + "')";
        return returnString;
    }
        /*
    
        todo: 
        Table "public.t_volunteeravailability"

 
        For EACH AVAILABILITY INTERVAL
        INSERT into t_volunteeravailability (vav_emailaddress_volunteer, vav_availability_start, vav_availability_end)
        VALUES (getEmail, timestamp '2001-09-29 03:00', timestamp '####-##-## ##:##')
        where you get the ####-##-## ##:## from the timeinterval start and end
        */
   
   
    
    /**
     * Returns the Volunteer associated with this action
     * @return The Volunteer associated with this action
     */ 
    private Volunteer volunteer()
    {
        if(businessObject() == null)
            return null;
        
        return (Volunteer)businessObject();
    }
    
    // Static Methods
    

}

