import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by miczi on 21.10.16.
 */
public class ThreadedEchoServer {

    public void connect(){

        try{
            int i = 1;
            ServerSocket s = new ServerSocket(8188);
            FunctionalClass functionalClass = new FunctionalClass();

            while(true){
                Socket incoming = s.accept();
//                System.out.println("name: " + incoming.getInetAddress());
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(incoming, functionalClass);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
