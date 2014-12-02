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
public class BusinessObjectListenerTest {
    
    public BusinessObjectListenerTest() {
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
     * Test of validStateAltered method, of class BusinessObjectListener.
     */
    @Test
    public void testValidStateAltered() {
        System.out.println("validStateAltered");
        boolean newState = false;
        BaseBusinessObject sender = null;
        BusinessObjectListener instance = new BusinessObjectListenerImpl();
        instance.validStateAltered(newState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changedStateAltered method, of class BusinessObjectListener.
     */
    @Test
    public void testChangedStateAltered() {
        System.out.println("changedStateAltered");
        boolean newState = false;
        BaseBusinessObject sender = null;
        BusinessObjectListener instance = new BusinessObjectListenerImpl();
        instance.changedStateAltered(newState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class BusinessObjectListenerImpl implements BusinessObjectListener {

        public void validStateAltered(boolean newState, BaseBusinessObject sender) {
        }

        public void changedStateAltered(boolean newState, BaseBusinessObject sender) {
        }
    }
    
}
