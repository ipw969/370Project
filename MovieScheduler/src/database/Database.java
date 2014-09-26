/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 * A interface which defines a standard API for saving to and loading from the
 * persistent data store. Different implementations can be created by inheriting
 * from Database, and overriding the executeSelectImplementation,
 * executeInsertImplementation and executeUpdateImplementation methods.
 * 
 */
public abstract class Database {
    /**
     * Base constructor for all databases. Sets the connection url which is used
     * to establish a connection to the database.
     * @param url::String ~ The connection url to access the database.
     */
    public Database(String url) {
        if(!url.isEmpty())
            connectionUrl = url;
    }
    
    /**
     * Selects from the database using the provided query text, then returns
     * the results as a ResultSet
     * @param queryText::String ~ The select query to execute
     * @return A ResultSet containing all the results returned by the database
     * @throws SQLException
     */
    public ResultSet executeSelect(String queryText) throws SQLException
    {
        return executeSelectImplementation(queryText);
    }
    
    /**
     * Inserts a new value into the database using the provided query text, then
     * returns the id of the newly inserted record.
     * @param queryText::String ~ The insert query to execute
     * @return The id of the inserted record
     * @throws SQLException 
     */
    public int executeInsert(String queryText) throws SQLException
    {
        return executeInsertImplementation(queryText);
    }    
    
    /**
     * Updates a record, or set of records, in the database using the provided
     * query text.
     * @param queryText::String ~ The update query to execute 
     * @throws SQLException
     */
    public void executeUpdate(String queryText) throws SQLException
    {
        executeUpdateImplementation(queryText);
    }
    
    /**
     * The abstract method which base classes will override in order to provide
     * an implementation for executeSelect method to call. This should ensure
     * that the query string meets the requirement of the particular database
     * implementation, open a connection to the database, execute the query,
     * build ResultSet, close the connection to the database, then return the
     * built ResultSet.
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
     * implementation, open a connection to the database, execute the query,
     * then return the id of the inserted record
     * @param queryText::String ~ The insert query to execute
     * @return The id of the inserted item
     * @throws SQLException 
     */
    protected abstract int executeInsertImplementation(String queryText)
            throws SQLException;

    /**
     * The abstract method which base classes will override in order to provide
     * an implementation for executeUpdate method to call. This should ensure
     * that the query string meets the requirement of the particular database
     * implementation, open a connection to the database, execute the query and
     * then close the connection to the database
     * @param queryText::String ~ The update query to execute
     * @throws SQLException 
     */
    protected abstract void executeUpdateImplementation(String queryText)
            throws SQLException;
    
    protected String getConnectionUrl()
    {
        return connectionUrl;
    }
    
    private String connectionUrl;
}
