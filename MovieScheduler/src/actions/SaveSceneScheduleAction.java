/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;
import database.Database;
import businessobjects.SceneSchedule;
import businessobjects.Scene;
import java.sql.SQLException;
import businessobjects.TimeInterval;
import java.util.Calendar;
import java.util.GregorianCalendar;
import database.JdbcDatabase;
/**
 *
 * A class which performs the action of saving a scene schedule to the database
 */
public class SaveSceneScheduleAction extends BaseAction {
    
    // Constructor
    /**
     * Creates an instance of a SaveSceneScheduleAction, prepared to run
     * the provided SceneSchedule on the provided Database
     * @param database::Database ~ The Database on which to run the action
     * @param sceneSchedule::SceneSchedule ~ The SceneSchedule that will be 
     * saved.
     */
    public SaveSceneScheduleAction(Database database, 
                                   SceneSchedule sceneSchedule)
    {
        super(database);
        setBusinessObject(sceneSchedule);
    }
    
    /**
     * Saves the SceneSchedule associated with this action to the database,
     * provided that:
     * - The database passed is valid and a connection can be reached
     * - The SceneSchedule is valid
     */
    @Override
    protected void runImplementation()
    {
        if(database() == null)
        {
            setErrorMessage("Database is null");
            setWasSuccessful(false);
            return;
        }
        if(sceneSchedule() == null)
        {
            setErrorMessage("Scene Schedule is null");
            setWasSuccessful(false);
            return;
        }
        
        if(!businessObject().isValid())
        {
            setErrorMessage("Scene Schedule is not Valid");
            setWasSuccessful(false);
            return;
        }
        
        String queryString = null;
        
        if(sceneSchedule().isNew())
        {
            queryString = buildInsertQueryString();
            
            try
            {
                database().executeInsert(queryString);
                setWasSuccessful(true);
                sceneSchedule().setHasChanged(false);
            }
            catch(SQLException ex)
            {
                setWasSuccessful(false);
                setErrorMessage(ex.getMessage());
            }
        }
        else
        {
            queryString = buildUpdateQueryString();
            
            try
            {
                database().executeUpdate(queryString);
                setWasSuccessful(true);
                sceneSchedule().setHasChanged(false);
            }
            catch(SQLException ex)
            {
                setWasSuccessful(false);
                setErrorMessage(ex.getMessage());
            }
        }
       
        
    }
    
    // Private Methods
    
    /**
     * Creates an SQL UPDATE string for the current SceneSchedule
     * @return An SQL UPDATE string for the current SceneSchedule
     */
    private String buildUpdateQueryString()
    {
        String returnString = 
                "UPDATE t_schedule "
                + "SET"
                + "sch_scheduledatetime_start = '" + sceneSchedule().sceneShootingInterval().startISODate() + "', "
                + "sch_scheduledatetime_end = '" + sceneSchedule().sceneShootingInterval().endISODate() + "', "
                + "WHERE "
                + "( sch_scenename = '" + sceneSchedule().scene().name() + "');";
        
        return returnString;
    }
    
    /**
     * Creates an SQL INSERT string for the current SceneSchedule
     * @return An SQL INSERT string for the current SceneSchedule
     */
    private String buildInsertQueryString()
    {
        
        System.out.println(sceneSchedule().sceneShootingInterval().startISODate());
        String returnString = 
                "INSERT INTO t_schedule "
                + "( sch_scheduledatetime_start, "
                + "sch_scheduledatetime_end, "
                + "sch_scenename ) "
                + "VALUES "
                + "('" + sceneSchedule().sceneShootingInterval().startISODate()
                + "', '" + sceneSchedule().sceneShootingInterval().endISODate()
                + "', '" + sceneSchedule().scene().name() + "');";
        
        return returnString;
    }
    
    /**
     * Returns the SceneSchedule associated with this action
     * @return The SceneSchedule associated with this action
     */
    private SceneSchedule sceneSchedule()
    {
        if(businessObject() == null)
            return null;
        
        return (SceneSchedule)businessObject();
    }
    
    // Static Methods
    
    /**
     * Simple demonstration method. NOTE: THIS IS NOT A UNIT TEST.
     * @param args 
     */
    public static void main(String[] args)
    {
        Scene testScene1 = new Scene("Test Scene 1", "");
        SceneSchedule testSchedule1 = new SceneSchedule();
        testSchedule1.setScene(testScene1);
        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();
        end.add(Calendar.MONDAY, 1);
    
        TimeInterval testSceneShootingInterval = new TimeInterval(
        start, end);
        testSchedule1.setSceneShootingInterval(testSceneShootingInterval);
        
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Could not load database driver with "
                        + "message: " + ex.toString());
            return;
        }
        
        JdbcDatabase testDatabase = null;
        try{
            testDatabase = new JdbcDatabase(
                "jdbc:postgresql://edjo.usask.ca/cmpt370_group06",
                "cmpt370_group06",
                "mu5jd8m1ae");
        }
        catch (SQLException ex)
        {
            System.out.println("Failed to connection to db with message: "
                + ex.getMessage());
            return;
        }
        
        SaveSceneScheduleAction testAction = new SaveSceneScheduleAction(testDatabase, testSchedule1);
        testAction.run();
        if(testAction.wasSuccessful())
            System.out.println("Save was reported successful");
        else
            System.out.println("Save failed with message " + testAction.lastErrorMessage());
    } 
}


