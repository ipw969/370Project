package moviescheduler;


import database.JdbcDatabase;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.SQLException;
import actions.*;
import businessobjects.*;
import database.Database;
import static moviescheduler.MovieScheduler.script;
import ui.*;

/**
 * Class which holds the main entry point function for the MovieScheduler system
 *
 * @author Iain Workman, Ryan La Forge, John Mason
 */
public class MovieScheduler {
protected static Script script;
protected static Database database;
protected static boolean initializedProperly;
protected static ArrayList<String> errorsEncountered;
protected static String errorMode = "JoptionPane";
    public static void saveBusinessObject(BaseBusinessObject objectToSave, BaseBusinessObject objectToReplace)
    {
        if (objectToSave instanceof Volunteer)
        {
            if (objectToReplace != null && !(objectToReplace instanceof Volunteer))
            {
                 throw new RuntimeException("When you used the MovieScheduler.saveBusinessObject() method you gave two objects of a different type"); 
            }
            Volunteer volunteerToReplace = (Volunteer) objectToReplace;
            Volunteer volunteerToSave = (Volunteer) objectToSave;
            MovieScheduler.saveVolunteer(volunteerToSave, volunteerToReplace);
        }
        
        else if (objectToSave instanceof Equipment)
        {
            if (objectToReplace != null && !(objectToReplace instanceof Equipment))
            {
                throw new RuntimeException("When you used the MovieScheduler.saveBusinessObject() method you gave two objects of a different type");
            } 
            Equipment equipmentToSave = (Equipment) objectToSave;
            Equipment equipmentToReplace = (Equipment) objectToReplace;
            MovieScheduler.saveEquipment(equipmentToSave, equipmentToReplace);
            
        }
        else if (objectToSave instanceof Scene)
        {
            if (objectToReplace != null && !(objectToReplace instanceof Scene))
            {
                throw new RuntimeException("When you used the MovieScheduler.saveBusinessObject() method you gave two objects of a different type");
            }
            
            Scene sceneToSave = (Scene) objectToSave;
            Scene sceneToReplace = (Scene) objectToReplace;
            MovieScheduler.saveScene(sceneToSave, sceneToReplace);
        }
        
        else if (objectToSave instanceof SceneFilmingDate)
        {
             if (objectToReplace != null && !(objectToReplace instanceof SceneFilmingDate))
            {
                throw new RuntimeException("When you used the MovieScheduler.saveBusinessObject() method you gave two objects of a different type");
            }
            SceneFilmingDate filmingDateToReplace = (SceneFilmingDate) objectToReplace;
            SceneFilmingDate filmingDateToSave = (SceneFilmingDate) objectToSave;
            MovieScheduler.saveSceneFilmingDate(filmingDateToSave, filmingDateToReplace);
        }
    }
      
    private static void saveSceneFilmingDate(SceneFilmingDate filmingDateToSave, SceneFilmingDate filmingDateToReplace)
    {
            SaveSceneFilmingDateAction saveAction = new SaveSceneFilmingDateAction(database, filmingDateToSave);
            saveAction.run();
            if (saveAction.wasSuccessful()) 
            {
                filmingDateToReplace.merge(filmingDateToSave);
                script.getSchedule().add(filmingDateToReplace);
            } 
            else 
            {
                MovieScheduler.displayError("Could not save scheduled filming date with message:" + saveAction.lastErrorMessage() + " Error Saving Filming Date");
            }
    }
    private static void saveVolunteer(Volunteer volunteerToSave, Volunteer volunteerToReplace)
    {
            for (Volunteer tempVolunteer : script.getVolunteers()) 
            {
                if ((tempVolunteer.getEmail().equals(volunteerToSave.getEmail())) && (!tempVolunteer.equals(volunteerToReplace))) 
                {
                    displayError("The script already contains a volunteer with that email");
                    return;
                }
            }
            if(!volunteerToSave.isValid())
            {
                displayError("The given volunteer is invalid and cannot be saved. Error message: " + volunteerToSave.getErrorMessage());
                return;
            }
            
            SaveVolunteerAction saveVolunteerAction = new SaveVolunteerAction(database, volunteerToSave, volunteerToReplace.getEmail());
            boolean wasNew = volunteerToSave.isNew();
            saveVolunteerAction.run();
            if (saveVolunteerAction.wasSuccessful()) 
            {
                
                if (wasNew) 
                {
                    script.getVolunteers().add(volunteerToReplace);
                }
                else
                {
                    volunteerToReplace.merge(volunteerToSave);
                }
            } 
            else 
            {
                MovieScheduler.displayError("Could not save volunteer with message:" + saveVolunteerAction.lastErrorMessage() + " Error Saving Volunteer!");
            }
    }
    private static void saveEquipment(Equipment equipmentToSave, Equipment equipmentToReplace)
    {
            for (Equipment tempEquipment : script.getEquipment()) 
            {
                if ((tempEquipment.getEquipmentName().equals(equipmentToSave.getEquipmentName())) && (!tempEquipment.equals(equipmentToReplace))) 
                {
                    displayError("The script already contains equipment with that name");
                    return;
                }
            }
            if(!equipmentToSave.isValid())
            {
                displayError("The given equipment is invalid and cannot be saved. Error message: " + equipmentToSave.getErrorMessage());
                return;
            }
            //create a query to the database that will send the equipment data there
            SaveEquipmentAction saveEquipmentAction;
            if (equipmentToReplace != null)
            {
                saveEquipmentAction = new SaveEquipmentAction(database, equipmentToSave, equipmentToReplace.getEquipmentName());
            }
            else
             {
                saveEquipmentAction = new SaveEquipmentAction(database, equipmentToSave);
             }
            saveEquipmentAction.run();

            //check to see if the equipment was successfully added to the database
            //if not give an error message
            if (!saveEquipmentAction.wasSuccessful()) 
            {
                displayError(saveEquipmentAction.lastErrorMessage());
            }
            else
            {
                if (equipmentToSave.isNew())
                {
                    script.addEquipment(equipmentToSave);
                }
                else
                {
                   equipmentToReplace.merge(equipmentToSave);
                }
            }
        }
    
    
    private static void saveScene(Scene sceneToSave, Scene sceneToReplace)
    {
        
            for (Scene tempScene : script.getScenes()) 
            {
                if ((tempScene.getName().equals(sceneToSave.getName())) && (!tempScene.equals(sceneToReplace))) 
                {
                    displayError("The script already contains a scene with that name");
                    return;
                }
            }
            
            if (!sceneToSave.isValid()) 
            {
                displayError("The scene is currently not in a valid state. Unable to save it.\n Error: \n" + sceneToSave.getErrorMessage());
                return;
            } 
            else 
            {

                //build the saveSceneAction and attempt to save the scene.
                SaveSceneAction saveClonedScene;
                if (sceneToReplace != null) 
                {
                    saveClonedScene = new SaveSceneAction(database, sceneToSave, sceneToReplace.getName(), script);
                } 
                else 
                {
                    saveClonedScene = new SaveSceneAction(database, sceneToSave, null, script);
                }

                saveClonedScene.run();
                if (saveClonedScene.wasSuccessful()) 
                {
                    if (sceneToReplace != null) 
                    {
                        //If this is editing a scene we need to merge the clone into the original.
                        sceneToReplace.setHasChanged(false);
                        sceneToReplace.merge(sceneToSave);
                        sceneToReplace.setHasChanged(true);
                    } 
                    else 
                    {
                        script.addScene(sceneToSave);
                    }
                }  
            }
    }
    
    
    public static void deleteBusinessObject(BaseBusinessObject objectToDelete)
    {
        if(!userWantsDeleted())
        {
            return;
        }
        if (objectToDelete instanceof Volunteer)
        {
            Volunteer volunteerToDelete = (Volunteer) objectToDelete;
            DeleteVolunteerAction deleteSelectedVolunteer = new DeleteVolunteerAction(database, volunteerToDelete.getEmail());
            deleteSelectedVolunteer.run();
            if (deleteSelectedVolunteer.wasSuccessful()) 
            {
                script.removeVolunteer(volunteerToDelete);
            } 
            else 
            {
                MovieScheduler.displayError(deleteSelectedVolunteer.lastErrorMessage());
            }
        }
        else if (objectToDelete instanceof Equipment)
        {
            Equipment equipmentToDelete = (Equipment) objectToDelete;
            DeleteEquipmentAction deleteSelectedEquipment = new DeleteEquipmentAction(database, equipmentToDelete.getOwnerEmail());
            deleteSelectedEquipment.run();
            if (deleteSelectedEquipment.wasSuccessful()) 
            {
                script.removeEquipment(equipmentToDelete);
            } 
            else 
            {
                MovieScheduler.displayError(deleteSelectedEquipment.lastErrorMessage());
            }


        }
        else if (objectToDelete instanceof SceneFilmingDate)
        {
            SceneFilmingDate sceneFilmingDate = (SceneFilmingDate) objectToDelete;
            DeleteSceneFilmingDateAction deleteSceneFilmingDateAction =  new DeleteSceneFilmingDateAction(database, sceneFilmingDate);
            deleteSceneFilmingDateAction.run();
            if(deleteSceneFilmingDateAction.wasSuccessful())
            {
                script.getSchedule().remove(sceneFilmingDate);
            }
            else
            {
                displayError(deleteSceneFilmingDateAction.lastErrorMessage());
            }   
        }
    }
    
    protected static boolean userWantsDeleted()
    {
        
       String[] options = new String[]{"Cancel", "Yes"};
       int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to delete? " , "Error",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[1]);
               
        if (choice == 0) // pressing OK button
        {
            return false;
        }
        else if (choice == 1)
        {
           return true;
        }
        return false;
    }
    
    public static void displayMainMenu(Script loadedScript, Database database)
    {
             MainMenu mainMenu = new MainMenu(loadedScript, database);      //want to remove datbase from this soon. All the Main Menu should have is the script.
             mainMenu.setVisible(true);
    }
    
    public static void displaySceneMenu(Scene sceneToEdit)
    {
        SceneMenu sceneMenu = new SceneMenu(sceneToEdit, sceneToEdit.clone());
        sceneMenu.setVisible(true);
    }
    
    public static void displayVolunteerMenu(Volunteer volunteerToEdit)
    {
       VolunteerForm volunteerForm = new VolunteerForm(volunteerToEdit, volunteerToEdit.clone());
       volunteerForm.setVisible(true);
    }
    
    public static void displayEquipmentMenu(Equipment equipmentToEdit)
    {
        EquipmentForm equipmentForm = new EquipmentForm(equipmentToEdit, equipmentToEdit.clone());
        equipmentForm.setVisible(true);
    }
    
    public static void displayError(String errorMessage)
    {
        if (errorMode.equals("JOptionPane"))
        {
            JOptionPane.showMessageDialog(null, errorMessage);
        }
        else if (errorMode.equals("ErrorFrame"))
        {
            ErrorDisplay errorDisplay = new ErrorDisplay(null, errorMessage);
            errorDisplay.setVisible(true);
        }
    }
    
    public static void displayStartMenu(Database database)
    {
        StartMenu startMenu = new StartMenu(database);
        startMenu.setVisible(true);
    }
    protected static void initializeDatabase()
    {
        
        // initialze sub systems. If fail any of the required inits, then
        // initializedProperly is set to false
        initializedProperly = true;
        // Each time an init action fails, add an error message to this list
        errorsEncountered = new ArrayList<>();

        // Attempt initialization of database driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            initializedProperly = false;
            errorsEncountered.add("Could not load database driver with "
                    + "message: " + ex.toString());
        }

        // Attempt connect to database
        final JdbcDatabase database;
        try {
            database = new JdbcDatabase(
                    "jdbc:postgresql://edjo.usask.ca/cmpt370_group06",
                    "cmpt370_group06",
                    "Raptorjesusisawesome55775");
        } catch (SQLException ex) {
            System.out.println("Failed to connection to db with message: "
                    + ex.getMessage());
            return;
        }

        MovieScheduler.database = database;
    }

protected static void initializeScript()
{
    // Attempt to load a script
        LoadScriptAction loadScriptAction = new LoadScriptAction(database);
        loadScriptAction.run();

        if (!loadScriptAction.wasSuccessful()) {
            initializedProperly = false;
            errorsEncountered.add(loadScriptAction.lastErrorMessage());
        }

        script = (Script) loadScriptAction.getBusinessObject();
}

    /**
     * Main entry point for the GUI of the movie scheduler system.
     *
     * @param args the command line arguments (unused)
     */
    public static void main(String[] args) 
    {
        MovieScheduler.initializeDatabase();
        MovieScheduler.initializeScript();
        
        
        if (script == null && initializedProperly) {
            // No errors were encountered, but we didn't find a script, must
            // be the first time loading. Display the start menu.
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MovieScheduler.displayStartMenu(database);     
                }
            });
        } else if (initializedProperly) {
            // Script was found, show it in the main menu
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MovieScheduler.displayMainMenu(script, database);
                }
            });

        } else {
            // One of this inits failed. Display an error message and exit
            JOptionPane.showMessageDialog(null, "Could not load the system,"
                    + " with errors:\n" + errorsEncountered.toString(),
                    "Error Loading System!", 0);
        }

        
    }
