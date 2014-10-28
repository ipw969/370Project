package businessobjects;

/**
 *  Class representing the filming schedule for scenes in the script.
 */
public class Schedule extends BusinessObjectList<SceneFilmingDate> {
        
    /**
     * Returns a list of all the SceneFilmingDates in the Schedule which are
     * currently in conflict.
     * @return A list of all the SceneFilmingDates in the Schedule which are
     * currently in conflict.
     */
    public BusinessObjectList<SceneFilmingDate> scheduleConflicts()
    {
        BusinessObjectList<SceneFilmingDate> conflictingSceneFilmingDates = 
                new BusinessObjectList<>();
        
        for(SceneFilmingDate currentFilmingDate : this)
        {
            if (currentFilmingDate.hasConflict())
                conflictingSceneFilmingDates.add(currentFilmingDate);
        }
        
        return conflictingSceneFilmingDates;
    }
}
