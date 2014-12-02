/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

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
public class ScheduleCalendarTest {
    
    public ScheduleCalendarTest() {
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
     * Test of setDate method, of class ScheduleCalendar.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        int year = 0;
        int month = 0;
        ScheduleCalendar instance = new ScheduleCalendar();
        instance.setDate(year, month);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDeleteActionListener method, of class ScheduleCalendar.
     */
    @Test
    public void testAddDeleteActionListener() {
        System.out.println("addDeleteActionListener");
        DeleteFilmingDateActionListener listener = null;
        ScheduleCalendar instance = new ScheduleCalendar();
        instance.addDeleteActionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDeleteActionListener method, of class ScheduleCalendar.
     */
    @Test
    public void testRemoveDeleteActionListener() {
        System.out.println("removeDeleteActionListener");
        DeleteFilmingDateActionListener listener = null;
        ScheduleCalendar instance = new ScheduleCalendar();
        instance.removeDeleteActionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSchedule method, of class ScheduleCalendar.
     */
    @Test
    public void testSetSchedule() {
        System.out.println("setSchedule");
        Schedule schedule = null;
        ScheduleCalendar instance = new ScheduleCalendar();
        instance.setSchedule(schedule);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
