/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import businessobjects.BusinessObjectList;
import businessobjects.Script;
import businessobjects.TimeInterval;
import businessobjects.Volunteer;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * A class representing an Action which loads Volunteer data from the Database
 * into the provided Script.
 */
public class PopulateScriptVolunteersAction extends BaseAction{
    // Constructor
    /**
     * Creates an instance of a PopulateScriptVolunteersAction which will
     * populate a list of Volunteers from the provided Database into the 
     * provided Script
     * @param database::Database ~ The Database from which to load the 
     * Volunteers
     * @param script::Script ~ The Script to load the Volunteers into 
     */
    public PopulateScriptVolunteersAction(Database database, Script script) {
        super(database);
        setBusinessObject(script);
    }
    
    // Protected Methods
    /**
     * Runs the Action of loading the Volunteers list from the Database into
     * the associated Script.
     */
    @Override
    protected void runImplementation() {
        if(getBusinessObject() == null)
        {
            setErrorMessage("Can't populate data for a null script.");
            setWasSuccessful(false);
            return;
        }
        
        if(!(getBusinessObject() instanceof Script))
        {
            setErrorMessage("Can't populate data into an object which is not"
                    + " a script.");
        }
        
        Script scriptToPopulate = (Script)getBusinessObject();
        
        try{
            scriptToPopulate.setVolunteers(loadVolunteers());
            setWasSuccessful(true);
        }
        catch(SQLException ex)
        {
            setErrorMessage(ex.getMessage());
            setWasSuccessful(false);
        }
    }
    
    // Private Methods
    /**
     * Loads the master list of Volunteers from the Database
     * @return The List of loaded Volunteers from the Database
     * @throws SQLException 
     */
    private BusinessObjectList<Volunteer> loadVolunteers()
            throws SQLException
    {
        String selectVolunteersQuery = 
        "SELECT " + 
        "vol_emailaddress, vol_firstname, vol_surname, vol_phone " +
        "FROM t_volunteer;";

        BusinessObjectList<Volunteer> volunteerList = 
                new BusinessObjectList<>();
        ResultSet selectResults = null;
        
        try{
            selectResults = getDatabase().executeSelect(selectVolunteersQuery);
            
                while (selectResults.next())
                {
                    String email = selectResults.getString(1);
                    String firstName = selectResults.getString(2);
                    String surname = selectResults.getString(3);
                    String phone = selectResults.getString(4);
                
                    BusinessObjectList<TimeInterval> availabilities = 
                            loadVolunteerAvailabilities(email);
                
                    Volunteer newVolunteer = new Volunteer(firstName,
                        surname, email, phone, availabilities);
                    
                    newVolunteer.setIsNew(false);
                    volunteerList.add(newVolunteer);
                }
        } catch(SQLException ex)
        {
            throw ex;
        }
        finally
        {
            if(selectResults != null)
                selectResults.close();
        }
        
        return volunteerList;
    }
    
    /**
     * Returns a list of the Availabilities for the Volunteer with the provided 
     * email address 
     * @param volunteerEmail::String ~ The email address of the Volunteer whose
     * availabilities are to be loaded from the Database
     * @return A list of the Availabilities for the Volunteer with the provided 
     * email address
     * @throws SQLException 
     */
    private BusinessObjectList<TimeInterval> loadVolunteerAvailabilities(
                                            String volunteerEmail)
            throws SQLException
    {
        String selectAvailabilityQuery = 
        "SELECT " + 
        "vav_availability_start, vav_availability_end " +
        "FROM t_volunteeravailability " +
        "WHERE vav_emailaddress_volunteer = '" + volunteerEmail + "';";

        BusinessObjectList<TimeInterval> availabilityList = 
                new BusinessObjectList<>();
        ResultSet selectResults = null;
        
        try{
            selectResults = getDatabase().executeSelect(selectAvailabilityQuery);
            
                while (selectResults.next())
                {
                    Timestamp startDate = selectResults.getTimestamp(1);
                    Timestamp endDate = selectResults.getTimestamp(2);
                    GregorianCalendar startCalendar = new GregorianCalendar();
                    startCalendar.setTime(startDate);
                    
                    GregorianCalendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(endDate);

                    TimeInterval availability = new TimeInterval(startCalendar, endCalendar);
                
                    availabilityList.add(availability);
                }
        } catch(SQLException ex)
        {
            throw ex;
        }
        finally
        {
            if(selectResults != null)
                selectResults.close();
        }
        
        return availabilityList;
    }
}
