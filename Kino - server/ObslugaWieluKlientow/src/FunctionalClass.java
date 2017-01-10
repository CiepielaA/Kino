/**
 * Created by miczi on 03.01.2017.
 */
public class FunctionalClass {

    public boolean access = true;

    public synchronized String addDoctor(String command){
        String temp = "";

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        if(command.equals("addDoctor"))
            temp = "doctor added";
        else temp = "doctor doesn't added";

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        access = true;
        notifyAll();
        return temp;
    }

    public synchronized String removeDoctor(String command){

        String temp = "";

        while(!access)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        access = false;
        if(command.equals("removeDoctor"))
            temp = "doctor removed";
        else temp = "doctor doesn't removed";

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        access = true;
        notifyAll();
        return temp;
    }

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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        access = true;
        notifyAll();
        return temp;
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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        access = true;
        notifyAll();
        return temp;
    }

}
