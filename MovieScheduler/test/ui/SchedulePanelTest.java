/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import businessobjects.Script;
import database.Database;
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
public class SchedulePanelTest {
    
    public SchedulePanelTest() {
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
     * Test of setDatabase method, of class SchedulePanel.
     */
    @Test
    public void testSetDatabase() {
        System.out.println("setDatabase");
        Database database = null;
        SchedulePanel instance = new SchedulePanel();
        instance.setDatabase(database);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSript method, of class SchedulePanel.
     */
    @Test
    public void testSetSript() {
        System.out.println("setSript");
        Script script = null;
        SchedulePanel instance = new SchedulePanel();
        instance.setSript(script);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
