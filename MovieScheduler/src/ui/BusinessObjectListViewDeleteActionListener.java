package ui;

import businessobjects.BaseBusinessObject;

/**
 * Interface which classes implement if they wish to listen to requests from a
 * BusinessObjectListView's delete action.
 * @author iain workman
 */
public interface BusinessObjectListViewDeleteActionListener<T extends BaseBusinessObject> {
    /**
     * Method which handles a notification that a request to delete an item has
     * been invoked by a BusinessObjectListView
     * @param event::BusinessObjectListViewDeleteEvent ~ The object which 
     * encapsulates that data associated with the delete event.
     */
    public void onDeleteActionPerformed(BusinessObjectListViewDeleteEvent<T> event);
}
