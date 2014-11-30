package ui;

import businessobjects.SceneFilmingDate;

/**
 * A class representing an event deleting a SceneFilmingDate
 * @author Iain Workman
 */
public class DeleteFilmingDateEvent {
    // Constructor
    /**
     * Creates an instance of a DeleteFilmingDate event to delete the provided
     * SceneFilmingDate
     * @param sceneFilmingDate::SceneFilmingDate ~ The SceneFilmingDate to 
     * delete.
     */
    public DeleteFilmingDateEvent(SceneFilmingDate sceneFilmingDate)
    {
        this.sceneFilmingDate = sceneFilmingDate;
    }
    
    // Public Methods
    /**
     * The SceneFilmingDate associated with this event.
     * @return 
     */
    public SceneFilmingDate getSceneFilmingDate()
    {
        return sceneFilmingDate;
    }
    
    // Private Member Variables
    /**
     * The SceneFilmingDate being deleted
     */
    private final SceneFilmingDate sceneFilmingDate;
}
