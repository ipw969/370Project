/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import businessobjects.SceneFilmingDate;
import database.Database;
import java.sql.SQLException;

/**
 * A class representing an action to remove a single SceneFilmingDate from the
 * Schedule in the datebase.
 *
 * @author Iain Workman
 */
public class DeleteSceneFilmingDateAction extends BaseAction {

    // Constructor
    /**
     * Creates a new instance of a DeleteSceneFilmingDateAction which will
     * delete the provided SceneFilmingDate from the provided Database.
     *
     * @param database::Database ~ The database to delete the SceneFilmingDate
     * from
     * @param filmingDate::SceneFilmingDate ~ The SceneFilmingDate to delete
     * from the Database
     */
    public DeleteSceneFilmingDateAction(Database database,
            SceneFilmingDate filmingDate) {
        super(database);
        this.setBusinessObject(filmingDate);
    }

    // Protected Methods
    /**
     * The Action's implementation of deleting the SceneFilmingDate
     */
    @Override
    protected void runImplementation() {

        SceneFilmingDate filmingDate = getSceneFilmingDate();

        if (filmingDate == null) {
            setErrorMessage("Cannot remove a null filming date");
            setWasSuccessful(false);
        } else if (filmingDate.scene() == null) {
            setErrorMessage("Cannot remove a filming date that contains no"
                    + " Scene");
            setWasSuccessful(false);
        } else if (getDatabase() == null) {
            setErrorMessage("Cannot remove a filming date from a null database");
            setWasSuccessful(false);
        } else {
            String queryString = getDeleteString();
            try {
                getDatabase().executeDelete(queryString);
                setWasSuccessful(true);
            } catch (SQLException ex) {
                    setErrorMessage("Unable to remove filming date with message: "
                            + ex.getMessage());
                    setWasSuccessful(false);
            }
        }

    }

    // Private Methods
    /**
     * Returns a formatted query to delete the SceneFilmingDate
     */
    private String getDeleteString() {
        SceneFilmingDate filmingDate = getSceneFilmingDate();

        String returnString = "DELETE FROM t_schedule "
                + "WHERE shc_scenename = '" + filmingDate.scene() + "';";

        return returnString;
    }

    /**
     * Returns the SceneFilmingDate on which this action is being performed
     */
    private SceneFilmingDate getSceneFilmingDate() {
        return (SceneFilmingDate) getBusinessObject();
    }
}
