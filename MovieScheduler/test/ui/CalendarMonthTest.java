/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import businessobjects.BaseBusinessObject;
import businessobjects.Schedule;
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
public class CalendarMonthTest {
    
    public CalendarMonthTest() {
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
     * Test of addDeleteActionListener method, of class CalendarMonth.
     */
    @Test
    public void testAddDeleteActionListener() {
        System.out.println("addDeleteActionListener");
        DeleteFilmingDateActionListener listener = null;
        CalendarMonth instance = new CalendarMonth();
        instance.addDeleteActionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDeleteActionListener method, of class CalendarMonth.
     */
    @Test
    public void testRemoveDeleteActionListener() {
        System.out.println("removeDeleteActionListener");
        DeleteFilmingDateActionListener listener = null;
        CalendarMonth instance = new CalendarMonth();
        instance.removeDeleteActionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class CalendarMonth.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        int year = 0;
        int month = 0;
        CalendarMonth instance = new CalendarMonth();
        instance.setDate(year, month);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSchedule method, of class CalendarMonth.
     */
    @Test
    public void testSetSchedule() {
        System.out.println("setSchedule");
        Schedule schedule = null;
        CalendarMonth instance = new CalendarMonth();
        instance.setSchedule(schedule);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of businessObjectAdded method, of class CalendarMonth.
     */
    @Test
    public void testBusinessObjectAdded() {
        System.out.println("businessObjectAdded");
        BaseBusinessObject itemAdded = null;
        CalendarMonth instance = new CalendarMonth();
        instance.businessObjectAdded(itemAdded);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of businessObjectRemoved method, of class CalendarMonth.
     */
    @Test
    public void testBusinessObjectRemoved() {
        System.out.println("businessObjectRemoved");
        BaseBusinessObject itemRemoved = null;
        CalendarMonth instance = new CalendarMonth();
        instance.businessObjectRemoved(itemRemoved);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listCleared method, of class CalendarMonth.
     */
    @Test
    public void testListCleared() {
        System.out.println("listCleared");
        CalendarMonth instance = new CalendarMonth();
        instance.listCleared();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validStateAltered method, of class CalendarMonth.
     */
    @Test
    public void testValidStateAltered() {
        System.out.println("validStateAltered");
        boolean newState = false;
        BaseBusinessObject sender = null;
        CalendarMonth instance = new CalendarMonth();
        instance.validStateAltered(newState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changedStateAltered method, of class CalendarMonth.
     */
    @Test
    public void testChangedStateAltered() {
        System.out.println("changedStateAltered");
        boolean newState = false;
        BaseBusinessObject sender = null;
        CalendarMonth instance = new CalendarMonth();
        instance.changedStateAltered(newState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
