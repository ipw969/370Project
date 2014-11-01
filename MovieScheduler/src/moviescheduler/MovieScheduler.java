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
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Could not load database driver with "
                        + "message: " + ex.toString());
            return;
        }
        
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
        //VolunteerForm volunteerForm = new VolunteerForm();
        //volunteerForm.setVisible(true);
        StartMenu startMenu = new StartMenu(database);
        startMenu.setVisible(true);
        
        // One of this inits failed. Display an error message and exit
        if(!initializedProperly)
            JOptionPane.showMessageDialog(null, "Could not load the system," + 
                    " with errors:\n" + errorsEncountered.toString(),
                    "Error Loading System!", 0);
        
        
    }    
}
