/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matthew
 */
public class BusinessObjectListListenerTest {
    
    public BusinessObjectListListenerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of businessObjectAdded method, of class BusinessObjectListListener.
     */
    @Test
    public void testBusinessObjectAdded() {
        System.out.println("businessObjectAdded");
        BaseBusinessObject itemAdded = null;
        BusinessObjectListListener instance = new BusinessObjectListListenerImpl();
        instance.businessObjectAdded(itemAdded);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of businessObjectRemoved method, of class BusinessObjectListListener.
     */
    @Test
    public void testBusinessObjectRemoved() {
        System.out.println("businessObjectRemoved");
        BaseBusinessObject itemRemoved = null;
        BusinessObjectListListener instance = new BusinessObjectListListenerImpl();
        instance.businessObjectRemoved(itemRemoved);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listCleared method, of class BusinessObjectListListener.
     */
    @Test
    public void testListCleared() {
        System.out.println("listCleared");
        BusinessObjectListListener instance = new BusinessObjectListListenerImpl();
        instance.listCleared();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class BusinessObjectListListenerImpl implements BusinessObjectListListener {

        public void businessObjectAdded(BaseBusinessObject itemAdded) {
        }

        public void businessObjectRemoved(BaseBusinessObject itemRemoved) {
        }

        public void listCleared() {
        }
    }
    
}
