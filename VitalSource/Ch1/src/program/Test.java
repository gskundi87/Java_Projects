package program;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
  
public class Test
{
   public static void main(String[] args) throws Exception
   {
      URL imageLocation = new URL("https://horstmann.com/java4everyone/duke.gif");
      BufferedImage c = ImageIO.read(imageLocation);
      ImageIcon image = new ImageIcon(c);
      JOptionPane.showMessageDialog(null, "Hello", "Title", JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));
   }
}
