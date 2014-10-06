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
/**
 * A class representing a Ui element displaying a single month of a calendar
 */
public class CalendarMonth extends JPanel {
    
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
        
        initComponents();
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
    }
    
    // Private Member Variables
    /**
     * A list of all the days that will be displayed
     */
    ArrayList<CalendarDay> calendarDays;
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
        
        testFrame.add(testCalendarMonth);
        testFrame.setVisible(true);
    }
}
