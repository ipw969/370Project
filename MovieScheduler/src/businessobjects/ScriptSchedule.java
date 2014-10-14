package businessobjects;
import java.util.GregorianCalendar;
/**
 * Class which represents the time interval that a scene is scheduled to
 * filmed in 
 */
public class ScriptSchedule extends BaseBusinessObject {
    // Constructor
    public ScriptSchedule() {}
    
    // Public methods
    
    /**
     * Returns the start datetime that the scene is scheduled to be shot
     * @return The start datetime that the scene is scheduled to be shot
     */
    public GregorianCalendar scheduledStart()
    {
        return scheduledStart;
    }
    
    /**
     * Returns the end datetime that the scene is scheduled to be shot
     * @return The end datetime that the scene is scheduled to be shot
     */
    public GregorianCalendar scheduledEnd()
    {
        return scheduledEnd;
    }
    
    /**
     * Returns the scene that is scheduled to be filmed in this time interval
     * @return The scene that is scheduled to be filmed in this time interval
     */
    public Script.Scene scene()
    {
        return scene;
    }
    
    /**
     * Sets the starting datetime that the scene will be filmed
     * @param newStart::GregorianCalendar ~ The start datetime that the scene
     * will be filmed
     */
    public void setScheduledStart(GregorianCalendar newStart)
    {
        scheduledStart = newStart;
        updateError("Start Date cannot be null", scheduledStart != null);
        if(scheduledEnd != null)
            updateError("Start Date cannot be before End Date",
                    scheduledStart.compareTo(scheduledEnd) <=0);
        setHasChanged(true);
    }
    
    /**
     * Sets the ending datetime that the scene will be filmed
     * @param newEnd::GregorianCalendar ~ The end datetime that the scene will
     * be filmed
     */
    public void setScheduledEnd(GregorianCalendar newEnd)
    {
        scheduledEnd = newEnd;
        updateError("End Date cannot be null", scheduledEnd != null);
        if(scheduledStart != null)
            updateError("Start Date cannot be before End Date",
                    scheduledStart.compareTo(scheduledEnd) <= 0);
        setHasChanged(true);
    }
    
    public void setScene(Script.Scene newScene)
    {
        scene = newScene;
        updateError("Scene cannot be null", scene != null);
        setHasChanged(true);
    }
    
    // Private methods
    
    // Private member vairables
    
    /**
     * The scene which is scheduled
     */
    private Script.Scene scene;
    
    /**
     * The scheduled start date that the scene will be filmed
     */
    private GregorianCalendar scheduledStart;
    
    /**
     * The scheduled end date that the scene will be filmed
     */
    private GregorianCalendar scheduledEnd;
}
