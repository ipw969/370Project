package actions;

import database.Database;
import businessobjects.*;
import java.sql.SQLException;
import businessobjects.TimeInterval;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * an action class to save a volunteer object to the database
 * @author John Mason
 */
public class SaveVolunteerAction extends BaseAction {

    //volunteerToReplace is the email address of the volunteer that needs to be edited
    private String volunteerToReplace;
    private Volunteer volunteer;

    //constructor for editing a volunteer
    public SaveVolunteerAction(Database database, Volunteer volunteer, String volunteerToReplace) {
        super(database);
        this.volunteerToReplace = volunteerToReplace;
        this.volunteer = volunteer;
        setBusinessObject(volunteer);
    }

    //constructor for creating a new volunteer in the database
    public SaveVolunteerAction(Database database, Volunteer volunteer) {
        super(database);
        this.volunteer = volunteer;
        setBusinessObject(volunteer);
    }

    @Override
    protected void runImplementation() {

        getDatabase().clearCommandList();
        if (volunteerToReplace != null) {
            getDatabase().addCommand("delete from t_scenevolunteer where snv_emailaddress_volunteer = '" + volunteerToReplace + "';");
            getDatabase().addCommand("delete from t_volunteeravailability where vav_emailaddress_volunteer = '" + volunteerToReplace + "';");
            getDatabase().addCommand("delete from t_volunteer where vol_emailaddress = '" + volunteerToReplace + "';");
        }
        if (getDatabase() == null) {
            setErrorMessage("Database is null");
            setWasSuccessful(false);
            return;
        }

        if (!getBusinessObject().isValid()) {
            setErrorMessage("Scene volunteer object is not Valid");
            setWasSuccessful(false);
            return;
        }

        String queryString;

        if (volunteer().isNew()) {
            queryString = getInsertQueryString();

            getDatabase().addCommand(queryString);
            getDatabase().addCommand(getUpdateQueryString());
            getDatabase().addCommand(getInsertAvailabilityString());

            try {
                getDatabase().executeCommandList();
                this.setWasSuccessful(true);
            } catch (SQLException ex) {
                this.setErrorMessage("Saving the volunteer to the database was a failure.");
                this.setWasSuccessful(false);
            }

        }

    }    // Private Methods

    /**
     * Creates an SQL UPDATE string for the current Volunteer
     *
     * @return An SQL UPDATE string for the current Volunteer
     */
    private String getInsertQueryString() {
        String returnString
                = "INSERT into t_volunteer("
                + "vol_emailaddress,"
                + "vol_firstname,"
                + "vol_surname,"
                + "vol_phone,"
                + "vol_password)"
                + " VALUES('"
                + volunteer().getEmail() + "','"
                + volunteer().getFirstName() + "','"
                + volunteer().getLastName() + "','"
                + volunteer().getPhone() + "','"
                + volunteer().getPhone() + "');";

        return returnString;

    }

    private String getUpdateQueryString() {
        String returnString
                = "UPDATE t_volunteer"
                + " SET "
                + "vol_firstname = '" + volunteer().getFirstName() + "',"
                + "vol_surname = '" + volunteer().getLastName() + "',"
                + "vol_phone = '" + volunteer().getPhone() + "'"
                + "WHERE "
                + "(vol_emailaddress = '" + volunteer().getEmail() + "')";
        return returnString;
    }

    private String getInsertAvailabilityString() {
        String insertAvailabilityString = "";
        for (TimeInterval currAvail : volunteer.getAvailability()) 
        {
            insertAvailabilityString = insertAvailabilityString + " "  
                    + "INSERT into t_volunteeravailability("
                    + "vav_emailaddress_volunteer,"
                    + "vav_availability_start,"
                    + "vav_availability_end)"
                    + " VALUES('"
                    + volunteer.getEmail() + "','"
                    + currAvail.getStartIsoDate() + "','"
                    + currAvail.getEndIsoDate() + "');";
        }
        return insertAvailabilityString;
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
     *
     * @return The Volunteer associated with this action
     */
    private Volunteer volunteer() {
        if (getBusinessObject() == null) {
            return null;
        }

        return (Volunteer) getBusinessObject();
    }

    // Static Methods
}
