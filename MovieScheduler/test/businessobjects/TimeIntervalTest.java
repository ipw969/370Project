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
public class TimeIntervalTest {
    
    public TimeIntervalTest() {

        
    /**
     * Test of getStart method, of class TimeInterval.
     */
    @Test
    public void testGetStart() {
        System.out.println("getStart");
        TimeInterval instance = null;
        GregorianCalendar expResult = null;
        GregorianCalendar result = instance.getStart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnd method, of class TimeInterval.
     */
    @Test
    public void testGetEnd() {
        System.out.println("getEnd");
        TimeInterval instance = null;
        GregorianCalendar expResult = null;
        GregorianCalendar result = instance.getEnd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartIsoDate method, of class TimeInterval.
     */
    @Test
    public void testGetStartIsoDate() {
        System.out.println("getStartIsoDate");
        TimeInterval instance = null;
        String expResult = "";
        String result = instance.getStartIsoDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndIsoDate method, of class TimeInterval.
     */
    @Test
    public void testGetEndIsoDate() {
        System.out.println("getEndIsoDate");
        TimeInterval instance = null;
        String expResult = "";
        String result = instance.getEndIsoDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStart method, of class TimeInterval.
     */
    @Test
    public void testSetStart() {
        System.out.println("setStart");
        GregorianCalendar newStart = null;
        TimeInterval instance = null;
        instance.setStart(newStart);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnd method, of class TimeInterval.
     */
    @Test
    public void testSetEnd() {
        System.out.println("setEnd");
        GregorianCalendar newEnd = null;
        TimeInterval instance = null;
        instance.setEnd(newEnd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class TimeInterval.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        TimeInterval other = null;
        TimeInterval instance = null;
        int expResult = 0;
        int result = instance.compareTo(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of overlaps method, of class TimeInterval.
     */
    @Test
    public void testOverlaps() {
        System.out.println("overlaps");
        GregorianCalendar date = null;
        TimeInterval instance = null;
        boolean expResult = false;
        boolean result = instance.overlaps(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOnThisDate method, of class TimeInterval.
     */
    @Test
    public void testIsOnThisDate() {
        System.out.println("isOnThisDate");
        GregorianCalendar date = null;
        TimeInterval instance = null;
        boolean expResult = false;
        boolean result = instance.isOnThisDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class TimeInterval.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TimeInterval instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDescriptiveString method, of class TimeInterval.
     */
    @Test
    public void testToDescriptiveString() {
        System.out.println("toDescriptiveString");
        TimeInterval instance = null;
        String expResult = "";
        String result = instance.toDescriptiveString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class TimeInterval.
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        TimeInterval instance = null;
        BaseBusinessObject expResult = null;
        BaseBusinessObject result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class TimeInterval.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        BaseBusinessObject mergeObject = null;
        TimeInterval instance = null;
        instance.merge(mergeObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
