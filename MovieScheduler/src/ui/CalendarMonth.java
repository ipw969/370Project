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
 * A ui class for visualizing a month of a Schedule
 *
 * @author Iain Workman
 */
public class CalendarMonth extends javax.swing.JPanel
        implements BusinessObjectListListener
{

    // Constructor
    /**
     * Creates a new empty CalendarMonth which is not yet visualizing a Schedule
     * focused on the current month
     */
    public CalendarMonth()
    {
        // Setup the list of CalendarDays which will be displayed in the panel
        calendarDays = new ArrayList<>(42);
        for (int iCalendarDay = 0; iCalendarDay < 42; iCalendarDay++)
        {
            calendarDays.add(new CalendarDay());
        }
        // Set the currentMonthStart to this month
        currentMonthStart = new GregorianCalendar();
        currentMonthStart.set(Calendar.DAY_OF_MONTH, 1);
        initComponents();
        refreshCalendarDays();
    }

    // Public Methods
    /**
     * Sets the date to visualize in the CalendarMonth
     *
     * @param year::int ~ The year to display in the CalendarMonth
     * @param month::int ~ The month to display in the CalendarMonth (indexed
     * from 0 because Java etc etc)
     */
    public void setDate(int year, int month)
    {
        currentMonthStart = new GregorianCalendar(year, month, 1);
        currentMonthStart.set(Calendar.HOUR, 0);
        currentMonthStart.set(Calendar.MINUTE, 0);
        currentMonthStart.set(Calendar.SECOND, 0);
        refreshCalendarDays();
    }

    /**
     * Sets the Schedule which is displayed in this CalendarMonth
     *
     * @param schedule::Schedule ~ The Schedule to display
     */
    public void setSchedule(Schedule schedule)
    {
        if (filmingSchedule != null)
        {
            filmingSchedule.removeListener(this);
        }

        filmingSchedule = schedule;
        filmingSchedule.addListener(this);
        refreshCalendarDays();
    }

    /**
     * Method which handles a SceneFilmingDate being added to the underlying
     * Schedule
     *
     * @param itemAdded::BaseBusinessObject ~ The SceneFilmingDate added to the
     * underlying Schedule
     */
    @Override
    public void businessObjectAdded(BaseBusinessObject itemAdded)
    {
        if (itemAdded instanceof SceneFilmingDate)
        {
            SceneFilmingDate addedFilmingDate = (SceneFilmingDate) itemAdded;

            for (CalendarDay currentCalendarDay : calendarDays)
            {
                java.text.SimpleDateFormat sdf
                        = new java.text.SimpleDateFormat("YYYY-MM-dd hh:mm:ss");

                System.out.println(sdf.format(currentCalendarDay.date().getTime()));
                if (addedFilmingDate.getSceneShootingInterval().isOnThisDate(currentCalendarDay.date()))
                {
                    currentCalendarDay.filmingDates().add(addedFilmingDate);
                }
            }
        }
    }

    /**
     * Method to handle a SceneFilmingDate being removed from the underlying
     * Schedule
     *
     * @param itemRemoved::BaseBusinessObject ~ The SceneFilmingDate removed
     * from the Schedule
     */
    @Override
    public void businessObjectRemoved(BaseBusinessObject itemRemoved)
    {
        if (itemRemoved instanceof SceneFilmingDate)
        {
            SceneFilmingDate removedFilmingDate = (SceneFilmingDate) itemRemoved;

            for (CalendarDay currentCalendarDay : calendarDays)
            {
                for (SceneFilmingDate currentDayFilmingDate : currentCalendarDay.filmingDates())
                {
                    if (currentDayFilmingDate.getScene().name().compareTo(removedFilmingDate.getScene().name()) == 0)
                    {
                        currentCalendarDay.filmingDates().remove(currentDayFilmingDate);
                    }
                }
            }
        }
    }

    /**
     * Method to handle the underlying Schedule being cleared of all its
     * SceneFilmingDates
     */
    @Override
    public void listCleared()
    {
        //Shouldn't really happen, but we'll deal with the case anyway
        for (CalendarDay currentCalendarDay : calendarDays)
        {
            currentCalendarDay.filmingDates().clear();
        }
    }

    /**
     * Method to handle one of the SceneFilmingDates contained within the 
     * Schedule notifying that its valid state has changed. Because we're
     * not editing the SceneFilmingDates here this shouldn't happen
     * @param newState::boolean ~ The new valid state of sender
     * @param sender::BaseBusinessObject ~ The BaseBusinessObject whose
     * valid state has been changed
     */
    @Override
    public void validStateAltered(boolean newState, BaseBusinessObject sender)
    {
        // We're not editing the items here, so we don't need to handle this
    }

    /**
     * Method to handle one of the SceneFilmingDates contained within the 
     * Schedule notifying that its changed state has been altered. 
     * @param newState::boolean ~ The changed state of sender
     * @param sender::BaseBusinessObject ~ The BaseBusinessObject whose changed
     * state has altered.
     */
    @Override
    public void changedStateAltered(boolean newState, BaseBusinessObject sender)
    {
        if (sender instanceof SceneFilmingDate)
        {
            SceneFilmingDate alteredFilmingDate = (SceneFilmingDate) sender;

            for (CalendarDay currentCalendarDay : calendarDays)
            {
                if (alteredFilmingDate.getSceneShootingInterval().overlaps(currentCalendarDay.date()))
                {
                    currentCalendarDay.filmingDates().add(alteredFilmingDate);
                }
                for (SceneFilmingDate currentDayFilmingDate : currentCalendarDay.filmingDates())
                {
                    if (currentDayFilmingDate.getScene().name().compareTo(alteredFilmingDate.getScene().name()) == 0)
                    {
                        currentCalendarDay.filmingDates().remove(currentDayFilmingDate);
                    }
                }
            }
        }

    }

    // Private Methods
    /**
     * Refreshes all the CalendarDays which this CalendarMonth contains
     */
    private void refreshCalendarDays()
    {
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
        while (calendarDate.get(Calendar.DAY_OF_WEEK) != calendarDate.getFirstDayOfWeek())
        {
            calendarDate.add(Calendar.DAY_OF_WEEK, -1);
        }

        // Then we need to add each day to the calendarDays list, until we're
        // at the start of the month we want
        while (calendarDate.get(Calendar.MONTH) != currentMonthStart.get(Calendar.MONTH))
        {
            CalendarDay currentCalendarDay = calendarDays.get(calendarDaysAdded);
            currentCalendarDay.setDate(calendarDate);
            currentCalendarDay.setForeground(Color.LIGHT_GRAY);

            if (filmingSchedule != null)
            {
                populateCalendarDay(currentCalendarDay, calendarDate);
            }
            add(currentCalendarDay);
            calendarDaysAdded += 1;
            calendarDate.add(Calendar.DAY_OF_WEEK, 1);
        }

        // Then we need to add each day to the calendarDays list, until we've
        // reached the end of the last day in this calendar month
        while (calendarDate.get(Calendar.MONTH) == currentMonthStart.get(Calendar.MONTH))
        {
            CalendarDay currentCalendarDay = calendarDays.get(calendarDaysAdded);
            currentCalendarDay.setDate(calendarDate);
            currentCalendarDay.setForeground(Color.BLACK);

            if (filmingSchedule != null)
            {
                populateCalendarDay(currentCalendarDay, calendarDate);
            }
            add(currentCalendarDay);
            calendarDaysAdded += 1;
            calendarDate.add(Calendar.DAY_OF_WEEK, 1);
        }

        // Then we need to fill up the rest of the list with the remaining days
        // left in this week
        while (calendarDate.get(Calendar.DAY_OF_WEEK) != calendarDate.getFirstDayOfWeek())
        {
            CalendarDay currentCalendarDay = calendarDays.get(calendarDaysAdded);
            currentCalendarDay.setDate(calendarDate);
            currentCalendarDay.setForeground(Color.LIGHT_GRAY);

            if (filmingSchedule != null)
            {
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

    /**
     * Populates the SceneFilmingDate list of the passed CalendarDay based on
     * @param calendarDay
     * @param date 
     */
    private void populateCalendarDay(CalendarDay calendarDay, GregorianCalendar date)
    {
        BusinessObjectList<SceneFilmingDate> daysSchedule
                = filmingSchedule.getScheduleFor(date);

        for (SceneFilmingDate currentFilmingDate : daysSchedule)
        {
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
