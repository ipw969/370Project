/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import businessobjects.Scene;
import database.Database;

/**
 *
 * @author ryan
 */
public class SaveSceneAction extends BaseAction {

    
    public SaveSceneAction(Database database ,Scene sceneToSave) 
    {
        super(database);
        setBusinessObject(sceneToSave);
    }
    
    

    @Override
    protected void runImplementation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
