/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import businessobjects.BusinessObjectList;
import businessobjects.SceneFilmingDate;
import businessobjects.BusinessObjectListener;
import businessobjects.BaseBusinessObject;
import businessobjects.TimeInterval;
/**
 * A class representing a Ui element displaying a single month of a calendar
 */
public class CalendarMonth extends JPanel implements BusinessObjectListener{
    
    // Constructor
    /**
     * Creates an instance of a calendar month displaying the given year and 
     * month
     * @param year::int ~ The AD year the calendar is displaying
     * @param month ::int ~ The month the year is displaying
     * NOTE: (0=Jan, 1=Feb, 2=Mar, ..., 10 = Nov, 11 = Dec) because Java
     */
    public CalendarMonth(int year, int month)
    {
        setLayout(new GridLayout(0,7));
        calendarDays = new ArrayList<>();
        // date used just to build the ui elements, date is saved as a calendar
        // for use for the rest of the lifetime of the class as the bottom of
        // the constructor
        GregorianCalendar calendarDate = new GregorianCalendar(year, month, 01);
        
        
        // First we need to iterate back to the first day of the week
        while(calendarDate.get(Calendar.DAY_OF_WEEK) != calendarDate.getFirstDayOfWeek())
            calendarDate.add(Calendar.DAY_OF_WEEK, -1);
        
        // Then we need to add each day to the calendarDays list, until we're
        // at the start of the month we want
        while(calendarDate.get(Calendar.MONTH) != month)
        {
            CalendarDay currentCalendarDay = new CalendarDay(calendarDate);
            currentCalendarDay.setForeground(Color.LIGHT_GRAY);
            calendarDays.add(currentCalendarDay);
            calendarDate.add(Calendar.DAY_OF_WEEK, 1);
        }
        
        // Then we need to add each day to the calendarDays list, until we've
        // reached the end of the last day in this calendar month
        while(calendarDate.get(Calendar.MONTH) == month)
        {
            calendarDays.add(new CalendarDay(calendarDate));
            calendarDate.add(Calendar.DAY_OF_WEEK, 1);
        }
        
        // Then we need to fill up the rest of the list with the remaining days
        // left in this week
        while(calendarDate.get(Calendar.DAY_OF_WEEK) != calendarDate.getFirstDayOfWeek())
        {
            CalendarDay currentCalendarDay = new CalendarDay(calendarDate);
            currentCalendarDay.setForeground(Color.LIGHT_GRAY);
            calendarDays.add(currentCalendarDay);
            calendarDate.add(Calendar.DAY_OF_WEEK, 1);
        }
        startOfMonth = new GregorianCalendar(year, month, 01);
        
        scheduledScenes = new BusinessObjectList<>();

        initComponents();
        

    }
    
    // Public Methods

    /**
     * Adds the provided SceneSchedule to this month
     * @param sceneSchedule::SceneSchedule ~ The SceneSchedule to add
     */
    public void add(SceneFilmingDate sceneSchedule)
    {
        for(CalendarDay currentCalendarDay: calendarDays)
        {
            if(sceneSchedule.sceneShootingInterval().overlaps(
                currentCalendarDay.date()))
                currentCalendarDay.addSceneFilmingDate(sceneSchedule);
        }       
    }
    
    /**
     * Method to react to one of our containing SceneSchedules notifying us
     * that is has changed. If it has changed to be outside this month then
     * we remove it from the list, otherwise we iterate through calendar days
     * and add it to all the ones which it overlaps
     * @param currentState::boolean ~ The current changed state of the 
     * SceneSchedule (unused)
     * @param sender::BaseBusinessObject ~ The sender of the notification
     */
    @Override
    public void changedStateAltered(boolean currentState, 
            BaseBusinessObject sender)
    {
        if(!(sender instanceof SceneFilmingDate))
        {
            return;
        }
        
        SceneFilmingDate sendingSceneSchedule = (SceneFilmingDate)sender;
        
        // Calendar days cope with removing stuff from themselves, so
        // all we need to do here is remove it from our collection if it's
        // changed month, and add it to the correct day(s) otherwise
        
        if(!sceneScheduleIsThisMonth(sendingSceneSchedule))
        {
            scheduledScenes.remove(sendingSceneSchedule);
        }
        else
        {
            // Todo: Iterate over all days and add to each on that it overlaps
            // with.
            for(CalendarDay currentCalendarDay: calendarDays)
            {
                if(sendingSceneSchedule.sceneShootingInterval().overlaps(
                    currentCalendarDay.date()))
                    currentCalendarDay.addSceneFilmingDate(sendingSceneSchedule);
            }
        }
        
    }
    
    /**
     * Method which deals with being notified that one of the contained 
     * SceneSchedules has altered its valid state. As we're not editing the
     * data associated with a scene schedule here, we don't need to do anything
     * @param currentState
     * @param sender 
     */
    @Override
    public void validStateAltered(boolean currentState, 
            BaseBusinessObject sender)
    {
        // We're not editing the SceneSchedules here, so we don't need to do
        // anything
    }
    
    // Private Methods
    /**
     * Initializes and positions all the components of the calendar month
     */
    private void initComponents()
    {
        for(CalendarDay day : calendarDays)
        {
            add(day);
        }
        
        scheduledScenes.addListener(this);
    }
    
    /**
     * Function to check if the provided SceneSchedule overlaps this month
     * @param sceneSchedule::SceneSchedule ~ The SceneSchedule to check if it
     * overlaps this month
     * @return True if SceneSchedule overlaps this month, false otherwise
     */
    private boolean sceneScheduleIsThisMonth(SceneFilmingDate sceneSchedule)
    {
        GregorianCalendar endOfMonth = (GregorianCalendar)startOfMonth.clone();
        endOfMonth.add(Calendar.MONTH, 1);
        endOfMonth.add(Calendar.DAY_OF_MONTH, -1);
        
        TimeInterval wholeMonth = new TimeInterval(startOfMonth, endOfMonth);
        
        return sceneSchedule.sceneShootingInterval().compareTo(wholeMonth) == 0;
    }
    
    // Private Member Variables
    /**
     * A list of all the days that will be displayed
     */
    private ArrayList<CalendarDay> calendarDays;
    
    /**
     * A List of all the scheduled scene shooting dates
     */
    private BusinessObjectList<SceneFilmingDate> scheduledScenes;
    
    /**
     * The first day of the month
     */
    private GregorianCalendar startOfMonth;


    // Static Methods
    
    /**
     * Method which just allows us to see the design displayed on a JFrame
     * @param args::String[] ~ Run arguments (ignored)
     */
    public static void main(String[] args)
    {
                javax.swing.JFrame testFrame = new javax.swing.JFrame();
        testFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(640, 480);
        CalendarMonth testCalendarMonth = new CalendarMonth(2014, 9);
        
        // Add some SceneSchedules
        SceneFilmingDate testSchedule1 = new SceneFilmingDate();
        testSchedule1.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014, 9, 1), new GregorianCalendar(2014, 9, 2)));
        
        SceneFilmingDate testSchedule2 = new SceneFilmingDate();
        testSchedule2.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014, 9, 1), new GregorianCalendar(2014, 9, 2)));
        
        SceneFilmingDate testSchedule3 = new SceneFilmingDate();
        testSchedule3.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014, 9, 1), new GregorianCalendar(2014, 9, 3)));
        
        SceneFilmingDate testSchedule4 = new SceneFilmingDate();
        testSchedule4.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014, 9, 1), new GregorianCalendar(2014, 9, 2)));
        
        SceneFilmingDate testSchedule5 = new SceneFilmingDate();
        testSchedule5.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014, 9, 1), new GregorianCalendar(2014, 9, 2)));
        
        SceneFilmingDate testSchedule6 = new SceneFilmingDate();
        testSchedule6.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014, 9, 1), new GregorianCalendar(2014, 9, 2)));
        
        SceneFilmingDate testSchedule7 = new SceneFilmingDate();
        testSchedule7.setSceneShootingInterval(new TimeInterval(new GregorianCalendar(2014, 9, 1), new GregorianCalendar(2014, 9, 2)));
        
        
        testCalendarMonth.add(testSchedule1);
        testCalendarMonth.add(testSchedule2);
        testCalendarMonth.add(testSchedule3);
        testCalendarMonth.add(testSchedule4);
        testCalendarMonth.add(testSchedule5);
        testCalendarMonth.add(testSchedule6);
        testCalendarMonth.add(testSchedule7);

        testFrame.add(testCalendarMonth);
        testFrame.setVisible(true);
    }
}
