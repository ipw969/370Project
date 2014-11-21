package businessobjects;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Class which represents the time interval that a scene is scheduled to filmed
 * in
 */
public class SceneFilmingDate extends BaseBusinessObject
        implements BusinessObjectListener {

    // Constructor

    /**
     * Creates a new instance of a SceneFilmingDate with no scene or shooting
     * interval.
     */
    public SceneFilmingDate() {
        updateError("Scene cannot be null", scene != null);
        updateError("Shooting interval cannot be null",
                sceneShootingInterval != null);
        this.conflictReason = new ArrayList<>();
        this.isIgnored = false;
    }

    // Public methods
    /**
     * Returns the scene that is scheduled to be filmed in this time interval
     *
     * @return The scene that is scheduled to be filmed in this time interval
     */
    public Scene scene() {
        return scene;
    }

    /**
     * Returns the TimeInterval during which the scene is scheduled to be shot
     *
     * @return The TimeInterval during which the scene is scheduled to be shot
     */
    public TimeInterval sceneShootingInterval() {
        return sceneShootingInterval;
    }

    /**
     * Whether the time of the scene filming date has a conflict with any of the
     * availabilites of its volunteers or equipment, with the current time
     * interval that the filming date is set for.
     *
     * @return True if any of the volunteers or equipment are not available on
     * the interval that this SceneFilmingDate is scheduled for, False
     * otherwise.
     */
    public boolean hasConflict() {
        if (scene == null) {
            return false;
        }

        if (sceneShootingInterval == null) {
            return false;
        }

        if (!scene.hasVolunteers() && !scene.hasEquipment()) {
            return false;
        }

        boolean filmingDateHasConflict = false;

        for (Volunteer currentVolunteer : scene.volunteers()) {
            boolean volunteerIsAvailable = false;
            for (TimeInterval currentAvailability : currentVolunteer.getAvailability()) {
                if (currentAvailability.compareTo(sceneShootingInterval) == 0) {
                    volunteerIsAvailable = true;
                }
            }
            if (!volunteerIsAvailable) {
                filmingDateHasConflict = true;
                conflictReason.add(currentVolunteer.toString()
                        + " not available on " + sceneShootingInterval.toString());
            }
        }

        for (Equipment currentEquipment : scene.equipment()) {
            boolean equipmentIsAvailable = false;
            for (TimeInterval currentAvailability : currentEquipment.getAvailability()) {
                if (currentAvailability.compareTo(sceneShootingInterval) == 0) {
                    equipmentIsAvailable = true;
                }
            }
            if (!equipmentIsAvailable) {
                filmingDateHasConflict = true;
                conflictReason.add(currentEquipment.toString()
                        + " not available on " + sceneShootingInterval.toString());
            }
        }

        return filmingDateHasConflict;
    }

    /**
     * Helper function which returns the start time that the scene will begin
     * shooting
     *
     * @return The start time that the scene will begin shooting
     */
    public GregorianCalendar sceneShootingStart() {
        return sceneShootingInterval.start();
    }

    /**
     * Helper function which returns the end time that the scene is scheduled to
     * be finished shooting
     *
     * @return The end time that the scene is scheduled to be finished shooting
     */
    public GregorianCalendar sceneShootingEnd() {
        return sceneShootingInterval.end();
    }

    /**
     * Sets the scene which will be scheduled to be shot in this time interval
     *
     * @param newScene::Scene ~ The scene to be shot
     */
    public void setScene(Scene newScene) {
        scene = newScene;
        updateError("Scene cannot be null", scene != null);
        setHasChanged(true);
    }

    /**
     * Sets the TimeInterval during which the scene will be shot
     *
     * @param shootingInterval::TimeInterval ~ The TimeInterval during which the
     * scene will be shot
     */
    public void setSceneShootingInterval(TimeInterval shootingInterval) {
        if (sceneShootingInterval != null) {
            sceneShootingInterval.removeListener(this);
        }

        sceneShootingInterval = shootingInterval;
        if (sceneShootingInterval != null) {
            sceneShootingInterval.addListener(this);
        }

        updateError("Shooting interval cannot be null",
                sceneShootingInterval != null);

        if (sceneShootingInterval != null) {
            updateError("Current shooting time is not valid",
                    sceneShootingInterval.isValid());
        }

        setHasChanged(true);
    }

    /**
     * Returns the list of strings that describe the reasons for this particular
     * schedule conflict
     *
     * @return Returns the list of strings that describe the reasons for this
     * particular schedule conflict
     */
    public ArrayList<String> getReasonList() {
        return conflictReason;
    }

    /**
     * Returns the state of whether or not a conflict on this date is to be
     * ignored
     *
     * @return isIgnored: The boolean set by the ignoreConflictFunction
     */
    public Boolean isConflictIgnored() {
        return isIgnored;
    }

    /**
     * Sets the ignore conflict status of this scene filming date to the passed
     * value. Ignored conflicts will not be flagged to the user when checking
     * for conflicts in the Schedule
     *
     * @param ignore::boolean ~ Whether to ignore conflicts in this
     * SceneFilmingDate
     */
    public void ignoreConflict(boolean ignore) {
        isIgnored = ignore;
    }

    /**
     * Returns the name of the contained Scene or an empty String if the
     * contained scene is null
     *
     * @return The name of the contained Scene or an empty String, if the
     * contained scene is null
     */
    @Override
    public String toString() {
        if (scene == null) {
            return "";
        }

        return scene.getName();
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
    private Boolean isIgnored;
    private ArrayList<String> conflictReason;

    @Override
    public void validStateAltered(boolean newState, BaseBusinessObject sender) {
        this.updateError("Current shooting time is not valid", newState);
    }

    @Override
    public void changedStateAltered(boolean newState, BaseBusinessObject sender) {
        this.setHasChanged(newState);
    }

    @Override
    public String toDescriptiveString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (scene() != null) {
            stringBuilder.append(scene().toString());
        }
        stringBuilder.append(" - ");
        if (sceneShootingInterval() != null) {
            stringBuilder.append(sceneShootingInterval().toString());
        }

        return stringBuilder.toString();
    }

    @Override
    public void merge(BaseBusinessObject mergeObject) {
        if (!(mergeObject instanceof SceneFilmingDate)) {
            throw new RuntimeException("Cannot merge a Business Object of"
                    + " non SceneFilmingDate type into a SceneFilmingDate");
        }

        super.merge(mergeObject);
        SceneFilmingDate otherFilmingDate = (SceneFilmingDate) mergeObject;
        this.setSceneShootingInterval(otherFilmingDate.sceneShootingInterval());
    }

    /**
     * Clones the current SceneFilmingDate. Returns a new SceneFilmingDate which
     * contains a reference to the same Scene object as contained in this, but
     * with a newly cloned TimeInterval
     *
     * @return A clone of the current SceneFilmingDate.
     */
    @Override
    public BaseBusinessObject clone() {
       try{
           SceneFilmingDate other = (SceneFilmingDate)super.clone();
           other.sceneShootingInterval = (TimeInterval)this.sceneShootingInterval().clone();
           return other;
       }catch (CloneNotSupportedException e)
       {
           throw new RuntimeException("Cannot clone type SceneFilmingDate");
       }
       
    }

}
