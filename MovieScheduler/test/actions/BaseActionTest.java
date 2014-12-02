/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import businessobjects.BaseBusinessObject;
import database.Database;
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
public class BaseActionTest {
    
    public BaseActionTest() {
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
     * Test of getBusinessObject method, of class BaseAction.
     */
    @Test
    public void testGetBusinessObject() {
        System.out.println("getBusinessObject");
        BaseAction instance = null;
        BaseBusinessObject expResult = null;
        BaseBusinessObject result = instance.getBusinessObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lastErrorMessage method, of class BaseAction.
     */
    @Test
    public void testLastErrorMessage() {
        System.out.println("lastErrorMessage");
        BaseAction instance = null;
        String expResult = "";
        String result = instance.lastErrorMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class BaseAction.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        BaseAction instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wasSuccessful method, of class BaseAction.
     */
    @Test
    public void testWasSuccessful() {
        System.out.println("wasSuccessful");
        BaseAction instance = null;
        boolean expResult = false;
        boolean result = instance.wasSuccessful();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBusinessObject method, of class BaseAction.
     */
    @Test
    public void testSetBusinessObject() {
        System.out.println("setBusinessObject");
        BaseBusinessObject targetBusinessObject = null;
        BaseAction instance = null;
        instance.setBusinessObject(targetBusinessObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runImplementation method, of class BaseAction.
     */
    @Test
    public void testRunImplementation() {
        System.out.println("runImplementation");
        BaseAction instance = null;
        instance.runImplementation();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWasSuccessful method, of class BaseAction.
     */
    @Test
    public void testSetWasSuccessful() {
        System.out.println("setWasSuccessful");
        boolean successful = false;
        BaseAction instance = null;
        instance.setWasSuccessful(successful);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setErrorMessage method, of class BaseAction.
     */
    @Test
    public void testSetErrorMessage() {
        System.out.println("setErrorMessage");
        String errorMessage = "";
        BaseAction instance = null;
        instance.setErrorMessage(errorMessage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDatabase method, of class BaseAction.
     */
    @Test
    public void testGetDatabase() {
        System.out.println("getDatabase");
        BaseAction instance = null;
        Database expResult = null;
        Database result = instance.getDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class BaseActionImpl extends BaseAction {

        public BaseActionImpl() {
            super(null);
        }

        public void runImplementation() {
        }
    }
    
}
