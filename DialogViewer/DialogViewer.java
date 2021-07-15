import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DialogViewer
{
//    public static void main(String[] args)
//    {
//        String name = JOptionPane.showInputDialog("What is your name?");
//        System.out.print("Hello, ");
//        System.out.print(name);
//        System.out.print("!");
//    }
    public static void main(String[] args) throws Exception
    {
        URL imageLocation = new URL("https://horstmann.com/java4everyone/duke.gif");
        JOptionPane.showMessageDialog(null, "Hello", "Title",
                JOptionPane.PLAIN_MESSAGE, new ImageIcon("duke.gif"));
        JOptionPane.showMessageDialog(null, "Hello", "Title",
                JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));
    }
}
