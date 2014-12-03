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
public class ScriptTest {
    
    public ScriptTest() {
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
     * Test of getName method, of class Script.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Script instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Script.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String newName = "";
        Script instance = null;
        instance.setName(newName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScenes method, of class Script.
     */
    @Test
    public void testGetScenes() {
        System.out.println("getScenes");
        Script instance = null;
        BusinessObjectList<Scene> expResult = null;
        BusinessObjectList<Scene> result = instance.getScenes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasScenes method, of class Script.
     */
    @Test
    public void testHasScenes() {
        System.out.println("hasScenes");
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.hasScenes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addScene method, of class Script.
     */
    @Test
    public void testAddScene() {
        System.out.println("addScene");
        Scene newScene = null;
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.addScene(newScene);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeScene method, of class Script.
     */
    @Test
    public void testRemoveScene() {
        System.out.println("removeScene");
        Scene oldScene = null;
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.removeScene(oldScene);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numberOfScenes method, of class Script.
     */
    @Test
    public void testNumberOfScenes() {
        System.out.println("numberOfScenes");
        Script instance = null;
        int expResult = 0;
        int result = instance.numberOfScenes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEverySceneScheduled method, of class Script.
     */
    @Test
    public void testIsEverySceneScheduled() {
        System.out.println("isEverySceneScheduled");
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.isEverySceneScheduled();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEverySceneComplete method, of class Script.
     */
    @Test
    public void testIsEverySceneComplete() {
        System.out.println("isEverySceneComplete");
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.isEverySceneComplete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScenes method, of class Script.
     */
    @Test
    public void testSetScenes() {
        System.out.println("setScenes");
        BusinessObjectList<Scene> newScenes = null;
        Script instance = null;
        instance.setScenes(newScenes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sceneIterator method, of class Script.
     */
    @Test
    public void testSceneIterator() {
        System.out.println("sceneIterator");
        Script instance = null;
        Iterator<Scene> expResult = null;
        Iterator<Scene> result = instance.sceneIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeVolunteer method, of class Script.
     */
    @Test
    public void testRemoveVolunteer() {
        System.out.println("removeVolunteer");
        Volunteer oldVolunteer = null;
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.removeVolunteer(oldVolunteer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of volunteerIterator method, of class Script.
     */
    @Test
    public void testVolunteerIterator() {
        System.out.println("volunteerIterator");
        Script instance = null;
        Iterator<Volunteer> expResult = null;
        Iterator<Volunteer> result = instance.volunteerIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVolunteers method, of class Script.
     */
    @Test
    public void testGetVolunteers() {
        System.out.println("getVolunteers");
        Script instance = null;
        BusinessObjectList<Volunteer> expResult = null;
        BusinessObjectList<Volunteer> result = instance.getVolunteers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVolunteers method, of class Script.
     */
    @Test
    public void testSetVolunteers() {
        System.out.println("setVolunteers");
        BusinessObjectList<Volunteer> newVolunteers = null;
        Script instance = null;
        instance.setVolunteers(newVolunteers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasVolunteers method, of class Script.
     */
    @Test
    public void testHasVolunteers() {
        System.out.println("hasVolunteers");
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.hasVolunteers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addVolunteer method, of class Script.
     */
    @Test
    public void testAddVolunteer() {
        System.out.println("addVolunteer");
        Volunteer newVolunteer = null;
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.addVolunteer(newVolunteer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasEquipment method, of class Script.
     */
    @Test
    public void testHasEquipment() {
        System.out.println("hasEquipment");
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.hasEquipment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEquipment method, of class Script.
     */
    @Test
    public void testAddEquipment() {
        System.out.println("addEquipment");
        Equipment newEquipment = null;
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.addEquipment(newEquipment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeEquipment method, of class Script.
     */
    @Test
    public void testRemoveEquipment() {
        System.out.println("removeEquipment");
        Equipment oldEquipment = null;
        Script instance = null;
        boolean expResult = false;
        boolean result = instance.removeEquipment(oldEquipment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equipmentIterator method, of class Script.
     */
    @Test
    public void testEquipmentIterator() {
        System.out.println("equipmentIterator");
        Script instance = null;
        Iterator<Equipment> expResult = null;
        Iterator<Equipment> result = instance.equipmentIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquipment method, of class Script.
     */
    @Test
    public void testGetEquipment() {
        System.out.println("getEquipment");
        Script instance = null;
        BusinessObjectList<Equipment> expResult = null;
        BusinessObjectList<Equipment> result = instance.getEquipment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEquipment method, of class Script.
     */
    @Test
    public void testSetEquipment() {
        System.out.println("setEquipment");
        BusinessObjectList<Equipment> newEquipment = null;
        Script instance = null;
        instance.setEquipment(newEquipment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSchedule method, of class Script.
     */
    @Test
    public void testGetSchedule() {
        System.out.println("getSchedule");
        Script instance = null;
        Schedule expResult = null;
        Schedule result = instance.getSchedule();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSchedule method, of class Script.
     */
    @Test
    public void testSetSchedule() {
        System.out.println("setSchedule");
        Schedule newSchedule = null;
        Script instance = null;
        instance.setSchedule(newSchedule);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Script.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Script.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Script.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Script instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDescriptiveString method, of class Script.
     */
    @Test
    public void testToDescriptiveString() {
        System.out.println("toDescriptiveString");
        Script instance = null;
        String expResult = "";
        String result = instance.toDescriptiveString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Script.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Script instance = null;
        BaseBusinessObject expResult = null;
        BaseBusinessObject result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class Script.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        BaseBusinessObject mergeObject = null;
        Script instance = null;
        instance.merge(mergeObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
