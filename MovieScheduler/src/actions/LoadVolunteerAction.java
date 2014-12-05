/*

 */
package actions;

import businessobjects.BusinessObjectList;
import businessobjects.TimeInterval;
import businessobjects.Volunteer;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * A class which represents an action to load the data of a volunteer from the
 * database corresponding to an e-mail into a volunteer object
 *
 * @author Matthew Galbraith, John Mason
 */
public class LoadVolunteerAction extends BaseAction {

    private final String volunteerEmail;

    // Constructor
    /**
     * Creates an instance of a LoadVolunteerAction which will act on the
     * provided database
     *
     * @param database::Database ~ The Database on which to perform the Action.
     * @param volunteerEmail::String ~ The email of the volunteer to be loaded
     * from the database.
     */
    public LoadVolunteerAction(String volunteerEmail, Database database) {
        super(database);
        this.volunteerEmail = volunteerEmail;
    }

    // Protected Methods
    /**
     * Runs the loadVolunteerAction
     *
     * @Postconds: - wasSuccessful() == The success state of the action -
     * errorMessage() == Any errors encountered by the action if failed -
     * businessObject() == The loaded volunteer - businessObject() == null if
     * the database contained no volunteer for the provided email
     */
    @Override
    protected void runImplementation() {
        //TEST
        Volunteer loadedVolunteer = null;
        boolean loadSuccess = false;
        try {

            loadedVolunteer = loadVolunteerFromDatabase();
            loadSuccess = true;
        } catch (SQLException ex) {
            setErrorMessage(ex.getMessage());
        }
        setBusinessObject(loadedVolunteer);
        setWasSuccessful(loadSuccess);
    }

    // Private Methods

    /**
     * Performs the action on the database of loading the Volunteer
     *
     * @return The Volunteer info loaded from the database
     * @throws SQLException
     */
    private Volunteer loadVolunteerFromDatabase() throws SQLException {
        Volunteer returnVolunteer = null;
        BusinessObjectList<TimeInterval> volunteerAvailabilities = loadVolunteerAvailabilities(volunteerEmail);
        ResultSet selectResults = null;
        try {
            selectResults = getDatabase().executeSelect(buildVolunteerQueryString());
            while (selectResults.next()) {
                String firstName = selectResults.getString(1);

                String lastName = selectResults.getString(2);
                String phone = selectResults.getString(3);
                returnVolunteer = new Volunteer(firstName, lastName, volunteerEmail, phone);
                returnVolunteer.setAvailabilties(volunteerAvailabilities);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (selectResults != null) {
                selectResults.close();
            }
        }

        return returnVolunteer;
    }

    /**
     * Builds the query string for the volunteers contact information
     *
     * @return the volunteer contact information query string
     */
    private String buildVolunteerQueryString() {
        String returnString
                = "SELECT "
                + "vol_firstname, vol_surname, vol_phone "
                + "FROM t_volunteer "
                + "WHERE vol_emailaddress = '" + volunteerEmail + "';";
        return returnString;
    }

    /**
     * Returns a list of the Availabilities for the Volunteer with the provided
     * email address
     *
     * @param volunteerEmail::String ~ The email address of the Volunteer whose
     * availabilities are to be loaded from the Database
     * @return A list of the Availabilities for the Volunteer with the provided
     * email address
     * @throws SQLException
     */
    private BusinessObjectList<TimeInterval> loadVolunteerAvailabilities(
            String volunteerEmail)
            throws SQLException {
        String selectAvailabilityQuery
                = "SELECT "
                + "vav_availability_start, vav_availability_end "
                + "FROM t_volunteeravailability "
                + "WHERE vav_emailaddress_volunteer = '" + volunteerEmail + "';";

        BusinessObjectList<TimeInterval> availabilityList
                = new BusinessObjectList<>();
        ResultSet selectResults = null;

        try {
            selectResults = getDatabase().executeSelect(selectAvailabilityQuery);

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
            throw ex;
        } finally {
            if (selectResults != null) {
                selectResults.close();
            }
        }
        return availabilityList;
    }

}
