import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SerialDemoWrite
{
    public static void main(String[] args)
    {

        /*
            private String IDNum;
            private String firstName;
            private String lastName;
            private int YOB;
         */

        // Create the Person Object data for the demo

        ArrayList<Product> folks = new ArrayList<>();

        Product sally = new Product("000001", "Pipeweed", "Long Bottom Leaf", 600.0);
        System.out.println(sally);
        folks.add(sally);

        Product fred = new Product("000002", "Lembas", "Elven Wayfare Bread", 200.0);
        System.out.println(fred);
        folks.add(fred);

        Product homer = new Product("000003", "Wine", "Woodland Elf Wine", 400.0);
        System.out.println(homer);
        folks.add(homer);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path path = Paths.get(workingDirectory.getPath() + "\\src\\PersonDat.bin");

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toFile())))
        {
            out.writeObject(folks);
        }
        catch (IOException e)
        {
           e.printStackTrace();
        }


    }
}