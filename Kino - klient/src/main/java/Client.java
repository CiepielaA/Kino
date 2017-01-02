import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by SG0226566 on 2016-11-16.
 */
public class Client
{
    public static void main(String[] args) throws IOException
    {
        try (Socket s = new Socket("192.168.0.53", 8188))
        {
            InputStream inStream = s.getInputStream();
            OutputStream outputStream = s.getOutputStream();
            Scanner in = new Scanner(inStream);
            PrintWriter  out  = new PrintWriter(outputStream, true);

            Scanner terminal = new Scanner(System.in);

            boolean flag = false;
            String temp = "";

            while (!flag && in.hasNextLine())
            {
                String line = in.nextLine();
                System.out.println(line);

                if(terminal.hasNextLine()){
                    temp = terminal.next();
                    System.out.println(temp);
                    out.println(temp);

                }
                if(temp.trim().equals("BYE")){
                    flag = true;
                }
            }


        }
    }
}
