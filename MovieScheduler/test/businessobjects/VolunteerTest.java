/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import java.util.GregorianCalendar;
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
public class VolunteerTest {
    
    public VolunteerTest() {
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
     * Test of addAvailability method, of class Volunteer.
     */
    @Test
    public void testAddAvailability() {
        System.out.println("addAvailability");
        TimeInterval avail = new TimeInterval(
                                   new GregorianCalendar(2014, 12, 12, 9, 0),
                                   new GregorianCalendar(2014, 12, 12, 12, 0));
        
        Volunteer instance = new Volunteer();
        assertTrue(instance.availability.isEmpty());
        instance.addAvailability(avail);
        assertEquals(instance.availability.get(0), avail);  
    }

    /**
     * Test of setFirstName method, of class Volunteer.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String name = "";
        Volunteer instance = new Volunteer();
        instance.setFirstName(name);
        assertEquals(name, instance.firstName);
    }

    /**
     * Test of setLastName method, of class Volunteer.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String name = "";
        Volunteer instance = new Volunteer();
        instance.setLastName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Volunteer.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Volunteer instance = new Volunteer();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPhone method, of class Volunteer.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "";
        Volunteer instance = new Volunteer();
        instance.setPhone(phone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class Volunteer.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Volunteer instance = new Volunteer();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class Volunteer.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Volunteer instance = new Volunteer();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Volunteer.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Volunteer instance = new Volunteer();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhone method, of class Volunteer.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        Volunteer instance = new Volunteer();
        String expResult = "";
        String result = instance.getPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailability method, of class Volunteer.
     */
    @Test
    public void testGetAvailability() {
        System.out.println("getAvailability");
        Volunteer instance = new Volunteer();
        BusinessObjectList<TimeInterval> expResult = null;
        BusinessObjectList<TimeInterval> result = instance.getAvailability();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Volunteer.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Volunteer instance = new Volunteer();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDescriptiveString method, of class Volunteer.
     */
    @Test
    public void testToDescriptiveString() {
        System.out.println("toDescriptiveString");
        Volunteer instance = new Volunteer();
        String expResult = "";
        String result = instance.toDescriptiveString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Volunteer.
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Volunteer instance = new Volunteer();
        BaseBusinessObject expResult = null;
        BaseBusinessObject result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class Volunteer.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        BaseBusinessObject mergeObject = null;
        Volunteer instance = new Volunteer();
        instance.merge(mergeObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
