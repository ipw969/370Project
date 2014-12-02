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
public class EquipmentTest {
    
    public EquipmentTest() {
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
     * Test of setEquipmentName method, of class Equipment.
     */
    @Test
    public void testSetEquipmentName() {
        System.out.println("setEquipmentName");
        String equipmentName = "";
        Equipment instance = new Equipment();
        instance.setEquipmentName(equipmentName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwnerFirstName method, of class Equipment.
     */
    @Test
    public void testSetOwnerFirstName() {
        System.out.println("setOwnerFirstName");
        String ownerFirstName = "";
        Equipment instance = new Equipment();
        instance.setOwnerFirstName(ownerFirstName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwnerLastName method, of class Equipment.
     */
    @Test
    public void testSetOwnerLastName() {
        System.out.println("setOwnerLastName");
        String ownerLastName = "";
        Equipment instance = new Equipment();
        instance.setOwnerLastName(ownerLastName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwnerEmail method, of class Equipment.
     */
    @Test
    public void testSetOwnerEmail() {
        System.out.println("setOwnerEmail");
        String ownerEmail = "";
        Equipment instance = new Equipment();
        instance.setOwnerEmail(ownerEmail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAvailability method, of class Equipment.
     */
    @Test
    public void testSetAvailability_TimeInterval() {
        System.out.println("setAvailability");
        TimeInterval avail = null;
        Equipment instance = new Equipment();
        instance.setAvailability(avail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquipmentName method, of class Equipment.
     */
    @Test
    public void testGetEquipmentName() {
        System.out.println("getEquipmentName");
        Equipment instance = new Equipment();
        String expResult = "";
        String result = instance.getEquipmentName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwnerFirstName method, of class Equipment.
     */
    @Test
    public void testGetOwnerFirstName() {
        System.out.println("getOwnerFirstName");
        Equipment instance = new Equipment();
        String expResult = "";
        String result = instance.getOwnerFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwnerLastName method, of class Equipment.
     */
    @Test
    public void testGetOwnerLastName() {
        System.out.println("getOwnerLastName");
        Equipment instance = new Equipment();
        String expResult = "";
        String result = instance.getOwnerLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwnerEmail method, of class Equipment.
     */
    @Test
    public void testGetOwnerEmail() {
        System.out.println("getOwnerEmail");
        Equipment instance = new Equipment();
        String expResult = "";
        String result = instance.getOwnerEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailability method, of class Equipment.
     */
    @Test
    public void testGetAvailability() {
        System.out.println("getAvailability");
        Equipment instance = new Equipment();
        BusinessObjectList<TimeInterval> expResult = null;
        BusinessObjectList<TimeInterval> result = instance.getAvailability();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAvailability method, of class Equipment.
     */
    @Test
    public void testSetAvailability_BusinessObjectList() {
        System.out.println("setAvailability");
        BusinessObjectList<TimeInterval> newAvailability = null;
        Equipment instance = new Equipment();
        instance.setAvailability(newAvailability);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Equipment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Equipment instance = new Equipment();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDescriptiveString method, of class Equipment.
     */
    @Test
    public void testToDescriptiveString() {
        System.out.println("toDescriptiveString");
        Equipment instance = new Equipment();
        String expResult = "";
        String result = instance.toDescriptiveString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Equipment.
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Equipment instance = new Equipment();
        BaseBusinessObject expResult = null;
        BaseBusinessObject result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class Equipment.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        BaseBusinessObject mergeObject = null;
        Equipment instance = new Equipment();
        instance.merge(mergeObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
