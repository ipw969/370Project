/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volunteeravailability.login;


/**
 * A class to handle authentication of a user into the Volunteer Availability
 * Application
 * @author matthewgalbraith
 */
public class Login {
    
    private String username;
    private String password;
    private Boolean isValid;
    
    //constructor with username and password
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        this.isValid = false;
        //TODO: Implement encryption of password
    }
    
    /** 
    * Sends the username and password to be compared against the database
    * @postcond isValid is set to true if username and password equal
    * 
    */
    public void sendUsernamePassword() {
        //TODO: IMPLEMENT
    }
    
    /**
     * Informs user of the successfulness of logging into the application
     * @return true if validation of user successful, otherwise false
     */
    public Boolean userValidated() {
        //TODO: IMPLEMENT
        return false;
    }
}