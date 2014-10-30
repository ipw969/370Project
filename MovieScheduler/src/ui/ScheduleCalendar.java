/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.TreeMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import businessobjects.Schedule;
import java.awt.BorderLayout;
import java.util.Calendar;
import businessobjects.BusinessObjectListener;
import businessobjects.BaseBusinessObject;
/**
 * Class representing a ui element displaying a calendar version of the Schedule
 */
public class ScheduleCalendar extends JPanel implements BusinessObjectListener{
    // Constructor
    public ScheduleCalendar(Schedule newSchedule)
    {
        if(newSchedule == null)
            throw new RuntimeException("Cannot initialize a Calendar with a "
                    + "null Schedule");
        
        schedule = newSchedule;
        currentFocusedDate = new GregorianCalendar();
        dateFormatter = new SimpleDateFormat("MMMM YYYY");
        
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        backAMonthButton = new JButton();
        backAMonthButton.setText("<");
        topPanel.add(backAMonthButton);
        
        currentMonthLabel = new JLabel();
        currentMonthLabel.setText(dateFormatter.format(
                currentFocusedDate.getTime()));
        topPanel.add(currentMonthLabel);
        
        forwardAMonthButton = new JButton();
        forwardAMonthButton.setText(">");
        topPanel.add(forwardAMonthButton);
        
        calendarMonths = new TreeMap<>();
        CalendarMonth initialCalendarMonth = new CalendarMonth(
            currentFocusedDate.get(Calendar.YEAR),
            currentFocusedDate.get(Calendar.MONTH));
        
        
        calendarMonths.put(dateFormatter.format(currentFocusedDate.getTime())
                            , initialCalendarMonth);
        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(initialCalendarMonth, BorderLayout.CENTER);

        
        schedule.addListener(this);
    }
    
    // Public Methods
    @Override
    public void changedStateAltered(boolean state, BaseBusinessObject sender)
    {
        
    }
    
    @Override
    public void validStateAltered(boolean state, BaseBusinessObject sender)
    {
        
    }
    
    // Private Methods
    private CalendarMonth calendarMonthAt(GregorianCalendar date)
    {
        String formattedDate = dateFormatter.format(date.getTime());
        
        if(calendarMonths.containsKey(formattedDate))
            return calendarMonths.get(formattedDate);
        
        CalendarMonth newCalendarMonth = new CalendarMonth(
            date.get(Calendar.YEAR), date.get(Calendar.MONTH));
        
        // iterate through schedule dates, add to calendar month.
        return newCalendarMonth;
    }
    
    // Private Member Variables
    private Schedule schedule;
    private GregorianCalendar currentFocusedDate;
    private JPanel topPanel;
    private JButton backAMonthButton;
    private JButton forwardAMonthButton;
    private JLabel currentMonthLabel;
    private SimpleDateFormat dateFormatter;
    private TreeMap<String, CalendarMonth> calendarMonths;
    // Static Methods
    public static void main(String[] args)
    {
        javax.swing.JFrame testFrame = new javax.swing.JFrame();
        testFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(800, 600);
        ScheduleCalendar testCalendar = new ScheduleCalendar(new Schedule());
        
        testFrame.add(testCalendar);
        testFrame.setVisible(true);
    }
}
