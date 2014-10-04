/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

/**
 * An interface which is implemented by any class which wishes to listen to
 * changes to the state of a BusinessObject. State changes which are signalled
 * include: 
 *  1. A change to the error state of the BusinessObject, that is when it moves
 * from a valid state to an invalid state or vice-versa
 *  2. A change to the hasChanged state of the BusinessObject, that is when it
 * moves from the not changed state (eg it has just been loaded from the 
 * database) to a changed state (eg it has just be altered by some user input)
 */
public interface BusinessObjectListener {
    /**
     * Indicates that the BusinessObject being listened to has moved from an
     * invalid state to a valid state, or vice-versa.
     * @param newState::boolean ~ The new valid state that the object has moved 
     * to
     */
    public void validStateAltered(boolean newState);
    
    /**
     * Indicates that the BusinessObject data has been changed since it was
     * loaded from the database
     */
    public void changedStateAltered();
}
