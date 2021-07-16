import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Test
{
    public static void main(String args[])
    {
        String river = "Mississippi";
        river = river.replace("i","x");
        System.out.println(river);

        Rectangle rect = new Rectangle(0,0,50,50);
        System.out.println(rect);

        frameViewer();
    }

    public static void frameViewer()
    {
        JFrame frame = new JFrame();
        frame.setLocation(500,250);
        frame.setSize(300,400);
        frame.setTitle("Empty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RectangleComponent component = new RectangleComponent();
        frame.add(component);
        frame.setVisible(true);
    }
}
