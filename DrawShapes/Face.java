import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;

public class Face extends JComponent
{
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        Ellipse2D.Double ellipse = new Ellipse2D.Double(25,50,80,80);
        g2.draw(ellipse);

        g2.setColor(Color.BLUE);
        ;
        Rectangle rect1 = new Rectangle(45,75,5,5);
        g2.fill(rect1);
        g2.draw(rect1);
        rect1.translate(35,0);
        g2.fill(rect1);
        g2.draw(rect1);

    }
}
