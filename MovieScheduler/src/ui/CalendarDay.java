/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.JPanel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
/**
 * A class representing a Ui element for displaying a single day of a calendar
 */
public class CalendarDay extends JPanel{
    
    // Constructor
    /**
     * Creates an instance of a calendar day with the provided calendar storing
     * the date
     * @param date::Calendar ~ The date of the day to display 
     */
    public CalendarDay(Calendar date)
    {
        this.date = (GregorianCalendar)date.clone();
        this.setBackground(Color.WHITE);
        setBorder(new LineBorder(new Color(240,240,240)));
        initComponents();
    }
    
    // Public methods
    /**
     * Sets the foreground color of the CalendarDay. This sets the color of the
     * text label, and then calls the super version.
     * @param color 
     */
    @Override
    public void setForeground(Color color)
    {
        if(dateLabel != null)
            dateLabel.setForeground(color);
        super.setForeground(color);
    }
    
    // Private methods
    /**
     * Initializes and positions the components of the CalendarDay
     */
    private void initComponents()
    {
        setLayout(new BorderLayout());
        
        // init dateLabel
        dateLabel = new JLabel();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, d");
        dateLabel.setText(dateFormatter.format(date.getTime()));
        add(dateLabel, BorderLayout.PAGE_START);
        
        // init SctollPane
        dataAreaScrollPane = new JScrollPane();
        dataAreaScrollPane.getViewport().setBackground(Color.WHITE);
        dataAreaScrollPane.setBorder(null);
        add(dataAreaScrollPane, BorderLayout.CENTER);
        
    }
    
    // Private Member Variables
    /**
     * The date which this CalendarDay displays
     */
    Calendar date;
    /**
     * Scroll are in which the data will sit
     */
    JScrollPane dataAreaScrollPane;
    /**
     * A label which displays the date in a human readable form
     */
    JLabel dateLabel;
    
    // Static Methods
    /** Method which just allows us to see the design displayed on a JFrame
    * @param args::String[] ~ Run arguments (ignored)
    */
    public static void main(String[] args)
    {
        javax.swing.JFrame testFrame = new javax.swing.JFrame();
        testFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(100, 100);
        
        testFrame.add(new CalendarDay(new GregorianCalendar()));
        testFrame.setVisible(true);
    }
}
