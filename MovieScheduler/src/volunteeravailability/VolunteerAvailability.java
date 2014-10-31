/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volunteeravailability;
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
        
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.setVisible(true);
        
        // One of this inits failed. Display an error message and exit
        if(!initializedProperly)
            JOptionPane.showMessageDialog(null, "Could not load the system," + 
                    " with errors:\n" + errorsEncountered.toString(),
                    "Error Loading System!", 0);
        
            
    }
    
}
