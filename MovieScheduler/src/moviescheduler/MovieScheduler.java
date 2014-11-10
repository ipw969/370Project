/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviescheduler;

import database.JdbcDatabase;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.SQLException;
import actions.LoadScriptAction;
import actions.PopulateScriptEquipmentAction;
import actions.PopulateScriptScenesAction;
import actions.PopulateScriptScheduleAction;
import actions.PopulateScriptVolunteersAction;
import businessobjects.Script;
import ui.*;
/**
 *
 *
 */
public class MovieScheduler {

    /**
     * Main entry point for the GUI of the movie scheduler system.
     * @param args the command line arguments (unused)
     */
    public static void main(String[] args) {
        // initialze sub systems. If fail any of the required inits, then
        // initializedProperly is set to false
        boolean initializedProperly = true;
        // Each time an init action fails, add an error message to this list
        ArrayList<String> errorsEncountered = new ArrayList<>();
        
        // Attempt initialization of database driver
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException ex)
        {
                initializedProperly = false;
                errorsEncountered.add("Could not load database driver with "
                        + "message: " + ex.toString());
        }
//        try {
//            Class.forName("org.postgresql.Driver");
//        }
//        catch (ClassNotFoundException ex)
//        {
//            System.out.println("Could not load database driver with "
//                        + "message: " + ex.toString());
//            return;
//        }
        
        JdbcDatabase database = null;
        try{
            database = new JdbcDatabase(
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
        
        LoadScriptAction loadScriptAction = new LoadScriptAction(database);
        loadScriptAction.run();
        
        if(!loadScriptAction.wasSuccessful())
        {
            initializedProperly = false;
            errorsEncountered.add(loadScriptAction.lastErrorMessage());
        }
        
        if(loadScriptAction.businessObject() == null && initializedProperly)
        {
            // No errors were encountered, but we didn't find a script, must
            // be the first time loading. Display the start menu.
            StartMenu startMenu = new StartMenu(database);
            startMenu.setVisible(true);
        }
        else
        {
            Script loadedScript = (Script)loadScriptAction.businessObject();
            // A Script was found, continue loading and start the main menu
            PopulateScriptVolunteersAction loadVolunteersAction = new 
                PopulateScriptVolunteersAction(database, loadedScript);
            
            loadVolunteersAction.run();
            
            if(loadVolunteersAction.wasSuccessful())
            {
                PopulateScriptEquipmentAction loadEquipmentAction = new 
                    PopulateScriptEquipmentAction(database, loadedScript);
            
                loadEquipmentAction.run();
                
                if(loadEquipmentAction.wasSuccessful())
                {
                    PopulateScriptScenesAction loadScenesAction = new
                        PopulateScriptScenesAction(database, loadedScript);
                    
                    loadScenesAction.run();
                    if(loadScenesAction.wasSuccessful())
                    {
                        PopulateScriptScheduleAction loadScheduleAction = new
                            PopulateScriptScheduleAction(database, loadedScript);
                        
                        loadScheduleAction.run();
                        if(loadScheduleAction.wasSuccessful())
                        {
                            try{
                            MainMenu mainMenu = new MainMenu(loadedScript, database);
                            mainMenu.setVisible(true);
                            } catch (SQLException pointless)
                            {
                                initializedProperly = false;
                                errorsEncountered.add(pointless.getMessage());
                            }
                        }
                        else
                        {
                            initializedProperly = false;
                            errorsEncountered.add(loadScheduleAction.lastErrorMessage());
                        }
                    }
                    else
                    {
                        initializedProperly = false;
                        errorsEncountered.add(loadScenesAction.lastErrorMessage());
                    }
                }
                else
                {
                    initializedProperly = false;
                    errorsEncountered.add(loadEquipmentAction.lastErrorMessage());
                }
            }
            else
            {
                initializedProperly = false;
                errorsEncountered.add(loadVolunteersAction.lastErrorMessage());
            }
        }
        
        // One of this inits failed. Display an error message and exit
        if(!initializedProperly)
            JOptionPane.showMessageDialog(null, "Could not load the system," + 
                    " with errors:\n" + errorsEncountered.toString(),
                    "Error Loading System!", 0);
        

    }    
}
