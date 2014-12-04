
package businessobjects;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Simple unit tests for BaseBusinessObject.
 * This class extends BaseBusinessObject because the abstract class cannot be instantiated.
 */
public class BaseBusinessObjectTest extends BaseBusinessObject{
    
    public BaseBusinessObjectTest() 
    {
        super();
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
        BaseBusinessObject instance = new BaseBusinessObjectTest();
        assertTrue("BaseBusinessObject::isValid() test failed: "
                + "New instance is not valid", instance.isValid());
        
         //force add an error
        instance.updateError("test error", false);
        
        assertFalse("BaseBusinessObject::isValid() test failed: "
                + "Instance with added error is valid", instance.isValid());
        
         //force remove error
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
        BaseBusinessObject instance = new BaseBusinessObjectTest();
        
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
        BaseBusinessObject instance = new BaseBusinessObjectTest();
        
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
        BaseBusinessObject instance = new BaseBusinessObjectTest();
        
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
        BaseBusinessObject instance = new BaseBusinessObjectTest();
        
        assertTrue("BaseBusinessObject::errorMessage() test failed: "
                + "Newly constructed instance doesn't return an empty error"
                + "message", instance.getErrorMessage().isEmpty());
        
         //Force add an error
        instance.updateError("TestErrorMessage1", false);
        assertTrue("BaseBusinessObject::errorMessage() test failed: "
                + "Did not return expected error message for a single error",
                instance.getErrorMessage().compareTo("TestErrorMessage1") == 0);
        
        instance.updateError("TestErrorMessage2", false);
        assertTrue("BaseBusinessObject::errorMessage() test failed: "
                + "Did not return expected error message for two errors",
                instance.getErrorMessage().compareTo("TestErrorMessage1\n"
                        + "TestErrorMessage2") == 0);
        
    }

    /**
     * Ensure that hasChanged() returns the expected value when shifting for all
     * permutations of shift from {T, F} to {T, F}
     */
    @Test
    public void testSetHasChanged() {
        System.out.println("setHasChanged");
        BaseBusinessObject instance = new BaseBusinessObjectTest();

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
        BaseBusinessObject instance = new BaseBusinessObjectTest();
        
        
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
                instance.getErrorMessage().compareTo("TestMessage1"
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
                instance.getErrorMessage().compareTo("TestMessage2"
                        + "\nTestMessage3\nTestMessage4") == 0);
        
        instance.updateError("TestMessage3", true);
        assertTrue("BaseBusinessObject::updateError() test failed: "
                + "Did not remove error properly", 
                instance.getErrorMessage().compareTo("TestMessage2"
                        + "\nTestMessage4") == 0);
        
        
    }

    
    /**Test the Clone() method.
     * This method needs to make every single attribute of the first object the same
     * The cloned object should not have a reference to the new object
     * Changes to the clone should not affect the original copy.
     * */
    @Test
    public void testClone() throws CloneNotSupportedException
    {
        //Create the original and give it some default values
        BaseBusinessObject original = new BaseBusinessObjectTest();
        original.setHasChanged(true);
        original.setIsNew(false);
        original.updateError("Test fail", false);
        
        
        assert(original.hasChanged());
        assert(!original.isNew());
        assert(original.getErrorMessage().equals("Test fail"));
        
        //Create the clone and ensure it has the same stats as the original.
        BaseBusinessObject clone = original.clone();
        assert(original.hasChanged() == clone.hasChanged());
        assert(clone.isNew() == original.isNew());
        assert(clone.getErrorMessage().equals(original.getErrorMessage()));
        assert(clone.isValid() == original.isValid());
        assert(clone.getErrorMessage() != original.getErrorMessage());
         
        //Change the clone's fields.
        clone.setIsNew(!original.isNew());
        clone.updateError(original.getErrorMessage(), true);
        clone.setHasChanged(!original.hasChanged());
        
        //Ensure the changed weren't effected.
        assert(original.hasChanged() != clone.hasChanged());
        assert(clone.isNew() != original.isNew());
        assert(!clone.getErrorMessage().equals(original.getErrorMessage()));
        assert(clone.isValid() != original.isValid()); 
        assert(clone.getErrorMessage() != original.getErrorMessage());
        
    }
   
    /**This is the test for the mergeObject function.
     * The merge method should take in an object and make all of the caller's instances the same as the given object.
     * Changes to one of the objects should not affect the others.
     */
    @Test
    public void testMergeObject()
    {
        
       BaseBusinessObject instance1 = new BaseBusinessObjectTest();
       instance1.setHasChanged(true);
       instance1.setIsNew(false);
       instance1.updateError("Test fail", false);
       
       BaseBusinessObject instance2 = new BaseBusinessObjectTest();
       instance2.setHasChanged(true);
       instance2.setIsNew(true);
       instance2.updateError("THIS SHOULD BE THE NEW STRING IN INSTANCE!", false);
       
       instance1.merge(instance2);
       
       //Ensure the stuff merged properly.
       assert(instance1.hasChanged() == instance2.hasChanged());
       assert(instance1.isValid() == instance2.isValid());
       assert(instance1.getErrorMessage().equals(instance2.getErrorMessage()));
       assert(instance1.getErrorMessage() != instance2.getErrorMessage());
       
       //Change the second instance and ensure it does not affect the first.
       instance2.setIsNew(true);
       instance2.updateError("THIS SHOULD BE THE NEW STRING IN INSTANCE!", true);
       instance2.setHasChanged(false);
       instance1.updateError("Test fail", true);
       assert(instance1.hasChanged() != instance2.hasChanged());
       assert(instance1.isValid() != instance2.isValid());
       assert(!instance1.getErrorMessage().equals(instance2.getErrorMessage()));
       assert(instance1.getErrorMessage() != instance2.getErrorMessage());

    }
    
    /* This method needs to notify all of its listeners if and only if 
        its valid state changes from true to false, or its valid state changes from false to true.
     * 
     */
    @Test
    public void testNotifyAllListenersOfValidStateAltered()
    {
        BaseBusinessObject instance1 = new BaseBusinessObjectTest();
        instance1.addListener(new TestListener());
        TestListener listener1 = new TestListener();
        TestListener listener2 = new TestListener();
        TestListener listener3 = new TestListener();
        instance1.addListener(listener1);
        instance1.addListener(listener2);
        instance1.addListener(listener3);
        
        //Asser that the listeners started off in a valid state.
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid());
        
        instance1.updateError("Cause the valid state to change", false);
        
        //assert that the listeners had their methods validStateChanged methods called(look at the private class below.)
        assert(!listener1.isValid());
        assert(!listener2.isValid());
        assert(!listener3.isValid());
        
        //Ensure the right method was called.
        assert(listener1.getErrorMessage().equals("The validStateAlteredFunction was reached"));
        assert(listener2.getErrorMessage().equals("The validStateAlteredFunction was reached"));
        assert(listener3.getErrorMessage().equals("The validStateAlteredFunction was reached"));
        
        //Force them into a valid state.
        listener1.updateError("The validStateAlteredFunction was reached", true);
        listener2.updateError("The validStateAlteredFunction was reached", true);
        listener3.updateError("The validStateAlteredFunction was reached", true);
        
        //Ensure they went back into a valid state before the next step.
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid());
        
        //Ensure the methods aren't called because the valid state wasn't actually changed.
        instance1.updateError("Cause the valid state to change", false);
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid());
        
        //Ensure that changing the valid state to true invoked the listener's haschanged methods.
        instance1.updateError("Cause the valid state to change", true);
        assert(instance1.isValid());
        assert(!listener1.isValid());
        assert(!listener2.isValid());
        assert(!listener3.isValid());
        
        //Ensrue the right method was called once again.
        assert(listener1.getErrorMessage().equals("The validStateAlteredFunction was reached"));
        assert(listener2.getErrorMessage().equals("The validStateAlteredFunction was reached"));
        assert(listener3.getErrorMessage().equals("The validStateAlteredFunction was reached"));
        
        //Forcibly change the listeners to be valid.
        listener1.updateError("The validStateAlteredFunction was reached", true);
        listener2.updateError("The validStateAlteredFunction was reached", true);
        listener3.updateError("The validStateAlteredFunction was reached", true);
        
        //ensure the listeners wer changed to be valid.
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid());
        
        //Ensure the valid state does not change and the listner's validStatEChanged not invoked.
        instance1.updateError("Cause the valid state to change", true);
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid());
    }
    
    /* This method needs to notify all of its listeners if and only if 
        its changed state changes from true to false, or its changed state changes from false to true.
     * 
     */
    @Test 
    public void testNotifyListenerOfChangedStateAltered()
    {
        BaseBusinessObject instance1 = new BaseBusinessObjectTest();
        instance1.addListener(new TestListener());
        TestListener listener1 = new TestListener();
        TestListener listener2 = new TestListener();
        TestListener listener3 = new TestListener();
        instance1.addListener(listener1);
        instance1.addListener(listener2);
        instance1.addListener(listener3);
        
        //Ensure the listeners start off valid.
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid());
        
        instance1.setHasChanged(true);
        //Ensure the changedStateAltered functions of the listeners are called.
        assert(!listener1.isValid());
        assert(!listener2.isValid());
        assert(!listener3.isValid());
        assert(listener1.getErrorMessage().equals("The changedStateAltered function was reached."));
        assert(listener2.getErrorMessage().equals("The changedStateAltered function was reached."));
        assert(listener3.getErrorMessage().equals("The changedStateAltered function was reached."));
        
        //force the listeners to be valid again.
        listener1.updateError("The changedStateAltered function was reached.", true);
        listener2.updateError("The changedStateAltered function was reached.", true);
        listener3.updateError("The changedStateAltered function was reached.", true);
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid());
        
        //Ensure the changedStateAltered functions are not called.
        instance1.setHasChanged(true);
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid());
        
        //Ensure the listeners changedStateAltered functions are called.
        instance1.setHasChanged(false);
        assert(!listener1.isValid());
        assert(!listener2.isValid());
        assert(!listener3.isValid());
        
        assert(listener1.getErrorMessage().equals("The changedStateAltered function was reached."));
        assert(listener2.getErrorMessage().equals("The changedStateAltered function was reached."));
        assert(listener3.getErrorMessage().equals("The changedStateAltered function was reached."));
        
        
        listener1.updateError("The changedStateAltered function was reached.", true);
        listener2.updateError("The changedStateAltered function was reached.", true);
        listener3.updateError("The changedStateAltered function was reached.", true);
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid());
        
        //Ensrue the methods are not called.
        instance1.setHasChanged(false);
        assert(listener1.isValid());
        assert(listener2.isValid());
        assert(listener3.isValid()); 
    }
    

  
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toDescriptiveString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**This class is a modified version of the listener class, built simply to report whether the 
     * methods were reached or not.
     */
    private class TestListener  extends BaseBusinessObject implements BusinessObjectListener
    {

        @Override
        public void validStateAltered(boolean newState, BaseBusinessObject sender) 
        {
                this.updateError("The validStateAlteredFunction was reached", false);
        }

        @Override
        public void changedStateAltered(boolean newState, BaseBusinessObject sender)
        {
            this.updateError("The changedStateAltered function was reached.", false);
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String toDescriptiveString() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
                
    }
    
}
