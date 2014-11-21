/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volunteeravailability.login;

import database.Database;
import database.JdbcDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * A class to handle authentication of a user into the Volunteer Availability
 * Application
 * @author matthewgalbraith
 */
public class Login {
    
    private String username;
    private String password;
    private Boolean isValid;
    private Database database;
    
    
    //constructor with username and password
    public Login(Database database, String username, String password) {
        this.database = database;
        this.username = username;
        this.password = password;
        this.isValid = false;
    }
    
    /** 
    * Sends the username and password to be compared against the database
    * @throws java.sql.SQLException
    * @postcond isValid is set to true if username and password equal credentials from database
    * 
    */
     
    public void sendUsernamePassword() throws SQLException {
        
        String selectUsernamePasswordQuery
                = "SELECT "
                + "vol_emailaddress, vol_password "
                + "FROM t_volunteer "
                + "WHERE vol_emailaddress ='" + this.username + "' and "
                + "vol_password = '" + this.password +"'; ";
        ResultSet selectResults = null;
        try
        {
            selectResults = database.executeSelect(selectUsernamePasswordQuery);
            if(selectResults.next()){
                this.isValid = (this.username.equalsIgnoreCase(selectResults.getString(1))
                            && this.password.equals(selectResults.getString(2)));
            }
        } catch (SQLException ex)
        {
            throw ex;
        } finally
        {
            if (selectResults != null)
            {
                selectResults.close();
            }
        }
        
    }
    
    /**
     * Informs user of the successfulness of logging into the application
     * @return true if validation of user successful, otherwise false
     */
    public Boolean userValidated() {
        //TODO: IMPLEMENT
        return isValid;
    }
}