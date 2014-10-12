
package datatypes;

/**
 * Equipment data class
 * @author johnmason
 */
public class Equipment 
{
    String equipmentType; 
    int stock;   //the amount of equipment we currently have
    boolean isRental;
    float rentalCost;
    
    //empty constructor
    public Equipment(){}
    
    //full constructor for when the equipment is a rental
    public Equipment(String equipmentType, int stock, boolean isRental, float cost)
    {
        this.equipmentType = equipmentType;
        this.stock = stock;
        this.isRental = isRental;
        this.rentalCost = cost;    //sets the cost of rentals (per unit *NOT* total cost)

    }
    
    //partial constructor for when the equipment is not a rental so we can add it without needing to enter a cost
    public Equipment(String equipmentType, int stock, boolean isRental)
    {
        this.equipmentType = equipmentType;
        this.stock = stock;
        this.isRental = isRental;
    }    
    
    public void setEquipmentType(String equipmentType)
    {
        this.equipmentType = equipmentType;
    }
    
    public void setStock(int amount)
    {
        this.stock = amount;
    }
    
    public void setIsRental(boolean isRental)
    {
        this.isRental = isRental;
    }
    
    public void setCost(float cost)
    {
        this.rentalCost = cost;
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
