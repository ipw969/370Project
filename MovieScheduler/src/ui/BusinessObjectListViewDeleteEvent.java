package ui;

import businessobjects.BaseBusinessObject;
import businessobjects.BusinessObjectList;

/**
 * Class which represents and event for deleting from a BusinessObjectListView
 *
 * @author iain workman
 */
public class BusinessObjectListViewDeleteEvent<T extends BaseBusinessObject> {

    /**
     * Creates a new instance of an delete event, which is being deleted from
     * the provided list
     *
     * @param listBeingDeletedFrom::BusinessObjectList<T> ~ The list being
     * deleted from
     * @param objectBeingDeleted::BaseBusinessObject ~ The object being deleted.
     */
    public BusinessObjectListViewDeleteEvent(
            BusinessObjectList<T> listBeingDeletedFrom,
            BaseBusinessObject objectBeingDeleted) {
        this.listBeingDeletedFrom = listBeingDeletedFrom;
        this.objectBeingDeleted = objectBeingDeleted;
    }

    /**
     * The list being added to
     *
     * @return The list being added to
     */
    public BusinessObjectList<T> getListBeingDeletedFrom() {
        return listBeingDeletedFrom;
    }

    /**
     * The object being deleted
     *
     * @return The object being deleted
     */
    public BaseBusinessObject getObjectBeingDeleted() {
        return objectBeingDeleted;
    }

    /**
     * The list being added to
     */
    private BusinessObjectList<T> listBeingDeletedFrom;
    private BaseBusinessObject objectBeingDeleted;
}
