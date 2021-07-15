/**
 * Abstract superclass for holding information being passed between user and program
 */
public abstract class Message
{
    /**
     * Enumeration specifying Message type
     */
    public enum MessageType {ACKNOWLEDGMENT, REQUEST}

    // Private instance variables
    private MessageType type;

    /**
     * Constructor
     * @param type Message type
     */
    public Message(MessageType type)
    {
        this.type = type;
    }
}
