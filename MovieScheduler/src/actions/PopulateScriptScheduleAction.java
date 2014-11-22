/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import businessobjects.Scene;
import businessobjects.SceneFilmingDate;
import businessobjects.Schedule;
import businessobjects.Script;
import businessobjects.TimeInterval;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * A class representing an Action which populates the Provided Script with
 * the filming Schedule, loaded from the provided Database.
 */
public class PopulateScriptScheduleAction extends BaseAction {

    // Constructor
    /**
     * 
     * @param database
     * @param script 
     */
    public PopulateScriptScheduleAction(Database database, Script script) {
        super(database);
        setBusinessObject(script);
    }

    // Protected Methods
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
            scriptToPopulate.setSchedule(loadSchedule());
            setWasSuccessful(true);
        }
        catch (SQLException ex)
        {
            setErrorMessage(ex.getMessage());
            setWasSuccessful(false);
        }
    }
    
    // Private Methods
    /**
     * The Script whose Schedule is to be populated by the Action.
     * @return 
     */
    private Script script()
    {
        if (getBusinessObject() == null || !(getBusinessObject() instanceof Script))
            return null;
        
        return (Script)getBusinessObject();
    }
    
    /**
     * Loads the Schedule from the Database.
     * @return The Schedule loaded from the Database.
     * @throws SQLException 
     */
    private Schedule loadSchedule() 
            throws SQLException
    {
        String selectScheduleQuery = 
        "SELECT " + 
        "sch_scheduledatetime_start, sch_scheduledatetime_end, sch_scenename " +
        "FROM t_schedule;";

        Schedule returnSchedule = 
                new Schedule();
        ResultSet selectResults = null;
        
        try{
            selectResults = getDatabase().executeSelect(selectScheduleQuery);
            
                while (selectResults.next())
                {
                    Timestamp startDate = selectResults.getTimestamp(1);
                    Timestamp endDate = selectResults.getTimestamp(2);

                    GregorianCalendar startCalendar = new GregorianCalendar();
                    startCalendar.setTime(startDate);
                
                    GregorianCalendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(endDate);
                    
                    String sceneName = selectResults.getString(3);
                    
                    SceneFilmingDate currentFilmingDate = null;
                    for(Scene currentScene : script().scenes())
                    {
                        if(currentScene.getName().compareTo(sceneName) == 0)
                        {
                            currentFilmingDate = new SceneFilmingDate();
                            currentFilmingDate.setSceneShootingInterval(
                                new TimeInterval(startCalendar, endCalendar));
                            currentFilmingDate.setScene(currentScene);
                            currentFilmingDate.setIsNew(false);
                            currentFilmingDate.setHasChanged(false);
                        }
                    }
                    if(currentFilmingDate != null)
                        returnSchedule.add(currentFilmingDate);
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
        return returnSchedule;
    }
    
}
