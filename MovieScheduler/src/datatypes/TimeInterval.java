/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatypes;

import java.util.GregorianCalendar;
/**
 * Class representing a set interval of time
 */
public class TimeInterval {
    // Constructor
    /**
     * Creates an instance of a TimeInterval from newStart to newEnd
     * @param newStart::GregorianCalendar ~ The start of the time interval
     * @param newEnd::GregorianCalendar ~ The end of the time interval
     * @throws IllegalArgumentException
     */
    public TimeInterval(GregorianCalendar newStart, GregorianCalendar newEnd)
            throws IllegalArgumentException
    {
        if(newStart == null)
            throw new IllegalArgumentException("TimeInterval::TimeInterval ~ "
                    + "start cannot be null");
        
        if (newEnd == null)
            throw new IllegalArgumentException("TimeInterval::TimeInterval ~ "
                    + "end cannot be null");
        
        if(newStart.compareTo(newEnd) > 0)
            throw new IllegalArgumentException("TimeInterval::TimeInterval ~ "
                    + "start cannot be before end");
        
        start = newStart;
        end = newEnd;
    }
    
    // Public Methods
    public GregorianCalendar start()
    {
        return start;
    }
    
    public GregorianCalendar end()
    {
        return end;
    }
    
    public void setStart(GregorianCalendar newStart) 
            throws IllegalArgumentException
    {
        if(newStart == null)
            throw new IllegalArgumentException("TimeInterval::setStart ~ "
                    + "start cannot be null");
        
        start = newStart;
    }
    
    public void setEnd(GregorianCalendar newEnd)
            throws IllegalArgumentException
    {
        if(newEnd == null)
            throw new IllegalArgumentException("TimeInterval::setEnd ~ "
                    + "end cannot be null");
        
        if(newEnd.compareTo(start) < 0)
            throw new IllegalArgumentException("TimeInterval::setEnd ~ "
                    + "end cannot be before start");
        
        end = newEnd;
    }
    
    // Private Methods
    /**
     * The start of the interval
     */
    private GregorianCalendar start;
    
    /**
     * The end of the interval
     */
    private GregorianCalendar end;
    
    // Private Member Variables
    
}
