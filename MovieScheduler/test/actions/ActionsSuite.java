/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

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
@Suite.SuiteClasses({actions.PopulateScriptScheduleActionTest.class, actions.DeleteVolunteerActionTest.class, actions.LoadScriptActionTest.class, actions.PopulateScriptEquipmentActionTest.class, actions.DeleteSceneFilmingDateActionTest.class, actions.DeleteEquipmentActionTest.class, actions.SaveSceneActionTest.class, actions.LoadVolunteerActionTest.class, actions.SaveSceneFilmingDateActionTest.class, actions.DeleteSceneActionTest.class, actions.BaseActionTest.class, actions.PopulateScriptScenesActionTest.class, actions.PopulateScriptVolunteersActionTest.class, actions.SaveEquipmentActionTest.class, actions.SaveVolunteerActionTest.class})
public class ActionsSuite {

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
