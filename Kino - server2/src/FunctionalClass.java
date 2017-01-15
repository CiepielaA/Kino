/**
 * Created by miczi on 03.01.2017.
 */
@SuppressWarnings("Duplicates")
public class FunctionalClass {

    private CinemaDatabaseAPI cinemaDatabaseAPI;

    public FunctionalClass() {
        cinemaDatabaseAPI = new CinemaDatabaseAPI();
    }

    public void openDatabaseConnection(){
        cinemaDatabaseAPI.openDatabaseConnection();
    }

    public void closeDatabaseConnection(){
        cinemaDatabaseAPI.closeDatabaseConnection();
    }

    public boolean access = true;

//********************************************************************************************


//********************************************************************************************

    public synchronized String showPrices(){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        String prices = cinemaDatabaseAPI.showPrices();

        access = true;
        notifyAll();
        if(prices.equals("blad"))
            return "blad";
        else return prices;
    }

//********************************************************************************************

    public synchronized String addClient(String command){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        System.out.println(command);
        String[] temp = command.substring(1, command.length()).split(",");
        String firstName = temp[0];
        String lastname = temp[1];
        cinemaDatabaseAPI.addClient(firstName,lastname);


        access = true;
        notifyAll();
        return "Client added";
    }

//********************************************************************************************

    public synchronized String checkEmployee(String command){

        String temp = "";

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        if(command.equals("checkEmp"))
            temp = "Emp checked";
        else temp = "Can't check emp";

        access = true;
        notifyAll();
        return temp;
    }

    public synchronized String addFilmToRep(String command) {
        String temp = "";

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        if(command.equals("addFilmToRep"))
            temp = "Film added";
        else temp = "Can't add film";


        access = true;
        notifyAll();
        return temp;
    }

    public synchronized String removeFilmFromRep(String command) {
        String temp = "";

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        if(command.equals("removeFilmFromRep"))
            temp = "Film removed";
        else temp = "Can't remove film from repertoire";


        access = true;
        notifyAll();
        return temp;
    }

    public synchronized String showViewership(String command) {
        String temp = "";

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        if(command.equals("showViewership"))
            temp = "Viewership showed";
        else temp = "Can't show viewership";


        access = true;
        notifyAll();
        return temp;
    }

    public synchronized String makeTimetableOfHall(String command) {
        String temp = "";

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        if(command.equals("makeTimetableOfHall"))
            temp = "Timetable made";
        else temp = "Can't make timetable";


        access = true;
        notifyAll();
        return temp;
    }

    public synchronized String showReservedPlaces(String arguments){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;


        String[] temp = arguments.substring(1, arguments.length()).split(",");
        String movieTitle = temp[0];
        String date = temp[1];
        String hours = temp[2];
        String result = cinemaDatabaseAPI.showReservedPlaces(movieTitle, date, hours);


        access = true;
        notifyAll();
        return result;

    }

    public synchronized String addPurchase(String arguments) {

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        String[] temp = arguments.substring(1, arguments.length()).split(",");
        for(int i = 0; i < temp.length - 1; i++){
            System.out.println(temp[i]);
        }
        String filmTitle = temp[0];
        String dateFilm = temp[1];
        String hours = temp[2];
        String priceType = temp[3];
        int rowNumber = Integer.parseInt(temp[4]);
        int placeNumber = Integer.parseInt(temp[5]);
        int isPaid = Integer.parseInt(temp[6]);
        //String priceType = "normalny";

        if(temp.length == 9) {
            Integer clientID = Integer.parseInt(temp[8]);
            cinemaDatabaseAPI.addPurchase(filmTitle, dateFilm, hours, rowNumber, placeNumber, isPaid, priceType, clientID);
        }
        else{
            cinemaDatabaseAPI.addPurchase(filmTitle, dateFilm, hours, rowNumber, placeNumber, isPaid, priceType);
        }


        access = true;
        notifyAll();
        return "reserveAdded";
    }


    public synchronized String getFilms() {

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        String result;
        result = cinemaDatabaseAPI.getFilms();


        access = true;
        notifyAll();
        return result;
    }

    public synchronized String getDates(String arguments) {

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        String result = cinemaDatabaseAPI.getDates(arguments);



        access = true;
        notifyAll();
        return result;
    }

    public synchronized String getHours(String arguments) {

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;


        String[] temp = arguments.substring(1, arguments.length()).split(",");
        String title = temp[0];
        String data = temp[1];

        String result = cinemaDatabaseAPI.getHours(title, data);


        access = true;
        notifyAll();
        return result;
    }



    public synchronized String priceMod(String command) {
        String temp = "";

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        if(command.equals("priceMod"))
            temp = "Prices modificated";
        else temp = "Can't modificate prices";


        access = true;
        notifyAll();
        return temp;
    }

}
