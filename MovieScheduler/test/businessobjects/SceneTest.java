/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import java.util.Iterator;
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
public class SceneTest {
    
    public SceneTest() {
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
     * Test of getName method, of class Scene.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Scene instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Scene.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String newName = "";
        Scene instance = null;
        instance.setName(newName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVolunteers method, of class Scene.
     */
    @Test
    public void testGetVolunteers() {
        System.out.println("getVolunteers");
        Scene instance = null;
        BusinessObjectList<Volunteer> expResult = null;
        BusinessObjectList<Volunteer> result = instance.getVolunteers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquipment method, of class Scene.
     */
    @Test
    public void testGetEquipment() {
        System.out.println("getEquipment");
        Scene instance = null;
        BusinessObjectList<Equipment> expResult = null;
        BusinessObjectList<Equipment> result = instance.getEquipment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Scene.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String newDescription = "";
        Scene instance = null;
        instance.setDescription(newDescription);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Scene.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Scene instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isScheduled method, of class Scene.
     */
    @Test
    public void testIsScheduled() {
        System.out.println("isScheduled");
        Scene instance = null;
        boolean expResult = false;
        boolean result = instance.isScheduled();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isComplete method, of class Scene.
     */
    @Test
    public void testIsComplete() {
        System.out.println("isComplete");
        Scene instance = null;
        boolean expResult = false;
        boolean result = instance.isComplete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScheduled method, of class Scene.
     */
    @Test
    public void testSetScheduled() {
        System.out.println("setScheduled");
        boolean isScheduled = false;
        Scene instance = null;
        instance.setScheduled(isScheduled);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setComplete method, of class Scene.
     */
    @Test
    public void testSetComplete() {
        System.out.println("setComplete");
        boolean isComplete = false;
        Scene instance = null;
        instance.setComplete(isComplete);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasVolunteers method, of class Scene.
     */
    @Test
    public void testHasVolunteers() {
        System.out.println("hasVolunteers");
        Scene instance = null;
        boolean expResult = false;
        boolean result = instance.hasVolunteers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of volunteerIterator method, of class Scene.
     */
    @Test
    public void testVolunteerIterator() {
        System.out.println("volunteerIterator");
        Scene instance = null;
        Iterator<Volunteer> expResult = null;
        Iterator<Volunteer> result = instance.volunteerIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasEquipment method, of class Scene.
     */
    @Test
    public void testHasEquipment() {
        System.out.println("hasEquipment");
        Scene instance = null;
        boolean expResult = false;
        boolean result = instance.hasEquipment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equipmentIterator method, of class Scene.
     */
    @Test
    public void testEquipmentIterator() {
        System.out.println("equipmentIterator");
        Scene instance = null;
        Iterator<Equipment> expResult = null;
        Iterator<Equipment> result = instance.equipmentIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsEquipment method, of class Scene.
     */
    @Test
    public void testContainsEquipment() {
        System.out.println("containsEquipment");
        Equipment e = null;
        Scene instance = null;
        boolean expResult = false;
        boolean result = instance.containsEquipment(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsVolunteer method, of class Scene.
     */
    @Test
    public void testContainsVolunteer() {
        System.out.println("containsVolunteer");
        Volunteer v = null;
        Scene instance = null;
        boolean expResult = false;
        boolean result = instance.containsVolunteer(v);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addVolunteer method, of class Scene.
     */
    @Test
    public void testAddVolunteer() {
        System.out.println("addVolunteer");
        Volunteer newVolunteer = null;
        Scene instance = null;
        instance.addVolunteer(newVolunteer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEquipment method, of class Scene.
     */
    @Test
    public void testAddEquipment() {
        System.out.println("addEquipment");
        Equipment newEquipment = null;
        Scene instance = null;
        instance.addEquipment(newEquipment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeVolunteer method, of class Scene.
     */
    @Test
    public void testRemoveVolunteer() {
        System.out.println("removeVolunteer");
        Volunteer oldVolunteer = null;
        Scene instance = null;
        instance.removeVolunteer(oldVolunteer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeEquipment method, of class Scene.
     */
    @Test
    public void testRemoveEquipment() {
        System.out.println("removeEquipment");
        Equipment oldEquipment = null;
        Scene instance = null;
        instance.removeEquipment(oldEquipment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Scene.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Scene instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDescriptiveString method, of class Scene.
     */
    @Test
    public void testToDescriptiveString() {
        System.out.println("toDescriptiveString");
        Scene instance = null;
        String expResult = "";
        String result = instance.toDescriptiveString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Scene.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        int[] args = null;
        Scene.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Scene.
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Scene instance = null;
        Scene expResult = null;
        Scene result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class Scene.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        BaseBusinessObject mergeObject = null;
        Scene instance = null;
        instance.merge(mergeObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEquipment method, of class Scene.
     */
    @Test
    public void testSetEquipment() {
        System.out.println("setEquipment");
        BusinessObjectList<Equipment> equipment = null;
        Scene instance = null;
        instance.setEquipment(equipment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVolunteers method, of class Scene.
     */
    @Test
    public void testSetVolunteers() {
        System.out.println("setVolunteers");
        BusinessObjectList<Volunteer> volunteers = null;
        Scene instance = null;
        instance.setVolunteers(volunteers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
