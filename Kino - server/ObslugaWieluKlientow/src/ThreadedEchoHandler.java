import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by miczi on 21.10.16.
 */
public class ThreadedEchoHandler implements Runnable{

    private Socket incoming;
    private FunctionalClass functions;

    public ThreadedEchoHandler(Socket incoming, FunctionalClass functions) {
        this.incoming = incoming;
        this.functions = functions;
    }

    public void run(){
        try{
            try{
                Scanner in = new Scanner(incoming.getInputStream());
                PrintWriter out = new PrintWriter(incoming.getOutputStream(), true);

                out.println( "" );


                boolean done = false;
                while(!done && in.hasNextLine()){
                    System.out.println(in);
                    String line = in.nextLine();
                    String wynik = choseFunction(line, functions);
                    out.println(wynik);

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


    private String choseFunction(String fun, FunctionalClass functions){
        switch(fun){
            case "addDoctor":
                return functions.addDoctor(fun);
            case "removeDoctor":
                return functions.removeDoctor(fun);
            case "checkEmp":
                return functions.checkEmployee(fun);
            case "addFilmToRep":
                return functions.addFilmToRep(fun);
            case "removeFilmFromRep":
                return functions.removeFilmFromRep(fun);
            case "showViewership":
                return functions.showViewership(fun);
            case "makeTimetableOfHall":
                return functions.makeTimetableOfHall(fun);
            case "priceMod":
                return functions.priceMod(fun);
            default:
                return "error in function /choseFunction/ - unknown function";
        }
    }
}
