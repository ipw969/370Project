/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessObjects;

/**
 * A base class from which all business object classes in the system will be
 * derived. The base class includes a validation mechanism, which reports, in
 * a standard way, if the business object is in an invalid state, plus
 * mechanisms for obtaining description of the warning messages pertaining to 
 * these errors. 
 * For information on deriving a class from this one see updateErrors() and
 * setHasChanged()
 * ToDo:
 *  - Implementation
 *  - Could do with some sort of event mechanism for signaling that the state
 *    has changed, and that the error state has changed.
 */
public class BaseBusinessObject {
    
    // Constructor
    
    /**
     * Creates an instance of a BaseBusinessObject with the provided id to be
     * used as the primary key identifier.
     * @param id::int ~ The primary key identifier of the constructed object
     */
    public BaseBusinessObject(int id)
    {
        //ToDo: Implement
    }
    
    // Public methods
    
    /**
     * Whether the business object is currently in a valid state
     * @return True if the business object has no associated errors, false 
     * otherwise
     */
    boolean isValid()
    {
        //ToDo: Implement
        return false;
    }
    
    /**
     * Whether the business object has been altered since it was last saved,
     * loaded from the database or newly created.
     * @return True if changes have been made to this business object, false
     * otherwise
     */
    boolean hasChanged()
    {
        //ToDo: Implement
        return false;
    }
    
    /**
     * The unique identifier of this business object. If this object is newly
     * created, and not yet saved, this value will be -1
     * @return The unique identifier of this business object, or -1 if it is 
     * newly created and as yet unsaved.
     */
    int id() 
    {
        //ToDo: Implement
        return -1;
    }
    
    //Protected Methods
    /**
     * Method which updates a particular error associated with this business
     * object. If passesTest is true, then the associated error message should
     * be removed from the object, otherwise the error should be ensured to
     * be present.
     * Usage example in derived classes:
     * Let's say we have a class Cat, which contains a field name.
     * Then let's say we wish  to ensure that the name is never empty. Then we 
     * can add the following to the setter field for name within Cat:
     * void setName(String newName)
     * {
     *      name = newName;
     *      updateErrors("Cat name cannot be empty", !name.isEmpty());
     * }
     * 
     * @param errorMessage::String ~ A description of the error
     * @param passesTest::boolean ~ Whether or not the test for this error has
     * been passed
     */
    void updateError(String errorMessage, boolean passesTest)
    {
        //ToDo: Implement
    }
    
    /**
     * Sets the changed state of the object to the provided changedState
     * This should be called in all setters for derived classes. This flag is
     * then used as an indicator that the object needs to be saved.
     * Usage in derived classes:
     * Let's say we have a class Cat, which contains a field name. Whenever
     * the setter for this member variable is called, we will need to call 
     * setHasChanged() to ensure that the ui knows that the object is unsaved,
     * and needs to warn the user before they exit a dialog.
     * void setName(string newName)
     * {
     *      name = newName;
     *      setHasChanged()
     * }
     * @param changedState::boolean ~ The new changed state of the object
     */
    void setHasChanged(boolean changedState)
    {
        //ToDo: Implement
    }
}
