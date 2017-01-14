package sample;


public class Main{


    public static String bridge = "";
    public static String solution = "";

    public static void main(String[] args) {

        GUI gui = new GUI();
        ServerConnection serverConnection = new ServerConnection();

        Thread guiThread = new Thread(gui);
        Thread serverConnThread = new Thread(serverConnection);


        guiThread.start();
        serverConnThread.start();
    }
}
