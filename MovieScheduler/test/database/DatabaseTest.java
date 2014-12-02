/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
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
public class DatabaseTest {
    
    public DatabaseTest() {
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
     * Test of executeSelect method, of class Database.
     */
    @Test
    public void testExecuteSelect() throws Exception {
        System.out.println("executeSelect");
        String queryText = "";
        Database instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.executeSelect(queryText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeInsert method, of class Database.
     */
    @Test
    public void testExecuteInsert() throws Exception {
        System.out.println("executeInsert");
        String queryText = "";
        Database instance = null;
        instance.executeInsert(queryText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeUpdate method, of class Database.
     */
    @Test
    public void testExecuteUpdate() throws Exception {
        System.out.println("executeUpdate");
        String queryText = "";
        Database instance = null;
        instance.executeUpdate(queryText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeDelete method, of class Database.
     */
    @Test
    public void testExecuteDelete() throws Exception {
        System.out.println("executeDelete");
        String queryText = "";
        Database instance = null;
        instance.executeDelete(queryText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCommand method, of class Database.
     */
    @Test
    public void testAddCommand() {
        System.out.println("addCommand");
        String commandText = "";
        Database instance = null;
        boolean expResult = false;
        boolean result = instance.addCommand(commandText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCommand method, of class Database.
     */
    @Test
    public void testRemoveCommand() {
        System.out.println("removeCommand");
        String commandText = "";
        Database instance = null;
        boolean expResult = false;
        boolean result = instance.removeCommand(commandText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearCommandList method, of class Database.
     */
    @Test
    public void testClearCommandList() {
        System.out.println("clearCommandList");
        Database instance = null;
        instance.clearCommandList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCommandListEmpty method, of class Database.
     */
    @Test
    public void testIsCommandListEmpty() {
        System.out.println("isCommandListEmpty");
        Database instance = null;
        boolean expResult = false;
        boolean result = instance.isCommandListEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeCommandList method, of class Database.
     */
    @Test
    public void testExecuteCommandList() throws Exception {
        System.out.println("executeCommandList");
        Database instance = null;
        instance.executeCommandList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeSelectImplementation method, of class Database.
     */
    @Test
    public void testExecuteSelectImplementation() throws Exception {
        System.out.println("executeSelectImplementation");
        String queryText = "";
        Database instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.executeSelectImplementation(queryText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeInsertImplementation method, of class Database.
     */
    @Test
    public void testExecuteInsertImplementation() throws Exception {
        System.out.println("executeInsertImplementation");
        String queryText = "";
        Database instance = null;
        instance.executeInsertImplementation(queryText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeUpdateImplementation method, of class Database.
     */
    @Test
    public void testExecuteUpdateImplementation() throws Exception {
        System.out.println("executeUpdateImplementation");
        String queryText = "";
        Database instance = null;
        instance.executeUpdateImplementation(queryText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeDeleteImplementation method, of class Database.
     */
    @Test
    public void testExecuteDeleteImplementation() throws Exception {
        System.out.println("executeDeleteImplementation");
        String queryText = "";
        Database instance = null;
        instance.executeDeleteImplementation(queryText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCommandImplementation method, of class Database.
     */
    @Test
    public void testAddCommandImplementation() {
        System.out.println("addCommandImplementation");
        String commandText = "";
        Database instance = null;
        boolean expResult = false;
        boolean result = instance.addCommandImplementation(commandText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCommandImplementation method, of class Database.
     */
    @Test
    public void testRemoveCommandImplementation() {
        System.out.println("removeCommandImplementation");
        String commandText = "";
        Database instance = null;
        boolean expResult = false;
        boolean result = instance.removeCommandImplementation(commandText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearCommandListImplementation method, of class Database.
     */
    @Test
    public void testClearCommandListImplementation() {
        System.out.println("clearCommandListImplementation");
        Database instance = null;
        instance.clearCommandListImplementation();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCommandListEmptyImplementation method, of class Database.
     */
    @Test
    public void testIsCommandListEmptyImplementation() {
        System.out.println("isCommandListEmptyImplementation");
        Database instance = null;
        boolean expResult = false;
        boolean result = instance.isCommandListEmptyImplementation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeCommandListImplementation method, of class Database.
     */
    @Test
    public void testExecuteCommandListImplementation() throws Exception {
        System.out.println("executeCommandListImplementation");
        Database instance = null;
        instance.executeCommandListImplementation();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnectionUrl method, of class Database.
     */
    @Test
    public void testGetConnectionUrl() {
        System.out.println("getConnectionUrl");
        Database instance = null;
        String expResult = "";
        String result = instance.getConnectionUrl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DatabaseImpl extends Database {

        public DatabaseImpl() {
            super("");
        }

        public ResultSet executeSelectImplementation(String queryText) throws SQLException {
            return null;
        }

        public void executeInsertImplementation(String queryText) throws SQLException {
        }

        public void executeUpdateImplementation(String queryText) throws SQLException {
        }

        public void executeDeleteImplementation(String queryText) throws SQLException {
        }

        public boolean addCommandImplementation(String commandText) {
            return false;
        }

        public boolean removeCommandImplementation(String commandText) {
            return false;
        }

        public void clearCommandListImplementation() {
        }

        public boolean isCommandListEmptyImplementation() {
            return false;
        }

        public void executeCommandListImplementation() throws SQLException {
        }
    }
    
}
