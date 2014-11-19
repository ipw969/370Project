package database;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class which provides a JDBC specific implementation of the Database
 * interface. The JDBC driver must be registered with the system before any call
 * to this class can be made, using: Class.forName("DRIVER_NAME");
 *
 * @author Iain Workman, Ryan Laforge
 */
public class JdbcDatabase extends Database
{
    // Private Member Variables
    
    /**
     * The connection to the JDBC database
     */
    private final Connection connection;

    /**
     * A command to be executed when attempting to perform several commands in
     * one transaction
     *
     */
    private ArrayList<String> commandList;
    
    // Constructor
    
    public JdbcDatabase(String url, String username, String password)
            throws SQLException
    {
        super(url);
        connection = DriverManager.getConnection(getConnectionUrl(),
                username/*"cmpt370_group06"*/,
                password/*"Raptorjesusisawesome55775"*/);
    }

    // Protected Methods
    
    /**
     * Executes the given query statement, and returns the result set given
     *
     * @param queryText::String ~ The query statement to execute
     * @return The ResultSet returned by the database
     * @throws SQLException
     */
    @Override
    protected ResultSet executeSelectImplementation(String queryText)
            throws SQLException
    {
        ResultSet returnResults = null;
        Statement selectStatement = null;
        try
        {
            selectStatement = connection.createStatement();
            returnResults = selectStatement.executeQuery(queryText);
        } catch (SQLException ex)
        {
            if (selectStatement != null && !selectStatement.isClosed())
            {
                selectStatement.close();
            }
            throw ex;

        }
        return returnResults;
    }

    /**
     * Inserts into the database using the provided query text
     *
     * @param queryText::String ~ The query statement to execute
     * @throws SQLException
     */
    @Override
    protected void executeInsertImplementation(String queryText)
            throws SQLException
    {
        PreparedStatement statement = null;

        try
        {
            statement = connection.prepareStatement(queryText,
                    Statement.RETURN_GENERATED_KEYS);

            boolean success = (statement.executeUpdate() == 0);

            if (!success)
            {
                throw new SQLException("Object could not be inserted");
            }

        } catch (SQLException ex)
        {
            throw ex;
        } finally
        {
            if (statement != null && !statement.isClosed())
            {
                statement.close();
            }
        }
    }

    /**
     * Updates data in the database according to the provided query
     *
     * @param queryText::String ~ The update query to execute
     * @throws SQLException
     */
    @Override
    protected void executeUpdateImplementation(String queryText)
            throws SQLException
    {
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            int updatedResults = statement.executeUpdate(queryText);
            if (updatedResults == 0)
            {
                throw new SQLException("Update failed to alter any data");
            }

        } catch (SQLException ex)
        {

            throw ex;

        } finally
        {
            if (statement != null && !statement.isClosed())
            {
                statement.close();
            }
        }
    }

    /**
     * deletes data in the database according to the queryText
     *
     * @param queryText the query to perform on the database.
     * @throws SQLException
     */
    @Override
    protected void executeDeleteImplementation(String queryText) throws SQLException
    {
        if (!queryText.contains("delete"))
        {
            throw new SQLException("The given query was not a delete query");
        }
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            int deleteResults = statement.executeUpdate(queryText);
            if (deleteResults == 0)
            {
                throw new SQLException("Update failed to delete any data");
            }

        } catch (SQLException ex)
        {

            throw ex;

        } finally
        {
            if (statement != null && !statement.isClosed())
            {
                statement.close();
            }
        }

    }

    /**
     * TODO: Add to commandList remove from command List empty commandList
     * execute commandList
     *
     */

    /**
     * Adds a query to the commandList.
     *
     * @precon the given queryText must be atomic, that is it must only execute
     * one command.
     * @param queryText The sql statement to add to the commandList.
     * @return true if successful, false if not
     */
    @Override
    public boolean addCommandImplementation(String queryText)
    {
        if (queryText == null)
        {
            return false;
        }
        return commandList.add(queryText);
    }

    /**
     * removes a query from the commandList
     *
     * @param queryText the query to remove from the commandList
     * @return true if successful, false if not
     */
    @Override
    protected boolean removeCommandImplementation(String queryText)
    {
        return commandList.remove(queryText);
    }

    /**
     * Empties the commandList
     */
    @Override
    protected void clearCommandListImplementation()
    {
        commandList.clear();
    }

    /**
     * @return true if the commandList is empty, false if not
     */
    @Override
    protected boolean isCommandListEmptyImplementation()
    {
        return commandList.isEmpty();
    }

    /**
     * This method executes all of the commands that are in the commandlist,
     * then empties the commandList. each query in the commandList is expected
     * to be atomic. A commit is not made unless all of the queries in the
     * commandList have been executed. If a single error occurs, a rollback is
     * issued and the database is left unchanged.
     *
     * @throws SQLException
     */
    @Override
    public void executeCommandListImplementation() throws SQLException
    {
        if (!isCommandListEmpty())
        {
            try
            {
                //The database will not save unless all of the sql statements succeed.
                connection.setAutoCommit(false);
                Iterator<String> commandIterator = commandListIterator();

                while (commandIterator.hasNext())
                {
                    String queryText = commandIterator.next();
                    if (queryText.contains("update"))
                    {
                        this.executeUpdateImplementation(queryText);
                    } else if (queryText.contains("insert"))
                    {
                        this.executeInsertImplementation(queryText);
                    } else if (queryText.contains("delete"))
                    {
                        this.executeDeleteImplementation(queryText);
                    } else if (queryText.contains("select"))
                    {
                        this.executeSelectImplementation(queryText);
                    } else
                    {
                        throw new SQLException("The following string is not a "
                                + "proper posgreSQL query string. \n" + queryText);
                    }
                }
                connection.commit();

            } catch (SQLException e)
            {
                //If an SQLexception was thrown here, then a datbase connection 
                //error occurred and the changes will be rolled back anyway.
                try
                {
                    connection.rollback();
                } catch (SQLException ex)
                {

                }
            } finally
            {
                connection.setAutoCommit(true);
                this.clearCommandList();
            }
        }
    }

    /**
     *
     * @return an iterator over all of the queries in the commandList
     */
    protected Iterator<String> commandListIterator()
    {
        return commandList.iterator();
    }
}
