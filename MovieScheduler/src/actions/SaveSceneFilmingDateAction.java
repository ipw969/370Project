/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;
import database.Database;
import businessobjects.SceneFilmingDate;
import businessobjects.Scene;
import java.sql.SQLException;
import businessobjects.TimeInterval;
import java.util.Calendar;
import java.util.GregorianCalendar;
import database.JdbcDatabase;
/**
 *
 * A class which performs the action of saving a scene filming date to the 
 * database
 */
public class SaveSceneFilmingDateAction extends BaseAction {
    
    // Constructor
    /**
     * Creates an instance of a SaveSceneFilmingDateAction, prepared to run
     * the provided SceneSchedule on the provided Database
     * @param database::Database ~ The Database on which to run the action
     * @param sceneFilmingDate::SceneFilmingDate ~ The SceneSchedule that will be 
     * saved.
     */
    public SaveSceneFilmingDateAction(Database database, 
                                   SceneFilmingDate sceneFilmingDate)
    {
        super(database);
        setBusinessObject(sceneFilmingDate);
    }
    
    /**
     * Saves the SceneFilmingDate associated with this action to the database,
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
        if(sceneFilmingDate() == null)
        {
            setErrorMessage("Scene Filming Date is null");
            setWasSuccessful(false);
            return;
        }
        
        if(!businessObject().isValid())
        {
            setErrorMessage("Scene Filming Date is not Valid");
            setWasSuccessful(false);
            return;
        }
        
        String queryString;
        
        if(sceneFilmingDate().isNew())
        {
            queryString = buildInsertQueryString();
            
            try
            {
                database().executeInsert(queryString);
                setWasSuccessful(true);
                sceneFilmingDate().setHasChanged(false);
               
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
                sceneFilmingDate().setHasChanged(false);
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
                + "SET "
                + "sch_scheduledatetime_start = '" + sceneFilmingDate().sceneShootingInterval().startIsoDate() + "', "
                + "sch_scheduledatetime_end = '" + sceneFilmingDate().sceneShootingInterval().endIsoDate() + "' "
                + "WHERE "
                + "( sch_scenename = '" + sceneFilmingDate().scene().name() + "');";
        
        return returnString;
    }
      
    /**
     * Creates an SQL INSERT string for the current SceneSchedule
     * @return An SQL INSERT string for the current SceneSchedule
     */
    private String buildInsertQueryString()
    {
        
        System.out.println(sceneFilmingDate().sceneShootingInterval().startIsoDate());
        String returnString = 
                "INSERT INTO t_schedule "
                + "( sch_scheduledatetime_start, "
                + "sch_scheduledatetime_end, "
                + "sch_scenename ) "
                + "VALUES "
                + "('" + sceneFilmingDate().sceneShootingInterval().startIsoDate()
                + "', '" + sceneFilmingDate().sceneShootingInterval().endIsoDate()
                + "', '" + sceneFilmingDate().scene().name() + "');";
        
        return returnString;
    }
    
    /**
     * Returns the SceneFilmingDate associated with this action
     * @return The SceneFilmingDate associated with this action
     */
    private SceneFilmingDate sceneFilmingDate()
    {
        if(businessObject() == null)
            return null;
        
        return (SceneFilmingDate)businessObject();
    }
    
    // Static Methods
    
    /**
     * Simple demonstration method. NOTE: THIS IS NOT A UNIT TEST.
     * @param args 
     */
    public static void main(String[] args)
    {
        Scene testScene1 = new Scene("Test Scene 1", "");
        SceneFilmingDate testFilmingDate1 = new SceneFilmingDate();
        testFilmingDate1.setScene(testScene1);
        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();
        end.add(Calendar.MONDAY, 1);
    
        TimeInterval testSceneShootingInterval = new TimeInterval(
        start, end);
        testFilmingDate1.setSceneShootingInterval(testSceneShootingInterval);
        
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
                "Raptorjesusisawesome55775");
        }
        catch (SQLException ex)
        {
            System.out.println("Failed to connection to db with message: "
                + ex.getMessage());
            return;
        }
        
        SaveSceneFilmingDateAction testAction = new SaveSceneFilmingDateAction(testDatabase, testFilmingDate1);
        testAction.run();
        if(testAction.wasSuccessful())
            System.out.println("Save was reported successful");
        else
            System.out.println("Save failed with message " + testAction.lastErrorMessage());
    } 
}


