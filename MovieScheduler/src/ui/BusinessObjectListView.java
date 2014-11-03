/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;
import businessobjects.BaseBusinessObject;
import businessobjects.BusinessObjectList;
import businessobjects.BusinessObjectListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.AbstractListModel;
/**
 *
 * @author iain
 */
public class BusinessObjectListView<T extends BaseBusinessObject>
    extends JList<T>
{
    BusinessObjectListView(BusinessObjectList<T> list)
    {
        BusinessObjectListModel<T> listModel = new BusinessObjectListModel<>();
        listModel.setData(list);
        this.setModel(listModel);
    }
    
    private class BusinessObjectListModel<T extends BaseBusinessObject> extends
            AbstractListModel<T> implements BusinessObjectListener
    {
        public void setData(BusinessObjectList<T> data)
        {
            this.data = data;
            data.addListener(this);
        }

        public T getElementAt(int index)
        {
            return data.get(index);
        }

        public int getSize()
        {
            return data.size();
        }
        
        public void changedStateAltered(boolean currentState, 
                BaseBusinessObject sender)
        {
            boolean objectFound = false;
            int indexOfSender = -1;
            for (int iCurrentObject = 0, count = data.size(); 
                    iCurrentObject < count && !objectFound;
                    iCurrentObject++)
            {
                if(data.get(iCurrentObject) == sender)
                {
                    objectFound = true;
                    indexOfSender = iCurrentObject;
                }
            }
            
            if(objectFound)
                fireContentsChanged(sender, indexOfSender, indexOfSender);
        }
        
        public void validStateAltered(boolean currentState,
                BaseBusinessObject sender)
        {
            
        }
        
        private BusinessObjectList<T> data;
    }
    
    public static void main(String[] args)
    {
        javax.swing.JFrame testFrame = new javax.swing.JFrame();
        testFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(100, 100);
        
        BusinessObjectList<businessobjects.Scene> testList = 
                new BusinessObjectList<>();
        
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
            public void actionPerformed(ActionEvent e)
            {
                testScene1.setName("Changed!");
            }        
        });
        javax.swing.JPanel testPanel = new javax.swing.JPanel();

        BusinessObjectListView<businessobjects.Scene> testListView = 
                new BusinessObjectListView<>(testList);
        testPanel.add(testAlterDataButton);
        testPanel.add(testListView);
        testFrame.add(testPanel);
        testFrame.setVisible(true);
    }
}
