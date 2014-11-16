/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import java.util.HashSet;
import java.util.ArrayList;
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
     * Creates an instance of a BaseBusinessObject
     * @postcond ~
     * isValid() == true
     * isNew() == true
     * hasChanged() == false
     */
    public BaseBusinessObject()
    {
        errorMessages = new HashSet<>();
        listeners = new ArrayList<>();
        hasChanged = false;
        isNew = true;        
    }
    
    // Public methods
    
    /**
     * Whether the business object is currently in a valid state
     * @return True if the business object has no associated errors, false 
     * otherwise
     */
    public final boolean isValid()
    {
        return errorMessages.isEmpty();
    }
    
    /**
     * Whether the business object has been altered since it was last saved,
     * loaded from the database or newly created.
     * @return True if changes have been made to this business object, false
     * otherwise
     */
    public final boolean hasChanged()
    {
        return hasChanged;
    }
    
    /**
     * Sets whether the BusinessObject is considered new to the database or not.
     * This value should be set to false after an action loads an the 
     * BusinessObject from the database, or after an action successfully INSERTS 
     * a BusinessObject to the database.
     * @param newState::boolean ~ The state to indicate whether the 
     * BusinessObject is new or not
     */
    public final void setIsNew(boolean newState)
    {
        isNew = newState;
    }
    
    /**
     * Indicates whether or not the BusinessObject is new to the DataBase, or
     * whether it is loaded an represents an item which already exists in the
     * database
     * @return True if the item has never been saved, false otherwise.
     */
    public final boolean isNew()
    {
        return isNew;
    }
    
    /**
     * Returns all the error messages currently associated with the 
     * BusinessObject, appended together with a new line after each message. If
     * the BusinessObject is currently valid, then an empty string is returned
     * @return The error messages associated with the BusinessObject, one to
     * a line
     */
    public String errorMessage()
    {
        StringBuilder errorMessageStringBuilder = new StringBuilder();
        for(String currentErrorMessage : errorMessages)
        {
            errorMessageStringBuilder.append(currentErrorMessage);
            errorMessageStringBuilder.append('\n');
        }
        
        if(errorMessageStringBuilder.length() > 0)
            errorMessageStringBuilder.deleteCharAt(
                    errorMessageStringBuilder.length() - 1);
        
        return errorMessageStringBuilder.toString();
    }
    
    /**
     * Adds a BusinessObjectListener to this BusinessObject's list of listeners.
     * Whenever the changed state, or valid state of the BusinessObject changes
     * all the listeners will be notified.
     * @param listener::BusinessObjectListener ~ The object to be added to the
     * notified listeners list
     */
    public void addListener(BusinessObjectListener listener)
    {
        if(listener != null && !listeners.contains(listener))
            listeners.add(listener);
    }
    
     /**
     * Removes a BusinessObjectListener to this BusinessObject's list of 
     * listeners. Whenever the changed state, or valid state of the 
     * BusinessObject changes all the listeners will be notified.
     * @param listener::BusinessObjectListener ~ The object to be removed from
     * the list of notified listeners.
     */
    public void removeListener(BusinessObjectListener listener)
    {
        if(listener != null && listeners.contains(listener))
            listeners.remove(listener);
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
     * Note: Class is marked final because, though it needs to be accessed by
     * derived classes, it should never be overridden.
     * see http://en.wikipedia.org/wiki/Fragile_base_class for justification.
     * @param changedState::boolean ~ The new changed state of the object
     */
    public void setHasChanged(boolean changedState)
    {
        if(hasChanged != changedState)
        {
            hasChanged = changedState;
            notifyListenersOfChangedStateAltered();
        }
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
     * Note: Class is marked final because, though it needs to be accessed by
     * derived classes, it should never be overridden.
     * see http://en.wikipedia.org/wiki/Fragile_base_class for justification.
     */
    protected final void updateError(String errorMessage, boolean passesTest)
    {
        boolean validStateBefore = isValid();
        if(passesTest)
            errorMessages.remove(errorMessage);
        else
            errorMessages.add(errorMessage);
        boolean validStateAfter = isValid();
        
        if(validStateBefore != validStateAfter)
            notifyListenersOfValidStateAltered();        
    }
    
    
    // Private Methods
    
    /**
     * Notifies all the listeners that the valid state of the BusinessObject
     * has been changed
     */
    private void notifyListenersOfValidStateAltered()
    {
        for (BusinessObjectListener listener : listeners)
        {
            if(listener != null)
                listener.validStateAltered(isValid(), this);
        }
    }

    private void notifyListenersOfChangedStateAltered()
    {
        for (BusinessObjectListener listener : listeners)
        {
            if(listener != null)
                listener.changedStateAltered(hasChanged, this);
        }
    }

    // Private Member Variables
    /**
     * A Set of all the error messages associated with the BusinessObject. The
     * existence of any errorMessages indicates that the BusinessObject is not
     * currently in a valid state to be saved to the database.
     */
    private final HashSet<String> errorMessages;
    
    /**
     * A collection of all the objects which are currently listening to changes
     * to this BusinessObject.
     */
    private final ArrayList<BusinessObjectListener> listeners; 
    
    /**
     * Flag indicating that some data element of the BusinessObject has been 
     * altered since it was initially loaded from the database.
     */
    private boolean hasChanged;
    
    /**
     * Flag indicating that the BusinessObject has never been saved to the 
     * database. If isNew == true, then the database will run an INSERT rather
     * than an UPDATE when saving.
     */
    private boolean isNew;
    
}