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

    public synchronized String showRepertoireInHall(String arguments){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        String[] temp = arguments.substring(1, arguments.length()).split(",");
        String date = temp[0];
        String numOfHall = temp[1];


        String result = cinemaDatabaseAPI.showRepertoireInHall(date, numOfHall);

        access = true;
        notifyAll();
        if(result.equals("blad")) return "Error";
        else return result;
    }


    public synchronized String removeFilmFromRepertoire(String arguments){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        String temp = arguments.substring(1, arguments.length());
        String title = temp;


        String result = cinemaDatabaseAPI.removeFilmFromRepertoire(title);

        access = true;
        notifyAll();
        if(result.equals("Movie deleted")) return "Movie deleted";
        else return "Movie didn't deleted";
    }



    public synchronized String addMovie(String arguments){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        String[] temp = arguments.substring(1, arguments.length()).split(",");
        String title = temp[0];
        int duration = Integer.parseInt(temp[1]);
        int yearOfProduction = Integer.parseInt(temp[2]);
        String director = temp[3];
        String typeOfmovie = temp[4];
        int ageLimit = Integer.parseInt(temp[5]);
        int isPlay = Integer.parseInt(temp[6]);

        cinemaDatabaseAPI.addMovie(title, duration, yearOfProduction, director, typeOfmovie, ageLimit, isPlay );

        access = true;
        notifyAll();
        return "Movie added";
    }

//********************************************************************************************

    public synchronized String updatePriceCost(String arguments){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        String []temp = arguments.substring(1, arguments.length()).split(",");
        String type = temp[0];
        int newCost = Integer.parseInt(temp[1]);

        cinemaDatabaseAPI.updatePriceCost(type, newCost);

        access = true;
        notifyAll();

        return "Price updated";


    }

//********************************************************************************************

    public synchronized String showTypesOfPrice(){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        String result = cinemaDatabaseAPI.showTypesOfPrice();

        access = true;
        notifyAll();
        return result;
    }

//********************************************************************************************

    public synchronized String viewership(String arguments){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        String temp = arguments.substring(1, arguments.length());
        String audience = temp;

        String result = cinemaDatabaseAPI.viewership(audience);

        access = true;
        notifyAll();
        if(result.equals("")){
            return "blad";
        }else{
            return result;
        }

    }

 //********************************************************************************************

    public synchronized String addSeance(String arguments){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        System.out.println(arguments);
        String[] temp = arguments.substring(1, arguments.length()).split(",");
        String title = temp[0];
        int hallId = Integer.parseInt(temp[1]);
        String dataSeance = temp[2];
        String hours = temp[3];

        cinemaDatabaseAPI.addSeance(title, hallId, dataSeance, hours);

        access = true;
        notifyAll();
        return "Seance added";
    }


 //********************************************************************************************

    public synchronized String removeEmployee(String argments){

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        argments = argments.substring(1, argments.length());
        String[] temp = argments.split(",");

        String firstName = temp[0];
        String lastName = temp[1];
        String position = temp[2];

        cinemaDatabaseAPI.removeEmployee(firstName, lastName, position);


        access = true;
        notifyAll();
        return "Employee removed!";

    }


 //********************************************************************************************

    public synchronized String addEmployee(String arguments){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        System.out.println(arguments);
        String[] temp = arguments.substring(1, arguments.length()).split(",");
        long pesel = Long.parseLong(temp[0]);
        String branchName = temp[1];
        String firstname = temp[2];
        String secondName = temp[3];
        String position = temp[4];
        String email = temp[5];
        String address = temp[6];
        int salary = Integer.parseInt(temp[7]);
        String start_date = temp[8];
        cinemaDatabaseAPI.addEmployee(pesel, branchName,firstname, secondName, position, email, address, salary, start_date);


        access = true;
        notifyAll();
        return "Employee added";
    }


//********************************************************************************************
    public synchronized String deletePurchase(String purId){

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        String result;
        purId = purId.substring(1, purId.length());
        if(cinemaDatabaseAPI.checkISPAID(purId).equals("0")){
           result = "Reservation cancelled";
           result = "Reservation cancelled";
           cinemaDatabaseAPI.removePurchase(purId);
        } else {
            result = "Purchase is paid. Cancel is not available";
        }

        access = true;
        notifyAll();
        return result;
    }


//********************************************************************************************
    public synchronized  String showRepertoire(String date){
        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        date = date.substring(1, date.length());
        String result = cinemaDatabaseAPI.showRepertoire(date);

        access = true;
        notifyAll();
        return result;
    }

//********************************************************************************************

    public synchronized String deleteTicket(String argments){

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        String ticketId = "";
        argments = argments.substring(1, argments.length());
        String[] temp = argments.split(";");

        ticketId = temp[0];
//        String argumentsForAddPurchase = temp[1];


        String ticketIdTemp = ticketId;
        String purchaseId = cinemaDatabaseAPI.takePurchaseIdFromTicketId(ticketIdTemp);
        //usun ticket
        cinemaDatabaseAPI.removeTicket(ticketIdTemp);
        //usun purchase
        cinemaDatabaseAPI.removePurchase(purchaseId);
//
//
//        access = true;
//        notifyAll();
//        addPurchase(argumentsForAddPurchase);
//        access = false;


        String ticketIdentifier = "blad";
        ticketIdentifier = cinemaDatabaseAPI.showTicketNumber();

        access = true;
        notifyAll();
        return ticketIdentifier;

    }

//********************************************************************************************

    public synchronized String ticketFromRes(String arg){


        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        arg = arg.substring(1, arg.length());

        String purchaseId = arg;

        cinemaDatabaseAPI.addTicket(purchaseId);
        String ticketId = "blad";
        ticketId = cinemaDatabaseAPI.showTicketNumber();

        access = true;
        notifyAll();
        return ticketId;
    }

//********************************************************************************************

    public  String showPurchaseID(){


        String purchId = cinemaDatabaseAPI.showPurchaseID();

        if(purchId.equals("blad"))
            return "blad";
        else return purchId;
    }

//********************************************************************************************

    public synchronized String showPrices(){

        String prices = cinemaDatabaseAPI.showPrices();

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

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;

        System.out.println(command);

        String lastname = command.substring(1, command.length());
        String result = cinemaDatabaseAPI.checkEmployee(lastname);

        access = true;
        notifyAll();
        return result;
    }

//********************************************************************************************

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
        int isSell = Integer.parseInt(temp[8]);

        String purchaseId = "";
        String ticketId ="blad";

        if(temp.length == 10) {
            Integer clientID = Integer.parseInt(temp[7]);
            cinemaDatabaseAPI.addPurchase(filmTitle, dateFilm, hours, rowNumber, placeNumber, isPaid, priceType, clientID);
            purchaseId = showPurchaseID();
            if(isSell == 1){
                access = true;
                notifyAll();
               cinemaDatabaseAPI.addTicket(purchaseId);
               ticketId = cinemaDatabaseAPI.showTicketNumber();
            }
        }
        else{
            cinemaDatabaseAPI.addPurchase(filmTitle, dateFilm, hours, rowNumber, placeNumber, isPaid, priceType);
            purchaseId = showPurchaseID();
            if(isSell == 1){
                access = true;
                notifyAll();
                cinemaDatabaseAPI.addTicket(purchaseId);
                ticketId = cinemaDatabaseAPI.showTicketNumber();
            }
        }

        access = true;
        notifyAll();

        if(isSell == 1) {
            return ticketId;
        }
        else {
            return purchaseId;
        }
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
