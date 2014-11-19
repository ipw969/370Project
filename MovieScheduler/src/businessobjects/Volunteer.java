package businessobjects;
import businessobjects.*;
import java.awt.List;
/**
 * volunteer data class
 * @author John Mason
 */
public class Volunteer extends BaseBusinessObject
{
    // Private Member Variables
    String firstName;
    String lastName;
    String email;
    String phone;   //since we are not actually doing anything with it having it as a string will get rid of many errors
    BusinessObjectList <TimeInterval> availability = new BusinessObjectList<>();
    
    // Constructors
    
    //empty constructor
    public Volunteer(){setIsNew(true);}
    
    //general constructor
    public Volunteer(String fName, String lName, String email, String phone, 
            BusinessObjectList<TimeInterval> avail)
    {
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phone = phone;
        this.availability = avail;
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

    // Public Methods
    public void addAvailability(TimeInterval avail)
    {
        this.availability.add(avail);
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
    
   public BusinessObjectList <TimeInterval> getAvailability()
   {
       return availability;
   }

    @Override
    public String toString()
    {
        return lastName + ", " + firstName;
    }

    @Override
    public String toDescriptiveString()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseBusinessObject clone()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void merge(BaseBusinessObject mergeObject)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
