import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class to simulate building with classrooms to reserve
 */
public class BuildingScheduler
{
    // Private instance variables
    private ArrayList<Classroom> rooms;
    private ArrayList<Acknowledgment> hits;
    private ArrayList<UserGroup> groups;
    private ArrayList<Request> requests;
    private ArrayList<Integer> confirmations;
    private Request currentRequest;
    private UserGroup currentUser;
    private Reservation.ReservationType currentType;

    /**
     * Constructor
     */
    public BuildingScheduler()
    {
        rooms = new ArrayList<Classroom>();
        hits = new ArrayList<Acknowledgment>();
        groups = new ArrayList<UserGroup>();
        requests = new ArrayList<Request>();
        confirmations = new ArrayList<Integer>();
        currentRequest = null;
        currentUser = null;
    }

    /**
     * Accessor Method to get current user
     * @return UserGroup current user
     */
    public UserGroup getCurrentUser()
    {
        return currentUser;
    }

    /**
     * Accessor Method to get current reservation type
     * @return Reservation.ReservationType current reservation type
     */
    public Reservation.ReservationType getCurrentType()
    {
        return currentType;
    }

    /**
     * Mutator Method to add a classroom
     * @param name classroom name
     * @param size classroom size type
     * @param seating classroom seating type
     * @param projector classroom projector type
     */
    public void addClassroom(String name, String size, String seating, String projector)
    {
        rooms.add(new Classroom(name, Classroom.Size.valueOf(size), Classroom.Seating.valueOf(seating),
                Classroom.Projector.valueOf(projector)));
    }

    /**
     * Mutator Method to set current user
     * @param name UserGroup name
     * @param priority UserGroup priority
     */
    public void setCurrentUser(String name, int priority)
    {
        currentUser = new UserGroup(name, priority);

        switch (currentUser.getType())
        {
            case CLUB:
                currentType = Reservation.ReservationType.WEEKLY;
                break;

            case GUEST_SPEAKER:
                currentType = Reservation.ReservationType.ONCE;
                break;

            case SEMINAR:
                currentType = Reservation.ReservationType.MONTHLY;
                break;

            case COURSE:
                currentType = Reservation.ReservationType.DAILY;
                break;
        }
    }

    /**
     * Mutator Method to set current request
     * @param name classroom name requested
     * @param size classroom size type requested
     * @param seating classroom seating type requested
     * @param projector classroom projector requested
     * @param reservedFor UserGroup requesting
     * @param startTimeHours 24hr format start hours requested
     * @param startTimeMinutes 24hr format start minutes requested
     * @param endTimeHours 24hr format end hours requested
     * @param endTimeMinutes 24hr format end minutes requested
     * @param currentType reservation type requested
     */
    public void setCurrentRequest(String name, Classroom.Size size, Classroom.Seating seating,
                                  Classroom.Projector projector, UserGroup reservedFor, int startTimeHours,
                                  int startTimeMinutes, int endTimeHours, int endTimeMinutes,
                                  Reservation.ReservationType currentType)
    {
        currentRequest = new Request(name, size, seating, projector, reservedFor, startTimeHours,
                startTimeMinutes, endTimeHours, endTimeMinutes, currentType);
    }

    /**
     * Mutator Method to send request to all classrooms and to receive Acknowledgments from classrooms
     * If Acknowledgement is not null, then there is an availability and Acknowledgment is saved
     */
    public void sendCurrentRequest()
    {
        Acknowledgment tempAck;

        for (int i = 0; i < rooms.size(); i++)
        {
            tempAck = rooms.get(i).receiveRequest(currentRequest);

            if (tempAck != null)
            {
                hits.add(tempAck);
            }
        }
    }

    /**
     * Mutator Method to add confirmed reservation request and send back confirmation to requested classroom
     * Method also returns log information for file output
     * @param index of Acknowledgment confirmed
     * @param outFile to write out data
     * @throws FileNotFoundException
     */
    public void sendConfirmation(int index, PrintWriter outFile) throws FileNotFoundException
    {
        confirmations.add(index);
        requests.add(currentRequest);

        // Determine whether to add currentUser
        // Will add only if currentUser is a new UserGroup
        boolean flag = true;

        for (int i = 0; i < groups.size(); i++)
        {
            if (currentUser.isEqual(groups.get(i)))
            {
                flag = false;
                break;
            }
        }

        if (flag)
        {
            groups.add(currentUser);
        }

        // Send confirmation to classrooms
        for (int i = 0; i < rooms.size(); i++)
        {
            if (hits.get(index).getName_Ack().compareTo(rooms.get(i).getName()) == 0)
            {
                rooms.get(i).processConfirmation(currentRequest);
            }
        }

        // Update log file
        for (int i = 0; i < confirmations.size(); i++)
        {
            outFile.println(groups.get(i).print());
            outFile.println(requests.get(i).print());
            outFile.println(confirmations.get(i));
        }

        // Clear current state
        clear();
    }

    /**
     * Accessor Method to get number of rooms
     * @return int number of rooms
     */
    public int getRoomsSize()
    {
        return rooms.size();
    }

    /**
     * Accessor Method to get Classroom
     * @param index of Classroom
     * @return Classroom
     */
    public Classroom getRoom(int index)
    {
        if (index < rooms.size())
        {
            return rooms.get(index);
        }

        return null;
    }

    /**
     * Accessor Method to get size of hits array list (Acknowledgements)
     * @return int size of hits
     */
    public int getHitsSize()
    {
        return hits.size();
    }

    /**
     * Accessor Method to get hit
     * @param index of hit
     * @return Acknowledgment
     */
    public Acknowledgment getHit(int index)
    {
        if (index < hits.size())
        {
            return hits.get(index);
        }

        return null;
    }

    /**
     * Mutator Method to reset BuildingScheduler for next reservation request
     */
    public void clear()
    {
        hits.clear();
        currentRequest = null;
        currentUser = null;
        currentType = null;
    }
}
