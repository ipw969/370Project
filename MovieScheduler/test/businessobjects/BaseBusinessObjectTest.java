/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Simple unit tests for BaseBusinessObject.
 */
public class BaseBusinessObjectTest {
    
    public BaseBusinessObjectTest() {
    }

    /**
     * isValid() should:
     * - Return true on a newly constructed instance
     * - Return false on an instance which has had an error added
     * - Return true on an instance which has had an error added, and then
     *   removed
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        BaseBusinessObject instance = new BaseBusinessObject();
        assertTrue("BaseBusinessObject::isValid() test failed: "
                + "New instance is not valid", instance.isValid());
        
        // force add an error
        instance.updateError("test error", false);
        
        assertFalse("BaseBusinessObject::isValid() test failed: "
                + "Instance with added error is valid", instance.isValid());
        
        // force remove error
        instance.updateError("test error", true);
        
        assertTrue("BaseBusinessObject::isValid() test failed: "
                + "Instance with removed error is not valid", instance.isValid());
    }

    /**
     * hasChanged() should:
     * - Return false initially after construction
     * - Return true after setHasChanged(true) has been called
     * - Return false after setHasChanged(false) has been called
     */
    @Test
    public void testHasChanged() {
        System.out.println("hasChanged");
        BaseBusinessObject instance = new BaseBusinessObject();
        
        assertFalse("BaseBusinessObject::hasChanged() test failed: "
                + "Returned true on a newly constructed instance",
                instance.hasChanged());
        
        instance.setHasChanged(true);
        
        assertTrue("BaseBusinessObject::hasChanged() test failed: "
                + "Returned false after hasChanged(true) called",
                instance.hasChanged());
        
        instance.setHasChanged(false);
        
        assertFalse("BaseBusinessObject::hasChanged() test failed: "
                + "Returned true after hasChanged(false) called",
                instance.hasChanged());
    }

    /**
     * Ensure that isNew() returns the expected value for all shifts of value
     * from {T, F} to {T, F}
     */
    @Test
    public void testSetIsNew() {
        System.out.println("setIsNew");
        BaseBusinessObject instance = new BaseBusinessObject();
        
        instance.setIsNew(true);
        assertTrue("BaseBusinessObject::setIsNew() test failed: "
                + "Failed going from true to true", instance.isNew());
        
        instance.setIsNew(false);
        assertFalse("BaseBusinessObject::setIsNew() test failed: "
                + "Failed going from true to false", instance.isNew());
        
        instance.setIsNew(false);
        assertFalse("BaseBusinessObject::setIsNew() test failed: "
                + "Failed going from false to false", instance.isNew());
        
        instance.setIsNew(true);
        assertTrue("BaseBusinessObject::setIsNew() test failed:"
                + "Failed going from false to true", instance.isNew());
        
    }

    /**
     * isNew() should:
     * - Return true on a newly constructed instance
     * - Return false after setIsNew(false) is called
     * - Return true after setIsNew(true) is called
     */
    @Test
    public void testIsNew() {
        System.out.println("isNew");
        BaseBusinessObject instance = new BaseBusinessObject();
        
        assertTrue("BaseBusinessObject::isNew() test failed: "
                + "Returned false on a newly constructed instance.",
                instance.isNew());
        
        instance.setIsNew(false);
        assertFalse("BaseBusinessObject::isNew() test failed: "
                + "Returned true after setIsNew(false) called", 
                instance.isNew());
        
        instance.setIsNew(true);
        assertTrue("BaseBusinessObject::isNew() test failed: "
                + "Returned false after setIsNew(true) called",
                instance.isNew());
    }

    /**
     * errorMessage() should:
     * - Return an empty string after initial construction
     * - Return a concatenation of all the contained error messages
     */
    @Test
    public void testErrorMessage() {
        System.out.println("errorMessage");
        BaseBusinessObject instance = new BaseBusinessObject();
        
        assertTrue("BaseBusinessObject::errorMessage() test failed: "
                + "Newly constructed instance doesn't return an empty error"
                + "message", instance.errorMessage().isEmpty());
        
        // Force add an error
        instance.updateError("TestErrorMessage1", false);
        assertTrue("BaseBusinessObject::errorMessage() test failed: "
                + "Did not return expected error message for a single error",
                instance.errorMessage().compareTo("TestErrorMessage1") == 0);
        
        instance.updateError("TestErrorMessage2", false);
        assertTrue("BaseBusinessObject::errorMessage() test failed: "
                + "Did not return expected error message for two errors",
                instance.errorMessage().compareTo("TestErrorMessage1\n"
                        + "TestErrorMessage2") == 0);
        
    }

    /**
     * Ensure that hasChanged() returns the expected value when shifting for all
     * permutations of shift from {T, F} to {T, F}
     */
    @Test
    public void testSetHasChanged() {
        System.out.println("setHasChanged");
        BaseBusinessObject instance = new BaseBusinessObject();

        instance.setHasChanged(true);
        assertTrue("BaseBusinessObject::setHasChanged() test failed: "
                + "Failed going from false to true", instance.hasChanged());
        
        instance.setHasChanged(true);
        assertTrue("BaseBusinessObject::setHasChanged() test failed: "
                + "Failed going from true to true", instance.hasChanged());
        
        instance.setHasChanged(false);
        assertFalse("BaseBusinessObject::setHasChanged() test failed: "
                + "Failed going from true to false", instance.hasChanged());
        
        instance.setHasChanged(false);
        assertFalse("BaseBusinessObject::setHasChanged() test failed: "
                + "Failed going from false to false", instance.hasChanged());        
    }

    /**
     * Ensures that:
     * - Calling with true for passesTest does not add the error
     * - Calling with false for passesTest adds the error
     * - Calling with the same error message twice does not duplicate the
     *   error
     * - Calling with true for passesTest removes the error
     * - Adding multiple errors works properly
     * - Removing one of the multiple errors works properly
     */
    @Test
    public void testUpdateError() {
        System.out.println("updateError");
        BaseBusinessObject instance = new BaseBusinessObject();
        
        
        instance.updateError("TestMessage1", true);
        assertTrue("BaseBusinessObject::updateError() test failed: "
                + "Added error when passedTest argument == true",
                instance.isValid());
        
        instance.updateError("TestMessage1", false);
        assertFalse("BaseBusinessObject::updateError() test failed: "
                + "Did not add error when passedTest argument == false", 
                instance.isValid());
        
        instance.updateError("TestMessage1", false);
        assertFalse("BaseBusinessObject::updateError() test failed: "
                + "Duplicated an error message", 
                instance.errorMessage().compareTo("TestMessage1"
                        + "\nTestMessage1") == 0);
        
        instance.updateError("TestMessage1", true);
        assertTrue("BaseBusinessObject::updateError() test failed: "
                + "Did not remove error when passedTest argument == true",
                instance.isValid());
        
        instance.updateError("TestMessage2", false);
        instance.updateError("TestMessage3", false);
        instance.updateError("TestMessage4", false);
        assertTrue("BaseBusinessObject::updateError() test failed: "
                + "Did not add two errors properly",
                instance.errorMessage().compareTo("TestMessage2"
                        + "\nTestMessage3\nTestMessage4") == 0);
        
        instance.updateError("TestMessage3", true);
        assertTrue("BaseBusinessObject::updateError() test failed: "
                + "Did not remove error properly", 
                instance.errorMessage().compareTo("TestMessage2"
                        + "\nTestMessage4") == 0);
        
        
    }
    
}
