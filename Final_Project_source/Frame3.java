import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class to create JFrame object for Frame3
 * Frame3 is for user confirmation input
 */
public class Frame3 extends JFrame
{
    //Private instance variables
    BuildingScheduler myBuilding;
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 450;
    private ArrayList<JLabel> labels;
    private JTextField userField;
    private JLabel userLabel;
    private JButton button;
    private ActionListener listener;

    /**
     * Constructor
     * @param myBuilding BuildingScheduler object
     */
    public Frame3(BuildingScheduler myBuilding)
    {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.myBuilding = myBuilding;
        labels = new ArrayList<JLabel>();
        listener = new ChoiceListener();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(myBuilding.getHitsSize(), 1));
        JLabel label;

        // Display all Acknowledgments from previous requests
        for (int i = 0; i < myBuilding.getHitsSize(); i++)
        {
            label = new JLabel(String.format("[%d] %s", i + 1, myBuilding.getHit(i).print()), SwingConstants.CENTER);
            topPanel.add(label);
            labels.add(label);
        }

        add(topPanel, BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);
    }

    /**
     * Class for event handling
     * Processes user input into Frame3 and constructs Frame1
     * Exception Handling is implemented to ensure proper user input
     */
    class ChoiceListener implements ActionListener
    {
        /**
         * Override method for ActionListener interface
         * @param event ActionEvent object
         */
        public void actionPerformed(ActionEvent event)
        {
            if (myBuilding.getHitsSize() == 0)
            {
                // Construct new Frame1, close current Frame3
                JFrame frame = new Frame1(myBuilding);
                frame.setTitle("Enter User Group");
                frame.setLocation(350,100);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                setVisible(false);
                return;
            }

            int int1 = 0;

            // Exception Handling
            try
            {
                // Get user input and parse
                int1 = Integer.parseInt(userField.getText()) - 1;

                // Validate user input
                if (int1 < 0 || int1 > myBuilding.getHitsSize() - 1)
                {
                    throw new Exception();
                }

                // Write out data
                PrintWriter outFile = new PrintWriter("User_Request_Confirm.txt");
                myBuilding.sendConfirmation(int1, outFile);
                outFile.close();

                // Construct new Frame1, close current Frame3
                JFrame frame = new Frame1(myBuilding);
                frame.setTitle("Enter User Group");
                frame.setLocation(350,100);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                setVisible(false);
            }
            catch (FileNotFoundException exception)
            {

            }
            catch (Exception exception)
            {
                JOptionPane.showMessageDialog(new JFrame(), "Incorrect Index Selection");
            }
        }
    }

    /**
     * Creates the control panel for Frame3
     * @return JPanel containing control panel
     */
    public JPanel createControlPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        userLabel = new JLabel("Enter Index To Confirm");

        final int FIELD_WIDTH = 15;
        userField = new JTextField(FIELD_WIDTH);
        userField.setText("");

        button = new JButton("Enter Confirmation");
        button.addActionListener(listener);

        panel.add(userLabel);
        panel.add(userField);
        panel.add(button);

        return panel;
    }
}
