package businessobjects;

import java.util.Iterator;

/*
 * This class contains all information relevent to the script including volunteers, equipment and scenes. 
 *This class encapsulates the Scene class to prevent scenes from being created anywhere else. This ensures that
 *-The scenes are strongly correlated with the script(there will be no cross-referencing if we decide to support multiple scripts
 *-Ensures that every scene is automatically put into the script. 
 @author Ryam La Forge
 */
public class Script extends BaseBusinessObject {

    protected BusinessObjectList<Scene> scenes;
    protected BusinessObjectList<Volunteer> volunteers;
    protected BusinessObjectList<Equipment> equipment;
    /**
     * The schedule which determines the filming date of all the Scenes.
     */
    private Schedule schedule;
    protected String name;

    /**
     * The constructor for the script
     *
     * @param name - the name of the script
     * @author Ryan La Forge
     */
    public Script(String name) {
        super();
        this.name = name;
        updateError("The name of the Script is null", !name.isEmpty());
        scenes = new BusinessObjectList<>();
        volunteers = new BusinessObjectList<>();
        equipment = new BusinessObjectList<>();
        schedule = new Schedule();
    }

    /**
     * Retrieves the name of the script.
     *
     * @return The name of the script
     * @author Ryan La Forge
     */
    public String name() {
        return name;
    }

    /**
     * Sets the name of the Script
     *
     * @param newName::String ~ The new name of the Script
     * @postcon the name of the script was set to the given name.
     * @author Ryan La Forge
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * EVERYTHING TO DO WITH THE SCENE PORTION OF THE SCRIPT IS LISTED BELOW
     */
    /**
     * Returns a list of the Scenes which comprise this Script
     *
     * @return A list of the Scenes which comprise this Script`
     * @author Iain Workman
     */
    public BusinessObjectList<Scene> scenes() {
        return scenes;
    }

    /**
     * @return true if the script contains scenes, false if not.
     * @author Ryan La Forge
   *
     */
    public boolean hasScenes() {
        return !scenes.isEmpty();
    }

    /**
     * Creates a scene with the specified name and description and adds it to
     * the script.
     *
     * @preCon name != null, description != null
     * @param newScene the new scene to add to the script.
     * @return the newly created volunteer.
     * @postcon the scene was added to the script.
     * @author Ryan La Forge
     */
    public boolean addScene(Scene newScene) {
        if (newScene == null || !newScene.isValid()) {
            return false;
        }
        /**
         * This whole thing is necessary because we need to check whether a
         * scene with that name already exists regardless of whether they are identical
         */
        if (this.hasScenes()) {
            Iterator<Scene> iter = this.sceneIterator();
            while (iter.hasNext()) {
                Scene examinedScene = iter.next();
                if (examinedScene.getName().equals(newScene.getName())) {

                    return false;
                }
            }

        }

        scenes.add(newScene);
        return true;

    }

    /**
     * removes the specified volunteer from the list of volunteers
     *
     * @param oldScene - the scene to be deleted from the list
     * @return true if successful, false if not
     * @postcon the scene was removed from the script
     * @author Ryan La Forge
     */
    public boolean removeScene(Scene oldScene) {
        return scenes.remove(oldScene);
    }

    /**
     * Retrieves the number of scenes in the script.
     *
     * @return the number of scenes in the script
     * @author Ryan La Forge
   *
     */
    public int numberOfScenes() {
        return scenes.size();
    }

    /**
     * Checks to see if all of the scenes in this script have been successfully
     * scheduled.
     *
     * @return true if all scenes have been scheduled or if the script is empty,
     * false if not.
     * @author Ryan La Forge
   *
     */
    public boolean isEverySceneScheduled() {
        if (scenes.isEmpty()) {
            return true;
        }
        for (Scene curScene : scenes) {
            if (curScene.isScheduled() == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if the script is complete. This is primarily to check if
     * the script can be removed.
     *
     * @return true if all of the scenes are complete, false if not.
     * @author Ryan La Forge
     */
    public boolean isEverySceneComplete() {
        for (Scene curScene : scenes) {
            if (curScene.isComplete() == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the list of Scenes associated with this Script.
     *
     * @param newScenes The list of Scenes associated with this Script.
     * @postcon the set of scenes in this script is now every element scene in
     * the given list.
     * @author Ryan La Forge
     */
    public void setScenes(BusinessObjectList<Scene> newScenes) {
        scenes = newScenes;
    }

    /**
     * Gives you an iterator over the scenes in the script
     *
     * @return an iterator over the scenes in the script.
     * @author Ryan La Forge
     */
    public Iterator<Scene> sceneIterator() {
        return scenes.iterator();
    }

    /**
     * EVERYTHING TO DO WITH THE LISTS OF VOLUNTEERS IS BELOW*
     */
    /**
     * removes the specified volunteer from the list of volunteers
     *
     * @param oldVolunteer - the volunteer to be deleted from the list
     * @return true if successful, false if not
     * @postcon the given volunteer is removed from the script.
     * @author Ryan La Forge
     */
    public boolean removeVolunteer(Volunteer oldVolunteer) {
        return volunteers.remove(oldVolunteer);
    }

    /**
     * Gives you an iterator over the scenes in the script
     *
     * @return an iterator over the scenes in the script.
     * @author Ryan La Forge
     */
    public Iterator<Volunteer> volunteerIterator() {
        return volunteers.iterator();
    }

    /**
     * Returns a list of all the volunteers associated with the Script.
     *
     * @return A list of all the volunteers associated with the Script.
     * @author Iain Workman
     */
    public BusinessObjectList<Volunteer> volunteers() {
        return volunteers;
    }

    /**
     * Sets the list of volunteers associated with the Script.
     *
     * @param newVolunteers The new list of volunteers to be associated with the
     * script.
     * @author Iain Workman
     */
    public void setVolunteers(BusinessObjectList<Volunteer> newVolunteers) {
        volunteers = newVolunteers;
    }

    /**
     * @return true if the script contains volunteers, false if not.
     * @author Ryan La Forge
     */
    public boolean hasVolunteers() {
        return !volunteers.isEmpty();
    }

    /**
     * Creates a Volunteer with the specified information and adds it to the
     * script.
     *
     * @precon: The given volunteer should be valid.
     * @param newVolunteer: The new volunteer to add to the script.
     * @return true if successful, false if not.
     * @author Ryan La Forge
     */
    public boolean addVolunteer(Volunteer newVolunteer) {
        if ((newVolunteer == null) || (!newVolunteer.isValid())) {
            return false;
        }
        /**
         * This whole thing is necessary because we need to check whether a
         * scene with that name already exists regardless of whether they are
         * identical
         */
        if (this.hasVolunteers()) {
            Iterator<Volunteer> iter = this.volunteerIterator();
            while (iter.hasNext()) {
                Volunteer examinedVolunteer = iter.next();
                if (examinedVolunteer.getEmail().equals(newVolunteer.getEmail())) {
                    return false;
                }
            }
        }

        volunteers.add(newVolunteer);
        return true;

    }

    /**
     * ALL EQUIPMENT INFORMATION IS BELOW HERE*
     */
    /**
     * @return true if the script contains equipment, false if not. *
     */
    public boolean hasEquipment() {
        return !equipment.isEmpty();
    }

    /**
     * Creates an equipment with the specified information and adds it to the
     * script.
     *
     * @precon: The given volunteer should be valid.
     * @param newEquipment: The new equipment to add to the script.
     * @postcon the given equipment is added to the script.
     * @return true if successful, false if not.
     * @author Ryan La Forge
     */
    public boolean addEquipment(Equipment newEquipment) {
        if ((newEquipment == null) || (!newEquipment.isValid())) {
            return false;
        }
        /**
         * This whole thing is necessary because we need to check whether a
         * scene with that name already exists regardless of whether they are identical
         */
        if (this.hasEquipment()) {
            Iterator<Equipment> iter = this.equipmentIterator();
            while (iter.hasNext()) {
                Equipment examinedEquipment = iter.next();
                if (examinedEquipment.getEquipmentName().equals(newEquipment.getEquipmentName())) {

                    return false;

                }
            }
        }

        return equipment.add(newEquipment);

    }

    /**
     * removes the specified equipment from the list of equipment
     *
     * @param oldEquipment - the equipment to be deleted from the list
     * @postcon removes the given equipment from the script.
     * @return true if successful, false if not
     * @author Ryan La Forge
     */
    public boolean removeEquipment(Equipment oldEquipment) {
        return equipment.remove(oldEquipment);
    }

    /**
     * Gives you an iterator over the equipment in the script
     *
     * @return an iterator over the equipment in the script.
     * @author Ryan La Forge
     */
    public Iterator<Equipment> equipmentIterator() {
        return equipment.iterator();
    }

    /**
     */
    /**
     * Returns the list of the equipment associated with the Script.
     *
     * @return The list of the equipment associated with the Script.
     * @author Iain Workman
     */
    public BusinessObjectList<Equipment> equipment() {
        return equipment;
    }

    /**
     * Sets the list of equipment associated with the Script
     *
     * @param newEquipment The list of equipment associated with the Script.
     * @postcon the equipment in the scene is set to the given equipment.
     * @author Ian Workman
     */
    public void setEquipment(BusinessObjectList<Equipment> newEquipment) {
        equipment = newEquipment;
    }

    // Everything to do with the schedule is below
    /**
     * Returns the current filming schedule for the script
     *
     * @return The current filming schedule for the script
     * @author Iain Workman
     */
    public Schedule schedule() {
        return schedule;
    }

    /**
     * Sets the schedule for filming for this script to the provided Schedule
     *
     * @param newSchedule::Schedule ~ The new schedule for the script
     * @postcon the schedule in the script is set to the given schedule.
     * @author Iain Workman
     */
    public void setSchedule(Schedule newSchedule) {
        schedule = newSchedule;
    }

    public static void main(String[] args) {
        Script newScript;
        newScript = new Script("Practice Script");
    }

    /**
     * The following methods are not implemented for the script, as they are not needed.*
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toDescriptiveString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseBusinessObject clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void merge(BaseBusinessObject mergeObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
