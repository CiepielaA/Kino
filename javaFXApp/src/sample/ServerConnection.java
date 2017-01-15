package sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by miczi on 02.01.17.
 */
public class ServerConnection implements Runnable{


    @Override
    public void run() {
        try {
            workWithServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void workWithServer() throws IOException
    {

        try (Socket s = new Socket("127.0.0.1", 8188))
        {
            InputStream inStream = s.getInputStream();
            OutputStream outputStream = s.getOutputStream();
            Scanner in = new Scanner(inStream);
            PrintWriter out  = new PrintWriter(outputStream, true);

//            Scanner terminal = new Scanner(System.in);

            boolean flag = false;
            String temp = "";

            while (!flag && in.hasNextLine())
            {
//                System.out.println(Main.bridge);
                String line = in.nextLine();
                Main.solution = line;
//                System.out.println(line);

                while (true) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!Main.bridge.equals("")) {
                        //temp = terminal.next();
                        temp = Main.bridge;
//                        System.out.println(temp);

                        out.println(temp);
                        Main.bridge = "";
                        break;
                    }
                }

                if(temp.trim().equals("BYE")){
                    flag = true;
                }
//                System.out.println("dupa");
            }
        }
    }
}
