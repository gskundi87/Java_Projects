/**
 * Class to hold user information
 */
public class UserGroup
{
    /**
     * Enumeration specifying UserGroup type
     * Contains methods to exchange integer value with enum value
     */
    public enum UserType
    {
        CLUB(1), GUEST_SPEAKER(2), SEMINAR(3),COURSE(4);

        private final int value;

        UserType(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }

        public static UserType fromInt(int x)
        {
            for (UserType type : values())
            {
                if (type.getValue() == x)
                {
                    return type;
                }
            }

            return null;
        }
    }

    // Private instance variables
    private String name;
    private UserType type;
    private int priority;

    /**
     * Constructor
     * @param name name of user group
     * @param priority priority level of user group
     */
    public UserGroup(String name, int priority)
    {
        this.name = name;
        this.type = UserType.fromInt(priority);
        this.priority = priority;
    }

    /**
     * Accessor Method to get UserGroup name
     * @return String UserGroup name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Accessor Method to get user type
     * @return UserType of user
     */
    public UserType getType()
    {
        return type;
    }

    /**
     * Accessor Method to compare two UserGroups
     * @param other UserGroup
     * @return boolean with standard comparison values
     */
    public boolean isEqual(UserGroup other)
    {
        return name.compareTo(other.name) == 0 && priority == other.priority;
    }

    /**
     * Accessor Method to print user group
     * @return String UserGroup
     */
    public String print()
    {
        return String.format("%s; %d", name, priority);
    }
}
