/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
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
public class JdbcDatabaseTest {
    
    public JdbcDatabaseTest() {
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
     * Test of executeSelectImplementation method, of class JdbcDatabase.
     */
    @Test
    public void testExecuteSelectImplementation() throws Exception {
        System.out.println("executeSelectImplementation");
        String queryText = "";
        JdbcDatabase instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.executeSelectImplementation(queryText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeInsertImplementation method, of class JdbcDatabase.
     */
    @Test
    public void testExecuteInsertImplementation() throws Exception {
        System.out.println("executeInsertImplementation");
        String queryText = "";
        JdbcDatabase instance = null;
        instance.executeInsertImplementation(queryText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeUpdateImplementation method, of class JdbcDatabase.
     */
    @Test
    public void testExecuteUpdateImplementation() throws Exception {
        System.out.println("executeUpdateImplementation");
        String queryText = "";
        JdbcDatabase instance = null;
        instance.executeUpdateImplementation(queryText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeDeleteImplementation method, of class JdbcDatabase.
     */
    @Test
    public void testExecuteDeleteImplementation() throws Exception {
        System.out.println("executeDeleteImplementation");
        String queryText = "";
        JdbcDatabase instance = null;
        instance.executeDeleteImplementation(queryText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCommandImplementation method, of class JdbcDatabase.
     */
    @Test
    public void testAddCommandImplementation() {
        System.out.println("addCommandImplementation");
        String queryText = "";
        JdbcDatabase instance = null;
        boolean expResult = false;
        boolean result = instance.addCommandImplementation(queryText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCommandImplementation method, of class JdbcDatabase.
     */
    @Test
    public void testRemoveCommandImplementation() {
        System.out.println("removeCommandImplementation");
        String queryText = "";
        JdbcDatabase instance = null;
        boolean expResult = false;
        boolean result = instance.removeCommandImplementation(queryText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearCommandListImplementation method, of class JdbcDatabase.
     */
    @Test
    public void testClearCommandListImplementation() {
        System.out.println("clearCommandListImplementation");
        JdbcDatabase instance = null;
        instance.clearCommandListImplementation();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCommandListEmptyImplementation method, of class JdbcDatabase.
     */
    @Test
    public void testIsCommandListEmptyImplementation() {
        System.out.println("isCommandListEmptyImplementation");
        JdbcDatabase instance = null;
        boolean expResult = false;
        boolean result = instance.isCommandListEmptyImplementation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeCommandListImplementation method, of class JdbcDatabase.
     */
    @Test
    public void testExecuteCommandListImplementation() throws Exception {
        System.out.println("executeCommandListImplementation");
        JdbcDatabase instance = null;
        instance.executeCommandListImplementation();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of commandListIterator method, of class JdbcDatabase.
     */
    @Test
    public void testCommandListIterator() {
        System.out.println("commandListIterator");
        JdbcDatabase instance = null;
        Iterator<String> expResult = null;
        Iterator<String> result = instance.commandListIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
