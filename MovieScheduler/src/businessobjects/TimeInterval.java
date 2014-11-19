package businessobjects;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class representing a set interval of time
 * @author Iain Workman
 */
public class TimeInterval
        extends BaseBusinessObject
        implements Comparable<TimeInterval>
{
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
    private final SimpleDateFormat isoDateFormatter;
    
    // Constructor

    /**
     * Creates an instance of a TimeInterval from newStart to newEnd
     *
     * @param newStart::GregorianCalendar ~ The start of the time interval
     * @param newEnd::GregorianCalendar ~ The end of the time interval
     */
    public TimeInterval(GregorianCalendar newStart, GregorianCalendar newEnd)
    {

        start = newStart;
        end = newEnd;
        isoDateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        updateError("Start cannot be null", start != null);
        updateError("End cannot be null", end != null);
        updateError("Start cannot be before end", start.compareTo(end) < 0);
    }

    // Public Methods
    /**
     * The start date of the TimeInterval
     *
     * @return The start date/time of the TimeInterval
     */
    public GregorianCalendar getStart()
    {
        return start;
    }

    /**
     * The end date of the TimeInterval
     *
     * @return The end date/time of the TimeInterval
     */
    public GregorianCalendar getEnd()
    {
        return end;
    }

    /**
     * Returns an ISO8601 compliant string for the start datetime
     *
     * @return An ISO8601 compliant string for the start datetime
     * http://en.wikipedia.org/wiki/ISO_8601
     */
    public String getStartIsoDate()
    {
        if (start != null)
        {
            return isoDateFormatter.format(start.getTime());
        } else
        {
            return "";
        }
    }

    /**
     * Returns an ISO8601 compliant string for the end datetime
     *
     * @return An ISO8601 compliant string for the end datetime
     * http://en.wikipedia.org/wiki/ISO_8601
     */
    public String getEndIsoDate()
    {
        if (end != null)
        {
            return isoDateFormatter.format(end.getTime());
        } else
        {
            return "";
        }
    }

    /**
     * Sets the start date/time of the TimeInterval
     *
     * @param newStart::GregorianCalendar ~ The new start date/time of the
     * TimeInterval
     */
    public void setStart(GregorianCalendar newStart)
            throws IllegalArgumentException
    {
        start = newStart;
        updateError("Start cannot be null", start != null);
        if (start != null && end != null)
        {
            updateError("Start cannot be before end", start.compareTo(end) < 0);
        }
        setHasChanged(true);
    }

    /**
     * Sets the end date/time of the TimeInterval
     *
     * @param newEnd::GregorianCalendar ~ The new end date/time of the
     * TimeInterval
     */
    public void setEnd(GregorianCalendar newEnd)
            throws IllegalArgumentException
    {
        end = newEnd;
        updateError("End cannot be null", end != null);
        if (start != null && end != null)
        {
            updateError("Start cannot be before end", start.compareTo(end) < 0);
        }
        setHasChanged(true);
    }

    /**
     * Compares this TimeInterval to the provided other TimeInterval. If this
     * overlaps other then returns 0 If this is strictly earlier than other
     * returns -1 If this is strictly later than other returns 1
     *
     * @param other::TimeInterval ~ The TimeInterval to compare this to
     * @return -1 if this is strictly earlier than other 0 if this overlaps
     * other 1 if this is strictly later than other
     */
    @Override
    public int compareTo(TimeInterval other)
    {
        // this.start is later than other.end
        if (this.start.compareTo(other.end) > 0)
        {
            return 1;
        }

        // this.end is earlier than other.start
        if (this.end.compareTo(other.start) < 0)
        {
            return -1;
        }

        return 0;
    }

    /**
     * Whether or not the provided date lies within this TimeInterval
     *
     * @param date::GregorianCalendar ~ The date to check for overlap with
     * @return true if the provided date lies within this TimeInterval. More
     * explicitly returns true if this.start is less than or equal to date AND
     * this.end is strictly greater than date
     */
    public boolean overlaps(GregorianCalendar date)
    {
        return (start.compareTo(date) <= 0
                && end.compareTo(date) > 0);
    }
    
    /**
     * Whether or not the TimeInverval is on the same date as the provided
     * date
     * @param date::GregorianCalendar ~ The date to check if the TimeInveral
     * is on the same date as
     * @return True if the TimeInterval is on the same date as the provided date
     * false otherwise. More specifically, true if and only if:
     *  this.overlaps(date)
     *  OR
     *  date.day == start.day AND date.month = start.month AND date.year == start.year
     *  OR
     *  date.day == end.day AND date.month == end.month AND date.year == end.year
     *  OR
     *  start < date AND end > date
     */
    public boolean isOnThisDate(GregorianCalendar date)
    {
        
        return
        (
                overlaps(date)
                ||
                (date.get(Calendar.YEAR) == start.get(Calendar.YEAR)
                 &&
                 date.get(Calendar.MONTH) == start.get(Calendar.MONTH)
                 &&
                 date.get(Calendar.DAY_OF_MONTH) == start.get(Calendar.DAY_OF_MONTH))
                ||
                (date.get(Calendar.YEAR) == end.get(Calendar.YEAR)
                 &&
                 date.get(Calendar.MONTH) == end.get(Calendar.MONTH)
                &&
                 date.get(Calendar.DAY_OF_MONTH) == end.get(Calendar.DAY_OF_MONTH)
                )
                ||
                (
                    start.compareTo(date) < 0
                    &&
                    end.compareTo(date) > 0
                )
        );
    }

    /**
     * Returns the start and end date formatted in ISO format as a string
     *
     * @return The start and end date formatted in ISO format as a string
     */
    @Override
    public String toString()
    {
        return getStartIsoDate() + " - " + getEndIsoDate();
    }

    /**
     * Returns the start and end date formatted in ISO format as a string
     *
     * @return The start and end date formatted in ISO format as a string
     */
    @Override
    public String toDescriptiveString()
    {
        return toString();
    }

    /**
     * Returns a deep clone of this TimeInterval
     *
     * @return A deep clone of this TimeInterval
     * @throws CloneNotSupportedException
     */
    @Override
    public BaseBusinessObject clone() throws CloneNotSupportedException
    {
        TimeInterval other;
        try
        {
            other = (TimeInterval) super.clone();

            if (other.start != null)
            {
                start = (GregorianCalendar) other.start.clone();
            }

            if (other.end != null)
            {
                end = (GregorianCalendar) other.end.clone();
            }

            return other;
        } catch (CloneNotSupportedException e)
        {
            throw new RuntimeException();
        }
    }

    /**
     * Merges the passed BusinessObject into this one, if the passed
     * BusinessObject is a TimeInterval type, otherwise throws a
     * RuntimeException()
     *
     * @param mergeObject::BaseBusinessObject ~ The object to copy the
     * information from
     */
    @Override
    public void merge(BaseBusinessObject mergeObject)
    {
        if (!(mergeObject instanceof TimeInterval))
                    throw new RuntimeException("Cannot merge a BusinessObject "
                            + "of non TimeInterval type into a TimeInterval");

        super.merge(mergeObject);
        TimeInterval otherTimeInterval = (TimeInterval) mergeObject;

        setStart(otherTimeInterval.start);
        setEnd(otherTimeInterval.end);

    }

    // Private Methods

}
