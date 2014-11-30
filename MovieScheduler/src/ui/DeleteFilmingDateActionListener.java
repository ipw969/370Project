package ui;

/**
 * An interface this is implemented by classes which wish to observe 
 * DeleteFilmingDateEvents
 * @author Iain Workman
 */
public interface DeleteFilmingDateActionListener {
    /**
     * Action to be performed on a notification that a FilmingDate is to be
     * deleted
     * @param e::DeleteFilmingDateEvent ~ The source event containing the 
     * SceneFilmingDate to be deleted.
     */
    public void deleteActionPerformed(DeleteFilmingDateEvent e);
}
