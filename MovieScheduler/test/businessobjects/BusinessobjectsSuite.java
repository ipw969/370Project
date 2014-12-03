/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author matthew
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ScheduleTest.class, businessobjects.BaseBusinessObjectTest.class, SceneTest.class, EquipmentTest.class, TimeIntervalTest.class, BusinessObjectListListenerTest.class, ScriptTest.class, VolunteerTest.class, BusinessObjectListTest.class, BusinessObjectListenerTest.class, SceneFilmingDateTest.class})
public class BusinessobjectsSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
