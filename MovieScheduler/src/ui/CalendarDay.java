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
 *
 */
public class CalendarDay extends JPanel{
    
    // Constructor
    public CalendarDay(Calendar date)
    {
        this.date = date;
        this.setBackground(Color.WHITE);
        setBorder(new LineBorder(new Color(240,240,240)));
        initComponents();
    }
    
    // Private methods
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
    Calendar date;
    JScrollPane dataAreaScrollPane;
    JLabel dateLabel;
    
    // Static Methods
    public static void main(String[] args)
    {
        javax.swing.JFrame testFrame = new javax.swing.JFrame();
        testFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(100, 100);
        
        testFrame.add(new CalendarDay(new GregorianCalendar()));
        testFrame.setVisible(true);
    }
}
