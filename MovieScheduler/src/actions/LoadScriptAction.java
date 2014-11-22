package actions;

import database.Database;
import businessobjects.Script;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class which represents an action to load all the data from the database
 * into a script object
 */
public class LoadScriptAction extends BaseAction
{

    // Constructor
    /**
     * Creates an instance of a LoadScriptAction which will act of the provided
     * database
     *
     * @param database::Database ~ The Database on which to perform the Action.
     */
    public LoadScriptAction(Database database)
    {
        super(database);
    }

    // Protected Methods
    /**
     * Runs the loadScriptAction
     *
     * @Postconds: - wasSuccessful() == The success state of the action -
     * errorMessage() == Any errors encountered by the action if failed -
     * businessObject() == The loaded script - businessObject() == null if the
     * database contained no script
     */
    @Override
    protected void runImplementation()
    {
        Script loadedScript = null;
        boolean totalSuccess = false;
        try
        {
            loadedScript = loadBasicScriptFromDatabase();
            totalSuccess = true;

            if (loadedScript != null)
            {
                if (totalSuccess)
                {
                    totalSuccess &= populateVolunteers(loadedScript);
                }

                if (totalSuccess)
                {
                    totalSuccess &= populateEquipment(loadedScript);
                }

                if (totalSuccess)
                {
                    totalSuccess &= populateScenes(loadedScript);
                }

                if (totalSuccess)
                {
                    totalSuccess &= populateSchedule(loadedScript);
                }
            }
        } catch (SQLException ex)
        {
            setErrorMessage(ex.getMessage());
        }

        setWasSuccessful(totalSuccess);
        setBusinessObject(loadedScript);
    }

    // Private Methods
    /**
     * Performs the action on the database of loading the Script
     *
     * @return The Script loaded from the database
     * @throws SQLException
     */
    private Script loadBasicScriptFromDatabase() throws SQLException
    {
        String selectScriptQuery
                = "SELECT "
                + "sct_scriptname, sct_scriptsynopsis "
                + "FROM t_script;";

        Script returnScript = null;
        ResultSet selectResults = null;
        try
        {
            selectResults = getDatabase().executeSelect(selectScriptQuery);

            while (selectResults.next())
            {
                String scriptName = selectResults.getString(1);
                //String scriptSynopsis = selectResults.getString(1);
                returnScript = new Script(scriptName);
                returnScript.setIsNew(false);
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
        return returnScript;
    }

    private boolean populateVolunteers(Script script)
    {
        PopulateScriptVolunteersAction populateVolunteersAction
                = new PopulateScriptVolunteersAction(getDatabase(), script);

        populateVolunteersAction.run();
        if (!populateVolunteersAction.wasSuccessful())
        {
            setErrorMessage("Error populating volunteers into script: "
                    + populateVolunteersAction.lastErrorMessage());
        }

        return populateVolunteersAction.wasSuccessful();
    }

    private boolean populateEquipment(Script script)
    {
        PopulateScriptEquipmentAction populateEquipmentAction
                = new PopulateScriptEquipmentAction(getDatabase(), script);

        populateEquipmentAction.run();
        if (!populateEquipmentAction.wasSuccessful())
        {
            setErrorMessage("Error populating equipment into script: "
                    + populateEquipmentAction.lastErrorMessage());
        }

        return populateEquipmentAction.wasSuccessful();
    }

    private boolean populateScenes(Script script)
    {
        PopulateScriptScenesAction populateScenesAction
                = new PopulateScriptScenesAction(getDatabase(), script);

        populateScenesAction.run();
        if (!populateScenesAction.wasSuccessful())
        {
            setErrorMessage("Error populating scenes into script: "
                    + populateScenesAction.lastErrorMessage());
        }

        return populateScenesAction.wasSuccessful();
    }

    private boolean populateSchedule(Script script)
    {
        PopulateScriptScheduleAction populateScheduleAction
                = new PopulateScriptScheduleAction(getDatabase(), script);

        populateScheduleAction.run();
        if (!populateScheduleAction.wasSuccessful())
        {
            setErrorMessage("Error populating schedule into script: "
                    + populateScheduleAction.lastErrorMessage());
        }

        return populateScheduleAction.wasSuccessful();
    }
}
