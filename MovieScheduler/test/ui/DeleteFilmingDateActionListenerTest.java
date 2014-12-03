/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

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
public class DeleteFilmingDateActionListenerTest {
    
    public DeleteFilmingDateActionListenerTest() {
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
     * Test of deleteActionPerformed method, of class DeleteFilmingDateActionListener.
     */
    @Test
    public void testDeleteActionPerformed() {
        System.out.println("deleteActionPerformed");
        DeleteFilmingDateEvent e = null;
        DeleteFilmingDateActionListener instance = new DeleteFilmingDateActionListenerImpl();
        instance.deleteActionPerformed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DeleteFilmingDateActionListenerImpl implements DeleteFilmingDateActionListener {

        public void deleteActionPerformed(DeleteFilmingDateEvent e) {
        }
    }
    
}
