/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import businessobjects.BaseBusinessObject;
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
public class EditSceneFilmingDateTest {
    
    public EditSceneFilmingDateTest() {
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
     * Test of validStateAltered method, of class EditSceneFilmingDate.
     */
    @Test
    public void testValidStateAltered() {
        System.out.println("validStateAltered");
        boolean newState = false;
        BaseBusinessObject sender = null;
        EditSceneFilmingDate instance = null;
        instance.validStateAltered(newState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changedStateAltered method, of class EditSceneFilmingDate.
     */
    @Test
    public void testChangedStateAltered() {
        System.out.println("changedStateAltered");
        boolean newState = false;
        BaseBusinessObject sender = null;
        EditSceneFilmingDate instance = null;
        instance.changedStateAltered(newState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class EditSceneFilmingDate.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        EditSceneFilmingDate instance = null;
        instance.save();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class EditSceneFilmingDate.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        EditSceneFilmingDate.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
