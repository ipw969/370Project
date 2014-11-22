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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author johnmason
 */
public class SaveVolunteerAction extends BaseAction{
    
    //volunteerToReplace is the email address of the volunteer that needs to be edited
    private String volunteerToReplace;
    private Volunteer volunteer;
    
    public SaveVolunteerAction(Database database, Volunteer volunteer, String volunteerToReplace)
    {
        super(database);
        this.volunteerToReplace = volunteerToReplace;
        this.volunteer = volunteer;
        setBusinessObject(volunteer);
    }
    
    public SaveVolunteerAction(Database database, Volunteer volunteer)
    {
        super(database);
        this.volunteer = volunteer;
        setBusinessObject(volunteer);
    }
 
     @Override
    protected void runImplementation()
    {

        getDatabase().clearCommandList();
        if (volunteerToReplace != null)
        {      
            getDatabase().addCommand("delete from t_scenevolunteer where snv_emailaddress_volunteer = '" + volunteerToReplace + "';" );
            getDatabase().addCommand("delete from t_volunteeravailability where vav_emailaddress_volunteer = '" + volunteerToReplace + "';" );
            getDatabase().addCommand("delete from t_volunteer where vol_emailaddress = '" + volunteerToReplace + "';" );
        }
        getDatabase().addCommand(buildInsertQueryString());
        if(getDatabase() == null)
        {
            setErrorMessage("Database is null");
            setWasSuccessful(false);
            return;
        }

        if(!getBusinessObject().isValid())
        {
            setErrorMessage("Scene volunteer object is not Valid");
            setWasSuccessful(false);
            return;
        }
        
        String queryString;
        
        if(volunteer().isNew())
        {
            queryString = buildInsertQueryString();
            
            try
            {
                getDatabase().executeInsert(queryString);
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
                getDatabase().executeUpdate(queryString);
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
                "INSERT into t_volunteer("
                + "vol_emailaddress,"
                + "vol_firstname,"
                + "vol_surname,"
                + "vol_phone,"
                + "vol_password)" 
                + "VALUES ('"
                + volunteer().getEmail() + "','"
                + volunteer().getFirstName() + "','"
                + volunteer().getLastName()+ "','"
                + volunteer().getPhone() + "','"
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
    
    public void buildInsertAvailabilityString()
    {
        getDatabase().addCommand("delete from t_volunteeravailablility where vol_emailaddress = '" + volunteer.getEmail() + "';" );

        
        for (TimeInterval currAvail: volunteer.getAvailability())
        {
                getDatabase().addCommand(
                "INSERT into t_volunteeravailability("
                + "vav_emailaddress_volunteer,"
                + "vav_availability_start," 
                + "vav_availability_end)"
                  
                + "VALUES(" 
                + volunteer.getEmail() + "','"
                + currAvail.startIsoDate() + "','"
                + currAvail.endIsoDate() + "');");
        }
    }          
        /*
        String returnString =
                "INSERT into t_volunteeravailability("
                + "vav_emailaddress_volunteer = '" + volunteer().getAvailability() + "',"
                + "vav_availability_start," + 
                + "vav_availability_end)"
        
                + "WHERE "
                + "(vav_emailaddress_volunteer = '" + volunteer().getEmail() + "')";
        return returnString;
        */
        
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
        if(getBusinessObject() == null)
            return null;
        
        return (Volunteer)getBusinessObject();
    }
    
    // Static Methods
    

}

