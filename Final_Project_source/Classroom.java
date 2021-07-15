import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to simulate a classroom and store reservations for that classroom
 */
public class Classroom
{
    /**
     * Enumeration specifying Classroom size
     */
    public enum Size
    {
        ANY, SMALL, MEDIUM, LARGE;
    }

    /**
     * Enumeration specifying Classroom seating
     */
    public enum Seating
    {
        ANY, COMFORT, NON_COMFORT;
    }

    /**
     * Enumeration specifying Classroom projector
     */
    public enum Projector
    {
        ANY, NO_PROJECTOR, PROJECTOR;
    }

    // Private instance variables
    private String name;
    private Size size;
    private Seating seating;
    private Projector projector;
    private ArrayList<Reservation> reservations;

    /**
     * Constructor
     * @param name Classroom name
     * @param size Classroom Size type
     * @param seating Classroom Seating type
     * @param projector Classroom Projector type
     */
    public Classroom(String name, Size size, Seating seating, Projector projector)
    {
        this.name = name;
        this.size = size;
        this.seating = seating;
        this.projector = projector;
        reservations = new ArrayList<Reservation>();
    }

    /**
     * Accessor Method to get name of Classroom
     * @return String name of classroom
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to receive a request for a reservation and relate availability
     * @param request reservation request
     * @return Acknowledgment relating availability for reservation
     */
    public Acknowledgment receiveRequest(Request request)
    {
        if ((request.getName_Request().compareTo("ANY") != 0 && request.getName_Request().compareTo(name) != 0) ||
                (request.getSize_Request() != Size.ANY && request.getSize_Request() != size) ||
                (request.getSeating_Request() != Seating.ANY && request.getSeating_Request() != seating) ||
                (request.getProjector_Request() != Projector.ANY && request.getProjector_Request() != projector))
        {
            return null;
        }

        for (int i = 0; i < reservations.size(); i++)
        {
            if (request.getReservation_Request().isEqual(reservations.get(i)))
            {
                return null;
            }
        }

        return new Acknowledgment(name, size, seating, projector,
                request.getReservation_Request().getStartTimeHours(),
                request.getReservation_Request().getStartTimeMinutes(),
                request.getReservation_Request().getEndTimeHours(),
                request.getReservation_Request().getEndTimeMinutes());
    }

    /**
     * Mutator Method to accept reservation request
     * @param confirmation request to be reserved
     */
    public void processConfirmation(Request confirmation)
    {
        reservations.add(confirmation.getReservation_Request());
        Collections.sort(reservations);
    }

    /**
     * Accessor Method to print room reservations
     * @return String containing information for each reservation
     */
    public String print()
    {
        String str1 = "";

        for (int i = 0; i < reservations.size(); i++)
        {
            str1 = str1 + String.format("%s %s %s %s %02d:%02d %02d:%02d%n", name, reservations.get(i).getReservedFor().getName(),
                    reservations.get(i).getReservedFor().getType().toString(), reservations.get(i).getReservationType().toString(),
                    reservations.get(i).getStartTimeHours(), reservations.get(i).getStartTimeMinutes(),
                    reservations.get(i).getEndTimeHours(), reservations.get(i).getEndTimeMinutes());
        }

        return str1;
    }
}
