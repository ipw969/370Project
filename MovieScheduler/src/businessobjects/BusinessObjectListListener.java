package businessobjects;

import businessobjects.BaseBusinessObject;

/**
 * Interface which classes implement if they wish to listen to changes to a
 * BusinessObjectList. Classes should implements the functions: public void
 * businessObjectAdded(BaseBusinessObject) {} public void
 * businessObjectAdded(BaseBusinessObject) {}
 *
 * then register themselves as listeners to a given BusinessObjectList via:
 * listToListenTo.addListener(this);
 *
 * @author Iain Workman
 */
public interface BusinessObjectListListener extends BusinessObjectListener {

    /**
     * Indicates that the BusinessObjectList has had a member added to it
     *
     * @param itemAdded::BaseBusinessObject ~ The BusinessObject which was added
     * to the list
     */
    public abstract void businessObjectAdded(BaseBusinessObject itemAdded);

    /**
     * Indicates that the BusinessObjectList has had a member removed from it
     *
     * @param itemRemoved::BaseBusinessObject ~ The BusinessObject which was
     * removed from the list
     */
    public abstract void businessObjectRemoved(BaseBusinessObject itemRemoved);

    /**
     * Indicates that the BusinessObjectList has all its members removed.
     */
    public abstract void listCleared();
}
