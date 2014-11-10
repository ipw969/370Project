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
 *
 * @author iain
 */
public class PopulateScriptVolunteersAction extends BaseAction{

    public PopulateScriptVolunteersAction(Database database, Script script) {
        super(database);
        setBusinessObject(script);
    }

    @Override
    protected void runImplementation() {
        if(businessObject() == null)
        {
            setErrorMessage("Can't populate data for a null script.");
            setWasSuccessful(false);
            return;
        }
        
        if(!(businessObject() instanceof Script))
        {
            setErrorMessage("Can't populate data into an object which is not"
                    + " a script.");
        }
        
        Script scriptToPopulate = (Script)businessObject();
        
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
            selectResults = database().executeSelect(selectVolunteersQuery);
            
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
            selectResults = database().executeSelect(selectAvailabilityQuery);
            
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
