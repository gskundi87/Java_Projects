import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFrame;

public class Tester
{
    static void readClassrooms(BuildingScheduler myBuilding, Scanner in)
    {
        while (in.hasNextLine())
        {
            myBuilding.addClassroom(in.next(), in.next(), in.next(), in.next());
        }
    }

    static void readRequests(BuildingScheduler myBuilding, Scanner in) throws FileNotFoundException
    {
        PrintWriter outFile;
        String str1;

        while (in.hasNextLine())
        {
            in.useDelimiter(";");
            str1 = in.next();
            in.useDelimiter("\\p{javaWhitespace}+");
            in.next();
            myBuilding.setCurrentUser(str1, in.nextInt());

            myBuilding.setCurrentRequest(in.next(), Classroom.Size.valueOf(in.next()), Classroom.Seating.valueOf(in.next()),
                    Classroom.Projector.valueOf(in.next()), myBuilding.getCurrentUser(), in.nextInt(), in.nextInt(),
                    in.nextInt(), in.nextInt(), myBuilding.getCurrentType());

            myBuilding.sendCurrentRequest();
            outFile = new PrintWriter("User_Request_Confirm.txt");
            myBuilding.sendConfirmation(in.nextInt(), outFile);
            outFile.close();

            if (in.hasNextLine())
            {
                in.skip("\\r");
                in.skip("\\n");
            }
            myBuilding.clear();
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        BuildingScheduler myBuilding = new BuildingScheduler();
        File inFile = new File("Rooms.txt");
        Scanner in = new Scanner(inFile);

        readClassrooms(myBuilding, in);

        in.close();

        inFile = new File("User_Request_Confirm.txt");
        in = new Scanner(inFile);

        readRequests(myBuilding, in);

        in.close();

        JFrame frame = new Frame1(myBuilding);
        frame.setTitle("Enter User Group");
        frame.setLocation(350,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
