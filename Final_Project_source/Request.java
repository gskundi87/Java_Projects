/**
 * Class to store reservation request information from user
 * Extends Message superclass
 */
public class Request extends Message
{
    // Private instance variables
    private String name_Request;
    private Classroom.Size size_Request;
    private Classroom.Seating seating_Request;
    private Classroom.Projector projector_Request;
    private Reservation reservation_Request;

    /**
     * Constructor
     * @param name_Request name of classroom requested
     * @param size_Request classroom size type requested
     * @param seating_Request classroom seating type requested
     * @param projector_Request classroom projector type requested
     * @param reservedFor UserGroup making request
     * @param startTimeHours 24hr format start hours requested
     * @param startTimeMinutes 24hr format start minutes requested
     * @param endTimeHours 24hr format end hours requested
     * @param endTimeMinutes 24hr format end minutes requested
     * @param reservationType reservation type
     */
    public Request(String name_Request, Classroom.Size size_Request,
                   Classroom.Seating seating_Request, Classroom.Projector projector_Request,
                   UserGroup reservedFor, int startTimeHours, int startTimeMinutes,
                   int endTimeHours, int endTimeMinutes, Reservation.ReservationType reservationType)
    {
        super(MessageType.REQUEST);
        this.name_Request = name_Request;
        this.size_Request = size_Request;
        this.seating_Request = seating_Request;
        this.projector_Request = projector_Request;
        reservation_Request = new Reservation(reservedFor, startTimeHours, startTimeMinutes,
                endTimeHours, endTimeMinutes, reservationType);
    }

    /**
     * Accessor Method to get classroom name requested
     * @return String of classroom name requested
     */
    public String getName_Request()
    {
        return name_Request;
    }

    /**
     * Accessor Method to get classroom size type requested
     * @return Classroom.Size enum type requested
     */
    public Classroom.Size getSize_Request()
    {
        return size_Request;
    }

    /**
     * Accessor Method to get classroom seating type requested
     * @return Classroom.Seating enum type requested
     */
    public Classroom.Seating getSeating_Request()
    {
        return seating_Request;
    }

    /**
     * Accessor Method to get classroom projector type requested
     * @return Classroom.Projector enum type requested
     */
    public Classroom.Projector getProjector_Request()
    {
        return projector_Request;
    }

    /**
     * Accessor Method to get reservation object requested
     * @return Reservation object requested
     */
    public Reservation getReservation_Request()
    {
        return reservation_Request;
    }

    /**
     * Accessor Method to print request
     * @return String
     */
    public String print()
    {
        return String.format("%s %s %s %s %02d %02d %02d %02d", name_Request, size_Request.toString(),
                seating_Request.toString(), projector_Request.toString(), reservation_Request.getStartTimeHours(),
                reservation_Request.getStartTimeMinutes(), reservation_Request.getEndTimeHours(),
                reservation_Request.getEndTimeMinutes());
    }
}
