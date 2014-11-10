/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import businessobjects.BusinessObjectList;
import businessobjects.Equipment;
import businessobjects.Script;
import businessobjects.TimeInterval;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author iain
 */
public class PopulateScriptEquipmentAction extends BaseAction {

    public PopulateScriptEquipmentAction(Database database, Script script) {
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
            scriptToPopulate.setEquipment(loadEquipment());
            setWasSuccessful(true);
        }
        catch (SQLException ex)
        {
            setErrorMessage(ex.getMessage());
            setWasSuccessful(false);
        }
    }
    
    private BusinessObjectList<Equipment> loadEquipment() throws SQLException
    {
        String selectEquipmentQuery = 
        "SELECT " + 
        "eqp_equipmentname, eqp_firstname_owner, eqp_surname_owner, eqp_emailaddress_owner " +
        "FROM t_equipment;";

        BusinessObjectList<Equipment> equipmentList = 
                new BusinessObjectList<>();
        ResultSet selectResults = null;
        
        try{
            selectResults = database().executeSelect(selectEquipmentQuery);
            
                while (selectResults.next())
                {
                    String equipmentName = selectResults.getString(1);
                    String firstName = selectResults.getString(2);
                    String surname = selectResults.getString(3);
                    String email = selectResults.getString(4);

                    BusinessObjectList<TimeInterval> availabilities = 
                            loadEquipmentAvailabilities(equipmentName);
                
                    Equipment newEquipment = new Equipment(equipmentName,
                        0, false, 0);
                    
                    newEquipment.setAvailabilities(availabilities);
                    equipmentList.add(newEquipment);
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
    
    private BusinessObjectList<TimeInterval> loadEquipmentAvailabilities(
                                            String equipmentName)
            throws SQLException
    {
        String selectAvailabilityQuery = 
        "SELECT " + 
        "eav_availability_start, eav_availability_end " +
        "FROM t_equipmentavailability " +
        "WHERE eav_equipmentname = '" + equipmentName + "';";

        BusinessObjectList<TimeInterval> availabilityList = 
                new BusinessObjectList<>();
        ResultSet selectResults = null;
        
        try{
            selectResults = database().executeSelect(selectAvailabilityQuery);
            
                while (selectResults.next())
                {
                    Timestamp startDate = selectResults.getTimestamp(1);
                    Timestamp endDate = selectResults.getTimestamp(2);
                    GregorianCalendar startCalendar = new GregorianCalendar();
                    startCalendar.setTime(startDate);
                    
                    GregorianCalendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(endDate);

                    TimeInterval availability = new TimeInterval(startCalendar, endCalendar);
                
                    availabilityList.add(availability);
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
        
        return availabilityList;
    }
}
