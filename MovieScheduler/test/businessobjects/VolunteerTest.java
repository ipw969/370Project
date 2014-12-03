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
        String name = "TestFirstName";
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
        String name = "TestLastName";
        Volunteer instance = new Volunteer();
        instance.setLastName(name);
        assertEquals(name, instance.lastName);
    }

    /**
     * Test of setEmail method, of class Volunteer.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "myEmail@mail.com";
        Volunteer instance = new Volunteer();
        instance.setEmail(email);
        assertEquals(email, instance.email);
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
        assertEquals(phone, instance.phone);
    }

    /**
     * Test of getFirstName method, of class Volunteer.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Volunteer instance = new Volunteer("Bob", "Dobbers", "email", "phone");
        String expResult = "Bob";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastName method, of class Volunteer.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Volunteer instance = new Volunteer("Bob", "Dobbers", "email", "phone");
        String expResult = "Dobbers";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Volunteer.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Volunteer instance = new Volunteer("Bob", "Dobbers", "email", "phone");
        String expResult = "email";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhone method, of class Volunteer.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        Volunteer instance = new Volunteer("Bob", "Dobbers", "email", "phone");
        String expResult = "phone";
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvailability method, of class Volunteer.
     */
    @Test
    public void testGetAvailability() {
        System.out.println("getAvailability");
        BusinessObjectList<TimeInterval> expResult = new BusinessObjectList<>();
        TimeInterval avail = new TimeInterval(
                                new GregorianCalendar(2014, 12, 12, 9, 0),
                                new GregorianCalendar(2014, 12, 12, 12, 0));
        
        expResult.add(avail);
        Volunteer instance = new Volunteer("Bob", "Dobbers", "lol", "test", expResult);
        BusinessObjectList<TimeInterval> result = instance.getAvailability();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Volunteer.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Volunteer instance = new Volunteer("1", "2", "3", "4");
        String expResult = "1 2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toDescriptiveString method, of class Volunteer.
     */
    @Test
    public void testToDescriptiveString() {
        System.out.println("toDescriptiveString");
        Volunteer instance = new Volunteer("1", "2","3", "4");
        String expResult = "First name: 1\nLast name: 2\nPhone number: 4\nEmail address: 3\n";
        String result = instance.toDescriptiveString();
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class Volunteer.
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Volunteer instance = new Volunteer("Test", "Name", "testMail", "testPhone");
        Volunteer result = (Volunteer)instance.clone();
        
        assertTrue(result.email.equals(instance.email) &&
                   result.firstName.equals(instance.firstName) &&
                   result.firstName.equals(instance.firstName) &&
                   result.phone.equals(instance.phone) &&
                   result.availability.toString().equals(instance.availability.toString()));
    }

    /**
     * Test of merge method, of class Volunteer.
     */
    @Test
    public void testMerge() {
        /* I'm not too certain about the whole merging and scene shebang -  if 
        one of you guys want to write this test that'd be great! Thanks :)
        */
        assertTrue("Apples"=="Bananas");
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
