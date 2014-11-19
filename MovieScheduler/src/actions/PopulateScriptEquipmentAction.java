package actions;

import businessobjects.BusinessObjectList;
import businessobjects.Equipment;
import businessobjects.Script;
import businessobjects.TimeInterval;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * Class which represents an Action which takes a Script and loads in the
 * Equipment associated with it.
 * @author Iain Workman
 */
public class PopulateScriptEquipmentAction extends BaseAction
{

    //Constructor
    /**
     * Creates an instance of a PopulateScriptEquipmentAction which will act on
     * the provided database, loading Equipment info into the provided Script.
     *
     * @param database::Database ~ The Database to load the data from
     * @param script::Script ~ The Script in which to place the loaded Equipment
     * data.
     */
    public PopulateScriptEquipmentAction(Database database, Script script)
    {
        super(database);
        setBusinessObject(script);
    }

    // Protected Methods
    /**
     * Runs the Action of loading the Equipment associated with the Script from
     * the database into the Script.
     *
     * @postconds: - wasSuccessful() == The success state of the action -
     * errorMessage() == Any errors encountered by the action if failed -
     * businessObject() == The script with populated Equipment Info.
     */
    @Override
    protected void runImplementation()
    {
        if (getBusinessObject() == null)
        {
            setErrorMessage("Can't populate data for a null script.");
            setWasSuccessful(false);
            return;
        }

        if (!(getBusinessObject() instanceof Script))
        {
            setErrorMessage("Can't populate data into an object which is not"
                    + " a script.");
        }

        Script scriptToPopulate = (Script) getBusinessObject();

        try
        {
            scriptToPopulate.setEquipment(loadEquipment());
            setWasSuccessful(true);
        } catch (SQLException ex)
        {
            setErrorMessage(ex.getMessage());
            setWasSuccessful(false);
        }
    }

    //Private methods
    /**
     * Loads the master list of Equipment from the database
     *
     * @return A list of all the Equipment in the database
     * @throws SQLException
     */
    private BusinessObjectList<Equipment> loadEquipment() throws SQLException
    {
        String selectEquipmentQuery
                = "SELECT "
                + "eqp_equipmentname, eqp_firstname_owner, eqp_surname_owner,"
                + " eqp_emailaddress_owner "
                + "FROM t_equipment;";

        BusinessObjectList<Equipment> equipmentList
                = new BusinessObjectList<>();
        ResultSet selectResults = null;

        try
        {
            selectResults = getDatabase().executeSelect(selectEquipmentQuery);

            while (selectResults.next())
            {
                String equipmentName = selectResults.getString(1);
                String firstName = selectResults.getString(2);
                String surname = selectResults.getString(3);
                String email = selectResults.getString(4);

                BusinessObjectList<TimeInterval> availabilities
                        = loadEquipmentAvailabilities(equipmentName);

                Equipment newEquipment = new Equipment(equipmentName,
                        0, false, 0);

                newEquipment.setAvailabilities(availabilities);
                equipmentList.add(newEquipment);
            }
        } catch (SQLException ex)
        {
            throw ex;
        } finally
        {
            if (selectResults != null)
            {
                selectResults.close();
            }
        }

        return equipmentList;
    }

    /**
     * Performs the action of the database of loading an individual Equipment
     * availability times.
     *
     * @param equipmentName::String ~ The name of the piece of Equipment whose
     * Availabilities are to be loaded.
     * @return A list of all the time intervals which a piece of Equipment is
     * available for
     * @throws SQLException
     */
    private BusinessObjectList<TimeInterval> loadEquipmentAvailabilities(
            String equipmentName)
            throws SQLException
    {
        String selectAvailabilityQuery
                = "SELECT "
                + "eav_availability_start, eav_availability_end "
                + "FROM t_equipmentavailability "
                + "WHERE eav_equipmentname = '" + equipmentName + "';";

        BusinessObjectList<TimeInterval> availabilityList
                = new BusinessObjectList<>();
        ResultSet selectResults = null;

        try
        {
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
        } catch (SQLException ex)
        {
            throw ex;
        } finally
        {
            if (selectResults != null)
            {
                selectResults.close();
            }
        }

        return availabilityList;
    }
}
