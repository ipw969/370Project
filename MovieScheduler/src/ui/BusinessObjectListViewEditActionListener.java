package ui;

import businessobjects.BaseBusinessObject;

/**
 * Interface which classes implement if they wish to listen to requests from a
 * BusinessObjectListView's edit action.
 * @author iain workman
 */
public interface BusinessObjectListViewEditActionListener<T extends BaseBusinessObject> {
    /**
     * Method which handles a notification that a request to edit an item has
     * been invoked by a BusinessObjectListView
     * @param event::BusinessObjectListViewEditEvent ~ The object which 
     * encapsulates that data associated with the edit event.
     */
    public void onEditActionPerformed(BusinessObjectListViewEditEvent event);
}
