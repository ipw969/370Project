package ui;

import businessobjects.BaseBusinessObject;

/**
 * Class which represents an event for editing an item in a BusinessObjectListView
 * @author iain workman
 */
public class BusinessObjectListViewEditEvent {
    /**
     * Creates a new instance of an edit event, which is being requested for the
     * provided BusinessObject.
     * 
     */
    public BusinessObjectListViewEditEvent(BaseBusinessObject objectBeingEdited)
    {
        this.objectBeingEdited = objectBeingEdited;
    }
    
    /**
     * Returns the object being edited
     * @return The object being edited
     */
    public BaseBusinessObject getObjectBeingEdited()
    {
        return objectBeingEdited;
    }   
    
    /**
     * The BusinessObject being edited
     */
    private BaseBusinessObject objectBeingEdited;
}
