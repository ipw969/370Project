package businessobjects;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class will hold all information based on a scene including the
 * associated volunteers and equipment.
 *
 * @author Ryan La Forge
 */
public class Scene extends BaseBusinessObject {

    private String name;
    private String description;
    private BusinessObjectList<Volunteer> necessaryVolunteers;
    private BusinessObjectList<Equipment> necessaryEquipment;
    private boolean scheduled;
    private boolean complete;

    /* @author Ryan**/
    public Scene(String name, String description) {
        super();

        this.name = name;
        this.description = description;

        necessaryVolunteers = new BusinessObjectList<>();
        necessaryEquipment = new BusinessObjectList<>();

        this.updateError("The name of the scene was not entered.", !name.isEmpty());

    }

    /**
     * retrieves the name of the scene
     *
     * @return the name of the scene
     * @author Ryan *
     */
    public String getName() {

        return this.name;

    }

    /**
     * Sets the name of the scene to the given value.
     *
     * @param newName- the new name of the scene
     * @postcon The scene now has the given name
     * @author Ryan
     */
    public void setName(String newName) {
        name = newName;
        this.setHasChanged(true);
    }

    /**
     * Retrieves the list of volunteers associated with this scene.
     *
     * @return the list of volunteers associated with this scene.
     * @author Iain*
     */
    public BusinessObjectList<Volunteer> getVolunteers() {
        return this.necessaryVolunteers;
    }

    /**
     * Retrieves the list of equipment associated with this scene
     *
     * @return the list of equipment associated with this scene
     * @author Iain*
     */
    public BusinessObjectList<Equipment> getEquipment() {
        return this.necessaryEquipment;
    }

    /**
     * Sets the description of this scene to the given value
     *
     * @param newDescription the new description of the scene.
     * @postcon the description is now the given description.
     * @author Ryan
     */
    public void setDescription(String newDescription) {
        description = newDescription;
        this.setHasChanged(true);
    }

    /**
     * Retrieves this scene's description.
     *
     * @return the description of the scene
     * @author Ryan*
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return true if the scene is scheduled, false if not*
     */
    public boolean isScheduled() {
        return scheduled;
    }

    /**
     * @return true if the scene has been filmed, false otherwise
     * @author Ryan*
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Sets whether the scene is scheduled or not
     *
     * @param isScheduled whether or not the scene is scheduled
     * @author Ryan
     */
    public void setScheduled(boolean isScheduled) {
        scheduled = isScheduled;
        this.setHasChanged(true);
    }

    /**
     * Sets whether the scene has been filmed or not (The producer may want to
     * change the status if a mistake was found)
     *
     * @param isComplete whether or no the scene was filmed.
     * @author Ryan
     */
    public void setComplete(boolean isComplete) {
        complete = isComplete;
        this.setHasChanged(true);
    }

    /**
     * @return true if the scene has volunteers associated with it, false if not
     * @author Ryan*
     */
    public boolean hasVolunteers() {
        return !necessaryVolunteers.isEmpty();
    }

    /**
     * @return true if the scene has volunteers associated with it ,false if
     * not*
     */
    /**
     * @precon the list of volunteers must not be empty
     * @return an iterator for the necessary volunteers of the scene
     * @throws RuntimeException
     * @author Ryan
     */
    public Iterator<Volunteer> volunteerIterator() throws RuntimeException {
        if (necessaryVolunteers.isEmpty()) {
            throw new RuntimeException("Cannot iterate over an empty volunteerList-Scene volunteerIterator");
        }
        return necessaryVolunteers.iterator();
    }

    /**
     * @return true if the scene has equipment associated with it, false if not
     * @author Ryan
     */
    public boolean hasEquipment() {
        return !necessaryEquipment.isEmpty();
    }

    /**
     * @precon the scene must have equipment associated with it.
     *
     * @return an iterator for the list of equipment.
     * @throws RuntimeException
     * @author Ryan
     */
    public Iterator<Equipment> equipmentIterator() throws RuntimeException {
        if (necessaryEquipment.isEmpty()) {
            throw new RuntimeException("Cannot iterate over an empty equipment list-Scene equipmentIterator");
        }
        return necessaryEquipment.iterator();
    }

    /**
     * @param e The equipment to check for
     * @return true if this scene contains the given equipment, false if not.
     * @author Ryan*
     */
    public boolean containsEquipment(Equipment e) {
        return necessaryEquipment.contains(e);
    }

    /**
     * Checks to see if the scene contains the given volunteer.
     *
     * @param v The volunteer to check for
     * @return true if this scene contains the given volunteer, false if not
     * @author Ryan*
     */
    public boolean containsVolunteer(Volunteer v) {
        if (this.hasVolunteers() && necessaryVolunteers.contains(v)) {
            return true;
        } else if (!this.hasVolunteers()) {
            return false;
        } else {
            //compare the volunteer by name and email to see if they are the same person. Everyone logs in via their email, so it must be unique. 
            Iterator<Volunteer> iter = this.volunteerIterator();
            while (iter.hasNext()) {
                Volunteer examinedVolunteer = iter.next();
                if (examinedVolunteer.getEmail().equals(v.getEmail())) {
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Adds the given volunteer to the list of volunteers
     *
     * @precon the volunteer must be in the scene's list of volunteers
     * @param newVolunteer the new volunteer to add to the list of volunteers.
     * @postcon the given volunteer is added to the scene
     * @author Ryan
     */
    public void addVolunteer(Volunteer newVolunteer) {
        if (this.containsVolunteer(newVolunteer)) {
            this.updateError("That volunteer already exists in the list of volunteers for this scene. Unable to add Volunteer. Note: All volunteers are expected to have a unique email.", true);
        } else {
            necessaryVolunteers.add(newVolunteer);
            this.setHasChanged(true);
        }
    }

    /**
     * Adds the given equipment to the list of equipment
     *
     * @precon the equipment must not already be in the list of equipment.
     * @param newEquipment thew new equipment to list of equipment.
     * @postcon the given equipment is added to the scene
     * @author Ryan
     */
    public void addEquipment(Equipment newEquipment) {
        if (this.containsEquipment(newEquipment)) {
            this.updateError("That equipment already exists in the list of equipment", true);
        } else {
            necessaryEquipment.add(newEquipment);
            this.setHasChanged(true);
        }

    }

    /**
     * Removes the given volunteer from the list of volunteers for this scene
     *
     * @precon The volunteer must be in the list of volunteers associated with
     * this scene.
     * @param oldVolunteer the volunteer to remove from the list of volunteers
     * for this scene
     * @postcon The given volunteer is removed from the scene
     */
    public void removeVolunteer(Volunteer oldVolunteer) {
        if (!this.containsVolunteer(oldVolunteer)) {
            updateError("The given volunteer does not exist.", true);
        } else {
            necessaryVolunteers.remove(oldVolunteer);
            this.setHasChanged(true);
        }
    }

    /**
     * Removes the given equipment from the list of equipment for this scene
     *
     * @precon The equipment must be in the list of equipment for this scene.
     * @param oldEquipment the equipment to remove from the list of equipment
     * for this scene The given equipment is removed from the scene.
     */
    public void removeEquipment(Equipment oldEquipment) {
        if (!this.containsEquipment(oldEquipment)) {
            this.updateError("The given equipment does not exist", true);
        } else {
            necessaryEquipment.remove(oldEquipment);
            this.setHasChanged(true);
        }
    }

    /**
     * Returns the name of this scene.
     *
     * @return the name of the scene to use as the toString() method.
     * @author Ryan
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Builds descriptive information about all aspects of this scene including
     * the name, description, volunteers, equipment, and whether it is complete
     * r scheduled or niether.
     *
     * @return A descriptive string of the scene.
     * @author Ryan
     */
    @Override
    public String toDescriptiveString() {
        StringBuilder newString = new StringBuilder();
        newString.append(this.name + "\n");
        if (!necessaryVolunteers.isEmpty()) {
            newString.append("List of volunteers:\n");
            for (Volunteer tempVolunteer : necessaryVolunteers) {
                newString.append(tempVolunteer.getFirstName() + " " + tempVolunteer.getLastName() + "\n");
            }
        } else {
            newString.append("No volunteers currently assigned to this scene\n");
        }

        if (!necessaryEquipment.isEmpty()) {
            newString.append("List of equipment:\n");
            for (Equipment tempEquipment : necessaryEquipment) {
                newString.append(tempEquipment.getEquipmentName() + "\n owner name:" + tempEquipment.getOwnerFirstName() + " " + tempEquipment.getOwnerLastName() + "\n owner email:" + tempEquipment.getOwnerEmail() + "\n\n");
            }

        } else {
            newString.append("No equipment assigned to this scene\n");
        }

        if (this.isScheduled()) {
            newString.append("The scene is scheduled");
        } else {
            newString.append("The scene is not scheduled.");
        }

        newString.append("\n");
        if (this.isComplete()) {
            newString.append("Complete\n");
        } else {
            newString.append("Not Complete\n");
        }
        return newString.toString();
    }

    public static void main(int args[]) {
        //test the 
    }

    /**
     * Builds a completely deep clone of this scene.
     *
     * @return a deep clone of this scene.
     * @throws CloneNotSupportedException
     * @author Ryan
     */
    @Override
    public Scene clone() throws CloneNotSupportedException {
        Scene cloneScene = (Scene) super.clone();
        cloneScene.setVolunteers(new BusinessObjectList<Volunteer>());
        cloneScene.setEquipment(new BusinessObjectList<Equipment>());

        cloneScene.setName(this.getName());
        cloneScene.setDescription(this.getDescription());

        if (this.hasVolunteers()) {
            Iterator<Volunteer> volunteerIter = volunteerIterator();
            while (volunteerIter.hasNext()) {
                Volunteer currentVolunteer = (Volunteer) volunteerIter.next();
                Volunteer clonedVolunteer = (Volunteer) currentVolunteer.clone();
                cloneScene.addVolunteer(clonedVolunteer);
            }
        }

        if (this.hasEquipment()) {
            //This is done in two steps to avoid the concurrentmodificationexception.
            Iterator<Equipment> equipmentIter = equipmentIterator();
            LinkedList<Equipment> equipmentToClone = new LinkedList<Equipment>();
            while (equipmentIter.hasNext()) {
                BaseBusinessObject equipment = equipmentIter.next();
                equipmentToClone.add((Equipment) equipment);

            }
            for (int i = 0; i < equipmentToClone.size() && equipmentToClone.get(i) != null; i++) {
                Equipment equipment = equipmentToClone.get(i);
                Equipment clonedEquipment = (Equipment) equipment.clone();
                cloneScene.addEquipment(clonedEquipment);
            }

        }

        cloneScene.setScheduled(this.isScheduled());
        cloneScene.setComplete(this.isComplete());

        return cloneScene;
    }

    /**
     * Merges the given scene into this scene.
     *
     * @param mergeObject The scene to merge into this scene
     * @postcon This object is made to have the exact same traits as the given
     * object.
     */
    @Override
    public void merge(BaseBusinessObject mergeObject) {
        if (mergeObject == null) {
            throw new RuntimeException("The given mergeObject was null for the scene merge.");
        } else if (!(mergeObject instanceof Scene)) {
            throw new RuntimeException("The given mergeObject is not an instance of Scene for the scene merge");
        }

        Scene mergeScene = (Scene) mergeObject;

        necessaryVolunteers.clear();
        necessaryEquipment.clear();

        if (mergeScene.hasVolunteers()) {
            Iterator<Volunteer> volunteerIter = mergeScene.volunteerIterator();
            while (volunteerIter.hasNext()) {
                Volunteer newVolunteer = volunteerIter.next();
                this.addVolunteer(newVolunteer);
            }
        }

        if (mergeScene.hasEquipment()) {
            Iterator<Equipment> equipmentIter = mergeScene.equipmentIterator();
            while (equipmentIter.hasNext()) {
                Equipment newEquipment = equipmentIter.next();
                this.addEquipment(newEquipment);
            }
        }

        this.setName(mergeScene.getName());
        this.setDescription(mergeScene.getDescription());
        this.setScheduled(mergeScene.isScheduled());
        this.setComplete(mergeScene.isComplete());
    }

    public void setEquipment(BusinessObjectList<Equipment> equipment) {
        necessaryEquipment = equipment;
    }

    public void setVolunteers(BusinessObjectList<Volunteer> volunteers) {
        necessaryVolunteers = volunteers;
    }
}
