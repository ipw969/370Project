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

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
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
    
    
    
    
    
    
    

    /**
     * Test of getScenesFilmingDate method, of class Schedule.
     */
    @Test
    public void testGetScenesFilmingDate() 
    {
        Scene scene1 = new Scene("Test1", "test1");
        Scene scene2 = new Scene("Test2", "test2");
        Scene scene3 = new Scene("3", "test3");
        Scene scene4 = new Scene("Test4", "test4");
        Scene scene5 = new Scene("Test5", "test5");
        Scene scene6 = new Scene("Test6", "test6");
        Scene scene7 = new Scene("Test7", "test7");
        Scene scene8 = new Scene("Test8", "test8");
        Scene scene9 = new Scene("Test9", "test9");
        Scene scene10 = new Scene("Test10", "test10");
        Scene scene11 = new Scene("Test11", "test11");
        Scene scene12 = new Scene("Test12", "test12");
        Scene scene13 = new Scene("Test13", "test13");
        Scene scene14 = new Scene("Test14", "test14");
        
        SceneFilmingDate filmingDate1 = new SceneFilmingDate();
        SceneFilmingDate filmingDate2 = new SceneFilmingDate();
        SceneFilmingDate filmingDate3 = new SceneFilmingDate();
        SceneFilmingDate filmingDate4 = new SceneFilmingDate();
        SceneFilmingDate filmingDate5 = new SceneFilmingDate();
        SceneFilmingDate filmingDate6 = new SceneFilmingDate();
        SceneFilmingDate filmingDate7 = new SceneFilmingDate();
        SceneFilmingDate filmingDate8 = new SceneFilmingDate();
        SceneFilmingDate filmingDate9 = new SceneFilmingDate();
        SceneFilmingDate filmingDate10 = new SceneFilmingDate();
        SceneFilmingDate filmingDate11 = new SceneFilmingDate();
        SceneFilmingDate filmingDate12 = new SceneFilmingDate();
        SceneFilmingDate filmingDate13 = new SceneFilmingDate();
        SceneFilmingDate filmingDate14 = new SceneFilmingDate();
        
        
        filmingDate1.setScene(scene1);
        filmingDate2.setScene(scene2);
        filmingDate3.setScene(scene3);
        filmingDate4.setScene(scene4);
        filmingDate5.setScene(scene5);
        filmingDate6.setScene(scene6);
        filmingDate7.setScene(scene7);
        filmingDate8.setScene(scene8);
        filmingDate9.setScene(scene9);
        filmingDate10.setScene(scene10);
        filmingDate11.setScene(scene11);
        filmingDate12.setScene(scene12);
        filmingDate13.setScene(scene13);
        filmingDate14.setScene(scene14);
        
        //These are filmed on 2014-11-20
        filmingDate1.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,11,20,0,00), new GregorianCalendar(2014,11,20,0,0)));
         filmingDate2.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,11,20,23,59), new GregorianCalendar(2014,11,20,23,59)));
         
         // 2014-11-21
          filmingDate3.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,11,21,0,0), new GregorianCalendar(2014,11,21,0,0)));
          //2014-11-19
           filmingDate4.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,11,19,23,59), new GregorianCalendar(2014,11,19,23,59)));
           
           //2014-10-20
            filmingDate5.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,10,20,0,00), new GregorianCalendar(2014,10,20,00,00)));
              filmingDate6.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,10,20,23,59), new GregorianCalendar(2014,10,20,23,59)));
               
              //2014-12-20
                filmingDate7.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,12,20,0,00), new GregorianCalendar(2014,12,20,00,00)));
                  filmingDate8.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014,12,20,23,59),new GregorianCalendar(2014,12,20,23,59)));
                  
                  //0000-10-20
                    filmingDate9.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(0000,10,20,0,00), new GregorianCalendar(0000,10,20,00,00)));
                    //10000-10-20-23-59
                     filmingDate10.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(10000,10,20,23,59), new GregorianCalendar(10000,10,20,23,59)));
                     
                     //2015-11-20
                filmingDate11.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2015,11,20,0,00), new GregorianCalendar(2015,11,20,00,00)));
                  filmingDate12.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2015,11,20,23,59),new GregorianCalendar(2015,11,20,23,59)));
                  
                  //2013-11-20
                    filmingDate13.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2013,11,20,0,00), new GregorianCalendar(2013,11,20,00,00)));
                    filmingDate14.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2013,11,20,23,59), new GregorianCalendar(2013,11,20,23,59)));
                    
            
        Schedule schedule = new Schedule();
        schedule.add(filmingDate1);
        schedule.add(filmingDate2);
        schedule.add(filmingDate3);
        schedule.add(filmingDate4);
        schedule.add(filmingDate5);
        schedule.add(filmingDate6);
        schedule.add(filmingDate7);
        schedule.add(filmingDate8);
        schedule.add(filmingDate9);
        schedule.add(filmingDate10);
        schedule.add(filmingDate11);
        schedule.add(filmingDate12);
        schedule.add(filmingDate13);
        schedule.add(filmingDate14);
        
        
        //Ensure that the sceneFilmingDates are paired with the correct scenes.
        assert(schedule.getScenesFilmingDate(scene1).equals(filmingDate1));
        assert(schedule.getScenesFilmingDate(scene2).equals(filmingDate2));
        assert(schedule.getScenesFilmingDate(scene3).equals(filmingDate3));
        assert(schedule.getScenesFilmingDate(scene4).equals(filmingDate4));
        assert(schedule.getScenesFilmingDate(scene5).equals(filmingDate5));
        assert(schedule.getScenesFilmingDate(scene6).equals(filmingDate6));
        assert(schedule.getScenesFilmingDate(scene7).equals(filmingDate7));
        assert(schedule.getScenesFilmingDate(scene8).equals(filmingDate8));
        assert(schedule.getScenesFilmingDate(scene9).equals(filmingDate9));
        assert(schedule.getScenesFilmingDate(scene10).equals(filmingDate10));
        assert(schedule.getScenesFilmingDate(scene11).equals(filmingDate11));
        assert(schedule.getScenesFilmingDate(scene12).equals(filmingDate12));
        assert(schedule.getScenesFilmingDate(scene13).equals(filmingDate13));
        assert(schedule.getScenesFilmingDate(scene14).equals(filmingDate14));
        
        
        
       
    }
}
