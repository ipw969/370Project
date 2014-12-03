/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import java.util.ArrayList;
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
public class SceneFilmingDateTest {
    
    public SceneFilmingDateTest() {
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
     * Test of getScene method, of class SceneFilmingDate.
     */
    @Test
    public void testGetScene() {
        System.out.println("getScene");
        SceneFilmingDate instance = new SceneFilmingDate();
        Scene expResult = null;
        Scene result = instance.getScene();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSceneShootingInterval method, of class SceneFilmingDate.
     */
    @Test
    public void testGetSceneShootingInterval() {
        System.out.println("getSceneShootingInterval");
        SceneFilmingDate instance = new SceneFilmingDate();
        TimeInterval expResult = null;
        TimeInterval result = instance.getSceneShootingInterval();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasConflict method, of class SceneFilmingDate.
     */
    @Test
    public void testHasConflict() {
        System.out.println("hasConflict");
        SceneFilmingDate instance = new SceneFilmingDate();
        boolean expResult = false;
        boolean result = instance.hasConflict();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sceneShootingStart method, of class SceneFilmingDate.
     */
    @Test
    public void testSceneShootingStart() {
        System.out.println("sceneShootingStart");
        SceneFilmingDate instance = new SceneFilmingDate();
        GregorianCalendar expResult = null;
        GregorianCalendar result = instance.sceneShootingStart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sceneShootingEnd method, of class SceneFilmingDate.
     */
    @Test
    public void testSceneShootingEnd() {
        System.out.println("sceneShootingEnd");
        SceneFilmingDate instance = new SceneFilmingDate();
        GregorianCalendar expResult = null;
        GregorianCalendar result = instance.sceneShootingEnd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScene method, of class SceneFilmingDate.
     */
    @Test
    public void testSetScene() {
        System.out.println("setScene");
        Scene newScene = null;
        SceneFilmingDate instance = new SceneFilmingDate();
        instance.setScene(newScene);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSceneShootingInterval method, of class SceneFilmingDate.
     */
    @Test
    public void testSetSceneShootingInterval() {
        System.out.println("setSceneShootingInterval");
        TimeInterval shootingInterval = null;
        SceneFilmingDate instance = new SceneFilmingDate();
        instance.setSceneShootingInterval(shootingInterval);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReasonList method, of class SceneFilmingDate.
     */
    @Test
    public void testGetReasonList() {
        System.out.println("getReasonList");
        SceneFilmingDate instance = new SceneFilmingDate();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getReasonList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConflictIgnored method, of class SceneFilmingDate.
     */
    @Test
    public void testIsConflictIgnored() {
        System.out.println("isConflictIgnored");
        SceneFilmingDate instance = new SceneFilmingDate();
        Boolean expResult = null;
        Boolean result = instance.isConflictIgnored();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ignoreConflict method, of class SceneFilmingDate.
     */
    @Test
    public void testIgnoreConflict() {
        System.out.println("ignoreConflict");
        boolean ignore = false;
        SceneFilmingDate instance = new SceneFilmingDate();
        instance.ignoreConflict(ignore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SceneFilmingDate.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SceneFilmingDate instance = new SceneFilmingDate();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validStateAltered method, of class SceneFilmingDate.
     */
    @Test
    public void testValidStateAltered() {
        System.out.println("validStateAltered");
        boolean newState = false;
        BaseBusinessObject sender = null;
        SceneFilmingDate instance = new SceneFilmingDate();
        instance.validStateAltered(newState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changedStateAltered method, of class SceneFilmingDate.
     */
    @Test
    public void testChangedStateAltered() {
        System.out.println("changedStateAltered");
        boolean newState = false;
        BaseBusinessObject sender = null;
        SceneFilmingDate instance = new SceneFilmingDate();
        instance.changedStateAltered(newState, sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDescriptiveString method, of class SceneFilmingDate.
     */
    @Test
    public void testToDescriptiveString() {
        System.out.println("toDescriptiveString");
        SceneFilmingDate instance = new SceneFilmingDate();
        String expResult = "";
        String result = instance.toDescriptiveString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class SceneFilmingDate.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        BaseBusinessObject mergeObject = null;
        SceneFilmingDate instance = new SceneFilmingDate();
        instance.merge(mergeObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class SceneFilmingDate.
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        SceneFilmingDate instance = new SceneFilmingDate();
        BaseBusinessObject expResult = null;
        BaseBusinessObject result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
