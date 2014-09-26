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
/**
 * A class which provides a JDBC specific implementation of the Database
 * interface.
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
     * 
     * @param queryText
     * @return
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
     * 
     * @param queryText
     * @return
     * @throws SQLException 
     */
    @Override
    protected int executeInsertImplementation(String queryText)
            throws SQLException
    {
        return 0;
    }
    
    /**
     * 
     * @param queryText
     * @throws SQLException 
     */
    @Override
    protected void executeUpdateImplementation(String queryText)
            throws SQLException
    {
        
    }
    
    private Connection connection;
}
