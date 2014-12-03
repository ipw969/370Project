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
        volunteerInConflict.addAvailability(new TimeInterval(new GregorianCalendar(2014,10,20,12,0), new GregorianCalendar(2014,10,20,13, 0)));
        
        //Crete a scene that will be in conflict and add the volunteer to is.
         Scene sceneInConflict = new Scene("Test Scene", "This is a test scene");
        sceneInConflict.addVolunteer(volunteerInConflict);
       
        //Add the scene to a sceneFilmingDate and set the time to conflict with that of the voluntters availability. 
        SceneFilmingDate sceneFilmingDate = new SceneFilmingDate();
        sceneFilmingDate.setScene(sceneInConflict);
        sceneFilmingDate.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,11,20,12,0), new GregorianCalendar(2014,11,20,13,0)));
        
        //Create the schedule and add the sceneFilmingDate. This sceneFilmingDate should be in conflict with its volunteer 
        //because its shooting date is scheduled to occur when The volunteer is not available.
        Schedule schedule = new Schedule();
        schedule.add(sceneFilmingDate);
        
        //Assert that the conflict was caught.
        //Note: This has stepped through all statements of the code.
        assertFalse(schedule.getScheduleConflicts().isEmpty());
        
        volunteerInConflict.addAvailability(new TimeInterval(new GregorianCalendar(2014,11,20,12,30), new GregorianCalendar(2014,11,20,13,00)));
        assertTrue("Scene should be in conflict, but getScheduleConflicts() is Empty", 
                schedule.getScheduleConflicts().isEmpty());
        
        
        //SceneShooting interval 2014-12-20 12:00 to 2014-12-20 1:00,   this volunteer now has
        //2014-12-20 11:30 - 2014-12-22  13:00 and the scene is not in conflict
        volunteerInConflict.addAvailability(new TimeInterval(new GregorianCalendar(2014,11,20,11,30), new GregorianCalendar(2014,11,22,13,00)));
		
		assertTrue( "Scene should not be in conflict, but getScheduleConflicts() is not Empty",
                schedule.getScheduleConflicts().isEmpty());    
				
        assertTrue(schedule.getScheduleConflicts().isEmpty());
 
    }  
    
    //* testing the getScheduleFor function. This functions is fairly simple and should just return the given date.
    
    @Test
    public void testgetScheduleFor()
    {
        SceneFilmingDate filmingDate1 = new SceneFilmingDate();
        SceneFilmingDate filmingDate2 = new SceneFilmingDate();
        SceneFilmingDate filmingDate3 = new SceneFilmingDate();
         SceneFilmingDate filmingDate4 = new SceneFilmingDate();
        SceneFilmingDate filmingDate5 = new SceneFilmingDate();
        SceneFilmingDate filmingDate6 = new SceneFilmingDate();
         SceneFilmingDate filmingDate7 = new SceneFilmingDate();
        SceneFilmingDate filmingDate8 = new SceneFilmingDate();
        SceneFilmingDate filmingDate9 = new SceneFilmingDate();
        
        
        //filmingDate1 will be in the appropriate time. filminDate2 and 3 will difer by one day, and the edge cases.
        filmingDate1.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,11,20,12,0), new GregorianCalendar(2014,11,20,13,0)));
        filmingDate2.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,11,19,23,59), new GregorianCalendar(2014,11,19,23,59)));
        filmingDate3.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,11,21,0,0), new GregorianCalendar(2014,11,21,0,0)));
        
        //These will differ by a month and hit the edge cases. 
        filmingDate4.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,10,31,23,59), new GregorianCalendar(2014,10,31,23,59)));
        filmingDate5.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,12,1,00,01), new GregorianCalendar(2014,12,1,00,01)));
        
        //These will differ by a year and hit the edge cases.
        filmingDate6.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2015,1,1,00,01), new GregorianCalendar(2015,1,1,00,01)));
        filmingDate7.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2013,12,31,23,59), new GregorianCalendar(2013,12,31,23,59)));
        
        Schedule testSchedule = new Schedule();
        
        testSchedule.add(filmingDate1);
         testSchedule.add(filmingDate2);
          testSchedule.add(filmingDate3);
           testSchedule.add(filmingDate4);
            testSchedule.add(filmingDate5);
             testSchedule.add(filmingDate6);
              testSchedule.add(filmingDate7);
              
              GregorianCalendar dateToTest = new GregorianCalendar(2014,11,20,0,0);
              
              //The schedule should contain this filming date for the above date.
          assertTrue(testSchedule.getScheduleFor(dateToTest).contains(filmingDate1));
          
          //The schedule should not contain any of these for the above date.
          assertFalse(testSchedule.getScheduleFor(dateToTest).contains(filmingDate2));
           assertFalse(testSchedule.getScheduleFor(dateToTest).contains(filmingDate3));
            assertFalse(testSchedule.getScheduleFor(dateToTest).contains(filmingDate4));
             assertFalse(testSchedule.getScheduleFor(dateToTest).contains(filmingDate5));
              assertFalse(testSchedule.getScheduleFor(dateToTest).contains(filmingDate6));
               assertFalse(testSchedule.getScheduleFor(dateToTest).contains(filmingDate7));
          
    }
    
    
    
    
    
    
    
    //public BusinessObjectList<SceneFilmingDate> getScheduleFor(GregorianCalendar date);
   // public SceneFilmingDate getScenesFilmingDate(Scene scene);
}
