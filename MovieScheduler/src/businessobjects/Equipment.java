package businessobjects;

/**
 * Equipment data class
 *
 * @author johnmason
 */
public class Equipment extends BaseBusinessObject 
{

    String equipmentName;
    String ownerFirstName;
    String ownerLastName;
    String ownerEmail;
    BusinessObjectList<TimeInterval> availability = new BusinessObjectList<>();

    //empty constructor
    public Equipment() 
    {
        setIsNew(true);
    }

    //full constructor for when the equipment is a rental
    public Equipment(String equipmentName, String ownerFirstName, String ownerLastName, String ownerEmail) 
    {
        this.equipmentName = equipmentName;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.ownerEmail = ownerEmail;
        setIsNew(true);
    }

    //full constructor for when the equipment is a rental

    public Equipment(String equipmentName, String ownerFirstName, String ownerLastName,
            String ownerEmail, BusinessObjectList<TimeInterval> avail) 
    {
        this.equipmentName = equipmentName;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.ownerEmail = ownerEmail;
        this.availability = avail;

        setIsNew(true);
    }

    public void setEquipmentName(String equipmentName) 
    {
        this.equipmentName = equipmentName;
        setHasChanged(true);
    }

    public void setOwnerFirstName(String ownerFirstName) 
    {
        this.ownerFirstName = ownerFirstName;
        setHasChanged(true);
    }

    public void setOwnerLastName(String ownerLastName) 
    {
        this.ownerLastName = ownerLastName;
        setHasChanged(true);
    }

    public void setOwnerEmail(String ownerEmail) 
    {
        this.ownerEmail = ownerEmail;
        setHasChanged(true);
    }
    
    public void addAvailability(TimeInterval avail)
    {
        this.availability.add(avail);
        setHasChanged(true);
    }

    public String getEquipmentName() 
    {
        return this.equipmentName;
    }

    public String getOwnerFirstName() 
    {
        return this.ownerFirstName;
    }

    public String getOwnerLastName() 
    {
        return this.ownerLastName;
    }

    public String getOwnerEmail() 
    {
        return this.ownerEmail;
    }
   
    public BusinessObjectList <TimeInterval> getAvailability()
   {
       return availability;
   }
    
    @Override
    public String toString() 
    {
        return equipmentName + "," + ownerLastName + "," + ownerFirstName;
    }

    @Override
    public String toDescriptiveString() 
    {
        StringBuilder newString = new StringBuilder();
        newString.append("Equipment name: " + this.equipmentName + "\n");
        newString.append("Owners last name: " + this.ownerLastName + "\n");
        newString.append("Owners first name: " + this.ownerFirstName + "\n");
        newString.append("Owners email address: " + this.ownerEmail + "\n");

        return newString.toString();
    }

    @Override
    public BaseBusinessObject clone() throws CloneNotSupportedException 
    {
        Equipment cloneEquipment = (Equipment) super.clone();
        cloneEquipment.setEquipmentName(this.getEquipmentName());
        cloneEquipment.setOwnerLastName(this.getOwnerLastName());
        cloneEquipment.setOwnerFirstName(this.getOwnerFirstName());
        cloneEquipment.setOwnerEmail(this.getOwnerEmail());

        return cloneEquipment;
    }

    @Override
    public void merge(BaseBusinessObject mergeObject) 
    {
        if (mergeObject == null) {
            throw new RuntimeException("The given mergeObject was null for the volunteer merge.");
        } else if (!(mergeObject instanceof Scene)) {
            throw new RuntimeException("The given mergeObject is not an instance of volunteer for the volunteer merge");
        }

        Equipment mergeEquipment = (Equipment) mergeObject;

        this.setEquipmentName(mergeEquipment.getEquipmentName());
        this.setOwnerLastName(mergeEquipment.getOwnerLastName());
        this.setOwnerFirstName(mergeEquipment.getOwnerFirstName());
        this.setOwnerEmail(mergeEquipment.getOwnerEmail());
    }
}
