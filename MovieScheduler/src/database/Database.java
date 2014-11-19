package database;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * A interface which defines a standard API for saving to and loading from the
 * persistent data store. Different implementations can be created by inheriting
 * from Database, and overriding the executeSelectImplementation,
 * executeInsertImplementation and executeUpdateImplementation methods.
 *
 * @author Iain Workman
 */
public abstract class Database
{
    // Private Member Variables
    /**
     * The connection string to be used to connect to the database.
     */
    private String connectionUrl;
    
    // Constructor
    /**
     * Base constructor for all databases. Sets the connection url which is used
     * to establish a connection to the database.
     *
     * @param url::String ~ The connection url to access the database.
     */
    public Database(String url)
    {
        if (!url.isEmpty())
        {
            connectionUrl = url;
        }
    }
    
    // Public Methods
    /**
     * Selects from the database using the provided query text, then returns the
     * results as a ResultSet. Note that the statement from which the ResultSet
     * was obtained may still be open. When you've done with the ResultsSet test
     * is its statement is not null and not closed. If both are true, close the
     * statement.
     *
     * @param queryText::String ~ The select query to execute
     * @return A ResultSet containing all the results returned by the database
     * @throws SQLException
     */
    public ResultSet executeSelect(String queryText) throws SQLException
    {
        return executeSelectImplementation(queryText);
    }

    /**
     * Inserts a new value into the database using the provided query text
     *
     * @param queryText::String ~ The insert query to execute
     * @throws SQLException
     */
    public void executeInsert(String queryText) throws SQLException
    {
        executeInsertImplementation(queryText);
    }

    /**
     * Updates a record, or set of records, in the database using the provided
     * query text.
     *
     * @param queryText::String ~ The update query to execute
     * @throws SQLException
     */
    public void executeUpdate(String queryText) throws SQLException
    {
        executeUpdateImplementation(queryText);
    }

    /**
     * Deletes a record, or set of records, in the database using the provided
     * query text.
     *
     * @param queryText::String ~ The delete query to execute
     * @throws SQLException
     */
    public void executeDelete(String queryText) throws SQLException
    {
        executeDeleteImplementation(queryText);
    }

    /**
     * Adds the provided command to the list of commands to be executed
     *
     * @param commandText::String ~ The command to add to the command list
     * @return True if the command was successfully added to the list, false
     * otherwise
     */
    public boolean addCommand(String commandText)
    {
        return addCommandImplementation(commandText);
    }

    /**
     * Removed the provided command from the list of commands to be executed, if
     * found.
     *
     * @param commandText::String ~ The command to be removed from the command
     * list
     * @return True if the command was removed from the list, False otherwise
     */
    public boolean removeCommand(String commandText)
    {
        return removeCommandImplementation(commandText);
    }

    /**
     * Removed all the commands from the command list
     */
    public void clearCommandList()
    {
        clearCommandListImplementation();
    }

    /**
     * Whether there are currently any commands in the command list
     *
     * @return True if the command list doesn't have any commands in it, false
     * otherwise
     */
    public boolean isCommandListEmpty()
    {
        return isCommandListEmptyImplementation();
    }

    /**
     * Executes all the commands in the command list in the order they were
     * added. The commands are run as an atomic transaction. If any of the
     * commands fail, non are committed to the database.
     *
     * @throws SQLException
     */
    public void executeCommandList() throws SQLException
    {
        executeCommandListImplementation();
    }

    // Protected Abstract Methods
    /**
     * The abstract method which base classes will override in order to provide
     * an implementation for executeSelect method to call. This should ensure
     * that the query string meets the requirement of the particular database
     * implementation, execute the query, build ResultSet, then return the built
     * ResultSet.
     *
     * @param queryText::String ~ The select query text to execute
     * @return The ResultSet returned by the database
     * @throws SQLException
     */
    protected abstract ResultSet executeSelectImplementation(String queryText)
            throws SQLException;

    /**
     * The abstract method which base classes will override in order to provide
     * an implementation for executeInsert method to call. This should ensure
     * that the query string meets the requirement of the particular database
     * implementation, executes the query, then returns the id of the inserted
     * record.
     *
     * @param queryText::String ~ The insert query to execute
     * @throws SQLException
     */
    protected abstract void executeInsertImplementation(String queryText)
            throws SQLException;

    /**
     * The abstract method which base classes will override in order to provide
     * an implementation for executeUpdate method to call. This should ensure
     * that the query string meets the requirement of the particular database
     * implementation then executes the query
     *
     * @param queryText::String ~ The update query to execute
     * @throws SQLException
     */
    protected abstract void executeUpdateImplementation(String queryText)
            throws SQLException;

    /**
     * The abstract method which base classes will override in order to provide
     * an implementation for the executeDelete method to call. This should
     * ensure that the query string meets the requirement of the particular
     * database implementation, then executes the query
     *
     * @param queryText::String ~ The update query to execute
     * @throws SQLException
     */
    protected abstract void executeDeleteImplementation(String queryText)
            throws SQLException;

    /**
     * The sub class implementation of the addCommand function
     *
     * @param commandText::String ~ The command that is to be added to the
     * command list
     * @return True if the command is successfully added to the command list,
     * false otherwise
     */
    protected abstract boolean addCommandImplementation(String commandText);

    /**
     * The sub class implementation of the removeCommand function
     *
     * @param commandText::String ~ The command that is to be removed from the
     * command list
     * @return True if the provided command was removed from the command list,
     * false otherwise
     */
    protected abstract boolean removeCommandImplementation(String commandText);

    /**
     * The sub class implementation of the clearCommandList function. Removed
     * all the commands from the command list.
     */
    protected abstract void clearCommandListImplementation();

    /**
     * The sub class implementation of the isCommandListEmpty function. Returns
     * whether or not the list of commands is empty.
     *
     * @return True if the command list is empty, false otherwise
     */
    protected abstract boolean isCommandListEmptyImplementation();

    /**
     * The sub class implementation of the executeCommandList function. Runs all
     * the commands in the command list as an atomic transaction
     *
     * @throws SQLException
     */
    protected abstract void executeCommandListImplementation()
            throws SQLException;

    /**
     * Returns the connection string to be used to connect to the database
     *
     * @return
     */
    protected String getConnectionUrl()
    {
        return connectionUrl;
    }

}
