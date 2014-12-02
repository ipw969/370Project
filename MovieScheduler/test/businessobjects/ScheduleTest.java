/*
 *This JUnnit test is used for testing the functionality of the schedule class.
* It is designed to perform statement level coverage.
 */
package businessobjects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import businessobjects.Schedule;
import java.util.GregorianCalendar;

/**
 *
 * @author Ryan
 */
public class ScheduleTest {
    
    public ScheduleTest() 
    {
        
    }
    
    
    @Before
    public void setUp() 
    {
        
    }
    
    @After
    public void tearDown() 
    {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /**Test getScheduledConflicts statement level.
     *
     */
    @Test
    public void  testgetScheduleConflicts()
    {
        //Construct a new volunteer to add to the scene.
        Volunteer volunteerInConflict = new Volunteer("Bobby", "lat", "testemail", "2490053");
        volunteerInConflict.addAvailability(new TimeInterval(new GregorianCalendar(2014,10,20,12,0), new GregorianCalendar(2014,10,20,1, 0)));
        
        //Crete a scene that will be in conflict and add the volunteer to is.
         Scene sceneInConflict = new Scene("Test Scene", "This is a test scene");
        sceneInConflict.addVolunteer(volunteerInConflict);
       
        //Add the scene to a sceneFilmingDate and set the time to conflict with that of the voluntters availability. 
        SceneFilmingDate sceneFilmingDate = new SceneFilmingDate();
        sceneFilmingDate.setScene(sceneInConflict);
        sceneFilmingDate.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,11,20,12,0), new GregorianCalendar(2014,11,20,1,0)));
        
        //Create the schedule and add the sceneFilmingDate. This sceneFilmingDate should be in conflict with its volunteer 
        //because its shooting date is scheduled to occur when The volunteer is not available.
        Schedule schedule = new Schedule();
        schedule.add(sceneFilmingDate);
        
        //Assert that the conflict was caught.
        //Note: This has stepped through all statements of the code.
        assertFalse(schedule.getScheduleConflicts().isEmpty());
        
        volunteerInConflict.addAvailability(new TimeInterval(new GregorianCalendar(2014,11,20,12,30), new GregorianCalendar(2014,11,20,13,00)));
        assertFalse(schedule.getScheduleConflicts().isEmpty());
        
        
        //SceneShooting interval 2014-12-20 12:00 to 2014-12-20 1:00,   this volunteer now has
        //2014-12-20 11:30 - 2014-12-22  13:00
        volunteerInConflict.addAvailability(new TimeInterval(new GregorianCalendar(2014,11,20,11,30), new GregorianCalendar(2014,11,22,13,00)));
        assertTrue(schedule.getScheduleConflicts().isEmpty());
        
        
        
    }    
    
    
    
    
    
    
    
    //public BusinessObjectList<SceneFilmingDate> getScheduleFor(GregorianCalendar date);
   // public SceneFilmingDate getScenesFilmingDate(Scene scene);
}
