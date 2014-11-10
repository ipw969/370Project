/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author iain
 */
public class PopulateScriptScenesAction extends BaseAction{

    public PopulateScriptScenesAction(Database database, Script script) {
        super(database);
        setBusinessObject(script);
    }

    @Override
    protected void runImplementation() {
        if(businessObject() == null)
        {
            setErrorMessage("Can't populate data for a null script.");
            setWasSuccessful(false);
            return;
        }
        
        if(!(businessObject() instanceof Script))
        {
            setErrorMessage("Can't populate data into an object which is not"
                    + " a script.");
        }
        
        Script scriptToPopulate = (Script)businessObject();
        
        try{
            scriptToPopulate.setScenes(loadScenes(scriptToPopulate.name()));
            setWasSuccessful(true);
        }
        catch (SQLException ex)
        {
            setErrorMessage(ex.getMessage());
            setWasSuccessful(false);
        }
    }
    
    private Script script()
    {
        if (businessObject() == null || !(businessObject() instanceof Script))
            return null;
        
        return (Script)businessObject();
    }
    
    private BusinessObjectList<Scene> loadScenes(String scriptName) throws SQLException
    {
        String selectScenesQuery = 
        "SELECT " + 
        "scn_scenename, scn_sceneisfilmed, scn_scenedescription, scn_scriptname " +
        "FROM t_scene " +
        "WHERE scn_scriptname = '" + scriptName + "';";

        BusinessObjectList<Scene> sceneList = 
                new BusinessObjectList<>();
        ResultSet selectResults = null;
        
        try{
            selectResults = database().executeSelect(selectScenesQuery);
            
            while (selectResults.next())
            {
                String sceneName = selectResults.getString(1);
                boolean sceneIsFilmed = selectResults.getBoolean(2);
                String sceneDescription = selectResults.getString(3);

                BusinessObjectList<Volunteer> sceneVolunteers = 
                        loadSceneVolunteers(sceneName);

                BusinessObjectList<Equipment> sceneEquipment =
                        loadSceneEquipment(sceneName);

                Scene newScene = new Scene(sceneName, sceneDescription);

                //newScene.setVolunteers(sceneVolunteers);
                //newScene.setEquipment(sceneEquipment);
                sceneList.add(newScene);
            }
        } catch(SQLException ex)
        {
            throw ex;
        }
        finally
        {
            if(selectResults != null)
                selectResults.close();
        }
        
        return sceneList;
    }
    
    private BusinessObjectList<Volunteer> loadSceneVolunteers(
                                            String sceneName)
            throws SQLException
    {
        String selectSceneVolunteersQuery = 
        "SELECT " + 
        "snv_emailaddress_volunteer " +
        "FROM t_scenevolunteer " +
        "WHERE snv_scenename = '" + sceneName + "';";

        BusinessObjectList<Volunteer> volunteerList = 
                new BusinessObjectList<>();
        ResultSet selectResults = null;
        
        try{
            selectResults = database().executeSelect(selectSceneVolunteersQuery);
            
            while (selectResults.next())
            {
               String volunteerEmail = selectResults.getString(1);

               for(Volunteer currentVolunteer : script().volunteers())
               {
                   if(volunteerEmail.compareTo(currentVolunteer.getEmail()) == 0)
                       volunteerList.add(currentVolunteer);
               }
            }
        } catch(SQLException ex)
        {
            throw ex;
        }
        finally
        {
            if(selectResults != null)
                selectResults.close();
        }
        
        return volunteerList;
    }
    
    private BusinessObjectList<Equipment> loadSceneEquipment(
                                            String sceneName)
            throws SQLException
    {
        String selectSceneEquipmentQuery = 
        "SELECT " + 
        "sne_equipmentname " +
        "FROM t_sceneequipment " +
        "WHERE sne_scenename = '" + sceneName + "';";

        BusinessObjectList<Equipment> equipmentList = 
                new BusinessObjectList<>();
        ResultSet selectResults = null;
        
        try{
            selectResults = database().executeSelect(selectSceneEquipmentQuery);
            
            while (selectResults.next())
            {
               String equipmentName = selectResults.getString(1);

               for(Equipment currentEquipment : script().equipment())
               {
                   if(equipmentName.compareTo(currentEquipment.getEquipmentType()) == 0)
                       equipmentList.add(currentEquipment);
               }
            }
        } catch(SQLException ex)
        {
            throw ex;
        }
        finally
        {
            if(selectResults != null)
                selectResults.close();
        }
        
        return equipmentList;
    }
}
