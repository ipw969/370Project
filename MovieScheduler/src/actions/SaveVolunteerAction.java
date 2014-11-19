package actions;

import database.Database;
import businessobjects.*;
import java.sql.SQLException;

/**
 *
 * @author John Mason
 */
public class SaveVolunteerAction extends BaseAction
{

    // Constructor
    public SaveVolunteerAction(Database database, Volunteer volunteer)
    {
        super(database);
        setBusinessObject(volunteer);
    }

    // Protected Methods
    @Override
    protected void runImplementation()
    {
        if (getDatabase() == null)
        {
            setErrorMessage("Database is null");
            setWasSuccessful(false);
            return;
        }

        if (!getBusinessObject().isValid())
        {
            setErrorMessage("Scene Filming Date is not Valid");
            setWasSuccessful(false);
            return;
        }

        String queryString;

        if (getVolunteer().isNew())
        {
            queryString = buildInsertQueryString();

            try
            {
                getDatabase().executeInsert(queryString);
                setWasSuccessful(true);
                getVolunteer().setHasChanged(false);
            } catch (SQLException ex)
            {
                setWasSuccessful(false);
                setErrorMessage(ex.getMessage());
            }
        } else
        {
            queryString = buildUpdateQueryString();

            try
            {
                getDatabase().executeUpdate(queryString);
                setWasSuccessful(true);
                getVolunteer().setHasChanged(false);
            } catch (SQLException ex)
            {
                setWasSuccessful(false);
                setErrorMessage(ex.getMessage());
            }
        }

    }
    // Private Methods

    /**
     * Creates an SQL UPDATE string for the current Volunteer
     *
     * @return An SQL UPDATE string for the current Volunteer
     */
    private String buildInsertQueryString()
    {
        String returnString
                = "INSERT into t_volunteer"
                + "(vol_emailaddress, "
                + "vol_firstname,"
                + "vol_surname,"
                + "vol_phone )"
                + "VALUES "
                + "('" + getVolunteer().getEmail() + "','"
                + getVolunteer().getFirstName() + "','"
                + getVolunteer().getLastName() + "','"
                + getVolunteer().getPhone() + "');";

        return returnString;

    }

    private String buildUpdateQueryString()
    {
        String returnString
                = "UPDATE t_volunteer"
                + "SET"
                + "vol_firstname ='" + getVolunteer().getFirstName() + "',"
                + "vol_surname ='" + getVolunteer().getLastName() + "',"
                + "vol_phone = '" + getVolunteer().getPhone() + "'"
                + "WHERE "
                + "(vol_emailaddress = '" + getVolunteer().getEmail() + "')";
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
     *
     * @return The Volunteer associated with this action
     */
    private Volunteer getVolunteer()
    {
        if (getBusinessObject() == null)
        {
            return null;
        }

        return (Volunteer) getBusinessObject();
    }

    // Static Methods
}
