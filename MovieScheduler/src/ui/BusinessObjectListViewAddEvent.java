package ui;

import businessobjects.BaseBusinessObject;
import businessobjects.BusinessObjectList;

/**
 * Class which represents and event for adding to a BusinessObjectListView
 * @author iain workman
 */
public class BusinessObjectListViewAddEvent <T extends BaseBusinessObject> {
    /**
     * Creates a new instance of an add event, which is being added to the 
     * provided list
     * @param listBeingAddedTo::BusinessObjectList<T> ~ The list being added to
     */
    public BusinessObjectListViewAddEvent(BusinessObjectList<T> listBeingAddedTo)
    {
        this.listBeingAddedTo = listBeingAddedTo;
    }
    
    /**
     * The list being added to
     * @return The list being added to
     */
    public BusinessObjectList<T> getListBeingAddedTo()
    {
        return listBeingAddedTo;
    }
    
    /**
     * The list being added to
     */
    private BusinessObjectList<T> listBeingAddedTo;
}
