/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author iain
 */
public class BusinessObjectComboBoxView<T extends BaseBusinessObject>
        extends JComboBox<T>
{

    public BusinessObjectComboBoxView(BusinessObjectList<T> data)
    {
        BusinessObjectComboBoxModel<T> model = 
                new BusinessObjectComboBoxModel<>();
        
        model.setData(data);
        setModel(model);
    }

    private class BusinessObjectComboBoxModel<T extends BaseBusinessObject>
            implements ComboBoxModel<T>, BusinessObjectListListener
    {

        public BusinessObjectComboBoxModel()
        {
            data = new BusinessObjectList<>();
            data.addListener(this);

            modelListeners = new ArrayList<>();
            selectedItem = null;
        }

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

        @Override
        public void addListDataListener(ListDataListener l)
        {
            modelListeners.add(l);
        }

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

        @Override
        public void businessObjectAdded(BaseBusinessObject itemAdded)
        {
            revalidate();
            repaint();
        }

        @Override
        public void businessObjectRemoved(BaseBusinessObject itemRemoved)
        {
            revalidate();
            repaint();
        }

        @Override
        public void listCleared()
        {
            revalidate();
            repaint();
        }

        @Override
        public void validStateAltered(boolean newState, BaseBusinessObject sender)
        {
            // Not editing the business objects here, not needed.
        }

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

    }
}
