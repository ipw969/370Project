/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.JPanel;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import businessobjects.SceneSchedule;
import businessobjects.BusinessObjectList;
import businessobjects.BusinessObjectListener;
import businessobjects.BaseBusinessObject;
import java.awt.List;
/**
 * A class representing a Ui element for displaying a single day of a calendar
 */
public class CalendarDay extends JPanel implements BusinessObjectListener{
    
    // Constructor
    /**
     * Creates an instance of a calendar day with the provided calendar storing
     * the date
     * @param date::GregorianCalendar ~ The date of the day to display 
     */
    public CalendarDay(GregorianCalendar date)
    {
        filmingSchedule = new BusinessObjectList<>();
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
    
    /**
     * Returns the date this Calendar day represents
     * @return The date this Calendar day represents 
     */
    public GregorianCalendar date()
    {
        return date;
    }
     
    // Public methods
    
    public void addSceneSchedule(SceneSchedule sceneSchedule)
    {
        if(filmingSchedule.contains(sceneSchedule))
            return;
        
        filmingSchedule.add(sceneSchedule);
        updateList();
    }
    
    public void removeSceneSchedule(SceneSchedule sceneSchedule)
    {
        if(filmingSchedule.remove(sceneSchedule))
        {
            updateList();
        }
    }
    
    @Override
    public void changedStateAltered(boolean currentState, 
                                    BaseBusinessObject sender)
    {
        if(sender instanceof SceneSchedule)
        {
            SceneSchedule sendingSceneSchedule = (SceneSchedule)sender;
            if(!sendingSceneSchedule.sceneShootingInterval().overlaps(date))
            {
                filmingSchedule.remove(sendingSceneSchedule);
                updateList();
            }
        }
    }
    
    @Override
    public void validStateAltered(boolean currentState,
                                  BaseBusinessObject sender)
    {
        // We're not editing the object, so it shouldn't ever be invalid.
    }
    // Private Methods
    
    private void updateList()
    {
        if(filmingSchedule == null)
            return;
        
        sceneList.setBackground(Color.WHITE);
        
        sceneList.removeAll();
        
        for (SceneSchedule currentScheduledScene : filmingSchedule)
        {
            sceneList.add(currentScheduledScene.toString());
        }
    }
    
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
        
        // init sceneList
        sceneList = new List();
        dataAreaScrollPane.setViewportView(sceneList);
        filmingSchedule.addListener(this);
    }
    
    // Private Member Variables
    /**
     * The date which this CalendarDay displays
     */
    private GregorianCalendar date;
    /**
     * Scroll are in which the data will sit
     */
    private JScrollPane dataAreaScrollPane;
    /**
     * A List UI element which displays the scenes for this day
     */
    private List sceneList;
    /**
     * A label which displays the date in a human readable form
     */
    private JLabel dateLabel;
    /**
     * The entire schedule
     */
    private BusinessObjectList<SceneSchedule> filmingSchedule;
    
    // Static Methods
    /** Method which just allows us to see the design displayed on a JFrame
    * @param args::String[] ~ Run arguments (ignored)
    */
    public static void main(String[] args)
    {
        javax.swing.JFrame testFrame = new javax.swing.JFrame();
        testFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(100, 100);
        CalendarDay testCalendarDay = new CalendarDay(new GregorianCalendar());
        
        testCalendarDay.sceneList.add("TEST");
        testFrame.add(testCalendarDay);
        testFrame.setVisible(true);
    }
}
