package ui;

import businessobjects.BaseBusinessObject;
import businessobjects.BusinessObjectList;
import businessobjects.BusinessObjectListListener;
import businessobjects.SceneFilmingDate;
import businessobjects.Schedule;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Iain Workman
 */
public class CalendarMonth extends javax.swing.JPanel
        implements BusinessObjectListListener {

    // Constructor
    /**
     * Creates new form CalendarMonth
     */
    public CalendarMonth() {
        // Setup the list of CalendarDays which will be displayed in the panel
        calendarDays = new ArrayList<>(42);
        for (int iCalendarDay = 0; iCalendarDay < 42; iCalendarDay++) {
            calendarDays.add(new CalendarDay());
        }
        // Set the currentMonthStart to this month
        currentMonthStart = new GregorianCalendar();
        currentMonthStart.set(Calendar.DAY_OF_MONTH, 1);
        initComponents();
        refreshCalendarDays();
    }

    // Public Methods
    public void setDate(int year, int month) {
        currentMonthStart = new GregorianCalendar(year, month, 1);
        currentMonthStart.set(Calendar.HOUR, 0);
        currentMonthStart.set(Calendar.MINUTE, 0);
        currentMonthStart.set(Calendar.SECOND, 0);
        refreshCalendarDays();
    }

    public void setSchedule(Schedule schedule) {
        if (filmingSchedule != null) {
            filmingSchedule.removeListener(this);
        }

        filmingSchedule = schedule;
        filmingSchedule.addListener(this);
        refreshCalendarDays();
    }

    @Override
    public void businessObjectAdded(BaseBusinessObject itemAdded) {
        if (itemAdded instanceof SceneFilmingDate) {
            SceneFilmingDate addedFilmingDate = (SceneFilmingDate) itemAdded;

            for (CalendarDay currentCalendarDay : calendarDays) {
                java.text.SimpleDateFormat sdf
                        = new java.text.SimpleDateFormat("YYYY-MM-dd hh:mm:ss");

                System.out.println(sdf.format(currentCalendarDay.date().getTime()));
                if (addedFilmingDate.getSceneShootingInterval().isOnThisDate(currentCalendarDay.date())) {
                    currentCalendarDay.filmingDates().add(addedFilmingDate);
                }
            }
        }
    }

    @Override
    public void businessObjectRemoved(BaseBusinessObject itemRemoved) {
        if (itemRemoved instanceof SceneFilmingDate) {
            SceneFilmingDate removedFilmingDate = (SceneFilmingDate) itemRemoved;

            for (CalendarDay currentCalendarDay : calendarDays) {
                for (SceneFilmingDate currentDayFilmingDate : currentCalendarDay.filmingDates()) {
                    if (currentDayFilmingDate.getScene().getName().compareTo(removedFilmingDate.getScene().getName()) == 0) {
                        currentCalendarDay.filmingDates().remove(currentDayFilmingDate);
                    }
                }
            }
        }
    }

    @Override
    public void listCleared() {
        //Shouldn't really happen, but we'll deal with the case anyway
        for (CalendarDay currentCalendarDay : calendarDays) {
            currentCalendarDay.filmingDates().clear();
        }
    }

    @Override
    public void validStateAltered(boolean newState, BaseBusinessObject sender) {
        // We're not editing the items here, so we don't need to handle this
    }

    @Override
    public void changedStateAltered(boolean newState, BaseBusinessObject sender) {
        if (sender instanceof SceneFilmingDate) {
            SceneFilmingDate alteredFilmingDate = (SceneFilmingDate) sender;

            for (CalendarDay currentCalendarDay : calendarDays) {
                if (alteredFilmingDate.getSceneShootingInterval().overlaps(currentCalendarDay.date())) {
                    currentCalendarDay.filmingDates().add(alteredFilmingDate);
                }
                SceneFilmingDate filmingDateToDelete = null;
                for (SceneFilmingDate currentDayFilmingDate : currentCalendarDay.filmingDates()) {
                    if (currentDayFilmingDate.getScene().getName().compareTo(alteredFilmingDate.getScene().getName()) == 0) {
                        filmingDateToDelete = currentDayFilmingDate;
                    }
                }
                if (filmingDateToDelete != null) {
                    currentCalendarDay.filmingDates().remove(filmingDateToDelete);
                }
            }
        }

    }

    // Private Methods
    private void refreshCalendarDays() {
        // Clear the current compoenents from the Panel
        this.removeAll();

        // We'll need to keep track of how many CalendarDays we've added to the
        // panel
        int calendarDaysAdded = 0;

        // First we need to clone the currentMonthStart, as we'll be altering it
        // as we go
        GregorianCalendar calendarDate
                = (GregorianCalendar) currentMonthStart.clone();

        // Then we need to iterate back to the first day of the week
        while (calendarDate.get(Calendar.DAY_OF_WEEK) != calendarDate.getFirstDayOfWeek()) {
            calendarDate.add(Calendar.DAY_OF_WEEK, -1);
        }

        // Then we need to add each day to the calendarDays list, until we're
        // at the start of the month we want
        while (calendarDate.get(Calendar.MONTH) != currentMonthStart.get(Calendar.MONTH)) {
            CalendarDay currentCalendarDay = calendarDays.get(calendarDaysAdded);
            currentCalendarDay.setDate(calendarDate);
            currentCalendarDay.setForeground(Color.LIGHT_GRAY);

            if (filmingSchedule != null) {
                populateCalendarDay(currentCalendarDay, calendarDate);
            }
            add(currentCalendarDay);
            calendarDaysAdded += 1;
            calendarDate.add(Calendar.DAY_OF_WEEK, 1);
        }

        // Then we need to add each day to the calendarDays list, until we've
        // reached the end of the last day in this calendar month
        while (calendarDate.get(Calendar.MONTH) == currentMonthStart.get(Calendar.MONTH)) {
            CalendarDay currentCalendarDay = calendarDays.get(calendarDaysAdded);
            currentCalendarDay.setDate(calendarDate);
            currentCalendarDay.setForeground(Color.BLACK);

            if (filmingSchedule != null) {
                populateCalendarDay(currentCalendarDay, calendarDate);
            }
            add(currentCalendarDay);
            calendarDaysAdded += 1;
            calendarDate.add(Calendar.DAY_OF_WEEK, 1);
        }

        // Then we need to fill up the rest of the list with the remaining days
        // left in this week
        while (calendarDate.get(Calendar.DAY_OF_WEEK) != calendarDate.getFirstDayOfWeek()) {
            CalendarDay currentCalendarDay = calendarDays.get(calendarDaysAdded);
            currentCalendarDay.setDate(calendarDate);
            currentCalendarDay.setForeground(Color.LIGHT_GRAY);

            if (filmingSchedule != null) {
                populateCalendarDay(currentCalendarDay, calendarDate);
            }
            add(currentCalendarDay);
            calendarDaysAdded += 1;
            calendarDate.add(Calendar.DAY_OF_WEEK, 1);
        }

        // Revalidate and paint the panel
        this.revalidate();
        this.repaint();
    }

    private void populateCalendarDay(CalendarDay calendarDay, GregorianCalendar date) {
        BusinessObjectList<SceneFilmingDate> daysSchedule
                = filmingSchedule.getScheduleFor(date);

        for (SceneFilmingDate currentFilmingDate : daysSchedule) {
            calendarDay.filmingDates().add(currentFilmingDate);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(0, 7));
    }// </editor-fold>//GEN-END:initComponents

    // Private Member Variables
    private final ArrayList<CalendarDay> calendarDays;
    private GregorianCalendar currentMonthStart;
    private Schedule filmingSchedule;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
