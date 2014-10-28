package businessobjects;

import java.util.GregorianCalendar;
import java.util.Iterator;
import datatypes.Volunteer;
import datatypes.Equipment;
/**
 * Class which represents the time interval that a scene is scheduled to
 * filmed in 
 */
public class SceneFilmingDate extends BaseBusinessObject {
    // Constructor
    /**
     * Creates a new instance of a SceneFilmingDate with no scene or shooting
     * interval.
     */
    public SceneFilmingDate() 
    {
        updateError("Scene cannot be null", scene != null);
        updateError("Shooting interval cannot be null", 
                    sceneShootingInterval != null);
    }
    
    // Public methods
    
    /**
     * Returns the scene that is scheduled to be filmed in this time interval
     * @return The scene that is scheduled to be filmed in this time interval
     */
    public Scene scene()
    {
        return scene;
    }

    /**
     * Returns the TimeInterval during which the scene is scheduled to be shot
     * @return The TimeInterval during which the scene is scheduled to be shot
     */
    public TimeInterval sceneShootingInterval()
    {
        return sceneShootingInterval;
    }
    
    /**
     * Whether the time of the scene filming date has a conflict with any of
     * the availabilites of its volunteers or equipment, with the current time
     * interval that the filming date is set for.
     * @return True if any of the volunteers or equipment are not available on
     * the interval that this SceneFilmingDate is scheduled for, False 
     * otherwise.
     */
    public boolean hasConflict()
    {
        if(scene == null)
            return false;
        
        if(sceneShootingInterval == null)
            return false;
        
        if(!scene.hasVolunteers() && !scene.hasEquipment())
            return true;
        
        boolean filmingDateHasConflict = false;
        
        Iterator<Volunteer> volunteerIterator = scene.volunteerIterator();
        while(volunteerIterator.hasNext())
        {
            Volunteer currentVolunteer = volunteerIterator.next();
            // Check volunteer availabilities against our time interval
            /*
            for (TimeInterval currentInterval : currentVolunteer.availabilities())
            {
                if(currentInterval.compareTo(currentInterval) == 0)
                    filmingDateHasConflict = true;
            }
            */
        }
        
        Iterator<Equipment> equipmentIterator = scene.equipmentIterator();
        while(equipmentIterator.hasNext())
        {
            Equipment currentEquipment = equipmentIterator.next();
            // Check equipment availablities against our timer interval
            /*
            for (TimeInterval currentInterval : currentEquipment.availabilities())
            {
                if(currentInterval.compareTo(currentInterval) == 0)
                    filmingDateHasConflict = true;
            }
            */
        }
        return filmingDateHasConflict;
    }
    
    /**
     * Helper function which returns the start time that the scene will begin
     * shooting
     * @return The start time that the scene will begin shooting
     */
    public GregorianCalendar sceneShootingStart()
    {
        return sceneShootingInterval.start();
    }
    
    /**
     * Helper function which returns the end time that the scene is scheduled
     * to be finished shooting
     * @return The end time that the scene is scheduled to be finished shooting
     */
    public GregorianCalendar sceneShootingEnd()
    {
        return sceneShootingInterval.end();
    }
    
    /**
     * Sets the scene which will be scheduled to be shot in this time interval
     * @param newScene::Scene ~ The scene to be shot
     */
    public void setScene(Scene newScene)
    {
        scene = newScene;
        updateError("Scene cannot be null", scene != null);
        setHasChanged(true);
    }
    
    /**
     * Sets the TimeInterval during which the scene will be shot
     * @param shootingInterval::TimeInterval ~ The TimeInterval during which the
     * scene will be shot
     */
    public void setSceneShootingInterval(TimeInterval shootingInterval)
    {
        sceneShootingInterval = shootingInterval;
        updateError("Shooting interval cannot be null",
                    sceneShootingInterval != null);
        
        if(sceneShootingInterval != null)
            updateError("Shooting interval is not valid", 
                        sceneShootingInterval.isValid());
        
        setHasChanged(true);
    }
    
    /**
     * Returns the name of the contained Scene or an empty String if the
     * contained scene is null
     * @return The name of the contained Scene or an empty String, if the
     * contained scene is null
     */
    @Override
    public String toString()
    {
        if(scene == null)
            return "";
        
        return scene.name();
    }
    
    // Private methods
    
    // Private member vairables
    /**
     * The TimeInterval during which the scene will be shot
     */
    TimeInterval sceneShootingInterval;
    /**
     * The scene which is scheduled
     */
    private Scene scene;

    
}
