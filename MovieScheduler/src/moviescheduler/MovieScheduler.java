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
public class MovieScheduler
{

    /**
     * Main entry point for the GUI of the movie scheduler system.
     *
     * @param args the command line arguments (unused)
     */
    public static void main(String[] args)
    {
        // initialze sub systems. If fail any of the required inits, then
        // initializedProperly is set to false
        boolean initializedProperly = true;
        // Each time an init action fails, add an error message to this list
        final ArrayList<String> errorsEncountered = new ArrayList<>();

        // Attempt initialization of database driver
        try
        {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex)
        {
            initializedProperly = false;
            errorsEncountered.add("Could not load database driver with "
                    + "message: " + ex.toString());
        }

        // Attempt connect to database
        final JdbcDatabase database;
        try
        {
            database = new JdbcDatabase(
                    "jdbc:postgresql://edjo.usask.ca/cmpt370_group06",
                    "cmpt370_group06",
                    "Raptorjesusisawesome55775");
        } catch (SQLException ex)
        {
            System.out.println("Failed to connection to db with message: "
                    + ex.getMessage());
            return;
        }
        
        // Attempt to load a script
        LoadScriptAction loadScriptAction = new LoadScriptAction(database);
        loadScriptAction.run();

        if (!loadScriptAction.wasSuccessful())
        {
            initializedProperly = false;
            errorsEncountered.add(loadScriptAction.lastErrorMessage());
        }

        final Script loadedScript = (Script) loadScriptAction.getBusinessObject();
        if (loadedScript == null && initializedProperly)
        {
            // No errors were encountered, but we didn't find a script, must
            // be the first time loading. Display the start menu.
            java.awt.EventQueue.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    StartMenu startMenu = new StartMenu(database);
                    startMenu.setVisible(true);
                }
            });
        } else if(initializedProperly)
        {
            // Script was found, show it in the main menu
            java.awt.EventQueue.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    MainMenu mainMenu = new MainMenu(loadedScript, database);
                    mainMenu.setVisible(true);
                }
            });

        }
        else
        {
            // One of this inits failed. Display an error message and exit
            JOptionPane.showMessageDialog(null, "Could not load the system,"
                    + " with errors:\n" + errorsEncountered.toString(),
                    "Error Loading System!", 0);
        }

    }
}
