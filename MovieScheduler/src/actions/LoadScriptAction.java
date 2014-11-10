package actions;

import businessobjects.BusinessObjectList;
import businessobjects.Equipment;
import businessobjects.Scene;
import businessobjects.Schedule;
import businessobjects.SceneFilmingDate;
import database.Database;
import businessobjects.Script;
import businessobjects.TimeInterval;
import businessobjects.Volunteer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A class which represents an action to load all the data from the database
 * into a script object
 */
public class LoadScriptAction extends BaseAction {

    public LoadScriptAction(Database database) {
        super(database);
    }

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
