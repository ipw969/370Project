package businessobjects;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
/**
 * Class representing a set interval of time
 */
public class TimeInterval 
            extends BaseBusinessObject
            implements Comparable<TimeInterval>{
    // Constructor
    /**
     * Creates an instance of a TimeInterval from newStart to newEnd
     * @param newStart::GregorianCalendar ~ The start of the time interval
     * @param newEnd::GregorianCalendar ~ The end of the time interval
     */
    public TimeInterval(GregorianCalendar newStart, GregorianCalendar newEnd)
    {
      
        start = newStart;
        end = newEnd;
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        updateError("Start cannot be null", start != null);
        updateError("End cannot be null", end != null);
        updateError("Start cannot be before end", start.compareTo(end) < 0);
    }
    
    // Public Methods
    /**
     * The start date of the TimeInterval
     * @return The start date/time of the TimeInterval
     */
    public GregorianCalendar start()
    {
        return start;
    }
    
    /**
     * The end date of the TimeInterval
     * @return The end date/time of the TimeInterval
     */
    public GregorianCalendar end()
    {
        return end;
    }
    
    /**
     * Returns an ISO8601 compliant string for the start datetime
     * @return An ISO8601 compliant string for the start datetime
     * http://en.wikipedia.org/wiki/ISO_8601
     */
    public String startISODate()
    {
        return dateFormatter.format(start.getTime());
    }
    
    
    /**
     * Returns an ISO8601 compliant string for the end datetime
     * @return An ISO8601 compliant string for the end datetime
     * http://en.wikipedia.org/wiki/ISO_8601
     */
    public String endISODate()
    {
        return dateFormatter.format(end.getTime());
    }
    
    /**
     * Sets the start date/time of the TimeInterval
     * @param newStart::GregorianCalendar ~ The new start date/time of the 
     * TimeInterval 
     */
    public void setStart(GregorianCalendar newStart) 
            throws IllegalArgumentException
    {
        start = newStart;
        updateError("Start cannot be null", start!= null);
        if(start != null && end != null)
            updateError("Start cannot be before end", start.compareTo(end) < 0);
        setHasChanged(true);
    }
    
    /**
     * Sets the end date/time of the TimeInterval
     * @param newEnd::GregorianCalendar ~ The new end date/time of the 
     * TimeInterval
     */
    public void setEnd(GregorianCalendar newEnd)
            throws IllegalArgumentException
    {
        end = newEnd;
        updateError("End cannot be null", end != null);
        if(start != null && end != null)
            updateError("Start cannot be before end", start.compareTo(end) < 0);
        setHasChanged(true);
    }
    
    /**
     * Compares this TimeInterval to the provided other TimeInterval.
     * If this overlaps other then returns 0
     * If this is strictly earlier than other returns -1
     * If this is strictly later than other returns 1
     * @param other::TimeInterval ~ The TimeInterval to compare this to
     * @return 
     * -1 if this is strictly earlier than other
     * 0 if this overlaps other
     * 1 if this is strictly later than other
     */
    @Override
    public int compareTo(TimeInterval other)
    {
        // this.start is later than other.end
        if(this.start.compareTo(other.end) > 0)
            return 1;
        
        // this.end is earlier than other.start
        if(this.end.compareTo(other.start) < 0)
            return -1;
        
        return 0;
    }
    
    /**
     * Whether or not the provided date lies within this TimeInterval
     * @param date::GregorianCalendar ~ The date to check for overlap with
     * @return true if the provided date lies within this TimeInterval. More
     * explicitly returns true if this.start is less than or equal to date AND
     * this.end is strictly greater than date
     */
    public boolean overlaps(GregorianCalendar date)
    {
        return
        (
                start.compareTo(date) <=0 
                &&
                end.compareTo(date) > 0
        );
    }
    
    // Private Methods
    
    // Private Member Variables
    /**
     * The start of the interval
     */
    private GregorianCalendar start;
    
    /**
     * The end of the interval
     */
    private GregorianCalendar end;
    
    /**
     * Formats the dates to be output in ISO standard form
     * http://en.wikipedia.org/wiki/ISO_8601
     */
    private SimpleDateFormat dateFormatter;

    @Override
    public String toString()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toDescriptiveString()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseBusinessObject clone()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void merge(BaseBusinessObject mergeObject)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
