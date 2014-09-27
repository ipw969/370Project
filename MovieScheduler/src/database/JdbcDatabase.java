/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 * A class which provides a JDBC specific implementation of the Database
 * interface. The JDBC driver must be registered with the system before any call
 * to this class can be made, using:
 * Class.forName("DRIVER_NAME");
 */
public class JdbcDatabase extends Database {
    
    public JdbcDatabase(String url, String username, String password)
            throws SQLException
    {
        super(url);
        connection = DriverManager.getConnection(getConnectionUrl(), 
                username/*"cmpt370_group06"*/,
                password/*"mu5jd8m1ae"*/);
    }
    
    /**
     * Executes the given query statement, and returns the result set given
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
        try{
            selectStatement = connection.createStatement();
            returnResults = selectStatement.executeQuery(queryText);
        } catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            if(selectStatement != null && !selectStatement.isClosed())
                selectStatement.close();
        }
        return returnResults;
    }
    
    /**
     * Inserts into the database using the provided query text
     * @param queryText::String ~ The query statement to execute
     * @return The id of the item inserted into the database
     * @throws SQLException 
     */
    @Override
    protected int executeInsertImplementation(String queryText)
            throws SQLException
    {
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(queryText, 
                    Statement.RETURN_GENERATED_KEYS);
            
            boolean success = (statement.executeUpdate() == 0);
            
            if(!success)
                throw new SQLException("Object could not be inserted");
            
            ResultSet generatedIds = statement.getGeneratedKeys();
            
            if(generatedIds.next())
                return generatedIds.getInt(1);
            else
                throw new SQLException("Object not inserted, did not receive " +
                        "return id");
        } catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            if(statement != null && !statement.isClosed())
                statement.close();
        }       
    }
    
    /**
     * Updates data in the database according to the provided query
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
            if(updatedResults == 0)
                throw new SQLException("Update failed to alter any data");
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            if(statement!= null && !statement.isClosed())
                statement.close();
        }
    }
    
    /**
     * The connection to the JDBC database
     */
    private final Connection connection;
}
