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
        functions.openDatabaseConnection();
        try{
            try{
                Scanner in = new Scanner(incoming.getInputStream());
                PrintWriter out = new PrintWriter(incoming.getOutputStream(), true);

                out.println( "" );


                boolean done = false;
                while(!done && in.hasNextLine()){
                    System.out.println(in);
                    String line = in.nextLine();

                    String[] temp = line.split(",");
                    String funName = temp[0];
                    String funArguments = "";
                    for(int i = 1; i < temp.length; i++){

                        funArguments += "," + temp[i];
                    }

                    String wynik = choseFunction(funName, funArguments, functions);
                    out.println(wynik);

                    if(line.equals("BYE")) done = true;
                }
            }finally {
                incoming.close();
                System.out.println("Zamknieto");
                functions.closeDatabaseConnection();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private String choseFunction(String funName, String funArguments, FunctionalClass functions){
        switch(funName){
            case "showRepertoireInHall":
                return functions.showRepertoireInHall(funArguments);
            case "removeFilmFromRepertoire":
                return functions.removeFilmFromRepertoire(funArguments);
            case "addMovie":
                return functions.addMovie(funArguments);
            case "updatePriceCost":
                return functions.updatePriceCost(funArguments);
            case "showTypesOfPrice":
                return functions.showTypesOfPrice();
            case "viewership":
                return functions.viewership(funArguments);
            case "addSeance":
                return functions.addSeance(funArguments);
            case "checkEmployee":
                return functions.checkEmployee(funArguments);
            case "removeEmployee":
                return functions.removeEmployee(funArguments);
            case "addEmployee":
                return functions.addEmployee(funArguments);
            case "deletePurchase":
                return functions.deletePurchase(funArguments);
            case "showRepertoire":
                return functions.showRepertoire(funArguments);
            case "deleteTicket":
                return functions.deleteTicket(funArguments);
            case "ticketFromRes":
                return functions.ticketFromRes(funArguments);
            case "showPurchaseID":
                return functions.showPurchaseID();  // show puchase_id last reservation
            case "showPrices":  //all prices
                return functions.showPrices();
            case "addClient":
                return functions.addClient(funArguments);
            case "showReservedPlaces":
                return functions.showReservedPlaces(funArguments);
            case "addPurchase":
                return functions.addPurchase(funArguments);
            case "getFilms":
                return functions.getFilms();
            case "getDates":
                return functions.getDates(funArguments);
            case "getHours":
                return functions.getHours(funArguments);
            case "checkEmp":
                return functions.checkEmployee(funArguments);
            case "addFilmToRep":
                return functions.addFilmToRep(funArguments);
            case "removeFilmFromRep":
                return functions.removeFilmFromRep(funArguments);
            case "showViewership":
                return functions.showViewership(funArguments);
            case "makeTimetableOfHall":
                return functions.makeTimetableOfHall(funArguments);
            case "priceMod":
                return functions.priceMod(funArguments);
            default:
                return "error in case ThreadedEchoHandler, get: " + funName;
        }
    }
}
