import javax.swing.*;

public class ShapeViewer
{
    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setLocation(400,300);
        frame.setSize(300,250);
        frame.setTitle("A Face!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Face face = new Face();
        frame.add(face);
        frame.setVisible(true);
    }
}
