package actions;

import businessobjects.BusinessObjectList;
import businessobjects.Equipment;
import businessobjects.Scene;
import businessobjects.Script;
import businessobjects.Volunteer;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class representing an Action which populates the Scenes associated with a
 * Script into the passed Script object.
 *
 * @author Iain Workman
 */
public class PopulateScriptScenesAction extends BaseAction {

    // Constructor

    /**
     * Loads an instance of a PopulateScriptSceneAction which will load Scene
     * data from the provided Database into the provided Script.
     *
     * @param database::Database ~ The Database from which to load the data.
     * @param script::Script ~ The Script into which to load the Scenes.
     */
    public PopulateScriptScenesAction(Database database, Script script) {
        super(database);
        setBusinessObject(script);
    }

    // Protected Methods
    /**
     * Runs the Action of loading the Scene data from the database into the
     * provided Script. - wasSuccessful() == The success state of the action -
     * errorMessage() == Any errors encountered by the action if failed -
     * businessObject() == The script with populated Scene info.
     */
    @Override
    protected void runImplementation() {
        if (getBusinessObject() == null) {
            setErrorMessage("Can't populate data for a null script.");
            setWasSuccessful(false);
            return;
        }

        if (!(getBusinessObject() instanceof Script)) {
            setErrorMessage("Can't populate data into an object which is not"
                    + " a script.");
        }

        Script scriptToPopulate = (Script) getBusinessObject();

        try {
            scriptToPopulate.setScenes(loadScenes(scriptToPopulate.getName()));
            setWasSuccessful(true);
        } catch (SQLException ex) {
            setErrorMessage(ex.getMessage());
            setWasSuccessful(false);
        }
    }

    // Private Methods
    /**
     * The Script which will be populated by the Action.
     *
     * @return The Script which will be populated by the Action.
     */
    private Script script() {
        if (getBusinessObject() == null || !(getBusinessObject() instanceof Script)) {
            return null;
        }

        return (Script) getBusinessObject();
    }

    /**
     * Loads the list of Scenes from the Database
     *
     * @param scriptName::String ~ The name of the Script whose Scenes are to be
     * loaded
     * @return The list of Scenes which belong to the Script being populated
     * @throws SQLException
     */
    private BusinessObjectList<Scene> loadScenes(String scriptName) throws SQLException {
        String selectScenesQuery
                = "SELECT "
                + "scn_scenename, scn_sceneisfilmed, scn_scenedescription, scn_scriptname "
                + "FROM t_scene "
                + "WHERE scn_scriptname = '" + scriptName + "';";

        BusinessObjectList<Scene> sceneList
                = new BusinessObjectList<>();
        ResultSet selectResults = null;

        try {
            selectResults = getDatabase().executeSelect(selectScenesQuery);

            while (selectResults.next()) {
                String sceneName = selectResults.getString(1);
                boolean sceneIsFilmed = selectResults.getBoolean(2);
                String sceneDescription = selectResults.getString(3);

                BusinessObjectList<Volunteer> sceneVolunteers
                        = loadSceneVolunteers(sceneName);

                BusinessObjectList<Equipment> sceneEquipment
                        = loadSceneEquipment(sceneName);

                Scene newScene = new Scene(sceneName, sceneDescription);

                newScene.setVolunteers(sceneVolunteers);
                newScene.setEquipment(sceneEquipment);
                newScene.setIsNew(false);
                newScene.setHasChanged(false);
                sceneList.add(newScene);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (selectResults != null) {
                selectResults.close();
            }
        }

        return sceneList;
    }

    /**
     * Loads the data for the Volunteers which are associated with the Scene
     * with the passed SceneName into a list. The list will contain references
     * to the same Volunteer objects which are in the provided Script.
     *
     * @param sceneName::String ~ The name of the Scene whose Volunteers are to
     * be loaded
     * @return A list of references to Volunteers who are associated with this
     * Scene.
     * @throws SQLException
     */
    private BusinessObjectList<Volunteer> loadSceneVolunteers(
            String sceneName)
            throws SQLException {
        String selectSceneVolunteersQuery
                = "SELECT "
                + "snv_emailaddress_volunteer "
                + "FROM t_scenevolunteer "
                + "WHERE snv_scenename = '" + sceneName + "';";

        BusinessObjectList<Volunteer> volunteerList
                = new BusinessObjectList<>();
        ResultSet selectResults = null;

        try {
            selectResults = getDatabase().executeSelect(selectSceneVolunteersQuery);

            while (selectResults.next()) {
                String volunteerEmail = selectResults.getString(1);

                for (Volunteer currentVolunteer : script().getVolunteers()) {
                    if (volunteerEmail.compareTo(currentVolunteer.getEmail()) == 0) {
                        volunteerList.add(currentVolunteer);
                    }
                }
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (selectResults != null) {
                selectResults.close();
            }
        }

        return volunteerList;
    }

    /**
     * Loads the data for the Equipment which is associated with the Scene with
     * the passed SceneName into a list. The list will contain references to the
     * same Equipment objects which are in the provided Script.
     *
     * @param sceneName::String ~ The name of the Scene whose Equipment is to be
     * loaded
     * @return A list of references to Equipment which is associated with this
     * Scene.
     * @throws SQLException
     */
    private BusinessObjectList<Equipment> loadSceneEquipment(
            String sceneName)
            throws SQLException {
        String selectSceneEquipmentQuery
                = "SELECT "
                + "sne_equipmentname "
                + "FROM t_sceneequipment "
                + "WHERE sne_scenename = '" + sceneName + "';";

        BusinessObjectList<Equipment> equipmentList
                = new BusinessObjectList<>();
        ResultSet selectResults = null;

        try {
            selectResults = getDatabase().executeSelect(selectSceneEquipmentQuery);

            while (selectResults.next()) {
                String equipmentName = selectResults.getString(1);

                for (Equipment currentEquipment : script().getEquipment()) {
                    if (equipmentName.compareTo(currentEquipment.getEquipmentName()) == 0) {
                        equipmentList.add(currentEquipment);
                    }
                }
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (selectResults != null) {
                selectResults.close();
            }
        }

        return equipmentList;
    }
}
