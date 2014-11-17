/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A Class representing a collection of Business Objects.
 * BusinessObjectListeners can be registered with the collection and will be
 * notified if any of the contained BusinessObjects change state.
 *
 * @param <T extends BaseBusinessObject> ~ The type to store
 */
public class BusinessObjectList<T extends BaseBusinessObject> extends
        ArrayList<T>
        implements BusinessObjectListener
{

    // Constructor
    /**
     * Creates a new instance of an empty BusinessObjectCollection
     */
    public BusinessObjectList()
    {
        listeners = new ArrayList<>();
    }

    // Public Methods
    /**
     * Notifies all the listeners that the changed state of one of the contained
     * BusinessObjects has been altered
     *
     * @param currentState::boolean ~ The valid state of the sender
     * @param sender::BaseBusinessObject ~ The BusinessObject that sent the
     * notification
     */
    @Override
    public void changedStateAltered(boolean currentState,
            BaseBusinessObject sender)
    {
        for (BusinessObjectListener listener : listeners)
        {
            if (listener != null)
            {
                listener.changedStateAltered(currentState, sender);
            }
        }
    }

    /**
     * Notifies all the listeners that the valid state of one of the contained
     * BusinessObjects has been changed
     *
     * @param currentState::boolean ~ The valid state of the sender
     * @param sender::BaseBusinessObject ~ The BusinessObject that sent the
     * notification
     */
    @Override
    public void validStateAltered(boolean currentState,
            BaseBusinessObject sender)
    {
        for (BusinessObjectListener listener : listeners)
        {
            if (listener != null)
            {
                listener.validStateAltered(currentState, sender);
            }
        }
    }

    /**
     * Adds a BusinessObjectListListener to this BusinessObjectList's list of
     * listeners. Whenever the changed state, or valid state of the contained
     * BusinessObjects changes all the listeners will be notified. Listeners are
     * also notified when BusinessObjects are added or removed from the list
     *
     * @param listener::BusinessObjectListener ~ The object to be added to the
     * notified listeners list
     */
    public void addListener(BusinessObjectListListener listener)
    {
        if (listener != null && !listeners.contains(listener))
        {
            listeners.add(listener);
        }
    }

    /**
     * Removes a BusinessObjectListListener to this BusinessObjectList's list of
     * listeners. Whenever the changed state, or valid state of the contained
     * BusinessObjects changes all the listeners will be notified. Listeners are
     * also notified when BusinessObjects are added or removed from the list
     *
     * @param listener::BusinessObjectListener ~ The object to be removed from
     * the list of notified listeners.
     */
    public void removeListener(BusinessObjectListListener listener)
    {
        if (listener != null && listeners.contains(listener))
        {
            listeners.remove(listener);
        }
    }

    /**
     * Adds the provided BusinessObject to the end of the List
     *
     * @param businessObject::T ~ The BusinessObject to add to the list
     * @return true (as specified by Collection.add(E))
     */
    @Override
    public boolean add(T businessObject)
    {
        if (businessObject != null)
        {
            businessObject.addListener(this);
        }
        boolean result = super.add(businessObject);
        notifyListenersOfAdd(businessObject);
        return result;
    }

    /**
     * Inserts the specified BusinessObject at the specified position in this
     * list. Shifts the BusinessObject currently at that position (if any) and
     * any subsequent BusinessObject to the right (adds one to their indices)
     *
     * @param index::int ~ index at which the specified BusinessObject is to be
     * inserted
     * @param businessObject::T ~ BusinessObject to be inserted
     */
    @Override
    public void add(int index, T businessObject)
    {
        if (businessObject != null)
        {
            businessObject.addListener(this);
        }
        super.add(index, businessObject);
        notifyListenersOfAdd(businessObject);
    }

    /**
     * Removes the BusinessObject at the specified position in this list. Shifts
     * any subsequent BusinessObjects to the left (subtracts one from their
     * indices).
     *
     * @param index::int ~ The index of the BusinessObject to be removed
     * @return The BusinessObject that was removed from the list
     */
    @Override
    public T remove(int index)
    {
        T retrievedBusinessObject = super.remove(index);
        if (retrievedBusinessObject != null)
        {
            retrievedBusinessObject.removeListener(this);
        }
        notifyListenersOfRemove(retrievedBusinessObject);
        return retrievedBusinessObject;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if
     * it is present. If the list does not contain the element, it is unchanged.
     * More formally, removes the element with the lowest index i such that
     * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
     * Returns true if this list contained the specified element (or
     * equivalently, if this list changed as a result of the call).
     *
     * @param object::Object ~ element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(Object object)
    {
        boolean result = super.remove(object);
        if (result)
        {
            if (object instanceof BaseBusinessObject)
            {
                BaseBusinessObject passedBusinessObject
                        = (BaseBusinessObject) object;

                passedBusinessObject.removeListener(this);

                notifyListenersOfRemove(passedBusinessObject);
            }
        }
        return result;
    }

    /**
     * Removes all of the BusinessObjects from this list. The list will be empty
     * after this call returns.
     */
    @Override
    public void clear()
    {
        for (T currentBusinessObject : this)
        {
            if (currentBusinessObject != null)
            {
                currentBusinessObject.removeListener(this);
            }
        }
        super.clear();
        notifyListenersOfCleared();
    }

    /**
     * Appends all of the BusinessObjects in the specified collection to the end
     * of this list, in the order that they are returned by the specified
     * collection's Iterator. The behavior of this operation is undefined if the
     * specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this list, and this list is nonempty.)
     *
     * @param collection:: collection containing BusinessObjects to be added to
     * this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends T> collection)
    {
        boolean superChanged = super.addAll(collection);
        for (BaseBusinessObject currentBusinessObject : collection)
        {
            if (currentBusinessObject != null)
            {
                currentBusinessObject.addListener(this);
                notifyListenersOfAdd(currentBusinessObject);
            }
        }
        return superChanged;

    }

    /**
     * Inserts all of the BusinessObjects in the specified collection into this
     * list, starting at the specified position. Shifts the BusinessObject
     * currently at that position (if any) and any subsequent BusinessObjects to
     * the right (increases their indices). The new BusinessObjects will appear
     * in the list in the order that they are returned by the specified
     * collection's iterator.
     *
     * @param index::integer ~ index at which to insert the first BusinessObject
     * from the specified collection
     * @param collection::Collection ~ collection containing BusinessObjects to
     * be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(int index,
            Collection<? extends T> collection)
    {
        boolean superChanged = super.addAll(index, collection);
        for (BaseBusinessObject currentBusinessObject : collection)
        {
            currentBusinessObject.addListener(this);
            notifyListenersOfAdd(currentBusinessObject);
        }

        return superChanged;
    }

    /**
     * Removes from this list all of its BusinessObjects that are contained in
     * the specified collection.
     *
     * @param collection::Collection ~ collection containing elements to be
     * removed from this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> collection)
    {
        boolean returnResult = false;

        for (Object obj : collection)
        {
            if (obj instanceof BaseBusinessObject)
            {
                T currentBusinessObject
                        = (T) obj;

                boolean currentItemRemoved = remove(currentBusinessObject);
                if(currentItemRemoved)
                {
                    notifyListenersOfRemove(currentBusinessObject);
                }
                returnResult |= currentItemRemoved;
                
            }
        }
        return returnResult;
    }

    /**
     * Retains only the BusinessObjects in this list that are contained in the
     * specified collection. In other words, removes from this list all of its
     * BusinessObjects that are not contained in the specified collection.
     *
     * @param collection::Collection ~ collection containing BusinessObjects to
     * be retained in this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> collection)
    {
        boolean returnResult = false;
        clear();
        notifyListenersOfCleared();
        for (Object obj : collection)
        {
            if (obj instanceof BaseBusinessObject)
            {
                returnResult = true;
                T currentBusinessObject
                        = (T) obj;

                add(currentBusinessObject);
                notifyListenersOfAdd(currentBusinessObject);
            }
        }

        return returnResult;
    }

    // Private Methods
    /**
     * Helper function which notifies all current listeners that the passed
     * BusinessObject was added to the list
     *
     * @param sender::BaseBusinessObject ~ The BusinessObject which was added to
     * the list
     */
    private void notifyListenersOfAdd(BaseBusinessObject sender)
    {
        for (BusinessObjectListListener currentListener : listeners)
        {
            currentListener.businessObjectAdded(sender);
        }
    }

    /**
     * Helper function which notifies all current listeners that the passed
     * BusinessObject was removed from the list.
     *
     * @param sender::BaseBusinessObject ~ The BusinessObject which was removed
     * from the list
     */
    private void notifyListenersOfRemove(BaseBusinessObject sender)
    {
        for (BusinessObjectListListener currentListener : listeners)
        {
            currentListener.businessObjectRemoved(sender);
        }
    }

    /**
     * Helper function which notifies all current listeners the
     * BusinessObjectList was cleared.
     */
    private void notifyListenersOfCleared()
    {
        for (BusinessObjectListListener currentListener : listeners)
        {
            currentListener.listCleared();
        }
    }

    // Private Member Variables
    private final ArrayList<BusinessObjectListListener> listeners;
}
