/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import businessobjects.BusinessObjectList;
import businessobjects.SceneFilmingDate;
import java.awt.Color;
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
public class CalendarDayTest {
    
    public CalendarDayTest() {
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
     * Test of addDeleteActionListener method, of class CalendarDay.
     */
    @Test
    public void testAddDeleteActionListener() {
        System.out.println("addDeleteActionListener");
        DeleteFilmingDateActionListener listener = null;
        CalendarDay instance = new CalendarDay();
        instance.addDeleteActionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDeleteActionListener method, of class CalendarDay.
     */
    @Test
    public void testRemoveDeleteActionListener() {
        System.out.println("removeDeleteActionListener");
        DeleteFilmingDateActionListener listener = null;
        CalendarDay instance = new CalendarDay();
        instance.removeDeleteActionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setForeground method, of class CalendarDay.
     */
    @Test
    public void testSetForeground() {
        System.out.println("setForeground");
        Color color = null;
        CalendarDay instance = new CalendarDay();
        instance.setForeground(color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class CalendarDay.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        GregorianCalendar date = null;
        CalendarDay instance = new CalendarDay();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of date method, of class CalendarDay.
     */
    @Test
    public void testDate() {
        System.out.println("date");
        CalendarDay instance = new CalendarDay();
        GregorianCalendar expResult = null;
        GregorianCalendar result = instance.date();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filmingDates method, of class CalendarDay.
     */
    @Test
    public void testFilmingDates() {
        System.out.println("filmingDates");
        CalendarDay instance = new CalendarDay();
        BusinessObjectList<SceneFilmingDate> expResult = null;
        BusinessObjectList<SceneFilmingDate> result = instance.filmingDates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
