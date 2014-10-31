
package businessobjects;
import businessobjects.*;
import businessobjects.BaseBusinessObject;

/**
 * Equipment data class
 * @author johnmason
 */
public class Equipment extends BaseBusinessObject
{
    String equipmentType; 
    int stock;   //the amount of equipment we currently have
    boolean isRental;
    float rentalCost;
    
    //empty constructor
    public Equipment(){setIsNew(true);}
    
    //full constructor for when the equipment is a rental
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

}
