/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;
import businessobjects.BaseBusinessObject;
/**
 * A base class from which all actions in the system will be derived.
 * Provides a standard interface for performing a given action on a business
 * object (eg. saving, loading)
 */
public abstract class BaseAction {
    // Public Methods
    
    /**
     * Sets the business object on which this action is to be run.
     * @param businessObject::BaseBusinessObject ~ The business object on which
     * this action is to be run.
     */
    void setBusinessObject(BaseBusinessObject businessObject)
    {
        //ToDo: Implement
    }
    
    BaseBusinessObject businessObject()
    {
        //ToDo: Implement
        return new BaseBusinessObject(-1);
    }
    
    /**
     * The last error message encountered by the Action
     * @return 
     */
    public String lastErrorMessage()
    {
        //ToDo: Implement
        return new String();
    }
    
    /**
     * Performs the action.
     */
    public void run()
    {
        runImplementation();
    }
    
    /**
     * Whether or not the action was performed successfully.
     * @return True if action was performed successfully, false otherwise
     */
    public boolean wasSuccessful()
    {
        //ToDo: Implement
        return false;
    }
    
    // Protected Methods
    
    /**
     * Method which defines the actual actions to take for the derived
     * classes particular implementation of the action. 
     * For info on why this is structured like this, see:
     * http://en.wikipedia.org/wiki/Non-virtual_interface_pattern
     */
    protected abstract void runImplementation();
}
