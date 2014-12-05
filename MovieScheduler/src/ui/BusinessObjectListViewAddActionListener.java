package ui;

import businessobjects.BaseBusinessObject;

/**
 * Interface which classes implement if they wish to listen to requests from a
 * BusinessObjectListView's add action.
 * @author iain workman
 */
public interface BusinessObjectListViewAddActionListener<T extends BaseBusinessObject> {
    /**
     * Method which handles a notification that a request to add an item has
     * been invoked by a BusinessObjectListView
     * @param event::BusinessObjectListViewAddEvent ~ The object which 
     * encapsulates that data associated with the add event.
     */
    public void onAddActionPerformed(BusinessObjectListViewAddEvent<T> event);
}
