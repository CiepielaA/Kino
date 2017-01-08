import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by miczi on 21.10.16.
 */
public class ThreadedEchoHandler implements Runnable{

    private Socket incoming;

    public ThreadedEchoHandler(Socket incoming) {
        this.incoming = incoming;
    }

    public void run(){

        try{
            try{
                Scanner in = new Scanner(incoming.getInputStream());
                PrintWriter out = new PrintWriter(incoming.getOutputStream(), true);

                out.println( "Hello! Enter BYE to exit." );

                boolean done = false;
                while(!done && in.hasNextLine()){
                    System.out.println(in);
                    String line = in.nextLine();
                    out.println("Echo: " + line);

                    if(line.equals("BYE")) done = true;
                }
            }finally {
                incoming.close();
                System.out.println("Zamknieto");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
