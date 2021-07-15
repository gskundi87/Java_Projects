import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Class to create JFrame object for Frame1
 * Frame1 is for user group input
 */
public class Frame1 extends JFrame
{
    // Private instance variables
    private BuildingScheduler myBuilding;
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 300;
    private JLabel userLabel;
    private JTextField userField;
    private JButton button;
    private JRadioButton clubButton;
    private JRadioButton guestSpeakerButton;
    private JRadioButton seminarButton;
    private JRadioButton courseButton;
    private ActionListener listener;

    /**
     * Constructor
     * @param myBuilding BuildingScheduler object
     */
    public Frame1(BuildingScheduler myBuilding)
    {
        this.myBuilding = myBuilding;
        listener = new ChoiceListener();
        createControlPanel();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    /**
     * Class for event handling
     * Processes user input into Frame1 and constructs Frame2
     */
    class ChoiceListener implements ActionListener
    {
        /**
         * Override method for ActionListener interface
         * @param event ActionEvent object
         */
        public void actionPerformed(ActionEvent event)
        {
            // Process radio button selection
            if (clubButton.isSelected())
            {
                myBuilding.setCurrentUser(userField.getText(), 1);
            }
            else if (guestSpeakerButton.isSelected())
            {
                myBuilding.setCurrentUser(userField.getText(), 2);
            }
            else if (seminarButton.isSelected())
            {
                myBuilding.setCurrentUser(userField.getText(), 3);
            }
            else
            {
                myBuilding.setCurrentUser(userField.getText(), 4);
            }

            // Construct new Frame2, close current Frame1
            JFrame frame = new Frame2(myBuilding);
            frame.setTitle("Enter Request");
            frame.setLocation(350,100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            setVisible(false);
        }
    }

    /**
     Creates the control panel for Frame1
     */
    public void createControlPanel()
    {
        // Line up component panels
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 1));
        controlPanel.add(createTextFields());
        controlPanel.add(createRadioButtons());
        controlPanel.add(createButton());

        // Add panels to Frame1
        add(controlPanel);
    }

    /**
     * Creates the text fields to enter user group name
     * @return JPanel containing text fields
     */
    private JPanel createTextFields()
    {
        userLabel = new JLabel("Enter User Group Name: ");

        final int FIELD_WIDTH = 15;
        userField = new JTextField(FIELD_WIDTH);
        userField.setText("");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(userLabel);
        panel.add(userField);

        return panel;
    }

    /**
     Creates the radio buttons to select the user type
     @return JPanel containing the radio buttons
     */
    public JPanel createRadioButtons()
    {
        clubButton = new JRadioButton("Club");
        guestSpeakerButton = new JRadioButton(" Guest Speaker");
        seminarButton = new JRadioButton("Seminar");
        courseButton = new JRadioButton("Course");
        courseButton.setSelected(true);

        // Add radio buttons to button group
        ButtonGroup group = new ButtonGroup();
        group.add(clubButton);
        group.add(guestSpeakerButton);
        group.add(seminarButton);
        group.add(courseButton);

        JPanel panel = new JPanel();
        panel.add(clubButton);
        panel.add(guestSpeakerButton);
        panel.add(seminarButton);
        panel.add(courseButton);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "User Type"));

        return panel;
    }

    /**
     * Method to create Frame1 window button
     * @return JPanel containing button
     */
    private JPanel createButton()
    {
        button = new JButton("Enter");
        button.addActionListener(listener);

        JPanel panel = new JPanel();
        panel.add(button);

        return panel;
    }
}
