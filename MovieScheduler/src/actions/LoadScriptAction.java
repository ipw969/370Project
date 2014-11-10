package actions;

import database.Database;
import businessobjects.Script;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class which represents an action to load all the data from the database
 * into a script object
 */
public class LoadScriptAction extends BaseAction {

    // Constructor
    /**
     * Creates an instance of a LoadScriptAction which will act of the provided
     * database
     * @param database::Database ~ The Database on which to perform the Action. 
     */
    public LoadScriptAction(Database database) {
        super(database);
    }

    // Protected Methods
    /**
     * Runs the loadScriptAction
     * @Postconds:
     *  - wasSuccessful() == The success state of the action
     *  - errorMessage() == Any errors encountered by the action if failed
     *  - businessObject() == The loaded script
     *  - businessObject() == null if the database contained no script
     */
    @Override
    protected void runImplementation() {
        Script loadedScript = null;
        try{
            loadedScript = loadBasicScriptFromDatabase();
            setWasSuccessful(true);
        }
        catch(SQLException ex)
        {
            setErrorMessage(ex.getMessage());
            setWasSuccessful(false);
        }
        
        setBusinessObject(loadedScript);
    }
    
    // Private Methods
    /**
     * Performs the action on the database of loading the Script
     * @return The Script loaded from the database
     * @throws SQLException 
     */
    private Script loadBasicScriptFromDatabase() throws SQLException
    {
        String selectScriptQuery = 
        "SELECT " + 
        "sct_scriptname, sct_scriptsynopsis " +
        "FROM t_script;";

        Script returnScript = null;
        ResultSet selectResults = null;
        try{  
            selectResults = database().executeSelect(selectScriptQuery);

            while (selectResults.next())
            {
                String scriptName = selectResults.getString(1);
                //String scriptSynopsis = selectResults.getString(1);
                returnScript = new Script(scriptName);
            }
        } catch(SQLException ex)
        {    
            throw ex;
        }
        finally{
            if(selectResults != null)
                selectResults.close();
        }
        return returnScript;
    }
}
