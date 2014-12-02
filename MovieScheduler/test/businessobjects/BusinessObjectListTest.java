/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import java.util.Collection;
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
public class BusinessObjectListTest {
    
    public BusinessObjectListTest() {
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
     * Test of changedStateAltered method, of class BusinessObjectList.
     */
    @Test
    public void testChangedStateAltered() {
        System.out.println("changedStateAltered");
        boolean currentState = false;
        BaseBusinessObject sender = null;
        BusinessObjectList instance = new BusinessObjectList();
        instance.changedStateAltered(currentState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validStateAltered method, of class BusinessObjectList.
     */
    @Test
    public void testValidStateAltered() {
        System.out.println("validStateAltered");
        boolean currentState = false;
        BaseBusinessObject sender = null;
        BusinessObjectList instance = new BusinessObjectList();
        instance.validStateAltered(currentState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addListener method, of class BusinessObjectList.
     */
    @Test
    public void testAddListener() {
        System.out.println("addListener");
        BusinessObjectListListener listener = null;
        BusinessObjectList instance = new BusinessObjectList();
        instance.addListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeListener method, of class BusinessObjectList.
     */
    @Test
    public void testRemoveListener() {
        System.out.println("removeListener");
        BusinessObjectListListener listener = null;
        BusinessObjectList instance = new BusinessObjectList();
        instance.removeListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class BusinessObjectList.
     */
    @Test
    public void testAdd_GenericType() {
        System.out.println("add");
        BaseBusinessObject businessObject = null;
        BusinessObjectList instance = new BusinessObjectList();
        boolean expResult = false;
        boolean result = instance.add(businessObject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class BusinessObjectList.
     */
    @Test
    public void testAdd_int_GenericType() {
        System.out.println("add");
        int index = 0;
        BaseBusinessObject businessObject = null;
        BusinessObjectList instance = new BusinessObjectList();
        instance.add(index, businessObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class BusinessObjectList.
     */
    @Test
    public void testRemove_int() {
        System.out.println("remove");
        int index = 0;
        BusinessObjectList instance = new BusinessObjectList();
        BaseBusinessObject expResult = null;
        BaseBusinessObject result = instance.remove(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class BusinessObjectList.
     */
    @Test
    public void testRemove_Object() {
        System.out.println("remove");
        Object object = null;
        BusinessObjectList instance = new BusinessObjectList();
        boolean expResult = false;
        boolean result = instance.remove(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class BusinessObjectList.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        BusinessObjectList instance = new BusinessObjectList();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAll method, of class BusinessObjectList.
     */
    @Test
    public void testAddAll_Collection() {
        System.out.println("addAll");
        Collection collection = null;
        BusinessObjectList instance = new BusinessObjectList();
        boolean expResult = false;
        boolean result = instance.addAll(collection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAll method, of class BusinessObjectList.
     */
    @Test
    public void testAddAll_int_Collection() {
        System.out.println("addAll");
        int index = 0;
        Collection collection = null;
        BusinessObjectList instance = new BusinessObjectList();
        boolean expResult = false;
        boolean result = instance.addAll(index, collection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAll method, of class BusinessObjectList.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        Collection collection = null;
        BusinessObjectList instance = new BusinessObjectList();
        boolean expResult = false;
        boolean result = instance.removeAll(collection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retainAll method, of class BusinessObjectList.
     */
    @Test
    public void testRetainAll() {
        System.out.println("retainAll");
        Collection collection = null;
        BusinessObjectList instance = new BusinessObjectList();
        boolean expResult = false;
        boolean result = instance.retainAll(collection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
