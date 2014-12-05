/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviescheduler;

import actions.DeleteEquipmentAction;
import actions.DeleteSceneFilmingDateAction;
import actions.DeleteVolunteerAction;
import actions.SaveEquipmentAction;
import actions.SaveSceneAction;
import actions.SaveSceneFilmingDateAction;
import actions.SaveVolunteerAction;
import businessobjects.BaseBusinessObject;
import businessobjects.Equipment;
import businessobjects.Scene;
import businessobjects.SceneFilmingDate;
import businessobjects.Script;
import businessobjects.Volunteer;
import database.Database;
import javax.swing.JOptionPane;
import ui.EquipmentForm;
import ui.ErrorDisplay;
import ui.MainMenu;
import ui.SceneMenu;
import ui.StartMenu;
import ui.VolunteerForm;

/**
 *
 * @author Ryan
 */
public class MovieSchedulerController {
    protected static Script script;
    protected static Database database;
    protected static String errorMode = "JoptionPane";
    
    public MovieSchedulerController(Script script, Database database)
    {
        this.script = script;
        this.database = database;
    }
    
    public void saveBusinessObject(BaseBusinessObject objectToSave, BaseBusinessObject objectToReplace)
    {
        if (objectToSave instanceof Volunteer)
        {
            if (objectToReplace != null && !(objectToReplace instanceof Volunteer))
            {
                 throw new RuntimeException("When you used the MovieScheduler.saveBusinessObject() method you gave two objects of a different type"); 
            }
            Volunteer volunteerToReplace = (Volunteer) objectToReplace;
            Volunteer volunteerToSave = (Volunteer) objectToSave;
            this.saveVolunteer(volunteerToSave, volunteerToReplace);
        }
        
        else if (objectToSave instanceof Equipment)
        {
            if (objectToReplace != null && !(objectToReplace instanceof Equipment))
            {
                throw new RuntimeException("When you used the MovieScheduler.saveBusinessObject() method you gave two objects of a different type");
            } 
            Equipment equipmentToSave = (Equipment) objectToSave;
            Equipment equipmentToReplace = (Equipment) objectToReplace;
            this.saveEquipment(equipmentToSave, equipmentToReplace);
            
        }
        else if (objectToSave instanceof Scene)
        {
            if (objectToReplace != null && !(objectToReplace instanceof Scene))
            {
                throw new RuntimeException("When you used the MovieScheduler.saveBusinessObject() method you gave two objects of a different type");
            }
            
            Scene sceneToSave = (Scene) objectToSave;
            Scene sceneToReplace = (Scene) objectToReplace;
            this.saveScene(sceneToSave, sceneToReplace);
        }
        
        else if (objectToSave instanceof SceneFilmingDate)
        {
             if (objectToReplace != null && !(objectToReplace instanceof SceneFilmingDate))
            {
                throw new RuntimeException("When you used the MovieScheduler.saveBusinessObject() method you gave two objects of a different type");
            }
            SceneFilmingDate filmingDateToReplace = (SceneFilmingDate) objectToReplace;
            SceneFilmingDate filmingDateToSave = (SceneFilmingDate) objectToSave;
            this.saveSceneFilmingDate(filmingDateToSave, filmingDateToReplace);
        }
    }
      
    private void saveSceneFilmingDate(SceneFilmingDate filmingDateToSave, SceneFilmingDate filmingDateToReplace)
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
               this.displayError("Could not save scheduled filming date with message:" + saveAction.lastErrorMessage() + " Error Saving Filming Date");
            }
    }
    private void saveVolunteer(Volunteer volunteerToSave, Volunteer volunteerToReplace)
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
                this.displayError("Could not save volunteer with message:" + saveVolunteerAction.lastErrorMessage() + " Error Saving Volunteer!");
            }
    }
    private void saveEquipment(Equipment equipmentToSave, Equipment equipmentToReplace)
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
    
    
    private void saveScene(Scene sceneToSave, Scene sceneToReplace)
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
    
    
    public void deleteBusinessObject(BaseBusinessObject objectToDelete)
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
                for(Scene tempScene: script.getScenes())
                {
                    tempScene.removeVolunteer(volunteerToDelete);
                }
            } 
            else 
            {
                this.displayError(deleteSelectedVolunteer.lastErrorMessage());
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
                for(Scene tempScene: script.getScenes())
                {
                    tempScene.removeEquipment(equipmentToDelete);
                }
            } 
            else 
            {
                this.displayError(deleteSelectedEquipment.lastErrorMessage());
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
    
    protected boolean userWantsDeleted()
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
    
    public void displayMainMenu()
    {
             MainMenu mainMenu = new MainMenu(this);      //want to remove datbase from this soon. All the Main Menu should have is the script.
             mainMenu.setVisible(true);
    }
    
    public void displaySceneMenu(Scene sceneToEdit)
    {
        if (sceneToEdit == null)
        {
            sceneToEdit = new Scene("insert name here.", "insert description here.");
        }
        
        SceneMenu sceneMenu = new SceneMenu(sceneToEdit, sceneToEdit.clone(), this);
        sceneMenu.setVisible(true);
    }
    
    public void displayVolunteerMenu(Volunteer volunteerToEdit)
    {
        if (volunteerToEdit == null)
        {
            volunteerToEdit = new Volunteer();
        }
       VolunteerForm volunteerForm = new VolunteerForm(volunteerToEdit, (Volunteer) volunteerToEdit.clone(), this);
       volunteerForm.setVisible(true);
    }
    
    public void displayEquipmentMenu(Equipment equipmentToEdit)
    {
        if (equipmentToEdit == null)
        {
            equipmentToEdit = new Equipment();
        }
 
        EquipmentForm equipmentForm = new EquipmentForm(equipmentToEdit, (Equipment) equipmentToEdit.clone(), this);
        equipmentForm.setVisible(true);
    }
    
    public void displayError(String errorMessage)
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
    
    public void displayStartMenu()
    {
        StartMenu startMenu = new StartMenu(database);
        startMenu.setVisible(true);
    }
}
