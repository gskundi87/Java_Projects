import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class to create JFrame object for Frame2
 * Frame2 is for user request input
 */
public class Frame2 extends JFrame
{
    // Private instance variables
    private BuildingScheduler myBuilding;
    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 450;
    private JComboBox classroomCombo;
    private JComboBox sizeCombo;
    private JComboBox seatingCombo;
    private JComboBox projectorCombo;
    private JComboBox startTimeHoursCombo;
    private JComboBox startTimeMinutesCombo;
    private JComboBox endTimeHoursCombo;
    private JComboBox endTimeMinutesCombo;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private ActionListener listener1;
    private ActionListener listener2;
    private ActionListener listener3;

    /**
     * Constructor
     * @param myBuilding BuildingScheduler object
     */
    public Frame2(BuildingScheduler myBuilding)
    {
        this.myBuilding = myBuilding;
        listener1 = new ChoiceListener1();
        listener2 = new ChoiceListener2();
        listener3 = new ChoiceListener3();
        createControlPanel();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    /**
     * Class for event handling
     * Processes user input into Frame2 and constructs Frame3
     * Exception Handling is implemented to ensure proper user input
     */
    class ChoiceListener1 implements ActionListener
    {
        /**
         * Override method for ActionListener interface
         * @param event ActionEvent object
         */
        public void actionPerformed(ActionEvent event)
        {
            // Exception Handling
            try
            {
                // Get user input information and parse
                String str1 = (String) classroomCombo.getSelectedItem();
                String str2 = (String) sizeCombo.getSelectedItem();
                String str3 = (String) seatingCombo.getSelectedItem();
                String str4 = (String) projectorCombo.getSelectedItem();
                int int1 = (int) startTimeHoursCombo.getSelectedItem();
                String str5 = (String) startTimeMinutesCombo.getSelectedItem();
                int int2 = (int) endTimeHoursCombo.getSelectedItem();
                String str6 = (String) endTimeMinutesCombo.getSelectedItem();

                // Validate user input
                if (str1.compareTo("ANY") != 0 && (str2.compareTo("ANY") != 0 || str3.compareTo("ANY") != 0) ||
                        str4.compareTo("ANY") != 0)
                {
                    throw new Exception();
                }

                // Validate user input
                if (int1 * 100 + Integer.parseInt(str5) >= int2 * 100 + Integer.parseInt(str6) ||
                        (int2 == 22 && Integer.parseInt(str6) == 30))
                {
                    throw new RuntimeException();
                }

                // Process validated user input
                myBuilding.setCurrentRequest(str1, Classroom.Size.valueOf(str2), Classroom.Seating.valueOf(str3),
                        Classroom.Projector.valueOf(str4), myBuilding.getCurrentUser(), int1,
                        Integer.parseInt(str5), int2, Integer.parseInt(str6), myBuilding.getCurrentType());

                myBuilding.sendCurrentRequest();

                // Construct new Frame3, close current Frame2
                JFrame frame = new Frame3(myBuilding);
                frame.setTitle("Choose Reservation Available");
                frame.setLocation(350, 100);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                setVisible(false);
            }
            catch(RuntimeException Exception)
            {
                JOptionPane.showMessageDialog(new JFrame(), "Incorrect Time Selection");

            }
            catch (Exception exception)
            {
                JOptionPane.showMessageDialog(new JFrame(), "Cannot Select Classroom And Options");
            }
        }
    }

    /**
     * Class for event handling
     * Empty for now
     */
    class ChoiceListener2 implements ActionListener
    {
        /**
         * Override method for ActionListener interface
         * @param event ActionEvent object
         */
        public void actionPerformed(ActionEvent event)
        {

        }
    }

    /**
     * Class for event handling
     * Exits program
     */
    class ChoiceListener3 implements ActionListener
    {
        /**
         * Override method for ActionListener interface
         * @param event ActionEvent object
         */
        public void actionPerformed(ActionEvent event)
        {
            System.exit(0);
        }
    }

    /**
     Creates the control panel for Frame2
     */
    public void createControlPanel()
    {
        // Line up component panels
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 3));
        controlPanel.add(createComboBox1());
        controlPanel.add(createComboBox2());
        controlPanel.add(createComboBox3());
        controlPanel.add(createComboBox4());
        controlPanel.add(createComboBox5());
        controlPanel.add(createComboBox6());
        controlPanel.add(createButton1());
        controlPanel.add(createButton2());
        controlPanel.add(createButton3());

        // Add panels to Frame1
        add(controlPanel);
    }

    /**
     Creates the combo box for classroom selection
     @return JPanel containing the combo box
     */
    public JPanel createComboBox1()
    {
        classroomCombo = new JComboBox();
        classroomCombo.addItem("ANY");

        for (int i = 0; i < myBuilding.getRoomsSize(); i++)
        {
            classroomCombo.addItem(myBuilding.getRoom(i).getName());
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(new Label("Choose Classroom"));
        panel.add(classroomCombo);

        return panel;
    }

    /**
     Creates the combo box for sizing selection
     @return JPanel containing the combo box
     */
    public JPanel createComboBox2()
    {
        sizeCombo = new JComboBox();
        sizeCombo.addItem("ANY");
        sizeCombo.addItem("SMALL");
        sizeCombo.addItem("MEDIUM");
        sizeCombo.addItem("LARGE");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(new Label("Choose Size"));
        panel.add(sizeCombo);

        return panel;
    }

    /**
     Creates the combo box for seating selection
     @return JPanel containing the combo box
     */
    public JPanel createComboBox3()
    {
        seatingCombo = new JComboBox();
        seatingCombo.addItem("ANY");
        seatingCombo.addItem("COMFORT");
        seatingCombo.addItem("NON_COMFORT");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(new Label("Choose Seating"));
        panel.add(seatingCombo);

        return panel;
    }

    /**
     Creates the combo box for projector selection
     @return JPanel containing the combo box
     */
    public JPanel createComboBox4()
    {
        projectorCombo = new JComboBox();
        projectorCombo.addItem("ANY");
        projectorCombo.addItem("NO_PROJECTOR");
        projectorCombo.addItem("PROJECTOR");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(new Label("Choose Projector"));
        panel.add(projectorCombo);

        return panel;
    }

    /**
     Creates the combo boxes for start time selection
     @return JPanel containing the combo boxes
     */
    public JPanel createComboBox5()
    {
        startTimeHoursCombo = new JComboBox();
        startTimeMinutesCombo = new JComboBox();

        for (int i = 5; i < 22; i++)
        {
            startTimeHoursCombo.addItem(i);
        }

        startTimeMinutesCombo.addItem("00");
        startTimeMinutesCombo.addItem("30");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.add(new Label("Choose Start Time"));
        panel.add(new Label());
        panel.add(startTimeHoursCombo);
        panel.add(startTimeMinutesCombo);

        return panel;
    }

    /**
     Creates the combo boxes for end time selection
     @return JPanel containing the combo boxes
     */
    public JPanel createComboBox6()
    {
        endTimeHoursCombo = new JComboBox();
        endTimeMinutesCombo = new JComboBox();

        for (int i = 5; i < 23; i++)
        {
            endTimeHoursCombo.addItem(i);
        }

        endTimeMinutesCombo.addItem("00");
        endTimeMinutesCombo.addItem("30");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.add(new Label("Choose End Time"));
        panel.add(new Label());
        panel.add(endTimeHoursCombo);
        panel.add(endTimeMinutesCombo);

        return panel;
    }

    /**
     * Method to create Frame2 window button1
     * @return JPanel containing button1
     */
    private JPanel createButton1()
    {
        button1 = new JButton("Enter Request");
        button1.addActionListener(listener1);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        panel.add(new Label());
        panel.add(button1);
        panel.add(new Label());

        return panel;
    }

    /**
     * Method to create Frame2 window button2
     * @return JPanel containing button2
     */
    private JPanel createButton2()
    {
        button2 = new JButton("Full Schedule");
        button2.addActionListener(listener2);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        panel.add(new Label());
        panel.add(button2);
        panel.add(new Label());

        return panel;
    }

    /**
     * Method to create Frame2 window button3
     * @return JPanel containing button3
     */
    private JPanel createButton3()
    {
        button3 = new JButton("Exit");
        button3.addActionListener(listener3);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        panel.add(new Label());
        panel.add(button3);
        panel.add(new Label());

        return panel;
    }
}
