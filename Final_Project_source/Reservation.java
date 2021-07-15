/**
 * Class to hold reservation information
 * Implements Comparable interface
 */
public class Reservation implements Comparable
{
    /**
     * Enumeration specifying Reservation type
     */
    public enum ReservationType {ONCE, DAILY, WEEKLY, MONTHLY}

    // Private instance variables
    private UserGroup reservedFor;
    private int startTimeHours;
    private int startTimeMinutes;
    private int endTimeHours;
    private int endTimeMinutes;
    private ReservationType reservationType;

    /**
     * Constructor
     * @param reservedFor UserGroup holding reservation
     * @param startTimeHours 24hr format start hours reserved
     * @param startTimeMinutes 24hr format start minutes reserved
     * @param endTimeHours 24hr format end hours reserved
     * @param endTimeMinutes 24hr format end minutes reserved
     * @param reservationType reservation type reserved
     */
    public Reservation(UserGroup reservedFor, int startTimeHours, int startTimeMinutes,
                       int endTimeHours, int endTimeMinutes, ReservationType reservationType)
    {
        this.reservedFor = reservedFor;
        this.startTimeHours = startTimeHours;
        this.startTimeMinutes = startTimeMinutes;
        this.endTimeHours = endTimeHours;
        this.endTimeMinutes = endTimeMinutes;
        this.reservationType = reservationType;
    }

    /**
     * Accessor Method to get user group holding reservation
     * @return UserGroup holding reservation
     */
    public UserGroup getReservedFor()
    {
        return reservedFor;
    }

    /**
     * Accessor method to get 24hr format start hours reserved
     * @return int 24hr format start hours reserved
     */
    public int getStartTimeHours()
    {
        return startTimeHours;
    }

    /**
     * Accessor method to get 24hr format start minutes reserved
     * @return int 24hr format start minutes reserved
     */
    public int getStartTimeMinutes()
    {
        return startTimeMinutes;
    }

    /**
     * Accessor method to get 24hr format end hours reserved
     * @return int 24hr format end hours reserved
     */
    public int getEndTimeHours()
    {
        return endTimeHours;
    }

    /**
     * Accessor method to get 24hr format end minutes reserved
     * @return int 24hr format end minutes reserved
     */
    public int getEndTimeMinutes()
    {
        return endTimeMinutes;
    }

    /**
     * Accessor Method to get reservation type
     * @return ReservationType
     */
    public ReservationType getReservationType()
    {
        return reservationType;
    }

    /**
     * Accessor Method to get total start time (for comparison)
     * @return int total start time
     */
    public int getStartTime()
    {
        return startTimeHours * 100 + startTimeMinutes;
    }

    /**
     * Accessor Method to get total end time (for comparison)
     * @return int total end time
     */
    public int getEndTime()
    {
        return endTimeHours * 100 + endTimeMinutes;
    }

    /**
     * Accessor Method to compare to Reservations objects
     * If any time overlap is found between Reservations objects then objects are equal
     * @param other Reservation object to determine equality
     * @return boolean true if equal, false otherwise
     */
    public boolean isEqual(Reservation other)
    {
        return (getStartTime() >= other.getStartTime() && getStartTime() < other.getEndTime()) ||
                (getEndTime() > other.getStartTime() && getEndTime() <= other.getEndTime());
    }

    /**
     * Override method for Comparable interface
     * @param o Reservation object to compare to
     * @return int for standard comparison algorithm
     */
    @Override
    public int compareTo(Object o)
    {
        Reservation other = (Reservation) o;

        return getStartTime() - other.getStartTime();
    }
}
