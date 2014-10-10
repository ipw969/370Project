
package datatypes;

/**
 * volunteer data class
 * @author johnmason
 */
public class Volunteer 
{
    String firstName;
    String lastName;
    String email;
    String phone;   //since we are not actually doing anything with it having it as a string will get rid of many errors
    
    
    //empty constructor
    public Volunteer(){}
    
    //general constructor
    public Volunteer(String fName, String lName, String email, String phone)
    {
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phone = phone;
    }

    public void setFirstName(String name)
    {
        this.firstName = name;
    }
    
    public void setLastName(String name)
    {
        this.lastName = name;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    } 
 
    public String getFirstName()
    {
        return this.firstName;
    }
    
    public String getLastName()
    {
        return this.lastName;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public String getPhone()
    {
        return this.phone;
    }
    
}
