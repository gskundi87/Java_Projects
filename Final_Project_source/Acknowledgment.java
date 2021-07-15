/**
 * Message Subclass to store return information to user from reservation request
 */
public class Acknowledgment extends Message
{
    // Private instance variables
    private String name_Ack;
    private Classroom.Size size_Ack;
    private Classroom.Seating seating_Ack;
    private Classroom.Projector projector_Ack;
    private int startTimeHours_Ack;
    private int startTimeMinutes_Ack;
    private int endTimeHours_Ack;
    private int endTimeMinutes_Ack;

    /**
     * Constructor
     * @param name_Ack name of classroom available
     * @param size_Ack classroom size type available
     * @param seating_Ack classroom seating type available
     * @param projector_Ack classroom projector type available
     * @param startTimeHours_Ack 24hr format start hours available
     * @param startTimeMinutes_Ack 24hr format start minutes available
     * @param endTimeHours_Ack 24hr format end hours available
     * @param endTimeMinutes_Ack 24hr format end minutes available
     */
    public Acknowledgment(String name_Ack, Classroom.Size size_Ack,
                          Classroom.Seating seating_Ack, Classroom.Projector projector_Ack,
                          int startTimeHours_Ack, int startTimeMinutes_Ack,
                          int endTimeHours_Ack, int endTimeMinutes_Ack)
    {
        super(MessageType.ACKNOWLEDGMENT);
        this.name_Ack = name_Ack;
        this.size_Ack = size_Ack;
        this.seating_Ack = seating_Ack;
        this.projector_Ack = projector_Ack;
        this.startTimeHours_Ack = startTimeHours_Ack;
        this.startTimeMinutes_Ack = startTimeMinutes_Ack;
        this.endTimeHours_Ack = endTimeHours_Ack;
        this.endTimeMinutes_Ack = endTimeMinutes_Ack;
    }

    /**
     * Copy constructor
     * @param other Acknowledgment object to copy
     */
    public Acknowledgment(Acknowledgment other)
    {
        super(MessageType.ACKNOWLEDGMENT);
        this.name_Ack = other.name_Ack;
        this.size_Ack = other.size_Ack;
        this.seating_Ack = other.seating_Ack;
        this.projector_Ack = other.projector_Ack;
        this.startTimeHours_Ack = other.startTimeHours_Ack;
        this.startTimeMinutes_Ack = other.startTimeMinutes_Ack;
        this.endTimeHours_Ack = other.endTimeHours_Ack;
        this.endTimeMinutes_Ack = other.endTimeMinutes_Ack;
    }

    /**
     * Accessor Method to get classroom name available
     * @return String for classroom name available
     */
    public String getName_Ack()
    {
        return name_Ack;
    }

    /**
     * Accessor Method to print Acknowledgment information
     * @return String with Acknowledgment information
     */
    public String print()
    {
        String str1 = String.format("%02d", startTimeHours_Ack);
        String str2 = String.format("%02d", startTimeMinutes_Ack);
        String str3 = String.format("%02d", endTimeHours_Ack);
        String str4 = String.format("%02d", endTimeMinutes_Ack);

        return "Room: " + name_Ack + " | Size: " + size_Ack.toString() + " | Seating: " + seating_Ack.toString() +
                " | Projector: " + projector_Ack.toString() + " | Start Time: " + str1 + ":" +
                str2 + " | End Time " + str3 + ":" + str4;
    }
}
