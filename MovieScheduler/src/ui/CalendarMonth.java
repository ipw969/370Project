/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 *
 * @author iain
 */
public class CalendarMonth extends JPanel {
    
    // Constructor
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
            calendarDays.add(new CalendarDay(calendarDate));
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
            calendarDays.add(new CalendarDay(calendarDate));
            calendarDate.add(Calendar.DAY_OF_WEEK, 1);
        }
        
        initComponents();
    }
    
    // Private Methods
    private void initComponents()
    {
        for(CalendarDay day : calendarDays)
        {
            add(day);
        }
    }
    
    // Private Member Variables
    ArrayList<CalendarDay> calendarDays;
    // Static Methods
    
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
