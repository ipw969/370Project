package ui;

import businessobjects.BaseBusinessObject;
import businessobjects.BusinessObjectList;
import businessobjects.BusinessObjectListListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;

/**
 * A ui view class for displaying information from a BusinessObjectList
 *
 * @author Iain Workman
 * @param <T extends BaseBusinessObject> ~ The type of BusinessObject contained
 * in the list whose data is to be displayed.
 */
public class BusinessObjectListView<T extends BaseBusinessObject>
        extends JList<T> {

    // Constructor
    /**
     * Creates an instance of a BusinessObjectListView to display the data in
     * the provided BusinessObjectList
     *
     * @param list::BusinessObjectList ~ The list which the view will use as a
     * model
     */
    BusinessObjectListView(BusinessObjectList<T> list) {
        BusinessObjectListModel<T> listModel = new BusinessObjectListModel<>();
        listModel.setData(list);
        this.setModel(listModel);
    }

    // Public Methods
    public void stopModelListening() {
        ListModel model = getModel();
        if (model != null && model instanceof BusinessObjectListModel) {
            BusinessObjectListModel<T> listModel = (BusinessObjectListModel<T>) model;
            listModel.stopListening();
        }
    }

    /**
     * Private class which represents the model which the BusinessObjectListView
     * will use to display its data
     *
     * @param <T extends BaseBusinessObject> ~ The list of BusinessObjects which
     * the model class will manage.
     */
    private class BusinessObjectListModel<T extends BaseBusinessObject> extends
            AbstractListModel<T> implements BusinessObjectListListener {

        /**
         * Sets the data for the model to the provided BusinessObjectList
         *
         * @param data::BusinessObjectList<T> ~ The list of BusinessObjects to
         * use as data for the model.
         */
        public void setData(BusinessObjectList<T> data) {
            this.data = data;
            data.addListener(this);
        }

        /**
         * Returns the BusinessObject at the given index
         *
         * @param index::int ~ The index of the BusinessObject to return
         * @return The BusinessObject at the provided index
         */
        @Override
        public T getElementAt(int index) {
            return data.get(index);
        }

        /**
         * Returns the number of items within the model
         *
         * @return The number of items within the model
         */
        @Override
        public int getSize() {
            return data.size();
        }

        /**
         * Handles one of the BusinessObjects contained within the managed list
         * notifying the model that its changedState has been altered
         *
         * @param currentState::boolean ~ The current changed state of the
         * sending BusinessObect
         * @param sender::BaseBusinessObject ~ The BusinessObject whose
         * changedState has been altered
         */
        @Override
        public void changedStateAltered(boolean currentState,
                BaseBusinessObject sender) {
            refreshBusinessObject(sender);
        }

        /**
         * Handles one of the BusinessObjects within the managed list notifying
         * the model that its validState has altered. We don't do anything with
         * this, as this class doesn't need to display BusinessObjects which are
         * being edited
         *
         * @param currentState::boolean ~ The current state of the sending
         * businessObject
         * @param sender::BaseBusinessObject ~ The BusinessObject which has sent
         * the notification
         */
        @Override
        public void validStateAltered(boolean currentState,
                BaseBusinessObject sender) {
            // We're not editing the object, so the validState doesn't matter.
        }

        /**
         * Handles the managed list notifying the model that is has had a
         * BusinessObject added to it
         *
         * @param itemAdded::BaseBusinessObject ~ The BusinessObject added to
         * the list.
         */
        @Override
        public void businessObjectAdded(BaseBusinessObject itemAdded) {
            refreshBusinessObject(itemAdded);
            revalidate();
            repaint();
        }

        /**
         * Handles the managed list notifying the model that it has had a
         * BusinessObject removed from it.
         *
         * @param itemRemoved::BaseBusinessObject ~ The BusinessObject which was
         * removed from the list.
         */
        @Override
        public void businessObjectRemoved(BaseBusinessObject itemRemoved) {
            refreshBusinessObject(itemRemoved);
            revalidate();
            repaint();
        }

        /**
         * Handles the managed list notifying the model that it has been emptied
         */
        @Override
        public void listCleared() {
            revalidate();
            repaint();
        }

        /**
         * Stops the model listening to changes to the underlying
         * BusinessObjectList. This should only be called immediately prior to
         * stopping using the model.
         */
        public void stopListening() {
            data.removeListener(this);
        }

        private void refreshBusinessObject(BaseBusinessObject businessObject) {
            // Iterate through the list to find the index of the changed 
            // businessObject
            boolean objectFound = false;
            int indexOfSender = -1;
            for (int iCurrentObject = 0, count = data.size();
                    iCurrentObject < count && !objectFound;
                    iCurrentObject++) {
                if (data.get(iCurrentObject) == businessObject) {
                    objectFound = true;
                    indexOfSender = iCurrentObject;
                }
            }

            if (objectFound) {
                fireContentsChanged(businessObject, indexOfSender, indexOfSender);
            }
        }

        /**
         * The BusinessObjectList that the Model is presenting to Views.
         */
        private BusinessObjectList<T> data;

    }

    // Static Methods
    /**
     * Simple class for displaying a BusinessObjectListView. This methods does
     * not provide unit testing. Rather it is simply to visualize the view as it
     * is being constructed
     *
     * @param args
     */
    public static void main(String[] args) {
        javax.swing.JFrame testFrame = new javax.swing.JFrame();
        testFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(100, 100);

        BusinessObjectList<businessobjects.Scene> testList
                = new BusinessObjectList<>();

        businessobjects.Scene testScene1 = new businessobjects.Scene(
                "TestScene1", "TestScene1");
        businessobjects.Scene testScene2 = new businessobjects.Scene(
                "TestScene2", "TestScene2");
        businessobjects.Scene testScene3 = new businessobjects.Scene(
                "TestScene3", "TestScene3");

        testList.add(testScene1);
        testList.add(testScene2);
        testList.add(testScene3);

        javax.swing.JButton testAlterDataButton = new javax.swing.JButton();
        testAlterDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testList.clear();
            }
        });
        javax.swing.JPanel testPanel = new javax.swing.JPanel();

        BusinessObjectListView<businessobjects.Scene> testListView
                = new BusinessObjectListView<>(testList);
        testPanel.add(testAlterDataButton);
        testPanel.add(testListView);
        testFrame.add(testPanel);
        testFrame.setVisible(true);
    }
}
