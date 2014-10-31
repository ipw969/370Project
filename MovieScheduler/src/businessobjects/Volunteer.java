
package businessobjects;
import businessobjects.*;
import java.awt.List;
/**
 * volunteer data class
 * @author johnmason
 */
public class Volunteer extends BaseBusinessObject
{
    String firstName;
    String lastName;
    String email;
    String phone;   //since we are not actually doing anything with it having it as a string will get rid of many errors
    BusinessObjectList <TimeInterval> availabilty = new BusinessObjectList();
    
    //empty constructor
    public Volunteer(){setIsNew(true);}
    
    //general constructor
    public Volunteer(String fName, String lName, String email, String phone, BusinessObjectList avail)
    {
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phone = phone;
        this.availabilty = avail;
        setIsNew(true);
    }
    
        //general constructor
    public Volunteer(String fName, String lName, String email, String phone)
    {
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phone = phone;
        setIsNew(true);
    }

    public void addAvailability(TimeInterval avail)
    {
        this.availabilty.add(avail);
        setHasChanged(true);
    }
    
    public void setFirstName(String name)
    {
        this.firstName = name;
        setHasChanged(true);
    }
    
    public void setLastName(String name)
    {
        this.lastName = name;
        setHasChanged(true);
    }
    
    public void setEmail(String email)
    {
        this.email = email;
        setHasChanged(true);
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
        setHasChanged(true);
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
