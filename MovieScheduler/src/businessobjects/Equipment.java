package businessobjects;

/**
 * Equipment data class
 *
 * @author John Mason
 */
public class Equipment extends BaseBusinessObject
{

    // Private Member Variables

    String equipmentType;
    int stock;   //the amount of equipment we currently have
    boolean isRental;
    float rentalCost;
    BusinessObjectList<TimeInterval> availabilities;

    // Constructor(s)
    /**
     * Empty Constructor
     */
    public Equipment()
    {
        setIsNew(true);
    }

    /**
     *
     * @param equipmentType
     * @param stock
     * @param isRental
     * @param cost
     */
    public Equipment(String equipmentType, int stock, boolean isRental, float cost)
    {
        this.equipmentType = equipmentType;
        this.stock = stock;
        this.isRental = isRental;
        this.rentalCost = cost;    //sets the cost of rentals (per unit *NOT* total cost)
        setIsNew(true);

    }

    //partial constructor for when the equipment is not a rental so we can add it without needing to enter a cost
    public Equipment(String equipmentType, int stock, boolean isRental)
    {
        this.equipmentType = equipmentType;
        this.stock = stock;
        this.isRental = isRental;
        setIsNew(true);

    }

    // Public Methods
    public void setEquipmentType(String equipmentType)
    {
        this.equipmentType = equipmentType;
        setHasChanged(true);
    }

    public void setStock(int amount)
    {
        this.stock = amount;
        setHasChanged(true);

    }

    public void setIsRental(boolean isRental)
    {
        this.isRental = isRental;
        setHasChanged(true);

    }

    public void setCost(float cost)
    {
        this.rentalCost = cost;
        setHasChanged(true);

    }

    public void setAvailabilities(BusinessObjectList<TimeInterval> availabilities)
    {
        this.availabilities = availabilities;
    }

    public BusinessObjectList<TimeInterval> getAvailabilities()
    {
        return availabilities;
    }

    public String getEquipmentType()
    {
        return this.equipmentType;
    }

    public int getStock()
    {
        return this.stock;
    }

    public boolean getIsRental()
    {
        return this.isRental;
    }

    public float getCost()
    {
        return this.rentalCost;
    }

    @Override
    public String toString()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
