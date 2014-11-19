package actions;

import businessobjects.BaseBusinessObject;
import database.Database;

/**
 * A base class from which all actions in the system will be derived. Provides a
 * standard interface for performing a given action on a business object (eg.
 * saving, loading)
 * @author Iain Workman
 */
public abstract class BaseAction
{
    // Private Member Variables

    /**
     * The BusinessObject on which the action is performed (in the case of a
     * save action) or the returned BusinessObject (in the case of a load
     * action)
     */
    private BaseBusinessObject businessObject;
    /**
     * Whether or not the action has been performed successfully
     */
    private boolean wasSuccessful;
    /**
     * The last encountered error message
     */
    private String lastErrorMessage;
    /**
     * The database on which the action will be performed
     */
    private final Database database;

    // Constructor
    /**
     * Creates an instance of a BaseAction which will act on the provided
     * Datebase.
     *
     * @param database::Database ~ The database on which to perform the Action.
     */
    public BaseAction(Database database)
    {
        this.database = database;
        wasSuccessful = false;
    }

    // Public Methods
    /**
     * Returns the business object which was returned by the performed action.
     * If the action has not been run, ran but failed, or is not of a type which
     * returns a BusinessObject, then a RuntimeException is thrown. I.e, for the
     * normal usage pattern, was successful should always be called before this
     * by any class that wishes to obtain the BusinessObject retrieved by an
     * action
     *
     * @return The BusinessObject this action returned
     * @throws RuntimeException
     */
    public BaseBusinessObject getBusinessObject() throws RuntimeException
    {
        return businessObject;
    }

    /**
     * The last error message encountered by the Action
     *
     * @return
     */
    public String getLastErrorMessage()
    {
        return lastErrorMessage;
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
     *
     * @return True if action was performed successfully, false otherwise
     */
    public boolean wasSuccessful()
    {
        return wasSuccessful;
    }

    // Protected Methods
    /**
     * Sets the business object on which this action is to be run.
     *
     * @param targetBusinessObject::BaseBusinessObject ~ The business object on
     * which this action is to be run.
     */
    protected void setBusinessObject(BaseBusinessObject targetBusinessObject)
    {
        businessObject = targetBusinessObject;
    }

    /**
     * Method which defines the actual actions to take for the derived classes
     * particular implementation of the action. For info on why this is
     * structured like this, see:
     * http://en.wikipedia.org/wiki/Non-virtual_interface_pattern
     */
    protected abstract void runImplementation();

    /**
     * Sets the success state of the Action. This should be called in
     * runImplementation with a false argument whenever an unrecoverable error
     * was encountered, or as true just before returning upon success
     *
     * @param successful::boolean ~ Whether or not the action was successful
     */
    protected void setWasSuccessful(boolean successful)
    {
        wasSuccessful = successful;
    }

    /**
     * Sets the error message that the action stores upon failure of its
     * operation
     *
     * @param errorMessage::String ~ The error message associated with the
     * failed action
     */
    protected void setErrorMessage(String errorMessage)
    {
        lastErrorMessage = errorMessage;
    }

    /**
     * Returns the database on which the action will be performed
     *
     * @return The database on which the action will be performed
     */
    protected Database getDatabase()
    {
        return database;
    }

}
