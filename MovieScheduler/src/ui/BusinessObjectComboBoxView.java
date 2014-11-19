package ui;

import businessobjects.BaseBusinessObject;
import businessobjects.BusinessObjectList;
import businessobjects.BusinessObjectListListener;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * A ui widget class which visualizes a BusinessObjectList as a ComboBox
 *
 * @author Iain Workman
 * @param <T extends BaseBusinessObject>
 */
public class BusinessObjectComboBoxView<T extends BaseBusinessObject>
        extends JComboBox<T>
{

    // Constructor
    /**
     * Creates an instance of a BusinessObjectComboBoxView with the passed
     * BusinessObjectList as its data source
     *
     * @param data::BusinessObjectList<T> ~ The BusinessObjectList to use as the
     * data source for the combo box
     */
    public BusinessObjectComboBoxView(BusinessObjectList<T> data)
    {
        BusinessObjectComboBoxModel<T> model
                = new BusinessObjectComboBoxModel<>();

        model.setData(data);
        setModel(model);
    }
    
    public void stopModelListening()
    {
        BusinessObjectComboBoxModel model = 
                (BusinessObjectComboBoxModel<T>)getModel();
        
        model.stopListening();
    }

    // Private Classes
    /**
     * A Class representing a model which manages providing data to a
     * BusinessObjectComboBoxView
     *
     * @param <T extends BaseBusinessObject> ~ The type of BusinessObject to
     * manage in the model
     */
    private class BusinessObjectComboBoxModel<T extends BaseBusinessObject>
            implements ComboBoxModel<T>, BusinessObjectListListener
    {

        /**
         * The BusinessObjectList that the Model is presenting to Views.
         */
        private BusinessObjectList<T> data;

        /**
         * Views which are listening to this model
         */
        private ArrayList<ListDataListener> modelListeners;

        /**
         * The item currently selected in the model
         */
        T selectedItem;

        // Constructor
        /**
         * Creates a BusinessObjectComboBoxModel with no data
         */
        public BusinessObjectComboBoxModel()
        {
            data = new BusinessObjectList<>();
            data.addListener(this);

            modelListeners = new ArrayList<>();
            selectedItem = null;
        }

        // Public Methods
        
        /**
         * Sets the data for the model to the provided BusinessObjectList
         *
         * @param data::BusinessObjectList<T> ~ The list of BusinessObjects to
         * use as data for the model.
         */
        public void setData(BusinessObjectList<T> data)
        {
            if (this.data == null)
            {
                throw new RuntimeException("Can't use null as a "
                        + "BusinessObjectComboBoxModel data source");
            }

            if (this.data != null)
            {
                this.data.removeListener(this);
            }

            this.data = data;
            data.addListener(this);
        }

        /**
         * Sets the selected item in the model to the passed Object, if it is
         * found in the model
         *
         * @param anItem::Object ~ The Object to select
         */
        @Override
        public void setSelectedItem(Object anItem)
        {
            selectedItem = (T) anItem;
            boolean businessObjectFound = false;
            int indexOfSelectedItem = -1;
            for (int iBusinessObject = 0, listSize = data.size();
                    iBusinessObject < listSize && !businessObjectFound;
                    iBusinessObject++)
            {
                T currentBusinessObject = data.get(iBusinessObject);
                if (currentBusinessObject == selectedItem)
                {
                    businessObjectFound = true;
                    indexOfSelectedItem = iBusinessObject;
                }
            }

            if (businessObjectFound)
            {
                for (ListDataListener currentListener : modelListeners)
                {
                    currentListener.contentsChanged(
                            new ListDataEvent(anItem,
                                    ListDataEvent.CONTENTS_CHANGED,
                                    indexOfSelectedItem,
                                    indexOfSelectedItem));
                }
            }
        }

        /**
         * Returns the current selected item
         *
         * @return The current selected item
         */
        @Override
        public Object getSelectedItem()
        {
            return selectedItem;
        }

        /**
         * Returns the number of items within the model
         *
         * @return The number of items within the model
         */
        @Override
        public int getSize()
        {
            return data.size();
        }

        /**
         * Returns the BusinessObject at the given index
         *
         * @param index::int ~ The index of the BusinessObject to return
         * @return The BusinessObject at the provided index
         */
        @Override
        public T getElementAt(int index)
        {
            return data.get(index);
        }

        /**
         * Adds the provided ListDataListener to this Model
         *
         * @param l::ListDataListener ~ The ListDataListener to add to the Model
         */
        @Override
        public void addListDataListener(ListDataListener l)
        {
            modelListeners.add(l);
        }

        /**
         * Removes the provided ListDataListener to this Model
         *
         * @param l::ListDataListener ~ The ListDataListener to remove from the
         * Model
         */
        @Override
        public void removeListDataListener(ListDataListener l)
        {
            modelListeners.remove(l);
        }

        /**
         * Stops the model listening to changes to the underlying
         * BusinessObjectList. This should only be called immediately prior to
         * stopping using the model.
         */
        public void stopListening()
        {
            data.removeListener(this);
        }

        /**
         * Method which handles a BaseBusinessObject being added to the
         * BusinessObjectList which the model is representing
         *
         * @param itemAdded::BaseBusinessObject ~ The BusinessObject which was
         * added to the List
         */
        @Override
        public void businessObjectAdded(BaseBusinessObject itemAdded)
        {
            revalidate();
            repaint();
        }

        /**
         * Method which handles a BaseBusinessObject being removed from the
         * BusinessObjectList which was removed from the List
         *
         * @param itemRemoved::BaseBusinessObject ~ The item which was removed
         * from the List
         */
        @Override
        public void businessObjectRemoved(BaseBusinessObject itemRemoved)
        {
            revalidate();
            repaint();
        }

        /**
         * Method which handles the list being cleared
         */
        @Override
        public void listCleared()
        {
            revalidate();
            repaint();
        }

        /**
         * Method which handles a BusinessObject contained within the list
         * notifying the model that its valid state has changed. As we're not
         * editing the contained BusinessObjects within here we don't have to
         * listen to these notifications
         *
         * @param newState::boolean ~ The new valid state of the
         * BaseBusinessObject
         * @param sender::BaseBusinessObject ~ The BaseBusinessObject whose
         * valid state has changed.
         */
        @Override
        public void validStateAltered(boolean newState, BaseBusinessObject sender)
        {
            // Not editing the business objects here, not needed.
        }
        
        /**
         * Method which handles a BusinessObject contained within the list
         * notifying the model that its changes state has been altered.
         *
         * @param newState::boolean ~ The new changed state of the
         * BaseBusinessObject
         * @param sender::BaseBusinessObject ~ The BaseBusinessObject whose
         * changed date has been altered.
         */
        @Override
        public void changedStateAltered(boolean newState, BaseBusinessObject sender)
        {
            // Interate through the list to find the index of the changed 
            // businessObject
            boolean objectFound = false;
            int indexOfSender = -1;
            for (int iCurrentObject = 0, count = data.size();
                    iCurrentObject < count && !objectFound;
                    iCurrentObject++)
            {
                if (data.get(iCurrentObject) == sender)
                {
                    objectFound = true;
                    indexOfSender = iCurrentObject;
                }
            }

            if (objectFound)
            {
                for (ListDataListener currentListener : modelListeners)
                {
                    currentListener.contentsChanged(
                            new ListDataEvent(sender,
                                    ListDataEvent.CONTENTS_CHANGED,
                                    indexOfSender,
                                    indexOfSender));
                }
            }
        }

    }
}
