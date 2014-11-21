/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volunteeravailability;
import database.Database;
import database.JdbcDatabase;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import volunteeravailability.ui.LoginMenu;
/**
 *The main GUI entry point for the volunteer availability application
 *@author matthewgalbraith
 */
public class VolunteerAvailability {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // initialze sub systems. If fail any of the required inits, then
        // initializedProperly is set to false
        boolean initializedProperly = true;
        // Each time an init action fails, add an error message to this list
        ArrayList<String> errorsEncountered = new ArrayList<>();
        
        JdbcDatabase database;
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Could not load database driver with "
                        + "message: " + ex.toString());
            return;
        }
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
        
        LoginMenu loginMenu = new LoginMenu(database);
        loginMenu.setVisible(true);
        
        // One of this inits failed. Display an error message and exit
        if(!initializedProperly)
            JOptionPane.showMessageDialog(null, "Could not load the system," + 
                    " with errors:\n" + errorsEncountered.toString(),
                    "Error Loading System!", 0);
        
            
    }
    
}
