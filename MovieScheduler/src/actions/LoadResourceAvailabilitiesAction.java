package actions;

import businessobjects.BusinessObjectList;
import businessobjects.Equipment;
import businessobjects.TimeInterval;
import businessobjects.Volunteer;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * A class representing an Action to load the provided resource's (Volunteer or
 * Equipment) availabilities from the database.
 * 
 */
public class LoadResourceAvailabilitiesAction extends BaseAction {
    // Constructor
    public LoadResourceAvailabilitiesAction(Database database)
    {
        super(database);
    }

    /**
     * Runs the action of loading all the availabilities for the provided
     * resource from the database
     */
    @Override
    protected void runImplementation() {

        String queryString = getQueryString();
        
        if(!lastErrorMessage().isEmpty())
            return;
        
        BusinessObjectList<TimeInterval> availabilityList
                = new BusinessObjectList<>();
        ResultSet selectResults = null;

        try {
            selectResults = getDatabase().executeSelect(queryString);

            while (selectResults.next()) {
                Timestamp startDate = selectResults.getTimestamp(1);
                Timestamp endDate = selectResults.getTimestamp(2);
                GregorianCalendar startCalendar = new GregorianCalendar();
                startCalendar.setTime(startDate);

                GregorianCalendar endCalendar = new GregorianCalendar();
                endCalendar.setTime(endDate);

                TimeInterval availability = new TimeInterval(startCalendar, endCalendar);

                availabilityList.add(availability);
            }
        } catch (SQLException ex) {
            setWasSuccessful(false);
            setErrorMessage("Could not populate avaiabilites, with messge: "
                    + ex.getMessage());
        } finally {
            if (selectResults != null) {
                try{
                selectResults.close();
                } catch(SQLException ex)
                {
                    setWasSuccessful(false);
                    setErrorMessage("Could not close the result set with mesasge " +
                            ex.getMessage());
                }
            }
        }

        
        populateBusinessObjectAvailabilities(availabilityList);
        setWasSuccessful(true);
    }
    
    
    // Private Methods
    private String getQueryString()
    {
        if(getBusinessObject() == null)
        {
            this.setErrorMessage("Cannot load the availabilities of a null resource");
            this.setWasSuccessful(false);
            return null;
        }
        
        if(!(getBusinessObject() instanceof Equipment) || !(getBusinessObject() instanceof Volunteer))
        {
            this.setErrorMessage("Cannot load availabilities of a BusinessObject "
                    + "which is not a Volunteer or Equipment");
            this.setWasSuccessful(false);
            return null;
        }
        
        String tableName;
        String prefix;
        String whereClause;
        
        if(getBusinessObject() instanceof Equipment)
        {
            Equipment equipment = (Equipment)getBusinessObject();
            tableName = "t_equipmentavailability";
            prefix = "eav_";
            whereClause = prefix + "equipmentname = '" + equipment.getEquipmentName() + "'";
        }
        else
        {
            Volunteer volunteer = (Volunteer)getBusinessObject();
            tableName = "t_volunteeravailability";
            prefix = "vav_";
            whereClause = prefix + "email = '" + volunteer.getEmail() + "'";
        }
        
        String queryString = "SELECT " 
                + prefix + "availability_start, " 
                + prefix + "availability_end "
                + "FROM " + tableName
                + " WHERE " + whereClause + ";";
        
        return queryString;
    }
    
    private void populateBusinessObjectAvailabilities(
            BusinessObjectList<TimeInterval> availabilities)
    {
        if(getBusinessObject() instanceof Volunteer)
        {
            Volunteer volunteer = (Volunteer)getBusinessObject();
            for(TimeInterval currentAvailability : availabilities)
                volunteer.addAvailability(currentAvailability);
        }
        else
        {
            Equipment equipment = (Equipment)getBusinessObject();
            for(TimeInterval currentAvailability : availabilities)
                equipment.getAvailability().add(currentAvailability);
        }
    }
}
